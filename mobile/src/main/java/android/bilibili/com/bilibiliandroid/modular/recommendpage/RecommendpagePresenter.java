package android.bilibili.com.bilibiliandroid.modular.recommendpage;

import android.bilibili.com.bilibiliandroid.modular.homepage.data.HomepageModel;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.data.RecommendItem;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.data.RecommendRepository;
import android.bilibili.com.bilibiliandroid.net.ProgressSubscriber;
import android.bilibili.com.bilibiliandroid.utils.LogUtils;
import android.bilibili.com.bilibiliandroid.utils.UIUtils;

import java.util.List;

import static android.bilibili.com.bilibiliandroid.utils.Precondition.checkNotNull;

/**
 * Created by ZRY on 2016/7/19.
 */
public class RecommendpagePresenter implements RecommendpageContract.Persenter {

    private final RecommendpageFragment mRecommendpageFragment;
    private final HomepageModel mHomepageModel;

    public RecommendpagePresenter(RecommendpageFragment recommendpageFragment, HomepageModel homepageModel) {
        mRecommendpageFragment = checkNotNull(recommendpageFragment);
        mHomepageModel = checkNotNull(homepageModel);
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

                mRecommendpageFragment.showRecommendItem(resultEntity);
                mHomepageModel.setIsrefresh(false);

            }
        });
      /*  Network.getInstance().getRecomendList().subscribe(new ProgressSubscriber<List<RecommendItem.ResultEntity>>() {
            @Override
            public void onNext(List<RecommendItem.ResultEntity> resultEntity) {
                LogUtils.d(resultEntity.size());
            }
        });*/
    }

    @Override
    public void clickRecommend(RecommendItem.ResultEntity.BodyEntity bodyEntity) {
        //TODO 根据类型判断是什么鬼(直播还是视频还是主题)
        UIUtils.showToast(bodyEntity.title);
    }

    @Override
    public void clickMore(String type) {
        UIUtils.showToast(type);
    }
}
