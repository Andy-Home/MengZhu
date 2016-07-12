package com.andy.mengzhu.ui.component;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.andy.mengzhu.R;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class DateChooser extends RecyclerView {
    /**
     * 当前视图的View
     */
    private View mCurrentView;

    /**
     * 回调接口
     */
    private OnItemScrollChangeListener mItemScrollChangeListener;

    /**
     * 提供给外部的回调接口
     */
    public interface OnItemScrollChangeListener {
        void onChange(View view, int position);
    }

    public DateChooser(Context context) {
        super(context);
        this.addOnScrollListener(new DataOnScrollListener());
    }

    public DateChooser(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.addOnScrollListener(new DataOnScrollListener());
    }

    public DateChooser(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.addOnScrollListener(new DataOnScrollListener());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mCurrentView = getChildAt(1);
        mCurrentView.setBackgroundResource(R.color.gainSboro);
        if (mItemScrollChangeListener != null) {
            mItemScrollChangeListener.onChange(mCurrentView,
                    getChildAdapterPosition(mCurrentView));
        }

    }

    public void setOnItemScrollChangeListener(
            OnItemScrollChangeListener mItemScrollChangeListener) {
        this.mItemScrollChangeListener = mItemScrollChangeListener;
    }

    public class DataOnScrollListener extends OnScrollListener {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            //super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            View newView = getChildAt(1);

            if (mItemScrollChangeListener != null) {
                if (newView != null && newView != mCurrentView) {
                    if (mCurrentView != null) {
                        mCurrentView.setBackgroundResource(R.color.white);
                    }
                    mCurrentView = newView;
                    mItemScrollChangeListener.onChange(mCurrentView,
                            getChildAdapterPosition(mCurrentView));

                }
            }
        }
    }


}
