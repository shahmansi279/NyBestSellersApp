package project.com.nybestsellerbooksapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import project.com.nybestsellerbooksapp.NyBsClient;
import project.com.nybestsellerbooksapp.PaginationScrollListener;
import project.com.nybestsellerbooksapp.R;
import project.com.nybestsellerbooksapp.model.BSHistoryList;
import project.com.nybestsellerbooksapp.model.Item;
import project.com.nybestsellerbooksapp.rest.BSApiClient;
import project.com.nybestsellerbooksapp.rest.BSInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnHistoryListFragmentInteractionListener}
 * interface.
 */
public class BSHistoryItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnHistoryListFragmentInteractionListener mListener;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private BSInterface bsClient;
    private MyBSHistoryItemRecyclerViewAdapter historyAdapter;
    private int offSetCount;
    private List<BSHistoryList.BSHistoryBookItem> bsHistoryListItems;
    private boolean isLoading = false;
    private int offset = 20;
    private int totalPages = 0;
    private int currentPage = 0;
    private boolean isLastPage = false;
    private SearchView searchView;
    private int searchCount = 0;
    private boolean isSearch = false;
    private String searchQuery = null;
    private TextView noDataView;
    private List<BSHistoryList.BSHistoryBookItem> books;
    private static final int HTTP_OK = 200;

    //private int
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BSHistoryItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static BSHistoryItemFragment newInstance(int columnCount) {
        BSHistoryItemFragment fragment = new BSHistoryItemFragment();
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
        View view = inflater.inflate(R.layout.fragment_bshistoryitem_list, container, false);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);


        recyclerView =(RecyclerView) view.findViewById(R.id.list);
        searchView = (SearchView)view.findViewById(R.id.search);

        noDataView = (TextView)view.findViewById(R.id.no_search_results);

        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecor.setDrawable(getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(itemDecor);



        bsClient =  BSApiClient.getClient().create(BSInterface.class);
        historyAdapter = new MyBSHistoryItemRecyclerViewAdapter(mListener);
        recyclerView.setLayoutManager(linearLayoutManager);


        bsHistoryListItems = new LinkedList<BSHistoryList.BSHistoryBookItem>();

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {

            @Override
            protected void loadMoreItems() {

                isLoading = true;

                if(!isSearch){

                    currentPage += 1;
                    if(currentPage == totalPages - 1)
                        isLastPage = true;

                    fetchResults(currentPage);
                }

                isLoading = false;
            }

            @Override
            public int getTotalPageCount() {
                return 0;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });



        // Add Search View to the fragment
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // do your search
                currentPage = 0;
                searchQuery = s;
                searchView.setIconified(true);
                performSearch(searchQuery);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String s){
                return false;
            }
        });


        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                recyclerView.setAdapter(null);
                historyAdapter.clear();
                noDataView.setVisibility(View.GONE);
                currentPage = 0;
                fetchResults(currentPage);
                isSearch = false;
                return false;
            }
        });


        //Fetch first 20 items.
        fetchResults(currentPage);
        return view;
    }
    // This method perfroms the search based on Query Text submitted

    private void performSearch(String query){

        searchCount = 0;
        isSearch = true;
        recyclerView.setAdapter(null);
        historyAdapter.clear();
        currentPage = 0;
        fetchSearchResults();

    }


    private void fetchSearchResults(){

        Observable.just(bsClient).subscribeOn(Schedulers.computation())
                .flatMap(s -> {
                    Observable<BSHistoryList> authorObservable
                            = ( Observable<BSHistoryList>)s.getBSHistoryByAuthor(NyBsClient.getBSIdentifier(), searchQuery, 0).subscribeOn(Schedulers.io());

                    Observable<BSHistoryList> titleObservable
                            = s.getBSHistoryByTitle(NyBsClient.getBSIdentifier(), searchQuery,0).subscribeOn(Schedulers.io());


                    return authorObservable.mergeWith(titleObservable);


                }).observeOn(AndroidSchedulers.mainThread()).subscribe(this::loadSearchResults,this::handleError);

    }

    private void loadSearchResults(BSHistoryList list){

        if(list != null)
        {
            bsHistoryListItems.addAll(list.getResults());
            searchCount ++;
        }

        //second time = title observable results are returned
        if(searchCount == 2)
        {
            if(bsHistoryListItems.size() > 0)
            {
                historyAdapter.addAll(bsHistoryListItems);
                recyclerView.setAdapter(historyAdapter);
                noDataView.setVisibility(View.GONE);
            }else
            {
                noDataView.setVisibility(View.VISIBLE);
            }

            searchCount = 0;
        }

    }

    private void handleError(Throwable t){

        t.printStackTrace();

    }
    private void fetchResults(int currentPage){

        Call<BSHistoryList> call = bsClient.getBSHistory(NyBsClient.getBSIdentifier(),currentPage * offset);

        call.enqueue(new Callback<BSHistoryList>() {
            @Override
            public void onResponse(Call<BSHistoryList> call, Response<BSHistoryList> response) {

                if(response.code() == HTTP_OK){

                    if(response.body().getResults().size() > 0){

                        books = new ArrayList<>();
                        books.addAll(response.body().getResults());

                        if(currentPage == 0)
                            totalPages = response.body().getNumResults()/ offset + 1;

                        historyAdapter.addAll(books);
                        recyclerView.setAdapter(historyAdapter);
                        books = null;
                    }

                }

            }

            @Override
            public void onFailure(Call<BSHistoryList> call, Throwable t) {
                call.cancel();
            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHistoryListFragmentInteractionListener) {
            mListener = (OnHistoryListFragmentInteractionListener) context;
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
    public interface OnHistoryListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onHistoryListFragmentInteraction(List<Item> bookDetailItemInfo, String bookIdentifier);
    }
}
