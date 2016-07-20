package android.bilibili.com.bilibiliandroid.net;

import android.bilibili.com.bilibiliandroid.base.HttpResult;
import android.bilibili.com.bilibiliandroid.modular.recommendpage.data.RecommendItem;
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
    private final Retrofit build;
    private Api hxApi;

    private Network() {
        OkHttpClient build = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
        this.build = new Retrofit.Builder()
                .client(build)
                .baseUrl(URL.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        hxApi = this.build.create(Api.class);
    }

    private static class SingletonHolder {
        private static final Network INSTANCE = new Network();
    }

    public static Network getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private final class HttpResultFuncC<T> implements Observable.Transformer<HttpResult<T>, T> {
        @Override
        public Observable<T> call(Observable<HttpResult<T>> httpResultObservable) {
            return httpResultObservable.map(tHttpResult -> {
                if (tHttpResult.code != 0) {//code不等于0
                    LogUtils.d(String.valueOf(tHttpResult.code));
                    throw new ApiException(tHttpResult.code);
                }
                return tHttpResult.result;
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    }

    private final HttpResultFuncC httpResultFuncC = new HttpResultFuncC();

    /**
     * 获取推荐页面的数据
     *
     * @return
     */
    public Observable<List<RecommendItem.ResultEntity>> getRecomendList() {
        Observable<HttpResult<List<RecommendItem.ResultEntity>>> recomendList = hxApi.getRecomendList();
        return recomendList.compose(httpResultFuncC);
    }

}
