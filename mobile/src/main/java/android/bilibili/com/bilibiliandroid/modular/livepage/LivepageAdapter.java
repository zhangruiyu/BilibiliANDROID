package android.bilibili.com.bilibiliandroid.modular.livepage;

import android.bilibili.com.bilibiliandroid.R;
import android.bilibili.com.bilibiliandroid.databinding.LivepageItemPrefectureBinding;
import android.bilibili.com.bilibiliandroid.modular.livepage.data.LiveIndex;
import android.bilibili.com.bilibiliandroid.ui.BilibiliBannerViewpager;
import android.bilibili.com.bilibiliandroid.utils.LogUtils;
import android.bilibili.com.bilibiliandroid.utils.UIUtils;
import android.databinding.adapters.LinearLayoutBindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ZRY on 2016/7/21.
 */
public class LivepageAdapter extends RecyclerView.Adapter<LivepageBaseHolder> {

    private LivepageContract.Persenter mPersenter;
    private LiveIndex.DataEntity mRources;
    private ArrayList<LiveIndex.DataEntity.BannerEntity> banner = new ArrayList<>();
    private List<LiveIndex.DataEntity.PartitionsEntity> partitions = new ArrayList<>();
    private List<LiveIndex.DataEntity.EntranceIconsEntity> entranceIcons = new ArrayList<>();
    private static final int ITEMTYPE_BANNER = 0;
    private static final int ITEMTYPE_PREFECTURE = 1;
    private static final int ITEMTYPE_NORMAL = 2;

    private LivepageAdapter() {
        throw new RuntimeException("我丢你雷姆啊");
    }

    public LivepageAdapter(LiveIndex.DataEntity dataEntity, LivepageContract.Persenter mPersenter) {
        this.mPersenter = mPersenter;
        replace(dataEntity);
    }

    @Override
    public LivepageBaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LivepageBaseHolder livepageAdapterHolder;
        if (viewType == ITEMTYPE_BANNER) {
            livepageAdapterHolder = new LivepageBannerHolder(mPersenter);
        } else if (viewType == ITEMTYPE_PREFECTURE) {
            livepageAdapterHolder = new LivepagePrefectureHolder(UIUtils.inflate(R.layout.livepage_item_prefecture), mPersenter);
        } else {
            livepageAdapterHolder = new LivepageBannerHolder(mPersenter);
        }
        return livepageAdapterHolder;
    }

    @Override
    public void onBindViewHolder(LivepageBaseHolder holder, int position) {
        holder.refreshView(mRources);
    }

    @Override
    public int getItemViewType(int position) {
        return position <= 1 ? position : ITEMTYPE_NORMAL;
    }


    @Override
    public int getItemCount() {
        return mRources == null ? 0 : 2;
    }

    public void replace(LiveIndex.DataEntity sources) {
        if (sources == null) {
            return;
        }
        mRources = sources;
        notifyDataSetChanged();
    }

    class LivepageBannerHolder extends LivepageBaseHolder {
        LiveBannerAdapter liveBannerAdapter = new LiveBannerAdapter(new ArrayList());
        private LivepageContract.Persenter mPersenter;
        private final BilibiliBannerViewpager bilibili_livepage_banner;

        public LivepageBannerHolder(LivepageContract.Persenter mPersenter) {
            super(UIUtils.inflate(R.layout.livepage_item_banner));
            this.mPersenter = mPersenter;
            bilibili_livepage_banner = (BilibiliBannerViewpager) itemView.findViewById(R.id.bilibili_livepage_banner);
            bilibili_livepage_banner.setAdapter(liveBannerAdapter);
            //   recommendAdapteritemBinding = RecommendAdapteritemBinding.bind(itemView);
        }


        @Override
        void refreshView(LiveIndex.DataEntity sources) {
            banner.clear();
            banner.addAll(sources.banner);
            banner.addAll(sources.banner);
            bilibili_livepage_banner.relaceBanner(banner);
        }
    }

    class LivepagePrefectureHolder extends LivepageBaseHolder {
        ImageView[] imageViews;
        private LivepageContract.Persenter mPersenter;
        private final LivepageItemPrefectureBinding livepageItemPrefectureBinding;

        public LivepagePrefectureHolder(View itemView, LivepageContract.Persenter mPersenter) {
            super(itemView);
            livepageItemPrefectureBinding = LivepageItemPrefectureBinding.bind(itemView);
            this.mPersenter = mPersenter;
            imageViews = new ImageView[]{livepageItemPrefectureBinding.ivLivepagePhonelive, livepageItemPrefectureBinding.ivLivepagePaintingZone,
                    livepageItemPrefectureBinding.ivLivepageMoeCurtilage, livepageItemPrefectureBinding.ivLivepageOnlineGame,
                    livepageItemPrefectureBinding.ivLivepageStandAloneGame, livepageItemPrefectureBinding.ivLivepageESports,
                    livepageItemPrefectureBinding.ivLivepageAllSale, livepageItemPrefectureBinding.ivLivepageAllLive
            };
        }


        @Override
        void refreshView(LiveIndex.DataEntity sources) {

            for (int i = 0; i < sources.entranceIcons.size(); i++) {
                LiveIndex.DataEntity.EntranceIconsEntity.EntranceIconEntity entrance_icon = sources.entranceIcons.get(i).entrance_icon;
                LogUtils.d(imageViews + "");
                    Glide.with(UIUtils.getContext()).load(entrance_icon.src).override(entrance_icon.width, entrance_icon.height).into(imageViews[i]);
            }

        }
    }
}
