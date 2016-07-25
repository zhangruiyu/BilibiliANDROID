package android.bilibili.com.bilibiliandroid.base;

/**
 * Created by zhangruiyu on 16/5/19.
 */
public class BilibiliData<T> {

    public int code;
 //   public String ret_msg;
    public T data;

    @Override
    public String toString() {
        return "BilibiliData{" +
                "code=" + code +
                ", data +=" + data +
                '}';
    }
}
