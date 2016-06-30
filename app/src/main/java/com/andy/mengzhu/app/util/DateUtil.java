package com.andy.mengzhu.app.util;

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
    private final static String dateStart = "2012-06-01";

    /**
     * 日期的结束时间
     */
    private final static String dateEnd = "2112-06-01";

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

        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(mEnd);

        //获取范围内的日期
        while (mEnd.after(calBegin.getTime())) {
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
        String year = "";

        int flag = calendar.get(Calendar.YEAR);
        year = "" + flag;
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
        String month = "";

        int flag = calendar.get(Calendar.MONTH);
        if (flag < 10) {
            month = "0" + flag;
        } else {
            month = "" + flag;
        }
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
        String day = "";

        int flag = calendar.get(Calendar.DATE);
        if (flag < 10) {
            day = "0" + flag;
        } else {
            day = "" + flag;
        }
        return day;
    }

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
}
