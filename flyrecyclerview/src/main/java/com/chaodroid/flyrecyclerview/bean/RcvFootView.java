package com.chaodroid.flyrecyclerview.bean;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.chaodroid.flyrecyclerview.handler.RcvBindHandler;
import com.chaodroid.flyrecyclerview.handler.RcvCreateHandler;

import java.util.List;

/**
 * Created by CHEN on 2016/10/21.
 */

public class RcvFootView<T> implements RcvViewType {

    public int viewType;
    public List<T> datas;
    public RcvCreateHandler createHandler;
    public RcvBindHandler bindHandler;

    private RcvFootView() {
    }

    public RcvFootView(@IntRange(from = TYPE_FOOT_START, to = TYPE_FOOT_END) int viewType,
                       @NonNull RcvCreateHandler createHandler, RcvBindHandler bindHandler) {
        this.viewType = viewType;
        this.createHandler = createHandler;
        this.bindHandler = bindHandler;
    }

    public RcvFootView(@IntRange(from = TYPE_FOOT_START, to = TYPE_FOOT_END) int viewType, List<T> datas,
                       @NonNull RcvCreateHandler createHandler, RcvBindHandler bindHandler) {
        this.viewType = viewType;
        this.datas = datas;
        this.createHandler = createHandler;
        this.bindHandler = bindHandler;
    }

    @Override
    public String toString() {
        return "RcvFootView{" +
                ", viewType=" + viewType +
                ", datas=" + datas +
                ", createHandler=" + createHandler +
                ", bindHandler=" + bindHandler +
                '}';
    }
}
