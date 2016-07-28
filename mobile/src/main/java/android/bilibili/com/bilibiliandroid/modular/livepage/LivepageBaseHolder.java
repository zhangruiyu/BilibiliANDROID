package android.bilibili.com.bilibiliandroid.modular.livepage;

import android.bilibili.com.bilibiliandroid.modular.livepage.data.LiveIndex;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by ZRY on 2016/7/28.
 */
public abstract class LivepageBaseHolder extends RecyclerView.ViewHolder {
    public LivepageBaseHolder(View itemView) {
        super(itemView);
    }

    abstract void refreshView(LiveIndex.DataEntity sources);
}
