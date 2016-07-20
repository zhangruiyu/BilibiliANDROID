package android.bilibili.com.bilibiliandroid.net;

import android.bilibili.com.bilibiliandroid.base.HttpResult;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.data.RecommendItem;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by zhangruiyu on 16/5/19.
 */
public interface Api {
    @GET("x/show/old?access_key=8a9d4dc77adc192a11b86fb639c1fabe&appkey=1d8b6e7d45233436&build=422000&channel=huawei&mobi_app=android&platform=android&screen=xxhdpi&ts=1468999486000&sign=9d229d1445748ece9e1875518466e4a2")
    Observable<HttpResult< List<RecommendItem.ResultEntity>>> getRecomendList();
}
