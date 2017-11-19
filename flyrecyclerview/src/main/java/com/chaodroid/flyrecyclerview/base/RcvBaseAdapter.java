package com.chaodroid.flyrecyclerview.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by CHEN on 2016/9/19.
 */

public abstract class RcvBaseAdapter<T> extends RecyclerView.Adapter<RcvBaseHolder> {

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mDatas;

    @Override
    public void onBindViewHolder(RcvBaseHolder holder, int position) {
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

    /**
     * 更新数据
     *
     * @param data item的数据
     */

    public void updateItemData(int position, @NonNull T data) {
        mDatas.set(position, data);
    }

    /**
     * 获取一条数据
     *
     * @return 该item对应的数据
     */
    public T getItemData(int position) {
        return mDatas.get(position);
    }

    /**
     * 填充数据,此方法会清空以前的数据
     *
     * @param list 需要显示的数据
     */
    public void fillList(@NonNull List<T> list) {
        mDatas.clear();
        mDatas.addAll(list);
    }

    /**
     * 追加一条数据
     *
     * @param data 追加的数据
     */
    public void appendData(T data) {
        mDatas.add(data);
    }

    /**
     * 追加一个集合数据
     *
     * @param list 要追加的数据集合
     */
    public void appendList(@NonNull List<T> list) {
        mDatas.addAll(list);
    }

    /**
     * 在最顶部插入数据
     *
     * @param data 要插入的数据
     */
    public void addToptData(T data) {
        mDatas.add(0, data);
    }

    /**
     * 在顶部插入数据集合
     *
     * @param list 要插入的数据集合
     */
    public void addToptList(@NonNull List<T> list) {
        mDatas.addAll(0, list);
    }

    /**
     * 在任意位置插入数据
     *
     * @param data 要插入的数据
     */
    public void insertData(T data, int index) {
        mDatas.add(index, data);
    }

    /**
     * 在任意位置插入数据集合
     *
     * @param list 要插入的数据集合
     */
    public void insertList(@NonNull List<T> list, int index) {
        mDatas.addAll(index, list);
    }

    /**
     * 删除数据
     *
     * @param data 要删除的数据
     */
    public void removeData(@NonNull T data) {
        mDatas.remove(data);
    }

    /**
     * 删除数据集合
     *
     * @param list 要删除的数据集合
     */
    public void removeList(@NonNull List<T> list) {
        mDatas.removeAll(list);
    }

}
