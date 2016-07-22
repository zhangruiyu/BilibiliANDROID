package android.bilibili.com.bilibiliandroid.modular.recommendpage;

import android.bilibili.com.bilibiliandroid.R;
import android.bilibili.com.bilibiliandroid.databinding.RecommendAdapteritemBinding;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.data.RecommendItem;
import android.bilibili.com.bilibiliandroid.utils.LogUtils;
import android.bilibili.com.bilibiliandroid.utils.UIUtils;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ZRY on 2016/7/21.
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendAdapterHolder> {

    private ArrayList<RecommendItem.ResultEntity> datas = new ArrayList<>();
    private RecommendpageContract.Persenter mPersenter;

    private RecommendAdapter() {
        throw new RuntimeException("我丢你雷姆啊");
    }

    public RecommendAdapter(List<RecommendItem.ResultEntity> resultEntity, RecommendpageContract.Persenter mPersenter) {
        this.mPersenter = mPersenter;
        replace(resultEntity);
    }

    @Override
    public RecommendAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecommendAdapterHolder recommendAdapterHolder = new RecommendAdapterHolder(UIUtils.inflate(R.layout.recommend_adapteritem), mPersenter);
        return recommendAdapterHolder;
    }

    @Override
    public void onBindViewHolder(RecommendAdapterHolder holder, int position) {
        holder.refreshView(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void replace(List<RecommendItem.ResultEntity> sources) {
        this.datas.clear();
        this.datas.addAll(sources);
        notifyDataSetChanged();
    }

    class RecommendAdapterHolder extends RecyclerView.ViewHolder {

        private final RecommendAdapteritemBinding recommendAdapteritemBinding;
        private RecommendpageContract.Persenter mPersenter;

        public RecommendAdapterHolder(View itemView, RecommendpageContract.Persenter mPersenter) {
            super(itemView);
            this.mPersenter = mPersenter;
            recommendAdapteritemBinding = RecommendAdapteritemBinding.bind(itemView);
        }

        ObservableList<RecommendItem.ResultEntity.BodyEntity> bodyEntities = new ObservableArrayList<>();

        public void refreshView(RecommendItem.ResultEntity resultEntity) {
            bodyEntities.clear();
            if ("recommend".equals(resultEntity.type)) {
                bodyEntities.addAll(resultEntity.body);
                recommendAdapteritemBinding.setRecommenditem(bodyEntities);
                //宽度是高度的几倍
                // int percent = bodyEntity.width / bodyEntity.height;
                ImageView[] imageViews = {recommendAdapteritemBinding.ivRecommendContentOne, recommendAdapteritemBinding.ivRecommendContentTwo
                        , recommendAdapteritemBinding.ivRecommendContentThree
                        , recommendAdapteritemBinding.ivRecommendContentFour};
                for (int i = 0; i < resultEntity.body.size(); i++) {
                    RecommendItem.ResultEntity.BodyEntity bodyEntity = resultEntity.body.get(i);
                    Glide.with(itemView.getContext())
                            .load(bodyEntity.cover)/*.placeholder(R.drawable.bili_default_image_tv)*/
                            .into(imageViews[i]);
                }
                RecommendItemActionHandler itemActionHandler =
                        new RecommendItemActionHandler();
                recommendAdapteritemBinding.setType(resultEntity.type);
                recommendAdapteritemBinding.setRecommenditem(bodyEntities);
                recommendAdapteritemBinding.setActionHandler(itemActionHandler);
                recommendAdapteritemBinding.executePendingBindings();
            }

        }


    }

    public class RecommendItemActionHandler {
        /* public RecommendItemActionHandler(RecommendpageContract.Persenter mPersenter) {
         }*/
        public void clickRecommend(RecommendItem.ResultEntity.BodyEntity bodyEntity) {
            mPersenter.clickRecommend(bodyEntity);
        }

        public void clickMore(String type) {
            mPersenter.clickMore(type);
        }
    }
}
