package android.bilibili.com.bilibiliandroid.net;

import android.app.Activity;
import android.app.Dialog;
import android.bilibili.com.bilibiliandroid.R;
import android.bilibili.com.bilibiliandroid.utils.LogUtils;
import android.content.Context;
import android.os.NetworkOnMainThreadException;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by zhangruiyu on 16/5/19.
 */
public abstract class ProgressSubscriber<T> extends Subscriber<T> {
    private Context context;
    private Dialog loadingDialog;

    public ProgressSubscriber(Activity context) {
        this.context = context;
    }

    public ProgressSubscriber() {
    }

    @Override
    public void onStart() {
        super.onStart();
        showProgressDialog();
    }



    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
            //网络出错
            LogUtils.e(e);
        } else if(e instanceof NetworkOnMainThreadException){
            //在主线程访问网络了
            LogUtils.e(e);
        }else{
            LogUtils.e(e);
        }
        dismissProgressDialog();
    }

    private void dismissProgressDialog() {
        if (context != null && loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    private void showProgressDialog() {

        if (context != null) {
         //   loadingDialog = createLoadingDialog(context);
          //  loadingDialog.show();
        }

    }

   /*    public static Dialog createLoadingDialog(Context context) {
     RotateAnimation anim = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(-1);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(700);

        View inflate = View.inflate(context, R.layout.dialog_loading, null);
        ImageView iv_dialogloading_img = (ImageView) inflate.findViewById(R.id.iv_dialogloading_img);
        TextView tipTextView = (TextView) inflate
                .findViewById(R.id.tv_dialog_loading_loadmessage);// 提示文字
        tipTextView.setText("请各位骚猪稍等");// 设置加载信息
        // 加载动画
        iv_dialogloading_img.startAnimation(anim);
       *//* Animation animation = AnimationUtils.loadAnimation(
                mactivity2, R.anim.loading_animation);
        iv_dialogloading_img.startAnimation(animation);*//*
        Dialog loadingDialog = new Dialog(context, R.style.TranslucentBackground);
        //    loadingDialog.setCanceledOnTouchOutside(Constants.loadingDialogCancelable);
        //  loadingDialog.setCancelable(Constants.loadingDialogCancelable);// 不可以用“返回键”取消
        // Constants.loadingDialogCancelable = false;
        loadingDialog.setContentView(inflate);
        return loadingDialog;
    }
*/
}
