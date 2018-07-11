package com.bestreads.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bestreads.R;
import com.bestreads.fragment.BSFavoriteItemFragment.OnListFragmentInteractionListener;
import com.bestreads.fragment.dummy.DummyContent.DummyItem;
import com.bestreads.model.BSFavoriteBookItem;
import com.bestreads.model.GBookDataItems;
import com.bestreads.model.Item;
import com.bestreads.rest.GBApiClient;
import com.bestreads.rest.GBInterface;
import com.bestreads.util.StringUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyBSFavoriteItemRecyclerViewAdapter extends RecyclerView.Adapter<MyBSFavoriteItemRecyclerViewAdapter.ViewHolder> {

    private final List<BSFavoriteBookItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private List<Item> gbBookItems = null;

    public MyBSFavoriteItemRecyclerViewAdapter(List<BSFavoriteBookItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_bsfavoriteitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String title = mValues.get(position).getBookTitle();
        String author = mValues.get(position).getBookAuthor();
        holder.mIdView.setText(StringUtil.camelCase(title));
        holder.mContentView.setText(author);

        boolean isDetailForBookAvailable = false;

        String isbn = null;

        if(!mValues.get(position).getBookIsbn().isEmpty()){
            isbn = mValues.get(position).getBookIsbn();
            isDetailForBookAvailable = true;
        }


        String finalIsbn = isbn;
        boolean finalIsDetailForBookAvailable = isDetailForBookAvailable;

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {

                    if(finalIsDetailForBookAvailable){

                       // + finalIsbn + " "
                        String query = title + " "  + author + " " + finalIsbn;
                        GBInterface gbClient = GBApiClient.getClient().create(GBInterface.class);
                        Call<GBookDataItems> call = gbClient.getGBookIem(GBApiClient.getGAPIIdentifier(),query);

                        call.enqueue(new Callback<GBookDataItems>() {
                            @Override
                            public void onResponse(Call<GBookDataItems> call, Response<GBookDataItems> response) {

                                gbBookItems = response.body().getItems();
                                mListener.onFavoriteListFragmentInteraction(gbBookItems, finalIsbn);
                            }


                            @Override
                            public void onFailure(Call<GBookDataItems> call, Throwable t) {
                                t.printStackTrace();
                                call.cancel();
                            }

                        });

                    }
                    else{
                        //If NY Api only doesnt have ISBN , we cant then fetch any item, so just to show data not available passing nulll values
                        mListener.onFavoriteListFragmentInteraction(gbBookItems, finalIsbn);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public void clear()
    {
        mValues.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public BSFavoriteBookItem mItem;

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
}
