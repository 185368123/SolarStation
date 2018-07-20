package com.shuorigf.solarstaition.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * author：clx on 16/7/13 14:41
 */

//    Glide是默认本地存储的，但重复加载的时候，是不需要重复访问网络。
//    下载的图片转圆形的方法
public class GlideCircleTransform extends BitmapTransformation {
    public GlideCircleTransform(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

//        int size = Math.min(source.getWidth(), source.getHeight());
//        int x = (source.getWidth() - size) / 2;
//        int y = (source.getHeight() - size) / 2;
//
//        // this could be acquired from the pool too
//        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

        int size = Math.max(source.getWidth(), source.getHeight());
        Bitmap squared = Bitmap.createScaledBitmap(source, size, size, true);

        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return result;
    }


    public String getId() {
        return getClass().getName();
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }

}