package project.release.sdk.com.weather.utils;

import android.util.Log;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class StringUtils {

    /**
     * 保留两位小数
     *
     * @param doubleValue
     * @return
     * @author 左成城
     * @data 2015-6-1 下午2:54:58
     */
    public static String getTwo(double doubleValue) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String value = df.format(doubleValue);
        return value;

    }

    /**
     * 截取中英文
     *
     * @param str 原字符串
     * @return
     * @author 左成城
     * @data 2015-7-31 下午2:32:37
     */
    public static String findLetter(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        String newStrs = str.replace(" ", "");
        char[] chs = newStrs.toCharArray();
        int k = 0;
        for (int i = 0; i < chs.length; i++) {
            if (!isAsciiLetter(chs[i])) {
                break;
            }
            k++;
        }
        String newStr = newStrs.substring(k, newStrs.length());
        // return new String(chs, 0, k);
        // return new String(chs, k, str.length()-1);
        return newStr;
    }

    public static String getContent(String content){
        if (content.contains("市")||content.contains("区")||content.contains("县")){

            if (content.length() > 1){
                content = content.substring(0,content.length()-1);
            }
        }
        Log.e("------","-----"+content);
        return content;
    }


    private static boolean isAsciiLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static boolean hasAnyPrefix(String number, String... prefixes) {
        if (number == null) {
            return false;
        }
        for (String prefix : prefixes) {
            if (number.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串不为空
     *
     * @param content
     * @return
     * @author 左成城
     * @data 2015年11月13日 下午2:08:36
     */
    public static String judgeStringIsNull(String content) {
        if (content == null || "".equals(content) || "null".equals(content)) {
            return "";
        } else {
            return content;
        }
    }
}
