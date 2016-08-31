package android.bilibili.com.bilibiliandroid.modular.homepage;

import static android.bilibili.com.bilibiliandroid.utils.Precondition.checkNotNull;

/**
 * Created by ZRY on 2016/7/19.
 */
public class HomepagePresenter implements HomepageContract.Persenter {

    private final HomepageContract.View mHomepageFragment;

    public HomepagePresenter(HomepageContract.View homepageFragment) {
        mHomepageFragment = checkNotNull(homepageFragment);
        mHomepageFragment.setPresenter(this);
    }

    @Override
    public void start() {
        onloadData(true);
    }

    @Override
    public void onloadData(boolean active) {
        mHomepageFragment.setLoadingIndicator(active);
        mHomepageFragment.onloadViewpagerData();
    }


}
