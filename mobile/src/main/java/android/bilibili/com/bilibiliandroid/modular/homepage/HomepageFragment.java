package android.bilibili.com.bilibiliandroid.modular.homepage;

import android.bilibili.com.bilibiliandroid.base.BaseFragment;

/**
 * Created by ZRY on 2016/7/19.
 */
public class HomepageFragment extends BaseFragment implements ContractHomepage.View{

    private ContractHomepage.Persenter mPersenter;

    public HomepageFragment newInstance() {
        return new HomepageFragment();
    }

    @Override
    public void setPresenter(ContractHomepage.Persenter presenter) {
        mPersenter = presenter;
    }
}
