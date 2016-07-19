package android.bilibili.com.bilibiliandroid.modular.favourite;

import android.bilibili.com.bilibiliandroid.base.BaseFragment;
import android.bilibili.com.bilibiliandroid.modular.homepage.HomepageContract;

import static android.bilibili.com.bilibiliandroid.utils.Precondition.checkNotNull;

/**
 * Created by ZRY on 2016/7/19.
 */
public class FavoriteFragment extends BaseFragment implements HomepageContract.View{

    private HomepageContract.Persenter persenter;

    @Override
    public void setPresenter(HomepageContract.Persenter presenter) {
        persenter = checkNotNull(presenter);
    }
}
