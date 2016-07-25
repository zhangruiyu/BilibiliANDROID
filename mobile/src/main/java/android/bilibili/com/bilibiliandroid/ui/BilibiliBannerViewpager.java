package android.bilibili.com.bilibiliandroid.ui;

import android.bilibili.com.bilibiliandroid.R;
import android.bilibili.com.bilibiliandroid.modular.livepage.data.LiveIndex;
import android.bilibili.com.bilibiliandroid.utils.UIUtils;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by ZRY on 2016/7/25.
 */
public class BilibiliBannerViewpager extends RelativeLayout {

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private PagerAdapter mAdapter;

    public BilibiliBannerViewpager(Context context) {
        this(context, null);
    }

    public BilibiliBannerViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewPager = new ViewPager(context);
        viewPager.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,UIUtils.dip2px(150)));
        linearLayout = new LinearLayout(context);
        addView(viewPager);
    }

    public void setAdapter(PagerAdapter adapter) {
        mAdapter = adapter;
        viewPager.setAdapter(adapter);
        addPoints();
    }

    private void addPoints() {
        ImageView point = new ImageView(getContext());
        point.setBackgroundResource(R.drawable.point_selector);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(UIUtils.dip2px(9), UIUtils.dip2px(9));
        params.leftMargin = UIUtils.dip2px(7);
        point.setLayoutParams(params);
        linearLayout.addView(point);

        if (mAdapter.getCount() == 0) {
            point.setEnabled(true);
        } else {
            point.setEnabled(false);
        }
    }



}