package com.example.office.image;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class RoundTransform extends BitmapTransformation {
    private static final int DEFAULT_STROKE_COLOR = 0XFFE0E0E0;

    private float radius = 0f;
    private float strokeWidth = 0;
    private int strokeColor;

    public RoundTransform(Context context) {
        this(context, 5);
    }

    public RoundTransform(Context context, int dp) {
        this(context, dp, 0.5f);
    }

    public RoundTransform(Context context, int dp, float strokeWidth) {
        this(context, dp, strokeWidth, DEFAULT_STROKE_COLOR);
    }

    public RoundTransform(Context context, int dp, float strokeWidth, int strokeColor) {
        super(context);
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        this.strokeWidth = Resources.getSystem().getDisplayMetrics().density * strokeWidth;
        this.strokeColor = strokeColor;
    }


    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }

    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);

        Paint mBitmapPaint = new Paint();
        mBitmapPaint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        mBitmapPaint.setAntiAlias(true);

        Paint mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(strokeColor);
        mBorderPaint.setStrokeWidth(strokeWidth);

        RectF mBorderRectF = new RectF(0, 0, source.getWidth(), source.getHeight());
        RectF mDrawableRectF = new RectF(strokeWidth, strokeWidth, mBorderRectF.width() - strokeWidth, mBorderRectF.height() - strokeWidth);

        canvas.drawRoundRect(mDrawableRectF, radius, radius, mBitmapPaint);
        canvas.drawRoundRect(mBorderRectF, radius, radius, mBorderPaint);
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName() + Math.round(radius);
    }
}