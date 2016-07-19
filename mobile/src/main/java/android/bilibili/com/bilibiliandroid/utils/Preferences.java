package android.bilibili.com.bilibiliandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ZRY on 2016/7/19.
 */
public class Preferences {
    private static final String shared_name="user_guide";
    private static SharedPreferences sp;

    public static String getString(Context context, String key,
                                   String defaultValues) {
        SharedPreferences sp = context.getSharedPreferences(shared_name,
                context.MODE_PRIVATE);
        return sp.getString(key, defaultValues);
    }

    public static void setString(Context context, String key, String Values) {
        SharedPreferences sp = context.getSharedPreferences(shared_name,
                context.MODE_PRIVATE);
        sp.edit().putString(key, Values).commit();
    }
}
