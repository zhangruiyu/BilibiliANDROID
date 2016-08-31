package android.bilibili.com.bilibiliandroid.modular.livepage;

import android.bilibili.com.bilibiliandroid.R;
import android.bilibili.com.bilibiliandroid.base.BaseHomepageFragment;
import android.bilibili.com.bilibiliandroid.modular.livepage.data.LiveIndex;
import android.bilibili.com.bilibiliandroid.utils.UIUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ZRY on 2016/7/20.
 */
public class LivepageFragment extends BaseHomepageFragment<LivepageContract.Persenter> implements LivepageContract.View{

    private RecyclerView rv_livepage_allitem;
    private LivepageAdapter livepageAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = UIUtils.inflate(R.layout.livepage_fragment);
        rv_livepage_allitem = (RecyclerView) inflate.findViewById(R.id.bilibili_livepage_banner);
        if (livepageAdapter == null) {
            livepageAdapter = new LivepageAdapter(null, mPersenter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            rv_livepage_allitem.setLayoutManager(layoutManager);
            rv_livepage_allitem.setAdapter(livepageAdapter);
        } else {
            livepageAdapter.notifyDataSetChanged();
        }

        return inflate;
    }

    @Override
    public void onloadData() {
        super.onloadData();
        mPersenter.onloadLiveItem();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPersenter != null) {
            mPersenter.start();
        }

    }

    @Override
    public void setPresenter(LivepageContract.Persenter presenter) {
        mPersenter = presenter;
    }

    @Override
    public void showLiveItem(LiveIndex.DataEntity resultEntity) {
        livepageAdapter.replace(resultEntity);
    }
}
