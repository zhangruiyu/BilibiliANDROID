package android.bilibili.com.bilibiliandroid.modular.livepage;

import android.bilibili.com.bilibiliandroid.modular.homepage.HomepageContract;
import android.bilibili.com.bilibiliandroid.modular.homepage.HomepageFragment;

/**
 * Created by ZRY on 2016/7/19.
 */
public class LivepagePresenter implements LivepageContract.Persenter {
    public LivepagePresenter(LivepageFragment livepageFragment) {
        livepageFragment.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
