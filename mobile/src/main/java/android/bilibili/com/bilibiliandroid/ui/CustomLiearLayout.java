package android.bilibili.com.bilibiliandroid.ui;

import android.bilibili.com.bilibiliandroid.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by ZRY on 2016/7/22.
 */
public class CustomLiearLayout extends LinearLayout {
    public CustomLiearLayout(Context context) {
        this(context,null);
    }

    public CustomLiearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomLiearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundResource(R.drawable.recycleview_touchbg);
    }
}
