package com.chaodroid.flyrecyclerview;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaodroid.flyandroid.recyclerview.base.RcvBaseAdapter;
import com.chaodroid.flyandroid.recyclerview.base.RcvBaseHolder;
import com.chaodroid.flyandroid.recyclerview.bean.RcvFootView;
import com.chaodroid.flyandroid.recyclerview.bean.RcvHeadView;
import com.chaodroid.flyandroid.recyclerview.bean.RcvItemView;
import com.chaodroid.flyandroid.recyclerview.bean.RcvViewType;
import com.chaodroid.flyandroid.recyclerview.handler.RcvBindHandler;
import com.chaodroid.flyandroid.recyclerview.handler.RcvCreateHandler;
import com.chaodroid.flyandroid.recyclerview.handler.RcvViewTypeHandler;
import com.chaodroid.flyandroid.recyclerview.listener.RcvItemClickListener;
import com.chaodroid.flyandroid.recyclerview.listener.RcvItemLongClickListener;
import com.chaodroid.flyandroid.recyclerview.listener.RcvScrollBottomListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHEN on 2016/9/19.
 */

public class FlyRcvAdapter<T> extends RcvBaseAdapter implements RcvViewType {

    private static Builder mBuilder = null;

    private RecyclerView mRecyclerView;

    private List<RcvItemView> mItems;
    private List<RcvHeadView> mHeads;
    private List<RcvFootView> mFoots;

    private RcvViewTypeHandler mViewTypeHandler;            //多条目类型

    private RcvItemClickListener mItemClickListener;        //单击
    private RcvItemLongClickListener mLongClickListener;    //长按

    private RcvScrollBottomListener mBottomListener;        //上拉加载

//    private boolean isEdit = false;
//
//    /**
//     * 编辑模式
//     */
//    public void setEditMode(boolean isEdit) {
//        this.isEdit = isEdit;
//        notifyDataSetChanged();
//    }
//
//    public boolean getEditMode() {
//        return isEdit;
//    }

    public static <E> Builder<E> newBuilder(@NonNull Context context, @NonNull RecyclerView recyclerView,
                                            @NonNull List<E> datas) {
        mBuilder = new Builder<>(context, recyclerView, datas);
        return mBuilder;
    }

    private FlyRcvAdapter(Builder builder) {
        this.mContext = builder.mContext;
        this.mInflater = builder.mInflater;
        this.mRecyclerView = builder.mRecyclerView;
        this.mDatas = builder.mDatas;
        this.mItems = builder.mItems;
        this.mHeads = builder.mHeads;
        this.mFoots = builder.mFoots;
        this.mViewTypeHandler = builder.mViewTypeHandler;
        this.mItemClickListener = builder.mItemClickListener;
        this.mLongClickListener = builder.mLongClickListener;
        this.mBottomListener = builder.mBottomListener;

        init();
    }

    private void init() {
        mRecyclerView.setAdapter(this);
        if (mBottomListener != null) {
            mRecyclerView.addOnScrollListener(mBottomListener);
        }
    }

    @Override
    final public int getItemViewType(int position) {
        if (isHeadByPosition(position)) {
            return TYPE_HEAD_START + position;
        } else if (isFootByPosition(position)) {
            return TYPE_FOOT_START + position - getHeadNum() - getItemNum();
        } else if (mViewTypeHandler != null) {
            //用户指定的条目类型
            return mViewTypeHandler.getViewType(position);
        } else {
            //单一条目类型
            return 0;
        }
    }

