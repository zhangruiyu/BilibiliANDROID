package android.bilibili.com.bilibiliandroid.modular.homepage;

/**
 * Created by ZRY on 2016/7/19.
 */
public class HomepagePresenter implements HomepageContract.Persenter {
    public HomepagePresenter(HomepageFragment homepageFragment) {
        homepageFragment.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
