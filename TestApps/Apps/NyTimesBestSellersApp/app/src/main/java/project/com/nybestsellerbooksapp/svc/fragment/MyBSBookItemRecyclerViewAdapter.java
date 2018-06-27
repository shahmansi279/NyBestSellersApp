package project.com.nybestsellerbooksapp.svc.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import project.com.nybestsellerbooksapp.R;
import project.com.nybestsellerbooksapp.svc.fragment.BSBookItemFragment.OnListFragmentInteractionListener;
import project.com.nybestsellerbooksapp.svc.fragment.dummy.DummyContent.DummyItem;
import project.com.nybestsellerbooksapp.svc.model.BSBookList;
import project.com.nybestsellerbooksapp.svc.util.StringUtil;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyBSBookItemRecyclerViewAdapter extends RecyclerView.Adapter<MyBSBookItemRecyclerViewAdapter.ViewHolder> {

    private final List<BSBookList.BSBookItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyBSBookItemRecyclerViewAdapter(List<BSBookList.BSBookItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_bsbookitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String title = mValues.get(position).getBookDetails().get(0).getTitle();
        holder.mIdView.setText(StringUtil.camelCase(title).trim());
        holder.mContentView.setText(mValues.get(position).getBookDetails().get(0).getAuthor());

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

    public void clear()
    {
        int size = mValues.size();
        mValues.clear();
        notifyItemRangeRemoved(0, size);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public BSBookList.BSBookItem mItem;

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
