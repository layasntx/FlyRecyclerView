package com.chaodroid.flyrecyclerview.handler;

/**
 * Created by CHEN on 2016/11/13.
 */

import android.view.ViewGroup;

import com.chaodroid.flyandroid.recyclerview.base.RcvBaseHolder;

public interface RcvCreateHandler {
    /**
     * @return 返回 viewType 对应的 ViewHolder
     */
    RcvBaseHolder create(ViewGroup parent, int viewType);
}