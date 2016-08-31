package android.bilibili.com.bilibiliandroid.modular.newdramapage;

/**
 * Created by ZRY on 2016/7/19.
 */
public class NewdramapagePresenter implements NewdramadpageContract.Persenter {
    public NewdramapagePresenter(NewdramadpageContract.View newdramapageFragment) {
        newdramapageFragment.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
