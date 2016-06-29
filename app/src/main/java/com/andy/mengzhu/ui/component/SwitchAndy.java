package com.andy.mengzhu.ui.component;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.andy.mengzhu.R;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class SwitchAndy extends View {

    /**
     * 选择月时的状态值
     */
    public static final int SWITCH_MONTH = 0;

    /**
     * 选择周时的状态值
     */
    public static final int SWITCH_WEEK = 1;

    /**
     * 当前的收支为正时的背景色
     */
    private int positiveColor = Color.rgb(255, 193, 15);

    /**
     * 当前收支为负时的背景色
     */
    private int negativeColor = Color.rgb(139, 0, 0);

    /**
     * 显示的收支的颜色
     */
    private int numColor = Color.rgb(255, 255, 255);

    /**
     * 当前用户选择的状态
     */
    private int flag = SWITCH_MONTH;

    /**
     * 本月的收支的大小
     */
    private double displayMonthNum = 0.00;

    /**
     * 本周的收支的大小
     */
    private double displayWeekNum = 0.00;

    /**
     * 显示的文字的大小
     */
    private float textSize = 88;

    /**
     * 背景圆的半径
     */
    private int radius = 198;

    public SwitchAndy(Context context) {
        super(context, null);
    }

    public SwitchAndy(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public SwitchAndy(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();  //实例化画笔
        int centre = getWidth() / 2; //获取圆心的x坐标

        double displayNum;

        if(flag == SWITCH_WEEK){
            displayNum = displayWeekNum;
        }else{
            displayNum = displayMonthNum;
        }

        if (displayNum >= 0) {
            paint.setColor(positiveColor);
        } else {
            paint.setColor(negativeColor);
        }
        paint.setStyle(Paint.Style.FILL); //设置实心
        paint.setAntiAlias(true);  //消除锯齿
        canvas.drawCircle(centre, centre, radius, paint); //画出圆

        paint.setStrokeWidth(0);
        paint.setColor(numColor);
        paint.setTextSize(textSize);
        float textWidth = paint.measureText(displayNum+"");
        if(textWidth > radius){
            float newSize = textSize - (textWidth/2 - radius)/2;
            paint.setTextSize(newSize);
            textWidth = paint.measureText(displayNum+"");
            canvas.drawText("" + displayNum, centre - textWidth / 2, centre + newSize/2, paint);
        }else{
            canvas.drawText("" + displayNum, centre - textWidth / 2, centre + textSize/2, paint);
        }

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                if(flag == SWITCH_WEEK){
                    flag = SWITCH_MONTH;
                }else {
                    flag = SWITCH_WEEK;
                }
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    public double getDisplayMonthNum() {
        return displayMonthNum;
    }

    public void setDisplayMonthNum(double displayMonthNum) {
        this.displayMonthNum = displayMonthNum;
    }

    public double getDisplayWeekNum() {
        return displayWeekNum;
    }

    public void setDisplayWeekNum(double displayWeekNum) {
        this.displayWeekNum = displayWeekNum;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
