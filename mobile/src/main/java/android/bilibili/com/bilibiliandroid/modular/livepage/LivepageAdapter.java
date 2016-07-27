package android.bilibili.com.bilibiliandroid.modular.livepage;

import android.bilibili.com.bilibiliandroid.R;
import android.bilibili.com.bilibiliandroid.modular.livepage.data.LiveIndex;
import android.bilibili.com.bilibiliandroid.ui.BilibiliBannerViewpager;
import android.bilibili.com.bilibiliandroid.utils.LogUtils;
import android.bilibili.com.bilibiliandroid.utils.UIUtils;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ZRY on 2016/7/21.
 */
public class LivepageAdapter extends RecyclerView.Adapter<LivepageAdapter.LivepageAdapterHolder> {

    private LivepageContract.Persenter mPersenter;
    private ArrayList<LiveIndex.DataEntity.BannerEntity> banner = new ArrayList<>();
    private List<LiveIndex.DataEntity.PartitionsEntity> partitions = new ArrayList<>();
    private List<LiveIndex.DataEntity.EntranceIconsEntity> entranceIcons = new ArrayList<>();

    private LivepageAdapter() {
        throw new RuntimeException("我丢你雷姆啊");
    }

    public LivepageAdapter(LiveIndex.DataEntity dataEntity, LivepageContract.Persenter mPersenter) {
        this.mPersenter = mPersenter;
        replace(dataEntity);
    }

    @Override
    public LivepageAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LivepageAdapterHolder recommendAdapterHolder = new LivepageAdapterHolder(UIUtils.inflate(R.layout.livepage_item_banner), mPersenter);
        return recommendAdapterHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(LivepageAdapterHolder holder, int position) {
        holder.refreshView(banner);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void replace(LiveIndex.DataEntity sources) {
        if (sources == null) {
            return;
        }
        banner.clear();
        partitions.clear();
        entranceIcons.clear();
        //再加一个数据不够
        banner.addAll(sources.banner);
        banner.addAll(sources.banner);
        partitions.addAll(sources.partitions);
        entranceIcons.addAll(sources.entranceIcons);
        notifyDataSetChanged();
    }

    class LivepageAdapterHolder extends RecyclerView.ViewHolder {
        LiveBannerAdapter liveBannerAdapter = new LiveBannerAdapter(new ArrayList());
        private LivepageContract.Persenter mPersenter;
        private final BilibiliBannerViewpager bilibili_livepage_banner;

        public LivepageAdapterHolder(View itemView, LivepageContract.Persenter mPersenter) {
            super(itemView);
            this.mPersenter = mPersenter;
            bilibili_livepage_banner = (BilibiliBannerViewpager) itemView.findViewById(R.id.bilibili_livepage_banner);
            bilibili_livepage_banner.setAdapter(liveBannerAdapter);
            //   recommendAdapteritemBinding = RecommendAdapteritemBinding.bind(itemView);
        }

        public void refreshView(ArrayList<LiveIndex.DataEntity.BannerEntity> resultEntity) {
            bilibili_livepage_banner.relaceBanner(resultEntity);
        }

    }


}
