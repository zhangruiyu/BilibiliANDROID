package android.bilibili.com.bilibiliandroid.modular.homepage;

import android.bilibili.com.bilibiliandroid.base.BasePresenter;
import android.bilibili.com.bilibiliandroid.base.BaseView;

/**
 * Created by ZRY on 2016/7/19.
 */
public interface HomepageContract {
    interface View extends BaseView<Persenter>{
        void setLoadingIndicator(boolean active);

        void onloadViewpagerData();
    }
    interface Persenter extends BasePresenter{
        void onloadData(boolean active);
    }
}
