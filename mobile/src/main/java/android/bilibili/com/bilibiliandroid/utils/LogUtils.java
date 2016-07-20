package android.bilibili.com.bilibiliandroid.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by zhangruiyu on 16/5/30.
 */

public class LogUtils {
    public static void d(String message) {
        Logger.d(message);
    }

    public static void d(int message) {
        Logger.d(String.valueOf(message));
    }

    public static void e(Throwable e) {
        Logger.e(e.getMessage());
    }
}
