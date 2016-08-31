package android.bilibili.com.bilibiliandroid.base;

import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Created by ZRY on 2016/7/19.
 */
public class BaseFragment<T extends BasePresenter> extends RxFragment {
    protected T mPersenter;

    public void setPresenter(T presenter) {
        mPersenter = presenter;
    }
}
