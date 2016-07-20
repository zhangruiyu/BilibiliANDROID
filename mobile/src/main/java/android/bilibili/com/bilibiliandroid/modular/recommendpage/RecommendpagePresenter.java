package android.bilibili.com.bilibiliandroid.modular.recommendpage;

import android.bilibili.com.bilibiliandroid.modular.recommendpage.data.RecommendItem;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.data.RecommendRepository;
import android.bilibili.com.bilibiliandroid.net.Network;
import android.bilibili.com.bilibiliandroid.net.ProgressSubscriber;
import android.bilibili.com.bilibiliandroid.utils.LogUtils;

import java.util.List;

/**
 * Created by ZRY on 2016/7/19.
 */
public class RecommendpagePresenter implements RecommendpageContract.Persenter {
    public RecommendpagePresenter(RecommendpageFragment recommendpageFragment) {
        recommendpageFragment.setPresenter(this);
    }

    @Override
    public void start() {
        onloadRecommendItem();
    }

    @Override
    public void onloadRecommendItem() {
       new RecommendRepository().getRecommendItem().subscribe(new ProgressSubscriber<List<RecommendItem.ResultEntity>>() {
            @Override
            public void onNext(List<RecommendItem.ResultEntity> resultEntity) {
                LogUtils.d(resultEntity.size());
            }
        });
      /*  Network.getInstance().getRecomendList().subscribe(new ProgressSubscriber<List<RecommendItem.ResultEntity>>() {
            @Override
            public void onNext(List<RecommendItem.ResultEntity> resultEntity) {
                LogUtils.d(resultEntity.size());
            }
        });*/
    }
}
