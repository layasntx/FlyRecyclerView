package com.chaodroid.flyrecyclerview.listener;

import android.support.v7.widget.RecyclerView;

/**
 * Created by CHEN on 2016/11/6.
 */

public abstract class RcvScrollBottomListener extends RecyclerView.OnScrollListener {

    private static final String TAG = "RcvScrollBottomListener";

    private static final int STATUS_COMPLETE = 0;
    private static final int STATUS_LOADING = 1;
    private static final int STATUS_NOMORE = 2;

    private int mLoadingStatus = STATUS_COMPLETE;

    public void setStartLoading() {
        mLoadingStatus = STATUS_LOADING;
    }

    public void setLoadComplete() {
        mLoadingStatus = STATUS_COMPLETE;
    }

    public void setNoMoreData() {
        mLoadingStatus = STATUS_NOMORE;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (!recyclerView.canScrollVertically(1)) {
                if (mLoadingStatus == STATUS_COMPLETE) {
                    onScrollBottom(this);
                }
            }
        }
//        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
//        //最后一个可见条目的Position
//        int lastVisibleItemPositions = RcvUtils.getLastVisibleItemPosition(recyclerView);
//        int itemCount = manager.getItemCount();
//        //最后一个条目是否可见
//        if (lastVisibleItemPositions == (itemCount - 1)) {
//            //可以加载更多
//            if (mLoadingStatus == STATUS_COMPLETE) {
//                onScrollBottom(RcvScrollBottomListener.this);
//            }
//            Log.e(TAG, "onScrollStateChanged:mLoadingStatus" + mLoadingStatus);
//            Log.e(TAG, "onScrollStateChanged: 底部可见!!!");
//        }
//        // 当不滑动时
//        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//            //最后一个完全可见条目的Position
//            int lastCompletelyVisibleItemPositions = RcvUtils.getLastCompletelytVisibleItemPosition(recyclerView);
//            //最后一个条目是否可见
//            if (lastCompletelyVisibleItemPositions == (itemCount - 1)) {
//                //可以加载更多
//                if (mLoadingStatus == STATUS_COMPLETE) {
//                    onScrollBottom(RcvScrollBottomListener.this);
//                }
//                Log.e(TAG, "onScrollStateChanged:mLoadingStatus" + mLoadingStatus);
//                Log.e(TAG, "onScrollStateChanged: 滑动到底了!!!");
//            }
//        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }

    public abstract void onScrollBottom(RcvScrollBottomListener loadMoreListener);

}


