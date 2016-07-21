package android.bilibili.com.bilibiliandroid.modular.homepage;

import android.bilibili.com.bilibiliandroid.R;
import android.bilibili.com.bilibiliandroid.base.BaseFragment;
import android.bilibili.com.bilibiliandroid.databinding.HomepageFragmentBinding;
import android.bilibili.com.bilibiliandroid.modular.attention.AttentionpageFragment;
import android.bilibili.com.bilibiliandroid.modular.discover.DiscoverpageFragment;
import android.bilibili.com.bilibiliandroid.modular.livepage.LivepageFragment;
import android.bilibili.com.bilibiliandroid.modular.newdramapage.NewdramapageFragment;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.RecommendpageFragment;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.RecommendpagePresenter;
import android.bilibili.com.bilibiliandroid.modular.subarea.SubareapageFragment;
import android.bilibili.com.bilibiliandroid.utils.UIUtils;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ZRY on 2016/7/19.
 */
public class HomepageFragment extends BaseFragment implements HomepageContract.View {
    private HomepageContract.Persenter mPersenter;
    private AllpageAdapter allpageAdapter;
    private String[] stringArray;
    Class[] allpageClasses = {LivepageFragment.class, RecommendpageFragment.class, NewdramapageFragment.class, SubareapageFragment.class, AttentionpageFragment.class, DiscoverpageFragment.class};

    public static HomepageFragment newInstance() {
        return new HomepageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HomepageFragmentBinding homepageFragmentBinding = HomepageFragmentBinding.inflate(inflater, container, false);
        stringArray = getResources().getStringArray(R.array.homepage_allbutton);
        homepageFragmentBinding.setHomepageModel(new HomepageModel());

        if (allpageAdapter == null) {
            allpageAdapter = new AllpageAdapter(getFragmentManager());
            homepageFragmentBinding.vpHomepageAllpage.setAdapter(allpageAdapter);
        } else {
            allpageAdapter.notifyDataSetChanged();
        }
        homepageFragmentBinding.tlHomepageAllbutton.setupWithViewPager(homepageFragmentBinding.vpHomepageAllpage);
        homepageFragmentBinding.setHomepageModel(new HomepageModel());

        homepageFragmentBinding.vpHomepageAllpage.setCurrentItem(1);
        return homepageFragmentBinding.getRoot();
    }

    @Override
    public void setPresenter(HomepageContract.Persenter presenter) {
        mPersenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.swp_homepage_refresh);

        srl.post(() -> srl.setRefreshing(active));
    }

    class AllpageAdapter extends FragmentStatePagerAdapter {

        public AllpageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                RecommendpageFragment recommendpageFragment = new RecommendpageFragment();
                new RecommendpagePresenter(recommendpageFragment);
                return recommendpageFragment;
            } else return new LivepageFragment();
        }

        //必须要重写这句,tablayout和viewpage关联时会删除之前的tabitem,重新调用这个方法 弄新标题
        @Override
        public CharSequence getPageTitle(int position) {
            return stringArray[position];
        }

        @Override
        public int getCount() {
            return stringArray.length;
        }
    }
}
