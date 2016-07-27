package android.bilibili.com.bilibiliandroid.modular.homepage;

import android.bilibili.com.bilibiliandroid.R;
import android.bilibili.com.bilibiliandroid.base.BaseFragment;
import android.bilibili.com.bilibiliandroid.base.BaseHomepageFragment;
import android.bilibili.com.bilibiliandroid.databinding.HomepageFragmentBinding;
import android.bilibili.com.bilibiliandroid.modular.attention.AttentionpageFragment;
import android.bilibili.com.bilibiliandroid.modular.discover.DiscoverpageFragment;
import android.bilibili.com.bilibiliandroid.modular.favourite.FavoriteFragment;
import android.bilibili.com.bilibiliandroid.modular.homepage.data.HomepageModel;
import android.bilibili.com.bilibiliandroid.modular.livepage.LivepageFragment;
import android.bilibili.com.bilibiliandroid.modular.livepage.LivepagePresenter;
import android.bilibili.com.bilibiliandroid.modular.newdramapage.NewdramapageFragment;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.RecommendpageFragment;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.RecommendpagePresenter;
import android.bilibili.com.bilibiliandroid.modular.subarea.SubareapageFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ZRY on 2016/7/19.
 */
public class HomepageFragment extends BaseFragment implements HomepageContract.View {
    private HomepageContract.Persenter mPersenter;
    HomepageModel homepageModel = new HomepageModel();
    private AllpageAdapter allpageAdapter;
    private String[] stringArray;
    Class[] allpageClasses = {LivepageFragment.class, RecommendpageFragment.class, NewdramapageFragment.class, SubareapageFragment.class, AttentionpageFragment.class, DiscoverpageFragment.class};
    private HomepageFragmentBinding homepageFragmentBinding;

    public static HomepageFragment newInstance() {
        return new HomepageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homepageFragmentBinding = HomepageFragmentBinding.inflate(inflater, container, false);
        stringArray = getResources().getStringArray(R.array.homepage_allbutton);

        if (allpageAdapter == null) {
            allpageAdapter = new AllpageAdapter(getChildFragmentManager());
            homepageFragmentBinding.vpHomepageAllpage.setAdapter(allpageAdapter);
        } else {
            allpageAdapter.notifyDataSetChanged();
        }
        homepageFragmentBinding.tlHomepageAllbutton.setupWithViewPager(homepageFragmentBinding.vpHomepageAllpage);
        homepageFragmentBinding.setHomepageModel(homepageModel);
        homepageFragmentBinding.setHomepagePersenter(mPersenter);
    //    homepageFragmentBinding.vpHomepageAllpage.setOffscreenPageLimit(stringArray.length);
        homepageFragmentBinding.vpHomepageAllpage.setCurrentItem(1);
        return homepageFragmentBinding.getRoot();
    }

    @Override
    public void setPresenter(HomepageContract.Persenter presenter) {
        mPersenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        homepageModel.setIsrefresh(true);
    }

    @Override
    public void onloadViewpagerData() {
        if (homepageFragmentBinding.vpHomepageAllpage.getCurrentItem() <= 1) {
            allpageAdapter.getItem(1).onloadData();
        }
    }

    RecommendpageFragment recommendpageFragment = new RecommendpageFragment();
    RecommendpagePresenter recommendpagePresenter = new RecommendpagePresenter(recommendpageFragment, homepageModel);

    LivepageFragment livepageFragment = new LivepageFragment();
    LivepagePresenter livepagePresenter = new LivepagePresenter(livepageFragment, homepageModel);

    class AllpageAdapter extends FragmentStatePagerAdapter {

        public AllpageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public BaseHomepageFragment getItem(int position) {
            if (position == 0) {
                return livepageFragment;
            }
            if (position == 1) {
                return recommendpageFragment;
            } else return new FavoriteFragment();
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
