package android.bilibili.com.bilibiliandroid.net;

/**
 * Created by zhangruiyu on 16/5/19.
 */
public class ApiException extends RuntimeException {
    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    private static String getApiExceptionMessage(int resultCode) {
        String message = "";
        switch (resultCode) {
            case 1500:
                message = "哈哈错";
                break;
            default:
                message = "装逼失误";
                break;
        }
        return message;
    }

    public ApiException(String message) {
        super(message);
    }
}
