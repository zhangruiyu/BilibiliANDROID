package android.bilibili.com.bilibiliandroid.modular.subarea;

import android.bilibili.com.bilibiliandroid.modular.homepage.HomepageContract;
import android.bilibili.com.bilibiliandroid.modular.homepage.HomepageFragment;

/**
 * Created by ZRY on 2016/7/19.
 */
public class SubareapagePresenter implements SubareapageContract.Persenter {
    public SubareapagePresenter(SubareapageFragment subareapageFragment) {
        subareapageFragment.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
