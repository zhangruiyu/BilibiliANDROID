package android.bilibili.com.bilibiliandroid.modular.discover;

/**
 * Created by ZRY on 2016/7/19.
 */
public class DiscoverpagePresenter implements DiscoverpageContract.Persenter {
    public DiscoverpagePresenter(DiscoverpageContract.View discoverpageFragment) {
        discoverpageFragment.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
