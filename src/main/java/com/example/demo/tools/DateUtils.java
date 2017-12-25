package com.example.demo.tools;

import com.example.demo.Demo;
import org.slf4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.slf4j.LoggerFactory.getLogger;

public class DateUtils
{
    // 日志
    private static Logger log = getLogger(Demo.class);

    /**
     * @param pattern
     * @return
     * @MethodName : getLocalTime
     * @Description : 获取本地时间Date类型
     */
    public static Date getLocalTime(String pattern)
    {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return date;
    }

    /**
     * @param pattern
     * @return
     * @MethodName : getLocalTime
     * @Description : 获取本地时间Date类型
     */
    public static String getLocalTimeStr(String pattern)
    {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return getDateToString(date,pattern);
    }

    /**
     * @param date
     * @param pattern
     * @return
     * @MethodName ：getDateToString
     * @Description :String类型转Date
     */
    public static String getDateToString(Date date, String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        String time = sdf.format(new Date());
        return time;
    }

    /**
     * @param pattern
     * @return
     * @MethodName ：getCurrentUTC
     * @Description :获取当前UTC时间String类型
     */
    public static String getCurrentUTC(String pattern)
    {

        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();

        // 2、取得时间偏移量：
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);

        // 3、取得夏令时差：
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);

        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));

        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * @param time
     * @param pattern
     * @return
     * @MethodName ：getStringToDate
     * @Description :String类型转Date
     */
    public static Date getStringToDate(String time, String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try
        {
            date = sdf.parse(time);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }
}
