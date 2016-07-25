package android.bilibili.com.bilibiliandroid.base;

/**
 * Created by zhangruiyu on 16/5/19.
 */
public class BilibiliResult<T> {

    public int code;
 //   public String ret_msg;
    public T result;

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", result=" + result +
                '}';
    }
}
