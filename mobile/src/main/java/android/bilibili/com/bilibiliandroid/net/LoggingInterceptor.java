package android.bilibili.com.bilibiliandroid.net;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ZRY on 2016/7/20.
 */
class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();

        //可以添加公共参数 增加校验签名等
//            request = request.newBuilder().addHeader("header", "header").build();
        Log.d("retrofit request", request.url().toString());

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        String bodyString = response.body().string();
        Log.d("retrofit response",
                "request time " + (t2 - t1) / 1e6d + "ms\n" +
                        "request url " + response.request().url().toString() + "\n"
                        + "response body " + bodyString
        );

        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), bodyString))
                .build();
    }
}