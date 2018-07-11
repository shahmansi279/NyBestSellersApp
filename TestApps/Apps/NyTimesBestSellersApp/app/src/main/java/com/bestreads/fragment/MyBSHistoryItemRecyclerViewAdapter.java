package com.bestreads.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.bestreads.R;
import com.bestreads.fragment.dummy.DummyContent.DummyItem;
import com.bestreads.model.BSHistoryList;
import com.bestreads.model.GBookDataItems;
import com.bestreads.model.Item;
import com.bestreads.rest.GBApiClient;
import com.bestreads.rest.GBInterface;
import com.bestreads.util.StringUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link com.bestreads.fragment.BSHistoryItemFragment.OnHistoryListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyBSHistoryItemRecyclerViewAdapter extends RecyclerView.Adapter<MyBSHistoryItemRecyclerViewAdapter.ViewHolder> {

    private final List<BSHistoryList.BSHistoryBookItem> mValues;
    private final BSHistoryItemFragment.OnHistoryListFragmentInteractionListener mListener;
    private List<Item> gbBookItems = null;

    public MyBSHistoryItemRecyclerViewAdapter(BSHistoryItemFragment.OnHistoryListFragmentInteractionListener listener) {
        mValues = new ArrayList<BSHistoryList.BSHistoryBookItem>(20);
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_bshistoryitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        String title = mValues.get(position).getTitle();
        String author = mValues.get(position).getAuthor();
        holder.mIdView.setText(StringUtil.camelCase(title));
        holder.mContentView.setText(StringUtil.camelCase(author));


        /*Get the history detail for a book to use on click of the item*/

        boolean isDetailForBookAvailable = false;

        String isbn = null;

        if(mValues.get(position).getIsbns().size()>0){
            isbn = mValues.get(position).getIsbns().get(0).getIsbn13();
            isDetailForBookAvailable = true;
        }


        String finalIsbn = isbn;
        boolean finalIsDetailForBookAvailable = isDetailForBookAvailable;

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    if(finalIsDetailForBookAvailable){

                        String query = "isbn=" + finalIsbn;
                        GBInterface gbClient = GBApiClient.getClient().create(GBInterface.class);
                        Call<GBookDataItems>  call = gbClient.getGBookIem(GBApiClient.getGAPIIdentifier(),query);

                        call.enqueue(new Callback<GBookDataItems>() {
                            @Override
                            public void onResponse(Call<GBookDataItems> call, Response<GBookDataItems> response) {

                                gbBookItems = response.body().getItems();
                                mListener.onHistoryListFragmentInteraction(gbBookItems, finalIsbn);
                                gbBookItems = null;
                            }


                            @Override
                            public void onFailure(Call<GBookDataItems> call, Throwable t) {
                                t.printStackTrace();
                                call.cancel();
                            }

                        });



                    }
                    else{
                        //Passing null values
                        mListener.onHistoryListFragmentInteraction(gbBookItems, finalIsbn);
                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public BSHistoryList.BSHistoryBookItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public void addAll(List<BSHistoryList.BSHistoryBookItem> historyBookItems){

        mValues.addAll(historyBookItems);

    }

    public void clear()
    {
        mValues.clear();
        notifyDataSetChanged();
    }

}
