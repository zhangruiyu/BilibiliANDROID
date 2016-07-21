package android.bilibili.com.bilibiliandroid.modular.newdramapage;

import android.bilibili.com.bilibiliandroid.modular.homepage.HomepageContract;
import android.bilibili.com.bilibiliandroid.modular.homepage.HomepageFragment;

/**
 * Created by ZRY on 2016/7/19.
 */
public class NewdramapagePresenter implements NewdramadpageContract.Persenter {
    public NewdramapagePresenter(NewdramapageFragment newdramapageFragment) {
        newdramapageFragment.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
