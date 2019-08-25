package work.toolset.code.jvm.web.minapp.util;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DateUtil
{
    
    private static final Pattern DB_DATETIME_REGEXP =
            Pattern.compile("[+-]?(\\d{4})-?(\\d{2})-?(\\d{2})T(\\d{2}):?(\\d{2}):?([0-9.]{2,})[Z+](\\d{2}:?\\d{2}?)?");
    
    private static final String DB_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX";
    
    /**
     * 北京时间
     *
     * @return
     */
    public static Calendar pekingCalendar()
    {
        return Calendar.getInstance(getTimeZone(8));
    }
    
    
    /**
     * 拿时区
     *
     * @param zone 比如东八区是 +8
     * @return
     */
    public static TimeZone getTimeZone(int zone)
    {
        String[] timeZoneIds = TimeZone.getAvailableIDs(1000 * 60 * 60 * zone);
        TimeZone timeZone = timeZoneIds != null && timeZoneIds.length > 0 ? TimeZone.getTimeZone(timeZoneIds[0]) : TimeZone.getDefault();
        return timeZone;
    }
    
    /**
     * 知晓云数据库 Date 类型字段返回 ISO8601 格式的日期字符串，例如："2018-09-01T18:31:02.631000+08:00"
     * 这里把它转换为 {@link Calendar}
     *
     * @param str
     * @return
     */
    public static @Nullable
    Calendar parseDBDateString(String str)
    {
        return parseDBDateStringByRegExp(str);
    }
    
    /**
     * 使用正则表达式来解析 ISO8601 格式的日期时间字符串
     *
     * @param str
     * @return
     */
    private static @Nullable
    Calendar parseDBDateStringByRegExp(String str)
    {
        Matcher matcher = DB_DATETIME_REGEXP.matcher(str);
        if (matcher.matches())
        {
            int year = Integer.valueOf(matcher.group(1));
            int month = Integer.valueOf(matcher.group(2));
            int day = Integer.valueOf(matcher.group(3));
            int hour = Integer.valueOf(matcher.group(4));
            int minute = Integer.valueOf(matcher.group(5));
            int second = Double.valueOf(matcher.group(6)).intValue();
            int timeZoneOffset = Integer.valueOf(matcher.group(7).substring(0, 2));
            
            String[] timeZoneIds = TimeZone.getAvailableIDs(1000 * 60 * 60 * timeZoneOffset);
            TimeZone timeZone = timeZoneIds != null && timeZoneIds.length > 0 ? TimeZone.getTimeZone(timeZoneIds[0]) : TimeZone.getDefault();
            
            Calendar calendar = Calendar.getInstance(timeZone);
            calendar.set(year, month, day, hour, minute, second);
            return calendar;
        }
        else return null;
    }
    
    /**
     * 使用 {@link SimpleDateFormat} 来解析 ISO8601 格式的日期时间字符串
     *
     * @param str
     * @return
     */
    private static @Nullable
    Calendar parseDBDateStringBySDF(String str)
            throws java.text.ParseException
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat(DB_DATETIME_FORMAT).parse(str));
        return calendar;
    }
    
    /**
     * @param calendar
     * @return
     * @see #parseDBDateString(String)
     */
    public static @Nullable
    String formatDBDateString(Calendar calendar)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(DB_DATETIME_FORMAT);
        return sdf.format(calendar.getTime());
    }
    
}
