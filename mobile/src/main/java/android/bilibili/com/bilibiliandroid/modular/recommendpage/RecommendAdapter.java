package android.bilibili.com.bilibiliandroid.modular.recommendpage;

import android.bilibili.com.bilibiliandroid.R;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.data.RecommendItem;
import android.bilibili.com.bilibiliandroid.utils.UIUtils;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Created by ZRY on 2016/7/21.
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendAdapterHolder> {

    private List<RecommendItem.ResultEntity> datas;

    public RecommendAdapter(List<RecommendItem.ResultEntity> resultEntity) {
        setList(resultEntity);
    }

    @Override
    public RecommendAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecommendAdapterHolder recommendAdapterHolder = new RecommendAdapterHolder(UIUtils.inflate(R.layout.recommend_adapteritem));
        return recommendAdapterHolder;
    }

    @Override
    public void onBindViewHolder(RecommendAdapterHolder holder, int position) {
        holder.refreshView(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 :datas.size();
    //    return datas == null ? 0 : datas.size();
    }

    public void replace(List<RecommendItem.ResultEntity> resultEntity) {
        setList(resultEntity);
    }

    private void setList(List<RecommendItem.ResultEntity> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

     class RecommendAdapterHolder extends RecyclerView.ViewHolder {

         private final ImageView iv_recommend_content_one;

         public RecommendAdapterHolder(View itemView) {
            super(itemView);
             iv_recommend_content_one = (ImageView) itemView.findViewById(R.id.iv_recommend_content_one);

             itemView.findViewById(R.id.iv_recommend_content_one).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIUtils.showToast("1111111");
                }
            });
            itemView.findViewById(R.id.iv_recommend_head).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIUtils.showToast("1123421321");
                }
            });
        }

        public void refreshView(RecommendItem.ResultEntity resultEntity) {
            RecommendItem.ResultEntity.BodyEntity bodyEntity = resultEntity.body.get(0);
            ViewGroup.LayoutParams layoutParams = iv_recommend_content_one.getLayoutParams();
            layoutParams.height= UIUtils.dip2px(bodyEntity.height);
            layoutParams.width= bodyEntity.width;
            Glide.with(itemView.getContext())
                    .load(bodyEntity.cover)
                    .into(iv_recommend_content_one);
        }
    }
}
