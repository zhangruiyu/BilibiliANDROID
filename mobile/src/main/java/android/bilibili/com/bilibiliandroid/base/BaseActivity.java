package android.bilibili.com.bilibiliandroid.base;

import android.bilibili.com.bilibiliandroid.R;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by ZRY on 2016/7/19.
 */
public class BaseActivity extends RxAppCompatActivity {

    protected Toolbar toolbar;

    @Override
    public void setContentView(View view) {
        super.setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getDelegate().setContentView(layoutResID);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
