package android.bilibili.com.bilibiliandroid.modular.subarea;

/**
 * Created by ZRY on 2016/7/19.
 */
public class SubareapagePresenter implements SubareapageContract.Persenter {
    public SubareapagePresenter(SubareapageContract.View subareapageFragment) {
        subareapageFragment.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
