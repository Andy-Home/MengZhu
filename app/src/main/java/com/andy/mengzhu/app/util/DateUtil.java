package com.andy.mengzhu.app.util;

import android.util.Log;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by andy on 16-6-30.
 */
public class DateUtil {

    /**
     * 日期的开始时间
     */
    private final static String dateStart = "2015-06-01";

    /**
     * 日期的结束时间
     */
    private final static String dateEnd = "2020-06-01";

    /**
     * 现在的日期所在的位置
     */
    private static int position;

    /**
     * 查找在 dateStart 与 dateEnd 之间的所有日期
     *
     * @return
     * @throws ParseException
     */
    public static List<Date> findDate() {
        //格式化数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date mBegin = null;
        Date mEnd = null;
        try {
            mBegin = sdf.parse(dateStart);
            mEnd = sdf.parse(dateEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Date> mDate = new ArrayList<>();

        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(mBegin);
        mDate.add(calBegin.getTime());

        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(mEnd);

        String currentTime = sdf.format(Calendar.getInstance().getTime());

        //获取范围内的日期
        while (mEnd.after(calBegin.getTime())) {
            String positionDate = sdf.format(mDate.get(mDate.size()-1));

            if(currentTime.equals(positionDate)){
                position = mDate.size()-1;
            }
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            mDate.add(calBegin.getTime());
        }
        return mDate;
    }

    /**
     * 根据日期获取该日期的年
     *
     * @param mDate
     * @return
     */
    public static String getYear(Date mDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        String year = "" + calendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * 根据日期获取该日期的月
     *
     * @param mDate
     * @return
     */
    public static String getMonth(Date mDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        String month = calendar.get(Calendar.MONTH)+ 1 +"";
        return month;
    }

    /**
     * 根据日期获取该日期的天
     *
     * @param mDate
     * @return
     */
    public static String getDay(Date mDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        String day = ""+calendar.get(Calendar.DATE);

        return day;
    }

    /**
     * 根据日期获取星期
     *
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        String[] weeks = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    /**
     * 获取当前日期的下标位置
     *
     * @return 当前日期的下标位置
     */
    public static int getPosition() {
        return position;
    }

    /**
     * 根据日期获取下标位置
     *
     * @param date
     * @return
     */
    public static int getPosition(Long date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start;
        start = sdf.parse(dateStart);
        int ans = (int) ((date - start.getTime()) / (1000 * 60 * 60 * 24));
        return ans;
    }

    /**
     * 本周的开始日期
     *
     * @return
     */
    public static Date getWeekStart() {
        Calendar cal = Calendar.getInstance();
        int num = cal.get(Calendar.DAY_OF_WEEK);
        Long time = cal.getTime().getTime() - (num * (1000 * 60 * 60 * 24));
        return new Date(time);
    }

    /**
     * 本周结束日期
     *
     * @return
     */
    public static Date getWeekEnd() {
        Calendar cal = Calendar.getInstance();
        int num = cal.get(Calendar.DAY_OF_WEEK);
        Long time = cal.getTime().getTime() + ((7 - num) * (1000 * 60 * 60 * 24));
        return new Date(time);
    }

    /**
     * 本月开始日期
     *
     * @return
     */
    public static Date getMonthStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));

        //int num = cal.get(Calendar.DATE);
        Long time = cal.getTime().getTime();
        return new Date(time);
    }


    /**
     * 本月结束日期
     *
     * @return
     */
    public static Date getMonthEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        //int num = cal.get(Calendar.DATE);
        Long time = cal.getTime().getTime();
        return new Date(time);
    }
}
