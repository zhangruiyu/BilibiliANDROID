package android.bilibili.com.bilibiliandroid.modular.attention;

/**
 * Created by ZRY on 2016/7/19.
 */
public class AttentionpagePresenter implements AttentionpageContract.Persenter {
    public AttentionpagePresenter(AttentionpageContract.View attentionpageFragment) {
        attentionpageFragment.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
