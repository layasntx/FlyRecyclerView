package com.chaodroid.flyrecyclerview.handler;

import android.support.annotation.IntRange;

import com.chaodroid.flyrecyclerview.bean.RcvViewType;

/**
 * Created by CHEN on 2016/11/13.
 */

public interface RcvViewTypeHandler extends RcvViewType {
    /**
     * @return 返回 position 对应的 ViewType
     */
    @IntRange(from = TYPE_ITEM_START, to = TYPE_ITEM_END)
    int getViewType(int position);
}