package g2048.v3_5.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Xcl
 * @date 2021/12/4 19:30
 * @package g2048.v3
 */

public class GIdCard {

    public static boolean isIDNumber(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        //假设18位身份证号码:41000119910101123X  410001 19910101 123X
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //(18|19|20)                19（现阶段可能取值范围18xx-20xx年）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
        //[0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
        //$结尾
        //假设15位身份证号码:410001910101123  410001 910101 123
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
        //$结尾
        boolean matches = IDNumber.matches(regularExpression);
        //判断第18位校验值
        if (matches) {
            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    return idCardY[idCardMod].equalsIgnoreCase(String.valueOf(idCardLast));
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return matches;
    }

    public static String isIDNumberSex(String IDNumber) {
        int a = Integer.parseInt(IDNumber.substring(16, 17)); // 取倒数第2位
        String sex = null;
        if (a % 2 == 0) { // 偶数为女性,基数为男性
            sex = "女";
        } else {
            sex = "男";
        }
        return sex;
    }

    public static int isIDNumberAge(String IDNumber) {
        String birth = IDNumber.substring(6, 14);
        // dayOfBirth出生那一日的时间
        Date dayOfBirth = null;
        try {
            dayOfBirth = new SimpleDateFormat("yyyyMMdd").parse(birth);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        Calendar cal = new GregorianCalendar();// 当前时间
        int year = cal.get(Calendar.YEAR);// year今年年份
        cal.setTime(dayOfBirth);// 当前cal为dayOfBirth出生那一日的Calendar时间
        int birthYear = cal.get(Calendar.YEAR);// 出生年份
        int age = year - birthYear;
        cal.set(Calendar.YEAR, year);// 计算今年生日
        Date birthday = cal.getTime();// birthday 每年的生日
        Date today = new Date();// today 今天
        if (today.compareTo(birthday) < 0) {// 当前时间小于今年的生日
            // 今年没有过生日, 减一岁
            age--;
        }
        return age;
    }
}