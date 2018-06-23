package project.com.nybestsellerbooksapp.svc.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import project.com.nybestsellerbooksapp.R;
import project.com.nybestsellerbooksapp.svc.fragment.BSHistoryItemFragment.OnListFragmentInteractionListener;
import project.com.nybestsellerbooksapp.svc.fragment.dummy.DummyContent.DummyItem;
import project.com.nybestsellerbooksapp.svc.model.BSHistoryList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyBSHistoryItemRecyclerViewAdapter extends RecyclerView.Adapter<MyBSHistoryItemRecyclerViewAdapter.ViewHolder> {

    private final List<BSHistoryList.BSHistoryBookItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyBSHistoryItemRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
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
        holder.mIdView.setText(mValues.get(position).getAuthor());
        holder.mContentView.setText(mValues.get(position).getReviews().get(0).getSundayReviewLink());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
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
