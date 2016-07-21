package android.bilibili.com.bilibiliandroid.modular.recommendpage;

import android.bilibili.com.bilibiliandroid.base.BaseFragment;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.data.RecommendItem;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.bilibili.com.bilibiliandroid.utils.Precondition.checkNotNull;

/**
 * Created by ZRY on 2016/7/20.
 */
public class RecommendpageFragment extends BaseFragment implements RecommendpageContract.View {

    private RecommendpageContract.Persenter mPersenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       /* RecomendFragmentBinding recomendFragmentBinding = RecomendFragmentBinding.inflate(inflater, container, false);
        return recomendFragmentBinding.getRoot();*/
        return null;
    }

    @Override
    public void setPresenter(RecommendpageContract.Persenter presenter) {
        mPersenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPersenter.start();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showRecommendItem(List<RecommendItem.ResultEntity> resultEntity) {

    }
}
