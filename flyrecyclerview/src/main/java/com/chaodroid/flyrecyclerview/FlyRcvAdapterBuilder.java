package com.chaodroid.flyrecyclerview;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.chaodroid.flyrecyclerview.bean.RcvFootView;
import com.chaodroid.flyrecyclerview.bean.RcvHeadView;
import com.chaodroid.flyrecyclerview.bean.RcvItemView;
import com.chaodroid.flyrecyclerview.bean.RcvViewType;
import com.chaodroid.flyrecyclerview.handler.RcvBindHandler;
import com.chaodroid.flyrecyclerview.handler.RcvCreateHandler;
import com.chaodroid.flyrecyclerview.handler.RcvViewTypeHandler;
import com.chaodroid.flyrecyclerview.listener.RcvItemClickListener;
import com.chaodroid.flyrecyclerview.listener.RcvItemLongClickListener;
import com.chaodroid.flyrecyclerview.listener.RcvScrollBottomListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHEN on 2017/11/15.
 */

public class FlyRcvAdapterBuilder<T> implements RcvViewType {


    private Context mContext;
    private LayoutInflater mInflater;
    private RecyclerView mRecyclerView;
    private List<T> mDatas;

    private List<RcvItemView> mItems;
    private List<RcvHeadView> mHeads;
    private List<RcvFootView> mFoots;

    private RcvViewTypeHandler mViewTypeHandler;            //多条目类型

    private RcvItemClickListener mItemClickListener;        //单击
    private RcvItemLongClickListener mLongClickListener;    //长按

    private RcvScrollBottomListener mBottomListener;        //上拉加载

    public FlyRcvAdapterBuilder(Context context, RecyclerView recyclerView, List<T> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mRecyclerView = recyclerView;
        mDatas = datas;
    }

    public FlyRcvAdapterBuilder<T> addItem(@NonNull RcvItemView itemView) {
        if (mItems == null) {
            mItems = new ArrayList<>();
        }
        mItems.add(itemView);
        return this;
    }

    public FlyRcvAdapterBuilder<T> addItem(@IntRange(from = TYPE_ITEM_START, to = TYPE_ITEM_END) int viewType,
                           @NonNull RcvCreateHandler createHandler,
                           @NonNull RcvBindHandler bindHandler) {
        RcvItemView itemView = new RcvItemView(viewType, createHandler, bindHandler);
        addItem(itemView);
        return this;
    }

    public FlyRcvAdapterBuilder<T> itemType(RcvViewTypeHandler viewTypeHandler) {
        mViewTypeHandler = viewTypeHandler;
        return this;
    }

    public FlyRcvAdapterBuilder<T> addHead(@NonNull RcvCreateHandler createHandler,
                           @NonNull RcvBindHandler bindHandler) {
        if (mHeads == null) {
            mHeads = new ArrayList<>();
        }
        RcvHeadView itemView = new RcvHeadView(mHeads.size() + TYPE_HEAD_START, createHandler, bindHandler);
        mHeads.add(itemView);
        return this;
    }

    public FlyRcvAdapterBuilder<T> addFoot(@NonNull RcvCreateHandler createHandler,
                           @NonNull RcvBindHandler bindHandler) {
        if (mFoots == null) {
            mFoots = new ArrayList<>();
        }
        RcvFootView itemView = new RcvFootView(mFoots.size() + TYPE_FOOT_START, createHandler, bindHandler);
        mFoots.add(itemView);
        return this;
    }

    public FlyRcvAdapterBuilder<T> setRcvItemClickListener(RcvItemClickListener listener) {
        mItemClickListener = listener;
        return this;
    }

    public FlyRcvAdapterBuilder<T> setRcvLongClickListener(RcvItemLongClickListener listener) {
        mLongClickListener = listener;
        return this;
    }

    public FlyRcvAdapterBuilder<T> addScrollBottomListener(RcvScrollBottomListener listener) {
        mBottomListener = listener;
        return this;
    }

//    public FlyRcvAdapter<T> create() {
//
//        FlyRcvAdapter<T> adapter = new FlyRcvAdapter<>(this);
//        return adapter;
//    }

    public Context getContext() {
        return mContext;
    }

    public LayoutInflater getInflater() {
        return mInflater;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public List<RcvItemView> getItems() {
        return mItems;
    }

    public List<RcvHeadView> getHeads() {
        return mHeads;
    }

    public List<RcvFootView> getFoots() {
        return mFoots;
    }

    public RcvViewTypeHandler getViewTypeHandler() {
        return mViewTypeHandler;
    }

    public RcvItemClickListener getItemClickListener() {
        return mItemClickListener;
    }

    public RcvItemLongClickListener getLongClickListener() {
        return mLongClickListener;
    }

    public RcvScrollBottomListener getBottomListener() {
        return mBottomListener;
    }
}
