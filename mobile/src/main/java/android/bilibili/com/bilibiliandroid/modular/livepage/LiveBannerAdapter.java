package android.bilibili.com.bilibiliandroid.modular.livepage;

import android.bilibili.com.bilibiliandroid.modular.livepage.data.LiveIndex;
import android.bilibili.com.bilibiliandroid.utils.UIUtils;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by ZRY on 2016/7/25.
 */
public class LiveBannerAdapter extends PagerAdapter {
    ArrayList<LiveIndex.DataEntity.BannerEntity> resultEntity;

    public LiveBannerAdapter(ArrayList<LiveIndex.DataEntity.BannerEntity> resultEntity) {
        this.resultEntity = resultEntity;
    }

    // 指的当前数量
    @Override
    public int getCount() {
        //只有一页不需要辣妈多
        return resultEntity.size() <= 1 ? resultEntity.size() : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View v, Object o) {
        return v == o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final int index = position % resultEntity.size();
        ImageView imageView = new ImageView(UIUtils.getContext());
        Glide.with(UIUtils.getContext()).load(resultEntity.get(index).img).centerCrop().crossFade().into(imageView);
        //  imageView.setOnTouchListener(onTouchListener);
        container.addView(imageView);
        return imageView;
    }
}
