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

import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Created by ZRY on 2016/7/21.
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendAdapterHolder> {

    private List<RecommendItem.ResultEntity> datas;
    private RecommendpageContract.Persenter mPersenter;

    private RecommendAdapter() {
        throw new RuntimeException("我丢你雷姆啊");
    }

    public RecommendAdapter(List<RecommendItem.ResultEntity> resultEntity, RecommendpageContract.Persenter mPersenter) {
        setList(resultEntity);
        this.mPersenter = mPersenter;
    }

    @Override
    public RecommendAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecommendAdapterHolder recommendAdapterHolder = new RecommendAdapterHolder(UIUtils.inflate(R.layout.recommend_adapteritem),mPersenter);
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

    public void replace(List<RecommendItem.ResultEntity> resultEntity) {
        setList(resultEntity);
    }

    private void setList(List<RecommendItem.ResultEntity> datas) {
        this.datas = datas;
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
                RecommendItem.ResultEntity.BodyEntity bodyEntity = resultEntity.body.get(0);
                LogUtils.d(itemView.getContext()+"hha");
                //宽度是高度的几倍
               // int percent = bodyEntity.width / bodyEntity.height;
                Glide.with(itemView.getContext())
                        .load(bodyEntity.cover)
                        .into(recommendAdapteritemBinding.ivRecommendContentOne);
                Glide.with(itemView.getContext())
                        .load(resultEntity.body.get(1).cover)
                        .into(recommendAdapteritemBinding.ivRecommendContentTwo);
                Glide.with(itemView.getContext())
                        .load(resultEntity.body.get(2).cover)
                        .into(recommendAdapteritemBinding.ivRecommendContentThree);
                Glide.with(itemView.getContext())
                        .load(resultEntity.body.get(3).cover)
                        .into(recommendAdapteritemBinding.ivRecommendContentFour);
            }

        }
    }
}
