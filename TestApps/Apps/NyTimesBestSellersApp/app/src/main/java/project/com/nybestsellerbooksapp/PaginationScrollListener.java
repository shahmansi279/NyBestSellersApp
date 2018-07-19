package project.com.nybestsellerbooksapp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Mansi on 1/25/18.
 */

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    LinearLayoutManager layoutManager;

    public PaginationScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!isLoading() && !isLastPage()) {
            if (((visibleItemCount + firstVisibleItemPosition) >= (totalItemCount))
                    && firstVisibleItemPosition >= 0) {
                    loadMoreItems();
            }
        }


    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

    }

    protected abstract void loadMoreItems();

    public  abstract int getTotalPageCount();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}

