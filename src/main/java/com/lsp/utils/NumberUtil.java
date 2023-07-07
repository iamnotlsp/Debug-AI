package com.lsp.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class NumberUtil {

    /**
     * 返回 min（包括）到max（包括）的一个随机数
     *
     * @param min 最小值（包括）
     * @param max 最大值（包括）
     * @return 随机数
     */
    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min + 1);
    }

    /**
     * 返回 min（包括）到max（包括）的一个随机数集合
     *
     * @param num 长度
     * @param min 最小值（包括）
     * @param max 最大值（包括）
     * @return 随机数集合
     */
    public static List<Integer> getRandomNumberList(int num, int min, int max) {
        //要存入集合,则新建一个集合
        List<Integer> numList = new ArrayList<>();
        //利用集合长度判断是否结束循环
        while (true) {
            int a = getRandomNumber(min, max);
            if (!numList.contains(a)) {
                numList.add(a);
                if (numList.size() == num) {
                    break;
                }
            }
        }
        return numList;
    }

    /**
     * 获取当天零点时间戳
     *
     * @return
     */
    public static long getTodayTimestamp() {
        //设置时区
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当天的 LocalDateTime
     *
     * @return
     */
    public static LocalDateTime getTodayLocalDateTime() {
        return LocalDateTime.ofEpochSecond(getTodayTimestamp() / 1000, 0, ZoneOffset.ofHours(8));
    }

    /**
     * LocalDateTime转时间戳
     *
     * @param localDateTime 数据库时间
     * @return 时间戳
     */
    public static long getTimestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 获取一周前的 LocalDateTime
     *
     * @param
     * @return
     */
    public static LocalDateTime getLastWeekLocalDateTime() {
        long timestamp = getTimestamp(LocalDateTime.now()) - 604800000;
        return LocalDateTime.ofEpochSecond(timestamp / 1000, 0, ZoneOffset.ofHours(8));
    }


    /**
     * 获取时间差值
     *
     * @param localDateTime1
     * @param localDateTime2
     * @return
     */
    public static String getTimeDifference(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        Duration duration = Duration.between(localDateTime1, localDateTime2);
        long days = duration.toDays(); //相差的天数
        long hours = duration.toHours();//相差的小时数
        long minutes = duration.toMinutes();//相差的分钟数
        long millis = duration.toMillis();//相差毫秒数
        long nanos = duration.toNanos();//相差的纳秒数

        return hours + ":" + minutes + ":" + millis / 1000;
    }


    /**
     * Date 转换为 LocalDateTime
     *
     * @param date java util Data
     * @return LocalDateTime
     */
    public static LocalDateTime dataToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        //获取LocalDate
//        LocalDate localDate = localDateTime.toLocalDate();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * list转string
     *
     * @param list      list
     * @param separator 分隔符
     * @return String
     */
    public static String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i));
                sb.append(separator);
            }
        }
        return sb.toString();
    }


    /**
     * 判断两个Integer list是否相同
     *
     * @param list1
     * @param list2
     * @return
     */
    //判断的方法list
    public static boolean isListEqual(CopyOnWriteArrayList<Integer> list1, CopyOnWriteArrayList<Integer> list2) {
        if (list1 == null && list2 == null) {
            return true;
        }
        //Only one of them is null
        else if(list1 == null || list2 == null) {
            return false;
        }
        else if(list1.size() != list2.size()) {
            return false;
        }
        //copying to avoid rearranging original lists
        list1 = new CopyOnWriteArrayList<Integer>(list1);
        list2 = new CopyOnWriteArrayList<Integer>(list2);
        Collections.sort(list1);
        Collections.sort(list2);

        return list1.equals(list2);
    }

    public static CopyOnWriteArrayList<Integer> stringToIntList(String str) {
        CopyOnWriteArrayList<Integer> integerList = new CopyOnWriteArrayList<>();
        if (str != null) {
            String[] strings = str.split(",");
            for (String string : strings) {
                integerList.add(Integer.parseInt(string));
            }
        }
        return integerList;
    }

    /**
     * 字母转换为数字
     *
     * @param str
     * @return
     */
    public static CopyOnWriteArrayList<Integer> optionIndexTransition(String str) {
        String[] strList = str.split(",");
        CopyOnWriteArrayList<Integer> integerList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < strList.length; i++) {
            char c = str.charAt(0);
            integerList.add(c - 'A' + 1);
        }
        return integerList;
    }

    /**
     * 解析用户选项，设置选项按钮状态
     * const val STATE_NORMAL = 0
     * const val STATE_CHECKED = 1
     * const val STATE_TRUE = 2
     * const val STATE_ERROR = 3
     *
     * @param optionNum
     * @param userChoiceList
     * @param answerList
     * @return
     */
    public static CopyOnWriteArrayList<Integer> analysisOptionState(int optionNum, CopyOnWriteArrayList<Integer> userChoiceList, CopyOnWriteArrayList<Integer> answerList) {
        CopyOnWriteArrayList<Integer> optionStateList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < optionNum; i++) {
            optionStateList.add(0);
        }
        System.out.println("长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长度" + optionStateList.size());
        if (NumberUtil.isListEqual(userChoiceList, answerList)) {
            for (Integer integer : answerList) {
                optionStateList.set(integer, 2);
            }
        } else {
            for (Integer integer : userChoiceList) {
                optionStateList.set(integer, 3);
            }
        }
        return optionStateList;
    }

    /**
     * 将数字转换为模糊数字字符串
     *
     * @param number
     * @return
     */
    public static String numberToVagueNumberString(Integer number) {
        if (number == 0) {
            return Integer.toString(number);
        }
        String VagueNumberString = Integer.toString(number);
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        switch (number / 1000) {
            case 0: // 1000以下
                break;
            case 1: // 1000到10000
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                VagueNumberString = decimalFormat.format(new BigDecimal(String.valueOf((float) number / 1000))) + "k";
                break;
            default:
                // 10000以上
                VagueNumberString = decimalFormat.format(new BigDecimal(String.valueOf((float) number / 10000))) + "w";
                break;
        }
        return VagueNumberString;
    }

    public static CopyOnWriteArrayList<String> getStringList(String string) {
        String[] strings = string.split(",");
        CopyOnWriteArrayList<String> stringList = new CopyOnWriteArrayList<>();
        Collections.addAll(stringList, strings);
        return stringList;
    }

}
