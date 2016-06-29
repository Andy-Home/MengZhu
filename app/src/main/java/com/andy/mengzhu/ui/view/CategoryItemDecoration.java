package com.andy.mengzhu.ui.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class CategoryItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paint;
    /**
     * 分割线的高度，RecycleView 为横向列表，所以取分割线的高度
     */
    private int mItemHeight = 1;

    /**
     *  分割线的颜色
     */
    private int mItemColor = Color.rgb(220, 220, 220);

    public CategoryItemDecoration() {
        paint = new Paint();
        paint.setColor(mItemColor);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
