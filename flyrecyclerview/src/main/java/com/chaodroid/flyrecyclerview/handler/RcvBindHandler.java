package com.chaodroid.flyrecyclerview.handler;


import com.chaodroid.flyrecyclerview.base.RcvBaseHolder;

/**
 * Created by CHEN on 2016/11/13.
 */


public interface RcvBindHandler {
    /**
     * 绑定 View 和数据
     * 可以调用 RcvBaseHolder的 bindLayout()方法
     * 也可以直接在此处绑定数据
     */
    void bind(RcvBaseHolder holder, final int position);
}