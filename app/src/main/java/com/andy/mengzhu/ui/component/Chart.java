package com.andy.mengzhu.ui.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.andy.mengzhu.model.entity.CategoryStatistics;
import com.andy.mengzhu.ui.Activity.Statistics;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14 0014.
 */
public class Chart extends View {

    /**
     * 支出类型本周的统计数据
     */
    private List<CategoryStatistics> weekStatistics;

    /**
     * 支出类型当月的统计数据
     */
    private List<CategoryStatistics> monthStatistics;

    /**
     * 显示的文字的大小
     */
    private float textSize = 36;

    /**
     * 圆心
     */
    private Point point = null;

    /**
     * 图表的半径
     */
    private int radius = 0;

    /**
     * 图块的颜色(红、橙、黄、绿、蓝、靛、紫)
     */
    private int[] color = {Color.rgb(255, 0, 0), Color.rgb(255, 165, 0), Color.rgb(255, 255, 0),
            Color.rgb(0, 255, 0), Color.rgb(0, 0, 255), Color.rgb(6, 82, 121), Color.rgb(160, 32, 240)};

    private int textColor = Color.rgb(0, 0, 0);
    /**
     * 是否是月统计，默认为是月统计
     */
    private boolean isMonth = true;

    //The degree position of the last item arc's center.
    private float lastDegree = 0;
    //The count of the continues 'small' item.
    private int addTimes = 0;

    private float smallMargin = 18;

    public Chart(Context context) {
        super(context);
    }

    public Chart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Chart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        point = new Point(getWidth() / 2, getHeight() / 3);
        radius = getWidth() / 4;

        paint.setStyle(Paint.Style.FILL); //设置实心
        paint.setAntiAlias(true);  //消除锯齿

        List<CategoryStatistics> statisticsList = new ArrayList<>();
        if (isMonth) {
            statisticsList.clear();
            statisticsList.addAll(monthStatistics);
        } else {
            statisticsList.clear();
            statisticsList.addAll(weekStatistics);
        }
        double total = Total(statisticsList);
        RectF oval = new RectF(point.x - radius, point.y - radius, point.x + radius, point.y + radius);
        float startAngle = 0.0f;
        int index = 0;
        for (CategoryStatistics cs : statisticsList) {
            if (cs.getCategoryNum() == 0) {
                continue;
            }
            //画扇形区
            paint.setColor(color[index % color.length]);
            float sweepAngle = (float) ((cs.getCategoryNum() / total) * 360);
            canvas.drawArc(oval, startAngle, sweepAngle, true, paint);

            //画线
            paint.setColor(textColor);
            float radians = (float) ((startAngle + sweepAngle / 2) / 180 * Math.PI);
            float lineStartX = point.x + radius * 0.7f * (float) (Math.cos(radians));
            float lineStartY = point.y + radius * 0.7f * (float) (Math.sin(radians));
            float lineStopX, lineStopY;
            float rate;
            if (getOffset(startAngle + sweepAngle / 2) > 60) {
                rate = 1.3f;
            } else if (getOffset(startAngle + sweepAngle / 2) > 30) {
                rate = 1.2f;
            } else {
                rate = 1.1f;
            }
            //如果这一个扇形区很小，则调节线的弧度，避免其余字体重合
            if (startAngle + sweepAngle / 2 - lastDegree < 30) {
                addTimes++;
                rate += 0.2f * addTimes;
            } else {
                addTimes = 0;
            }
            lineStopX = point.x + radius * rate * (float) (Math.cos(radians));
            lineStopY = point.y + radius * rate * (float) (Math.sin(radians));
            canvas.drawLine(lineStartX, lineStartY, lineStopX, lineStopY, paint);

            //画文字
            NumberFormat mFormat = NumberFormat.getNumberInstance();
            mFormat.setMaximumFractionDigits(2);//设置小数点后面位数为
            String categoryText = cs.getCategoryName();
            String itemPercentText = mFormat.format(cs.getCategoryNum() / total * 100) + "%";

            paint.setTextSize(textSize);
            float categoryTextLen = paint.measureText(categoryText);
            float itemPercentTextLen = paint.measureText(itemPercentText);
            float lineTextWidth = Math.max(categoryTextLen, itemPercentTextLen);

            float textStartX = lineStopX;
            float textStartY = lineStopY - smallMargin;
            float percentStartX = lineStopX;
            float percentStartY = lineStopY + paint.getTextSize();
            if (lineStartX > point.x) {
                textStartX += (smallMargin + Math.abs(categoryTextLen - lineTextWidth) / 2);
                percentStartX += (smallMargin + Math.abs(itemPercentTextLen - lineTextWidth) / 2);
            } else {
                textStartX -= (smallMargin + lineTextWidth - Math.abs(categoryTextLen - lineTextWidth) / 2);
                percentStartX -= (smallMargin + lineTextWidth - Math.abs(itemPercentTextLen - lineTextWidth) / 2);
            }
            canvas.drawText(categoryText, textStartX, textStartY, paint);
            canvas.drawText(itemPercentText, percentStartX, percentStartY, paint);

            //文字间的线
            float textLineStopX = lineStopX;
            if (lineStartX > point.x) {
                textLineStopX += (lineTextWidth + smallMargin * 2);
            } else {
                textLineStopX -= (lineTextWidth + smallMargin * 2);
            }
            canvas.drawLine(lineStopX, lineStopY, textLineStopX, lineStopY, paint);
            lastDegree = startAngle + sweepAngle / 2;
            startAngle += sweepAngle;
            index++;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (isMonth) {
                    isMonth = false;
                } else {
                    isMonth = true;
                }
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    private double Total(List<CategoryStatistics> datas) {
        double sum = 0.0;
        for (CategoryStatistics cs : datas) {
            sum += cs.getCategoryNum();
        }
        return sum;
    }

    private float getOffset(float radius) {
        int a = (int) (radius % 360 / 90);
        switch (a) {
            case 0:
                return radius;
            case 1:
                return 180 - radius;
            case 2:
                return radius - 180;
            case 3:
                return 360 - radius;
        }

        return radius;
    }

    public List<CategoryStatistics> getWeekStatistics() {
        return weekStatistics;
    }

    public void setWeekStatistics(List<CategoryStatistics> weekStatistics) {
        this.weekStatistics = weekStatistics;
    }

    public List<CategoryStatistics> getMonthStatistics() {
        return monthStatistics;
    }

    public void setMonthStatistics(List<CategoryStatistics> monthStatistics) {
        this.monthStatistics = monthStatistics;
    }

}