    @Override
    final public RcvBaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isHeadByViewType(viewType)) {
            RcvHeadView itemView = mHeads.get(viewType - TYPE_HEAD_START);
            return itemView.createHandler.create(parent, viewType);
        } else if (isFootByViewType(viewType)) {
            RcvFootView itemView = mFoots.get(viewType - TYPE_FOOT_START);
            return itemView.createHandler.create(parent, viewType);
        } else {
            for (RcvItemView itemView : mItems) {
                if (itemView.viewType == viewType) {
                    return itemView.createHandler.create(parent, viewType);
                }
            }
            throw new IllegalArgumentException("could not find the view type: " + viewType);
        }
    }

    @Override
    final public void onBindViewHolder(RcvBaseHolder holder, int position) {
        if (isHeadByPosition(position)) {
            RcvHeadView itemView = mHeads.get(position);
            itemView.bindHandler.bind(holder, position);
        } else if (isFootByPosition(position)) {
            RcvFootView itemView = mFoots.get(position - getHeadNum() - getItemNum());
            itemView.bindHandler.bind(holder, position - getHeadNum() - getItemNum());
        } else {
            boolean flag = true;
            int viewType = mViewTypeHandler.getViewType(position);
            for (RcvItemView itemView : mItems) {
                if (itemView.viewType == viewType) {
                    itemView.bindHandler.bind(holder, position - getHeadNum());
                    flag = false;
                }
            }
            if (flag) {
                throw new IllegalArgumentException("could not find the view type: " + viewType);
            }
            setListener(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return getItemNum() + getHeadNum() + getFootNum();
    }

    public int getItemNum() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public int getHeadNum() {
        return mHeads == null ? 0 : mHeads.size();
    }

    public int getFootNum() {
        return mFoots == null ? 0 : mFoots.size();
    }

    public boolean isHeadByViewType(int viewType) {
        return viewType >= TYPE_HEAD_START && viewType <= TYPE_HEAD_END;
    }

    public boolean isFootByViewType(int viewType) {
        return viewType >= TYPE_FOOT_START && viewType <= TYPE_FOOT_END;
    }

    public boolean isHeadByPosition(int position) {
        return position < getHeadNum();
    }

    public boolean isFootByPosition(int position) {
        return position >= getHeadNum() + getItemNum();
    }

    public Context getContext() {
        return mContext;
    }

    public LayoutInflater getInflater() {
        return mInflater;
    }

    public List<T> getDatas() {
        return mDatas;
    }


    private void setListener(RcvBaseHolder holder, final int position) {
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(v, position);
                }
            });
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongClickListener.onItemLongClick(v, position);
                    return true;
                }
            });
        }
    }

    public static class Builder<T> {

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

        private Builder(Context context, RecyclerView recyclerView, List<T> datas) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
            mRecyclerView = recyclerView;
            mDatas = datas;
        }

        public Builder<T> addItem(@NonNull RcvItemView itemView) {
            if (mItems == null) {
                mItems = new ArrayList<>();
            }
            mItems.add(itemView);
            return this;
        }

        public Builder<T> addItem(@IntRange(from = TYPE_ITEM_START, to = TYPE_ITEM_END) int viewType,
                               @NonNull RcvCreateHandler createHandler,
                               @NonNull RcvBindHandler bindHandler) {
            RcvItemView itemView = new RcvItemView(viewType, createHandler, bindHandler);
            addItem(itemView);
            return this;
        }

        public Builder<T> itemType(RcvViewTypeHandler viewTypeHandler) {
            mViewTypeHandler = viewTypeHandler;
            return this;
        }

        public Builder<T> addHead(@NonNull RcvCreateHandler createHandler,
                               @NonNull RcvBindHandler bindHandler) {
            if (mHeads == null) {
                mHeads = new ArrayList<>();
            }
            RcvHeadView itemView = new RcvHeadView(mHeads.size() + TYPE_HEAD_START, createHandler, bindHandler);
            mHeads.add(itemView);
            return this;
        }

        public Builder<T> addFoot(@NonNull RcvCreateHandler createHandler,
                               @NonNull RcvBindHandler bindHandler) {
            if (mFoots == null) {
                mFoots = new ArrayList<>();
            }
            RcvFootView itemView = new RcvFootView(mFoots.size() + TYPE_FOOT_START, createHandler, bindHandler);
            mFoots.add(itemView);
            return this;
        }

        public Builder<T> setRcvItemClickListener(RcvItemClickListener listener) {
            mItemClickListener = listener;
            return this;
        }

        public Builder<T> setRcvLongClickListener(RcvItemLongClickListener listener) {
            mLongClickListener = listener;
            return this;
        }

        public Builder<T> addScrollBottomListener(RcvScrollBottomListener listener) {
            mBottomListener = listener;
            return this;
        }

        public FlyRcvAdapter<T> create() {

            FlyRcvAdapter<T> adapter = new FlyRcvAdapter<>(mBuilder);
            mBuilder = null;
            return adapter;
        }
    }

}
