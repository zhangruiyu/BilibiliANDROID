package android.bilibili.com.bilibiliandroid.modular.recommendpage;

import android.bilibili.com.bilibiliandroid.R;
import android.bilibili.com.bilibiliandroid.base.BaseFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ZRY on 2016/7/20.
 */
public class RecommendpageFragment extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setText("ahha");
        textView.setTextColor(getResources().getColor(R.color.action_blue));
        return textView;
    }
}
