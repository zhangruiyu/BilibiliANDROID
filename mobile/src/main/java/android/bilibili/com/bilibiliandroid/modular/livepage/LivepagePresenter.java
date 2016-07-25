package android.bilibili.com.bilibiliandroid.modular.livepage;

import android.bilibili.com.bilibiliandroid.modular.homepage.data.HomepageModel;
import android.bilibili.com.bilibiliandroid.net.Network;
import android.bilibili.com.bilibiliandroid.utils.LogUtils;

/**
 * Created by ZRY on 2016/7/19.
 */
public class LivepagePresenter implements LivepageContract.Persenter {
    HomepageModel mHomepageModel;
    LivepageFragment mLlivepageFragment;

    public LivepagePresenter(LivepageFragment livepageFragment, HomepageModel homepageModel) {
        mLlivepageFragment = livepageFragment;
        livepageFragment.setPresenter(this);
        mHomepageModel = homepageModel;
    }

    @Override
    public void start() {
        onloadLiveItem();
    }

    @Override
    public void onloadLiveItem() {
        Network.getInstance().getLiveIndexList().subscribe(liveData -> {
            mHomepageModel.setIsrefresh(false);
            LogUtils.d(liveData.banner.size() + "轮播图size");
            mLlivepageFragment.showLiveItem(liveData);
        });
    }
}
