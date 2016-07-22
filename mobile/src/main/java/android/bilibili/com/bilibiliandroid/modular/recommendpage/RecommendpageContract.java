package android.bilibili.com.bilibiliandroid.modular.recommendpage;

import android.bilibili.com.bilibiliandroid.base.BasePresenter;
import android.bilibili.com.bilibiliandroid.base.BaseView;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.data.RecommendItem;

import java.util.List;

/**
 * Created by ZRY on 2016/7/19.
 */
public interface RecommendpageContract {
    interface View extends BaseView<Persenter>{
        void showRecommendItem(List<RecommendItem.ResultEntity> resultEntity);
    }
    interface Persenter extends BasePresenter{
        void onloadRecommendItem();

        void clickRecommend(RecommendItem.ResultEntity.BodyEntity bodyEntity);

        void clickMore(String type);
    }
}
