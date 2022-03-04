package com.xcl.jianjia.util;

import ohos.eventhandler.EventRunner;

/**
 * @author 裴云飞
 * @date 2021/1/23
 */

public class SysUtils {

    public static boolean isMainThread() {
        return EventRunner.getMainEventRunner().getThreadId() == Thread.currentThread().getId();
    }
}
