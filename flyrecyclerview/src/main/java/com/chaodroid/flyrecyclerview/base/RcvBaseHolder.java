package com.chaodroid.flyrecyclerview.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by CHEN on 2016/9/19.
 */

public abstract class RcvBaseHolder<T> extends RecyclerView.ViewHolder {

    protected Context mContext;
    protected View mRootView;
    protected Resources mResources;
    protected SparseArrayCompat<View> mViewMap;//存放 Viewid 和 View 的映射
    protected T mInfo;

    public RcvBaseHolder(@NonNull View rootView) {
        super(rootView);
        mRootView = rootView;
        mContext = mRootView.getContext();
        mResources = mContext.getResources();
        mViewMap = new SparseArrayCompat<>();
    }

    /**
     * 模板代码：
     * 初始化布局调用：initLayout
     * 更新布局调用：updateLayout
     */
    final public void bindLayout(T info) {
        if (mInfo == null || mInfo != info) {
            mInfo = info;
            initLayout(info);
        } else {
            updateLayout(info);
        }
    }

    /**
     * 生成新的条目布局
     */
    abstract protected void initLayout(T info);

    /**
     * 更新条目布局
     */
    abstract protected void updateLayout(T info);

    /**
     * findViewById
     *
     * @param viewId view的id
     * @param <V>    view本身
     * @return
     */
    public <V extends View> V getView(@IdRes int viewId) {
        View view = mViewMap.get(viewId);
        if (view == null) {
            view = mRootView.findViewById(viewId);
            if (view == null) {
                throw new NullPointerException("The view's ID does not exist");
            } else {
                mViewMap.put(viewId, view);
            }
        }
        return (V) view;
    }

    /****以下为辅助方法*****/

    /**
     * 设置TextView内容
     *
     * @param viewId
     * @param value
     */
    public RcvBaseHolder setText(@IdRes int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    public RcvBaseHolder setText(@IdRes int viewId, @StringRes int resId) {
        TextView view = getView(viewId);
        view.setText(mResources.getString(resId));
        return this;
    }

    public RcvBaseHolder setImageResource(@IdRes int viewId, @DrawableRes int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public RcvBaseHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public RcvBaseHolder setImageDrawable(@IdRes int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public RcvBaseHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public RcvBaseHolder setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public RcvBaseHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public RcvBaseHolder setTextColorRes(@IdRes int viewId, @ColorRes int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mResources.getColor(textColorRes));
        return this;
    }

    @SuppressLint("NewApi")
    public RcvBaseHolder setAlpha(@IdRes int viewId, @FloatRange(from = 0.0, to = 1.0) float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public RcvBaseHolder setVisibleOrNo(@IdRes int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    public RcvBaseHolder setVisibleOrGone(@IdRes int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * TextView设置超链接
     *
     * @param viewId
     */
    public RcvBaseHolder linkify(@IdRes int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /**
     * TextView设置字体样式
     *
     * @param typeface
     * @param viewIds
     */
    public RcvBaseHolder setTypeface(Typeface typeface, @IdRes int... viewIds) {
        for (@IdRes int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public RcvBaseHolder setProgress(@IdRes int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public RcvBaseHolder setProgress(@IdRes int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public RcvBaseHolder setMax(@IdRes int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public RcvBaseHolder setRating(@IdRes int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public RcvBaseHolder setRating(@IdRes int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public RcvBaseHolder setTag(@IdRes int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public RcvBaseHolder setTag(@IdRes int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public RcvBaseHolder setChecked(@IdRes int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 关于事件的
     */
    public RcvBaseHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public RcvBaseHolder setOnTouchListener(@IdRes int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public RcvBaseHolder setOnLongClickListener(@IdRes int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

}
