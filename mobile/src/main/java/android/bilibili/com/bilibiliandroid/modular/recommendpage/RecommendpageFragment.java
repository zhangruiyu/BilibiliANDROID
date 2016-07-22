package android.bilibili.com.bilibiliandroid.modular.recommendpage;

import android.bilibili.com.bilibiliandroid.R;
import android.bilibili.com.bilibiliandroid.base.BaseFragment;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.data.RecommendItem;
import android.bilibili.com.bilibiliandroid.utils.UIUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

import static android.bilibili.com.bilibiliandroid.utils.Precondition.checkNotNull;

/**
 * Created by ZRY on 2016/7/20.
 */
public class RecommendpageFragment extends BaseFragment implements RecommendpageContract.View {

    private RecommendpageContract.Persenter mPersenter;
    private RecyclerView rv_recommend_allitem;
    private RecommendAdapter recommendAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = UIUtils.inflate(R.layout.recommend_fragment);
        rv_recommend_allitem = (RecyclerView) inflate.findViewById(R.id.rv_recommend_allitem);
        if (recommendAdapter == null) {
            recommendAdapter = new RecommendAdapter(new ArrayList<>(0),mPersenter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            rv_recommend_allitem.setLayoutManager(layoutManager);
            rv_recommend_allitem.setAdapter(recommendAdapter);
        } else {
            recommendAdapter.notifyDataSetChanged();
        }

        return inflate;
    }

    @Override
    public void setPresenter(RecommendpageContract.Persenter presenter) {
        mPersenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPersenter != null){
            mPersenter.start();
        }
    }

    @Override
    public void setLoadingIndicator(boolean active) {
    }

    @Override
    public void showRecommendItem(List<RecommendItem.ResultEntity> resultEntity) {
        recommendAdapter.replace(resultEntity);
    }
}
