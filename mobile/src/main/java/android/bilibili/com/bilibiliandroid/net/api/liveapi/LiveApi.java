package android.bilibili.com.bilibiliandroid.net.api.liveapi;

import android.bilibili.com.bilibiliandroid.base.BilibiliData;
import android.bilibili.com.bilibiliandroid.base.BilibiliResult;
import android.bilibili.com.bilibiliandroid.modular.livepage.data.LiveIndex;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by zhangruiyu on 16/5/19.
 */
public interface LiveApi {
    @GET("AppIndex/home?_device=android&_hwid=76fb87addd042ab4&_ulv=5000&access_key=7a2e7c036ad0de5d209b0f1343dcf825&appkey=1d8b6e7d45233436&build=422000&mobi_app=android&platform=android&scale=xxhdpi&sign=76e58a014dec4bc7ffa026574a123887")
    Observable<BilibiliData<LiveIndex.DataEntity>> getIndexRx();
}
