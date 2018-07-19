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

import project.com.nybestsellerbooksapp.BSFavoritesDBHelper;
import project.com.nybestsellerbooksapp.R;
import project.com.nybestsellerbooksapp.model.BSFavoriteBookItem;
import project.com.nybestsellerbooksapp.model.Item;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class BSFavoriteItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private BSFavoritesDBHelper favoritesDBHelper;
    private List<BSFavoriteBookItem> mFavItems;
    private RecyclerView recyclerView;
    private MyBSFavoriteItemRecyclerViewAdapter mAdapter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BSFavoriteItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static BSFavoriteItemFragment newInstance(int columnCount) {
        BSFavoriteItemFragment fragment = new BSFavoriteItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bsfavoriteitem_list, container, false);

        favoritesDBHelper = new BSFavoritesDBHelper(getContext());

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
        return view;
    }


    @Override
    public void onResume(){
        super.onResume();
        fetchData();

    }

    private void fetchData(){
        mFavItems = favoritesDBHelper.allFavoriteBooks();
        recyclerView.setAdapter(new MyBSFavoriteItemRecyclerViewAdapter(mFavItems, mListener));

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
        void onFavoriteListFragmentInteraction( List<Item> bookDetailItemInfo, String bookIdentifier);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFavItems = null;
        recyclerView.setAdapter(null);
    }
}
