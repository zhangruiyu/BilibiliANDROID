package android.bilibili.com.bilibiliandroid.utils;

import android.bilibili.com.bilibiliandroid.BilibiliApplication;
import android.bilibili.com.bilibiliandroid.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


/**
 * 专门处理界面相关的工具类
 *
 * @author itcast
 */
public class UIUtils {
    private static Toast makeText;

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */

    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static void showToast(int stringId) {
        // Toast长时间轮流显示问题
        if (makeText == null) {
            makeText = Toast.makeText(getContext(), stringId, Toast.LENGTH_SHORT);
            makeText.setGravity(Gravity.TOP | Gravity.CENTER,
                    makeText.getXOffset() / 2, 400);
        } else {
            makeText.setText(getString(stringId));
        }
        makeText.show();
    }

    public static void showToast(String str) {
        // Toast长时间轮流显示问题
        if (makeText == null) {
            makeText = Toast.makeText(getContext(), str, Toast.LENGTH_SHORT);
            makeText.setGravity(Gravity.TOP | Gravity.CENTER,
                    makeText.getXOffset() / 2, 400);
        } else {
            makeText.setText(str);
        }
        makeText.show();
    }

    public static Context getContext() {
        return BilibiliApplication.getApplication().getApplicationContext();
    }

    /**
     * 返回资源文件下,字符数组
     *
     * @param id 资源id
     * @return
     */
    public static String[] getStringArray(int id) {
        return getResource().getStringArray(id);
    }

    public static String getString(int id) {
        return getResource().getString(id);
    }

    public static Resources getResource() {
        return getContext().getResources();
    }

    public static Drawable getDrawable(int id) {
        return getResource().getDrawable(id);

    }

    /**
     * 根据资源id  返回一个颜色
     *
     * @param id
     * @return
     */
    public static int getColor(int id) {
        return getResource().getColor(id);
    }

    /**
     * 创建View对象
     *
     * @param layoutId 布局id
     * @return
     */
    public static View inflate(int layoutId) {
        return View.inflate(getContext(), layoutId, null);
    }

}
