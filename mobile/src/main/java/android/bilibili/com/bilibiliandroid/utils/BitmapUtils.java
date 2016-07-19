package android.bilibili.com.bilibiliandroid.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by ZRY on 2016/7/19.
 */
public class BitmapUtils {
    public static Bitmap small(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(0.7f, 0.7f); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }
}
