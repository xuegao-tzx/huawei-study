package com.xcl.jianjia.util;

/**
 * @author 裴云飞
 * @date 2021/1/26
 */

public class TextUtils {

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0 || "null".contentEquals(str);
    }
}
