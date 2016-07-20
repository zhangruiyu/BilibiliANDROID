package android.bilibili.com.bilibiliandroid.modular.recommendpage.data;

import android.bilibili.com.bilibiliandroid.net.Network;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by ZRY on 2016/7/20.
 */
public class RecommendRepository {
    public Observable<List<RecommendItem.ResultEntity>> getRecommendItem() {
        return  Network.getInstance().getRecomendList();
    }
}
