package project.com.nybestsellerbooksapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.com.nybestsellerbooksapp.NyBsClient;
import project.com.nybestsellerbooksapp.R;
import project.com.nybestsellerbooksapp.model.BSBookList;
import project.com.nybestsellerbooksapp.model.Item;
import project.com.nybestsellerbooksapp.rest.BSApiClient;
import project.com.nybestsellerbooksapp.rest.BSInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class BSBookItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String BOOK_LIST_NAME="list-name";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private String mBookListName = null;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView = null;
    private MyBSBookItemRecyclerViewAdapter bookItemAdapter = null;
    private List<BSBookList.BSBookItem> bsBookItems = null;
    private static final int HTTP_OK = 200;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BSBookItemFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static BSBookItemFragment newInstance(int columnCount, String listName) {
        BSBookItemFragment fragment = new BSBookItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putString(BOOK_LIST_NAME,listName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mBookListName = getArguments().getString(BOOK_LIST_NAME);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bsbookitem_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
            itemDecor.setDrawable(getResources().getDrawable(R.drawable.divider));
            recyclerView.addItemDecoration(itemDecor);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    }

        fetchResults();

        return view;
    }


    private void fetchResults()
    {
        BSInterface nyClient =  BSApiClient.getClient().create(BSInterface.class);

        Call<BSBookList> call = nyClient.getBSBookListItems("json", NyBsClient.getBSIdentifier(),mBookListName);

        call.enqueue(new Callback<BSBookList>() {
            @Override
            public void onResponse(Call<BSBookList> call, Response<BSBookList> response) {

                if(response.code() == HTTP_OK) {
                    if (response.body().getResults().size() > 0) {
                        bsBookItems = response.body().getResults();
                        bookItemAdapter = new MyBSBookItemRecyclerViewAdapter(mListener);
                        bookItemAdapter.addAll(bsBookItems);
                        recyclerView.setAdapter(bookItemAdapter);
                    }
                }
            }


            @Override
            public void onFailure(Call<BSBookList> call, Throwable t) {
                t.printStackTrace();
                call.cancel();
            }

        });
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        if(bookItemAdapter != null){

            bookItemAdapter.clear();
            recyclerView.setAdapter(null);
        }


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(List<Item> item, String bookIdentifier);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class BSBookDetailFragment extends Fragment {

        public BSBookDetailFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_bsbook_detail, container, false);
        }
    }
}
