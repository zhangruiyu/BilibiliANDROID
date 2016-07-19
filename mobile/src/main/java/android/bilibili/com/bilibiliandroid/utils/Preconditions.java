package android.bilibili.com.bilibiliandroid.utils;

/**
 * Created by ZRY on 2016/7/19.
 */
public class Preconditions {
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

}
