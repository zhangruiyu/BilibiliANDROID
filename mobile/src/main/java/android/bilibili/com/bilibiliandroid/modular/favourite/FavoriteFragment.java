package android.bilibili.com.bilibiliandroid.modular.favourite;

import android.bilibili.com.bilibiliandroid.base.BaseFragment;
import android.bilibili.com.bilibiliandroid.modular.homepage.ContractHomepage;

import static android.bilibili.com.bilibiliandroid.utils.Preconditions.checkNotNull;

/**
 * Created by ZRY on 2016/7/19.
 */
public class FavoriteFragment extends BaseFragment implements ContractHomepage.View{

    private ContractHomepage.Persenter persenter;

    @Override
    public void setPresenter(ContractHomepage.Persenter presenter) {
        persenter = checkNotNull(presenter);
    }
}
