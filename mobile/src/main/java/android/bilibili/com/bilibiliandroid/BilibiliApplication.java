package android.bilibili.com.bilibiliandroid;


/**
 * Created by ZRY on 2016/7/19.
 */
public class BilibiliApplication extends android.app.Application {

    private static BilibiliApplication bilibiliApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        bilibiliApplication = this;
    }
    public static BilibiliApplication getApplication() {
        return bilibiliApplication;
    }
}
