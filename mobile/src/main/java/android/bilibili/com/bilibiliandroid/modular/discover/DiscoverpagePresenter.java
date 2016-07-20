package android.bilibili.com.bilibiliandroid.modular.discover;

import android.bilibili.com.bilibiliandroid.modular.homepage.HomepageContract;
import android.bilibili.com.bilibiliandroid.modular.homepage.HomepageFragment;

/**
 * Created by ZRY on 2016/7/19.
 */
public class DiscoverpagePresenter implements HomepageContract.Persenter {
    public DiscoverpagePresenter(HomepageFragment homepageFragment) {
        homepageFragment.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
