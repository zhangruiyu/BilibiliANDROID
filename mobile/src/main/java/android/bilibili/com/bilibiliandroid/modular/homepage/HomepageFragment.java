package android.bilibili.com.bilibiliandroid.modular.homepage;

import android.bilibili.com.bilibiliandroid.R;
import android.bilibili.com.bilibiliandroid.base.BaseFragment;
import android.bilibili.com.bilibiliandroid.utils.UIUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ZRY on 2016/7/19.
 */
public class HomepageFragment extends BaseFragment implements HomepageContract.View{

    private HomepageContract.Persenter mPersenter;

    public static HomepageFragment newInstance() {
        return new HomepageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = UIUtils.inflate(R.layout.homepage_fragment);
        return inflate;
    }

    @Override
    public void setPresenter(HomepageContract.Persenter presenter) {
        mPersenter = presenter;
    }
}
