package project.com.nybestsellerbooksapp.svc.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import project.com.nybestsellerbooksapp.R;
import project.com.nybestsellerbooksapp.svc.PaginationScrollListener;
import project.com.nybestsellerbooksapp.svc.model.BSHistoryList;
import project.com.nybestsellerbooksapp.svc.model.Item;
import project.com.nybestsellerbooksapp.svc.rest.BSApiClient;
import project.com.nybestsellerbooksapp.svc.rest.BSInterface;
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
    private int TOTAL_PAGES = 0;
    private int currentPage = 0;
    private boolean isLastPage = false;
    private SearchView searchView;
    private int searchCount = 0;
    private boolean isSearch = false;
    private String searchQuery = null;
    private int numResults = 0;


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

        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecor.setDrawable(getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(itemDecor);


        //Fetch first 20 items.
        bsClient =  BSApiClient.getClient().create(BSInterface.class);
        historyAdapter = new MyBSHistoryItemRecyclerViewAdapter(mListener);

        loadFirstPage();

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {

            @Override
            protected void loadMoreItems() {

                isLoading = true;
                currentPage += 1;

                if(currentPage == TOTAL_PAGES)
                    isLastPage = true;

                if(!isSearch)
                    fetchMoreItems();
                else
                   fetchMoreSearchItems();
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

        recyclerView.clearOnScrollListeners();

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                recyclerView.setAdapter(null);
                historyAdapter.clear();
                loadFirstPage();
                isSearch = false;
                return false;
            }
        });



        return view;
    }
    // This method perfroms the search based on Query Text submitted

    private void performSearch(String query){

        searchCount = 0;
        isSearch = true;
        bsHistoryListItems = new LinkedList<BSHistoryList.BSHistoryBookItem>();
        recyclerView.setAdapter(null);
        historyAdapter.clear();


        Observable.just(bsClient).subscribeOn(Schedulers.computation())
                .flatMap(s -> {
                    Observable<BSHistoryList> authorObservable
                            = s.getBSHistoryByAuthor("36cca1784b09425492d25bbb4ef5bdf4", searchQuery, 0).subscribeOn(Schedulers.io());

                    Observable<BSHistoryList> titleObservable
                            = s.getBSHistoryByTitle("36cca1784b09425492d25bbb4ef5bdf4", searchQuery,0).subscribeOn(Schedulers.io());

                    return authorObservable.merge(authorObservable, titleObservable);


                }).observeOn(AndroidSchedulers.mainThread()).subscribe(this::loadSearchResults,this::handleError);

    }


    private void loadSearchResults(BSHistoryList list){

        if(list!=null)
        {
            bsHistoryListItems.addAll(list.getResults());
            searchCount ++;
        }

        if(searchCount == 2)
        {
            historyAdapter.addAll(bsHistoryListItems);
            recyclerView.setAdapter(historyAdapter);

        }

    }

    private void handleError(Throwable t){

        t.printStackTrace();

    }
    private void loadFirstPage(){

        Call<BSHistoryList> call = bsClient.getBSHistory("36cca1784b09425492d25bbb4ef5bdf4",0);

        call.enqueue(new Callback<BSHistoryList>() {
            @Override
            public void onResponse(Call<BSHistoryList> call, Response<BSHistoryList> response) {

                List<BSHistoryList.BSHistoryBookItem> books = response.body().getResults();
                isLoading = false;
                historyAdapter.addAll(books);
                recyclerView.setAdapter(historyAdapter);
                Log.d("Mansi", "Number of bs lists received: " + response.body().getNumResults());
            }

            @Override
            public void onFailure(Call<BSHistoryList> call, Throwable t) {
                call.cancel();
            }

        });
    }

    private void fetchMoreItems() {

        Call<BSHistoryList> call = bsClient.getBSHistory("36cca1784b09425492d25bbb4ef5bdf4",currentPage * offset);

        call.enqueue(new Callback<BSHistoryList>() {
            @Override
            public void onResponse(Call<BSHistoryList> call, Response<BSHistoryList> response) {

                List<BSHistoryList.BSHistoryBookItem> books = response.body().getResults();
                isLoading = false;
                historyAdapter.addAll(books);
                recyclerView.setAdapter(historyAdapter);
                Log.d("Mansi", "Number of bs lists received: " + response.body().getNumResults());
            }

            @Override
            public void onFailure(Call<BSHistoryList> call, Throwable t) {
                call.cancel();
            }

        });

    }

    private void fetchMoreSearchItems(){

        Observable.just(bsClient).subscribeOn(Schedulers.computation())
                .flatMap(s -> {
                    Observable<BSHistoryList> authorObservable
                            = s.getBSHistoryByAuthor("36cca1784b09425492d25bbb4ef5bdf4", searchQuery, currentPage * offset).subscribeOn(Schedulers.io());

                    Observable<BSHistoryList> titleObservable
                            = s.getBSHistoryByTitle("36cca1784b09425492d25bbb4ef5bdf4", searchQuery,currentPage * offset).subscribeOn(Schedulers.io());

                    return Observable.concat(authorObservable,titleObservable);
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(this::loadSearchResults  ,this::handleError);
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
        void onHistoryListFragmentInteraction(List<Item> bookDetailItemInfo);
    }
}
