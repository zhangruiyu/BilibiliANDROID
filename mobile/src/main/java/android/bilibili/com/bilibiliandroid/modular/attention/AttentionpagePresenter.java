package android.bilibili.com.bilibiliandroid.modular.attention;

import android.bilibili.com.bilibiliandroid.modular.homepage.HomepageContract;
import android.bilibili.com.bilibiliandroid.modular.homepage.HomepageFragment;

/**
 * Created by ZRY on 2016/7/19.
 */
public class AttentionpagePresenter implements HomepageContract.Persenter {
    public AttentionpagePresenter(HomepageFragment homepageFragment) {
        homepageFragment.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
