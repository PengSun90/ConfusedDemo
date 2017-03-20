package com.example.office.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.example.office.util.Base64;
import com.example.office.util.PreferenceUtils;

/**
 * Created by shanqiu on 2016/7/10.
 */
public final class GlideService {

    public static final int WIDTH_750_LIMIT = 750;
    public static final int WIDTH_360_LIMIT = 360;
    public static final int WIDTH_120_LIMIT = 120;
    public static final int QUALITY_50_PERCENT = 50;
    public static final String SUFFIX_WEBP = ".webp";
    public static final String SUFFIX_JPEG = ".jpg";

    private static final String TEST_DATA = "data:image/webp;base64,UklGRlAAAABXRUJQVlA4WAoAAAAQAAAADwAADwAAQUxQSBIAAAABBxAR/Q8ABOH/3EFE/1MDAABWUDggGAAAADABAJ0BKhAAEAACACYlpAADcAD+/PQAAA==";

    public static void load(Context context, String url, ImageView target) {
        load(context, url, 0, 0, target);
    }

    public static void load(Context context, String url, int placeHolder, int error, ImageView target) {
        load(context, url, placeHolder, error, DiskCacheStrategy.SOURCE, target);
    }

    public static void load(Context context, String url, int placeHolder, int error, DiskCacheStrategy strategy, ImageView target) {
        Glide.with(context).load(url).placeholder(placeHolder).error(error).crossFade().diskCacheStrategy(strategy).into(target);
    }

    public static void loadFitCenter(Context context, String url, int placeHolder, int error, ImageView target) {
        url = getWebpSupportUrl(url);

        Glide.with(context).load(url).placeholder(placeHolder).error(error).fitCenter().into(target);
    }

    public static void loadCrossFade(Context context, String url, int placeHolder, int error, ImageView target) {
        url = getWebpSupportUrl(url);
        Glide.with(context).load(url).placeholder(placeHolder).error(error).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.IMMEDIATE).crossFade().fitCenter().into(target);
    }

    public static void loadOverride(Context context, String url, int placeHolder, int error, int width, int height, ImageView target) {
        url = getWebpSupportUrl(url);
        Glide.with(context).load(url).placeholder(placeHolder).error(error).override(width, height).into(target);
    }

    public static void loadOptimized(Context context, String url, ImageView target) {
        loadOptimized(context, url, 0, 0, target);
    }

    public static void loadOptimized(Context context, String url, int placeHolder, int error, ImageView target) {
        loadOptimized(context, url, placeHolder, error, WIDTH_750_LIMIT, target);
    }

    public static void loadOptimized(Context context, String url, int placeHolder, int error, int maxWidth, ImageView target) {
        loadOptimized(context, url, placeHolder, error, maxWidth, QUALITY_50_PERCENT, target);
    }

    public static void loadOptimized(Context context, String url, int placeHolder, int error, int maxWidth, int quality, ImageView target) {
        loadOptimized(context, url, SUFFIX_WEBP, placeHolder, error, maxWidth, quality, target);
    }

    public static void loadOptimized(Context context, String url, String suffix, int placeHolder, int error, int maxWidth, int quality, ImageView target) {
        String webpUrl = getWebpSupportUrl(url, suffix, maxWidth, quality);
        load(context, webpUrl, placeHolder, error, target);
    }

    public static void loadTransform(Context context, String url, int placeHolder, int error, ImageView target, BitmapTransformation bitmapTransformation) {
        String webpUrl = getWebpSupportUrl(url);
        Glide.with(context).load(webpUrl).placeholder(placeHolder).error(error).crossFade().transform(bitmapTransformation).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(target);
    }

    public static void loadCircleImage(final Context context, String url, int placeHolder, int error, DiskCacheStrategy strategy, final ImageView target) {
        String webpUrl = getWebpSupportUrl(url);
        Glide.with(context).load(webpUrl).asBitmap().centerCrop().dontAnimate().transform(new CircleTransform(context)).into(target);
    }

    /**
     * CHECK
     *
     * @return
     */
    public static boolean isWebpSupport() {
        boolean isWebSupport = PreferenceUtils.shareInstance().isWebpSupport();
        if (!isWebSupport) {
            int retryCnt = PreferenceUtils.shareInstance().getWebpRetryCnt();
            if (retryCnt > 3) return isWebSupport;
            try {
                String dataStr = "";
                int index = TEST_DATA.indexOf("base64,");
                if (index != -1) {
                    dataStr = TEST_DATA.substring(index + 7);
                    byte[] imageData = Base64.decode(dataStr);
                    if (imageData != null) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                        if (bmp != null) {
                            if (bmp.getWidth() == 16 && bmp.getHeight() == 16) {
                                isWebSupport = true;
                                PreferenceUtils.shareInstance().setWebpSupport(isWebSupport);
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!isWebSupport) {
            PreferenceUtils.shareInstance().addWebpRetryCnt();
        }

        return isWebSupport;
    }

    public static boolean checkAliyunOsUrl(String url) {
        if (TextUtils.isEmpty(url) || !url.startsWith("http")) return false;
        // url support {{xxx}}img.yangkeduo.com, {{xxx}}.img-cn-shanghai.aliyuncs.com

        return url.contains("img.yangkeduo.com") || url.contains("img-cn-shanghai.aliyuncs.com");
    }

    public static boolean checkIsWebpUrl(String url) {
        if (TextUtils.isEmpty(url)) return false;
        return url.endsWith(SUFFIX_WEBP);
    }

    public static String getWebpSupportUrl(String url) {
        return getWebpSupportUrl(url, SUFFIX_WEBP, WIDTH_750_LIMIT, QUALITY_50_PERCENT);
    }

    public static String getWebpSupportUrl(String url, int maxWidth, int quality) {
        return getWebpSupportUrl(url, SUFFIX_WEBP, maxWidth, quality);
    }

    public static String getWebpSupportUrl(String url, String suffix, int maxWidth, int quality) {
        if (!isWebpSupport() || !checkAliyunOsUrl(url) || checkIsWebpUrl(url)) {
            return url;
        }

        StringBuilder sb = new StringBuilder(url);
        sb.append("@");

        sb.append(maxWidth + "w");
        sb.append("_");
        sb.append("1l");
        sb.append("_");
        sb.append(quality + "Q");

        sb.append(suffix);
        return sb.toString();
    }
}
