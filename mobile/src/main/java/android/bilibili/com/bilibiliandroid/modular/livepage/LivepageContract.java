package android.bilibili.com.bilibiliandroid.modular.livepage;

import android.bilibili.com.bilibiliandroid.base.BasePresenter;
import android.bilibili.com.bilibiliandroid.base.BaseView;
import android.bilibili.com.bilibiliandroid.modular.livepage.data.LiveIndex;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.data.RecommendItem;

import java.util.List;

/**
 * Created by ZRY on 2016/7/19.
 */
public interface LivepageContract {
    interface View extends BaseView<Persenter>{
        void showLiveItem(LiveIndex.DataEntity resultEntity);
    }
    interface Persenter extends BasePresenter{
        void onloadLiveItem();
    }


}
