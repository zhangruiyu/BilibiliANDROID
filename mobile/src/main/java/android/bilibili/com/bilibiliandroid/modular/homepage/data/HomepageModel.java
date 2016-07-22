package android.bilibili.com.bilibiliandroid.modular.homepage.data;

import android.databinding.ObservableBoolean;

/**
 * Created by ZRY on 2016/7/20.
 */
public class HomepageModel {
   public ObservableBoolean isrefresh = new ObservableBoolean();

    public void setIsrefresh(boolean isrefresh) {
        this.isrefresh.set(isrefresh);
    }
}
