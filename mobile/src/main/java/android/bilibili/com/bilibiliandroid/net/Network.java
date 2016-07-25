package android.bilibili.com.bilibiliandroid.net;

import android.bilibili.com.bilibiliandroid.base.BilibiliData;
import android.bilibili.com.bilibiliandroid.base.BilibiliResult;
import android.bilibili.com.bilibiliandroid.modular.livepage.data.LiveIndex;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.data.RecommendItem;
import android.bilibili.com.bilibiliandroid.net.api.liveapi.LiveApi;
import android.bilibili.com.bilibiliandroid.net.api.recommend.RecommendApi;
import android.bilibili.com.bilibiliandroid.utils.LogUtils;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangruiyu on 16/5/19.
 */
public class Network {
//    private final Retrofit build;
   // private Api hxApi;

   /* private Network() {
        this.build = new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build())
                .baseUrl(URL.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        hxApi = this.build.create(Api.class);
    }*/
    private volatile static Retrofit appBilibiliRetrofit;
    //返回B站API
    public static Retrofit getAppBiliBili() {
        if (appBilibiliRetrofit == null) {
            synchronized (Retrofit.class) {
                if (appBilibiliRetrofit == null) {
                    appBilibiliRetrofit = new Retrofit.Builder().client(new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build())
                            .baseUrl("http://app.bilibili.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return appBilibiliRetrofit;
    }
    private volatile static Retrofit liveBilibiliRetrofit;

    //返回B站API
    public static Retrofit getLiveBilibiliRetrofit() {
        if (liveBilibiliRetrofit == null) {
            synchronized (Retrofit.class) {
                if (liveBilibiliRetrofit == null) {
                    liveBilibiliRetrofit = new Retrofit.Builder()
                            .baseUrl("http://live.bilibili.com/").client(new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return liveBilibiliRetrofit;
    }

    private static class SingletonHolder {
        private static final Network INSTANCE = new Network();
    }

    public static Network getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private final class HttpResultFuncC<T> implements Observable.Transformer<BilibiliResult<T>, T> {
        @Override
        public Observable<T> call(Observable<BilibiliResult<T>> httpResultObservable) {
            return httpResultObservable.map(tHttpResult -> {
                if (tHttpResult.code != 0) {//code不等于0
                    LogUtils.d(String.valueOf(tHttpResult.code));
                    throw new ApiException(tHttpResult.code);
                }
                return tHttpResult.result;
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    }
    private final class HttpDataFuncC<T> implements Observable.Transformer<BilibiliData<T>, T> {
        @Override
        public Observable<T> call(Observable<BilibiliData<T>> httpResultObservable) {
            return httpResultObservable.map(tHttpResult -> {
                if (tHttpResult.code != 0) {//code不等于0
                    LogUtils.d(String.valueOf(tHttpResult.code));
                    throw new ApiException(tHttpResult.code);
                }
                return tHttpResult.data;
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    }

    private final HttpResultFuncC httpResultFuncC = new HttpResultFuncC();
    private final HttpDataFuncC httpDataFuncC = new HttpDataFuncC();

    /**
     * 获取推荐页面的数据
     *
     * @return
     */
    public Observable<List<RecommendItem.ResultEntity>> getRecomendList() {
        Observable<BilibiliResult<List<RecommendItem.ResultEntity>>> recomendList = getAppBiliBili().create(RecommendApi.class).getRecomendList();
        return recomendList.compose(httpResultFuncC);
    }

    /**
     * 获取直播页面的数据
     *
     * @return
     */
    public Observable<LiveIndex.DataEntity> getLiveIndexList() {
        Observable<BilibiliData<LiveIndex.DataEntity>> indexRx = getLiveBilibiliRetrofit().create(LiveApi.class).getIndexRx();
        return indexRx.compose(httpDataFuncC);
    }

}
