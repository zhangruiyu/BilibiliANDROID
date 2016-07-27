package android.bilibili.com.bilibiliandroid.ui;

import android.bilibili.com.bilibiliandroid.MainActivity;
import android.bilibili.com.bilibiliandroid.R;
import android.bilibili.com.bilibiliandroid.modular.livepage.LiveBannerAdapter;
import android.bilibili.com.bilibiliandroid.modular.livepage.data.LiveIndex;
import android.bilibili.com.bilibiliandroid.utils.DrawableUtils;
import android.bilibili.com.bilibiliandroid.utils.LogUtils;
import android.bilibili.com.bilibiliandroid.utils.UIUtils;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by ZRY on 2016/7/25.
 */
public class BilibiliBannerViewpager extends RelativeLayout {

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private LiveBannerAdapter mAdapter;
    private AutoRunningTask autoRunningTask = new AutoRunningTask();

    public BilibiliBannerViewpager(Context context) {
        this(context, null);
    }

    public BilibiliBannerViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewPager = new ViewPager(context);
        viewPager.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2px(120)));
        linearLayout = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, UIUtils.dip2px(15));
        layoutParams.rightMargin = UIUtils.dip2px(10);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        linearLayout.setLayoutParams(layoutParams);
        //   linearLayout.setBackgroundResource(R.color.bg_blue);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        viewPager.setOnTouchListener(onTouchListener);
        addView(viewPager);
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

        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = getContext().getTheme();
        theme.resolveAttribute(R.attr.bannerSelectColor, typedValue, true);
        GradientDrawable pointPressed = createPointPressed(typedValue.data);
        GradientDrawable pointNormal = createPointPressed(getResources().getColor(R.color.white));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(UIUtils.dip2px(5), UIUtils.dip2px(5));
        params.leftMargin = UIUtils.dip2px(7);
        for (int i = 0; i < mAdapter.getDataCount(); i++) {
            ImageView point = new ImageView(getContext());
            //必须每次都创建drawble 要不每个点颜色都会一致
            point.setBackgroundDrawable(DrawableUtils.createStateListDrawable(pointNormal, pointPressed));
            point.setLayoutParams(params);
            linearLayout.addView(point);
            point.setEnabled(i == 0);
        }
        autoRunningTask.start();
    }

    private GradientDrawable createPointPressed(int backgroundColor) {
        int strokeWidth = 5;
        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setSize(UIUtils.dip2px(strokeWidth), UIUtils.dip2px(strokeWidth));
        gd.setColor(backgroundColor);
        gd.setShape(GradientDrawable.OVAL);
        gd.setCornerRadius(backgroundColor);
        return gd;
    }


    public void relaceBanner(ArrayList<LiveIndex.DataEntity.BannerEntity> resultEntity) {
        if (mAdapter != null) {
            mAdapter.relaceData(resultEntity);
            addPoints();
        }

    }

    class AutoRunningTask {
        long delayMillis = 3000;
        boolean autoRun = false;
        private Observable<Long> delayOb;
        private Subscription subscribe;

        public void start() {
            if (!autoRun) {
                delayOb = getDelayOb();
                autoRun = true;
                subscribe = delayOb.subscribe(new BannerRun());

            }
        }

        public void stop() {
            if (autoRun) {
                autoRun = false;
                if (subscribe != null && !subscribe.isUnsubscribed()) {
                    subscribe.unsubscribe();
                    delayOb = null;
                }
            }
        }


        class BannerRun implements Action1<Long> {

            @Override
            public void call(Long aBoolean) {
                int currentItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(++currentItem, true);// 平滑的滑动的指定的条目
                //当前的指示点设置高亮
                int myindex = currentItem % mAdapter.getDataCount();
                linearLayout.getChildAt(myindex).setEnabled(true);
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    if (linearLayout.getChildAt(i) != null && i != myindex) {
                        linearLayout.getChildAt(i).setEnabled(false);
                    }
                }
            }
        }


        public Observable<Long> getDelayOb() {
            return Observable.interval(delayMillis, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread());
        }
    }

    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (autoRunningTask != null) {
                        autoRunningTask.stop();
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:  //取消的事件
                case MotionEvent.ACTION_UP:
                    int i = 0;
                    if (autoRunningTask != null) {
                        autoRunningTask.start();
                    }
            }
            return false;
        }
    };
}