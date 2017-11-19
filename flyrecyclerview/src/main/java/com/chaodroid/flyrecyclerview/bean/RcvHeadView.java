package com.chaodroid.flyrecyclerview.bean;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.chaodroid.flyandroid.recyclerview.handler.RcvBindHandler;
import com.chaodroid.flyandroid.recyclerview.handler.RcvCreateHandler;

import java.util.List;

/**
 * Created by CHEN on 2016/10/21.
 */

public class RcvHeadView<T> implements RcvViewType {

    public int viewType;
    public List<T> datas;
    public RcvCreateHandler createHandler;
    public RcvBindHandler bindHandler;

    private RcvHeadView() {
    }

    public RcvHeadView(@IntRange(from = TYPE_HEAD_START, to = TYPE_HEAD_END) int viewType,
                       @NonNull RcvCreateHandler createHandler, RcvBindHandler bindHandler) {
        this.viewType = viewType;
        this.createHandler = createHandler;
        this.bindHandler = bindHandler;
    }

    public RcvHeadView(@IntRange(from = TYPE_HEAD_START, to = TYPE_HEAD_END) int viewType, List<T> datas,
                       @NonNull RcvCreateHandler createHandler, RcvBindHandler bindHandler) {
        this.viewType = viewType;
        this.datas = datas;
        this.createHandler = createHandler;
        this.bindHandler = bindHandler;
    }

    @Override
    public String toString() {
        return "RcvHeadView{" +
                ", viewType=" + viewType +
                ", datas=" + datas +
                ", createHandler=" + createHandler +
                ", bindHandler=" + bindHandler +
                '}';
    }
}
