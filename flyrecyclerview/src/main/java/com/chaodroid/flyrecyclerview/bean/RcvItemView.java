package com.chaodroid.flyrecyclerview.bean;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.chaodroid.flyrecyclerview.handler.RcvBindHandler;
import com.chaodroid.flyrecyclerview.handler.RcvCreateHandler;


/**
 * Created by CHEN on 2016/10/21.
 */

public class RcvItemView implements RcvViewType {

    public int viewType;
    public RcvCreateHandler createHandler;
    public RcvBindHandler bindHandler;

    private RcvItemView() {
    }

    public RcvItemView(@IntRange(from = TYPE_ITEM_START, to = TYPE_ITEM_END) int viewType,
                       @NonNull RcvCreateHandler createHandler, RcvBindHandler bindHandler) {
        this.viewType = viewType;
        this.createHandler = createHandler;
        this.bindHandler = bindHandler;
    }

    @Override
    public String toString() {
        return "RcvItemView{" +
                ", viewType=" + viewType +
                ", createHandler=" + createHandler +
                ", bindHandler=" + bindHandler +
                '}';
    }
}
