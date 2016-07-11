package com.andy.mengzhu.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * RecycleView 中的侧滑按钮
 * <p/>
 * Created by Administrator on 2016/7/11 0011.
 */
public class SlidingView extends ViewGroup {
    private TextView delete;
    private TextView edit;

    public SlidingView(Context context) {
        super(context);
    }

    public SlidingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SlidingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
