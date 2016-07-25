package android.bilibili.com.bilibiliandroid.ui;

import android.bilibili.com.bilibiliandroid.R;
import android.bilibili.com.bilibiliandroid.modular.livepage.LiveBannerAdapter;
import android.bilibili.com.bilibiliandroid.modular.livepage.data.LiveIndex;
import android.bilibili.com.bilibiliandroid.utils.UIUtils;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by ZRY on 2016/7/25.
 */
public class BilibiliBannerViewpager extends RelativeLayout {

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private LiveBannerAdapter mAdapter;

    public BilibiliBannerViewpager(Context context) {
        this(context, null);
    }

    public BilibiliBannerViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewPager = new ViewPager(context);
        viewPager.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2px(150)));
        linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2px(150)));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

     //   addView(viewPager);
        addView(linearLayout);
    }

    public void setAdapter(LiveBannerAdapter adapter) {
        mAdapter = adapter;
        viewPager.setAdapter(adapter);
        addPoints();
    }

    private void addPoints() {
        if (mAdapter.getDataCount() <= 1) {
            return;
        }
        linearLayout.removeAllViews();
        for (int i = 0; i < mAdapter.getDataCount(); i++) {

            ImageView point = new ImageView(getContext());
            point.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(UIUtils.dip2px(9), UIUtils.dip2px(9));
            params.leftMargin = UIUtils.dip2px(7);
            point.setLayoutParams(params);
            linearLayout.addView(point);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
        }
    }


    public void relaceBanner(ArrayList<LiveIndex.DataEntity.BannerEntity> resultEntity) {
        if (mAdapter != null) {
            mAdapter.relaceData(resultEntity);
            addPoints();
        }

    }
}