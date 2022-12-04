package com.xtn.common.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 时间工具类
 *
 * @author jiangjh
 * @date 2020/1/9 17:31
 */
@Slf4j
public final class TimeUtil {


    private static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT_THREAD_LOCAL
            = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    private static final ThreadLocal<SimpleDateFormat> YEAR_MONTH_DATE
            = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    /**
     * 年yyyy
     */
    public static final DateTimeFormatter YYYY = DateTimeFormatter.ofPattern("yyyy");

    /**
     * 年月日yyyyMMdd
     */
    public static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 年月日yyyy-MM-dd
     */
    public static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 年月yyyyMM
     */
    public static final DateTimeFormatter YYYYMM = DateTimeFormatter.ofPattern("yyyyMM");
    /**
     * 时分HH:mm
     */
    public static final DateTimeFormatter HHMM = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * 年月日时分秒
     */
    public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");


    private TimeUtil() {
        throw new RuntimeException("不能初始化的日期工具类");
    }


    public static Date now() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }


    public static Date parse(String timeStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(timeStr);
        } catch (ParseException e) {
            log.error("日期转化出现异常");
            e.printStackTrace();
        }
        return null;
    }

    public static String nowStr() {
        Calendar calendar = Calendar.getInstance();
        return SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().format(calendar.getTime());
    }

    /**
     * 现在的时间和指定时间点，时差是否在指定时间内
     * 例子，现在2021年10月1号，上次登录时间2020年1月1号 ，是否超过100(day）天
     */
    public static boolean compareCurrent(Date time, int minute) {
        if (minute == 0) {
            return false;
        }
        if (time == null) {
            return false;
        }
        long now = Calendar.getInstance().getTime().getTime();
        long t = time.getTime();
        return now - t < minute * 60 * 1000L;
    }

    /**
     * 计算逾期天数
     * @param time 检测时间
     * @param minute 分
     * @return
     */
    public static Integer overdueDay(Date time, int minute) {
        long now = Calendar.getInstance().getTime().getTime();
        long t = time.getTime();
        if (minute == 0) {
            long l = (now - t) / (1000 * 60 * 60 * 24);
            return Math.toIntExact(l);
        }
        long l = (now - (t + minute * 60 * 1000)) / (1000 * 60 * 60 * 24);
        return Math.toIntExact(l);

    }


    public static String todayStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    public static boolean isToday(Date time) {
        if (time == null) {
            return false;
        }
        String now = YEAR_MONTH_DATE.get().format(Calendar.getInstance().getTime());
        String timeStr = YEAR_MONTH_DATE.get().format(time);
        return Objects.equals(now, timeStr);
    }

    /**
     * 获得今天加上（减去）指定天数的日期Date对象
     *
     * @return
     */
    public static Date specifyDate(int offer) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, offer);
        return now.getTime();
    }


    /**
     * 获取当前时间 多少分钟后的时间
     *
     * @param minute
     * @return
     */
    public static Date getFutureTime(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }


    /**
     * 格式化日期
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            log.info("system error:{}", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String currentTime() {
        LocalDateTime now = LocalDateTime.now();
        try {
            return now.format(TIME);
        } catch (Exception e) {
            log.error("system error:{}", e);
            return "";
        }
    }

    /**
     * 获取形如yyyy-MM-dd格式的当天
     *
     * @return
     */
    public static String getCurrentDay() {
        LocalDate now = LocalDate.now();
        try {
            return now.format(YYYY_MM_DD);
        } catch (Exception e) {
            log.error("system error:{}", e);
            return "";
        }
    }

    public static Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取形如yyyyMMdd格式的当前天
     *
     * @return
     */
    public static String currentDay() {
        LocalDate now = LocalDate.now();
        try {
            return now.format(YYYYMMDD);
        } catch (Exception e) {
            log.error("system error:{}", e);
            return "";
        }
    }

    /**
     * 获取形如yyyy-MM-dd格式的当天
     *
     * @return
     */
    public static String currentDay(DateTimeFormatter formatter) {
        LocalDate now = LocalDate.now();
        try {
            return now.format(formatter);
        } catch (Exception e) {
            log.error("system error:{}", e.toString());
            return "";
        }
    }


    /**
     * 获取yyyyMMdd格式的明天
     *
     * @return
     */
    public static String nextDay() {
        LocalDate now = LocalDate.now();
        try {
            return now.plusDays(1).format(YYYYMMDD);
        } catch (Exception e) {
            log.error("system error:{}", e.toString());
            return "";
        }
    }

    /**
     * 获取yyyyMMdd格式的几天之后
     *
     * @return
     */
    public static String nextDay(int days, DateTimeFormatter formatter) {
        LocalDate now = LocalDate.now();
        try {
            return now.plusDays(days).format(formatter);
        } catch (Exception e) {
            log.error("system error:{}", e.toString());
            return "";
        }
    }


    /**
     * 获取yyyy-MM-dd格式的昨天
     *
     * @return
     */
    public static String yesterDay() {
        LocalDate now = LocalDate.now();
        try {
            return now.plusDays(-1).format(YYYY_MM_DD);
        } catch (Exception e) {
            log.error("system error:{}", e.getMessage());
            return "";
        }
    }

    /**
     * 获取yyyyMMdd格式的昨天
     *
     * @return
     */
    public static String yesterDays() {
        LocalDate now = LocalDate.now();
        try {
            return now.plusDays(-1).format(YYYYMMDD);
        } catch (Exception e) {
            log.error("system error:{}", e.getMessage());
            return "";
        }
    }

    /**
     * 获取yyyyMMdd格式的前天
     *
     * @return
     */
    public static String beforeYesterDays() {
        LocalDate now = LocalDate.now();
        try {
            return now.plusDays(-2).format(YYYY_MM_DD);
        } catch (Exception e) {
            log.error("system error:{}", e.getMessage());
            return "";
        }
    }

    /**
     * 获取yyyy格式的当年
     *
     * @return
     */
    public static String currentYear() {
        LocalDate now = LocalDate.now();
        try {
            return now.format(YYYY);
        } catch (Exception e) {
            log.error("system error:{}", e.getMessage());
            return "";
        }
    }

    /**
     * 获取yyyy格式的上一年
     *
     * @return
     */
    public static String lastYear() {
        LocalDate now = LocalDate.now();
        try {
            return now.plusYears(-1).format(YYYY);
        } catch (Exception e) {
            log.error("system error:{}", e.getMessage());
            return "";
        }
    }

    /**
     * 获取yyyy格式的下一年
     *
     * @return
     */
    public static String nextYear() {
        LocalDate now = LocalDate.now();
        try {
            return now.plusYears(+1).format(YYYY);
        } catch (Exception e) {
            log.error("system error:{}", e.getMessage());
            return "";
        }
    }

    /**
     * 获取yyyyMMdd格式的上year年的今天
     *
     * @return
     */
    public static String lastYearsDay(int year) {
        LocalDate now = LocalDate.now();
        try {
            return now.plusYears(year).format(YYYYMMDD);
        } catch (Exception e) {
            log.error("system error:{}", e.getMessage());
            return "";
        }
    }

    /**
     * 获取yyyy格式的前几年
     *
     * @return
     */
    public static String lastYears(int years) {
        LocalDate now = LocalDate.now();
        try {
            return now.plusYears(years).format(YYYY);
        } catch (Exception e) {
            log.error("system error:{}", e.getMessage());
            return "";
        }
    }

    /**
     * 上个月
     *
     * @return
     */
    public static String lastMonth() {
        LocalDate now = LocalDate.now();
        try {
            return now.plusMonths(-1).format(YYYYMM);
        } catch (Exception e) {
            log.error("system error:{}", e);
            return "";
        }
    }


    /**
     * 获取想要的月份  格式为YYYYMM
     * <p>
     * 当前月,参数为0,
     * 上个月,参数为-1,
     * 上上个月,参数为-2,
     * 下个月,参数为1,
     * 下下个月,参数为2,
     * 以此类推
     *
     * @param num
     * @return
     */
    public static String getWantMonth(int num) {
        LocalDate now = LocalDate.now();
        try {
            return now.plusMonths(num).format(YYYYMM);
        } catch (Exception e) {
            log.error("system error:{}", e);
            return "";
        }
    }


    /**
     * 获取yyyyMMdd格式最近7天
     * 升序排列
     *
     * @return
     */
    public static List<String> nearSevenDay() {
        List<String> list = new ArrayList<>();
        try {
            int days = 7;
            LocalDate now = LocalDate.now();
            for (int i = 0; i < days; i++) {
                list.add(now.format(YYYYMMDD));
                now = now.plusDays(-1);
            }
        } catch (Exception e) {
            log.error("system error:{}", e.getMessage());
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * 获取yyyyMMdd格式最近days天
     * 升序排列
     *
     * @return
     */
    public static List<String> nearDays(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("获取最近天数参数有误!");
        }
        List<String> list = new ArrayList<>();
        try {
            LocalDate now = LocalDate.now();
            for (int i = 0; i < days; i++) {
                list.add(now.format(YYYYMMDD));
                now = now.plusDays(-1);
            }
        } catch (Exception e) {
            log.error("system error:{}", e.getMessage());
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * 获取现在的时分 形如HH:MM
     *
     * @return
     */
    public static String currentHourMinute() {
        LocalTime now = LocalTime.now();
        try {
            return now.format(HHMM);
        } catch (Exception e) {
            log.error("system error:{}", e);
            return "";
        }
    }

    /**
     * 获取从开始时间到结束时间范围内的时间
     *
     * @param startDay 格式YYYYMMDD
     * @param endDay   格式YYYYMMDD
     * @return
     */
    public static List<String> startEndDay(String startDay, String endDay) {
        if (Objects.nonNull(startDay) && Objects.nonNull(endDay)) {
            List<String> list = new ArrayList<>();
            try {
                LocalDate start = LocalDate.parse(startDay, YYYYMMDD);
                LocalDate end = LocalDate.parse(endDay, YYYYMMDD);
                if (start.isAfter(end)) {
                    throw new IllegalArgumentException("获取startEndDay, 开始日期不能大于结束日期!");
                }
                while (start.isBefore(end)) {
                    list.add(start.format(YYYYMMDD));
                    start = start.plusDays(1);
                }
                list.add(endDay);
            } catch (Exception e) {
                log.error("system error:{}", e);
            }
            return list;
        } else {
            throw new IllegalArgumentException("获取startEndDay参数有误!");
        }
    }

    /**
     * 比较时间是否在范围内
     *
     * @param resultStartTime 数据库开始时间
     * @param resultEndTime   数据库结束时间
     * @param paramStartTime  参数开始时间
     * @param paramEndTime    参数结束时间
     * @return
     */
    public static boolean checkTime(Long resultStartTime, Long resultEndTime, Long paramStartTime, Long paramEndTime) {
        if (paramStartTime >= resultStartTime && paramStartTime <= resultEndTime) {//检查开始时间
            return true;
        }
        if (paramEndTime >= resultStartTime && paramEndTime <= resultEndTime) {//检查结束时间
            return true;
        }

        return false;
    }

    /**
     * 从现在开始倒退12个月的年份和月份
     */
    public static List<Map<String, Object>> last12MonthInfo() {
        List<Map<String, Object>> result = Lists.newArrayListWithCapacity(12);
        Calendar instance = Calendar.getInstance();
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
        map.put("year", instance.get(Calendar.YEAR));
        map.put("month", instance.get(Calendar.MONTH) + 1);
        result.add(map);

        for (int i = 0; i < 11; i++) {
            instance.add(Calendar.MONTH, -1);
            map = Maps.newHashMapWithExpectedSize(2);
            map.put("year", instance.get(Calendar.YEAR));
            map.put("month", instance.get(Calendar.MONTH) + 1);
            result.add(map);
        }
        return result;
    }

    /**
     * 获得两个时间段内所有Date集合
     */
    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    //将时间戳转换为时间
    public static String stampToDate(Long lt){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

}
