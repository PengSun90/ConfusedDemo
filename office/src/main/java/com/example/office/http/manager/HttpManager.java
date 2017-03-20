package com.example.office.http.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.office.app.BaseApplication;
import com.example.office.http.callback.BaseCallback;
import com.example.office.http.entity.FileProps;
import com.example.office.util.FileUtils;
import com.example.office.util.PreferenceUtils;
import com.example.office.util.StreamUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Created by YCY on 16/5/4.
 */
public class HttpManager {
    private static HttpManager httpManager;
    private OkHttpClient okHttpClient;
    private Handler handler;
    private static final int CONNECTTIME_OUT = 10;//s
    private static final int WRITETIME_OUT = 10;//s
    private static final int READTIME_OUT = 60;//s

    private HttpManager() {
        okHttpClient = new OkHttpClient();
        /**
         * 设置连接的超时时间
         */
        okHttpClient.setConnectTimeout(CONNECTTIME_OUT, TimeUnit.SECONDS);
        /**
         * 设置响应的超时时间
         */
        okHttpClient.setWriteTimeout(WRITETIME_OUT, TimeUnit.SECONDS);
        /**
         * 请求的超时时间
         */
        okHttpClient.setReadTimeout(READTIME_OUT, TimeUnit.SECONDS);
        /**
         * 允许使用Cookie
         */
        okHttpClient.setCookieHandler(new CookieManager(PersistentCookieStoreManager.getInstance(BaseApplication.getContext()),
                CookiePolicy.ACCEPT_ALL));
        /**
         * 获取主线程的handler
         */
        handler = new Handler(Looper.getMainLooper());
    }

    public Handler getHandler() {
        return handler;
    }

    public static HttpManager getHttpManager() {
        if (httpManager == null) {
            synchronized (HttpManager.class) {
                if (httpManager == null) {
                    httpManager = new HttpManager();
                }
            }
        }
        return httpManager;
    }

    public void getRequest(WeakReference<Context> contextWeakReference, String url,
                           BaseCallback callback, HashMap<String, String> headers) {
        Request request = new Request.Builder().url(url).headers(Headers.of(headers)).build();
        deliverRequest(contextWeakReference, callback, request);
    }

    public void postRequest(WeakReference<Context> contextWeakReference, String url, BaseCallback callback,
                            String params, HashMap<String, String> headers) {
        RequestBody requestBody = buildRequestBody(params);
        Request request = new Request.Builder().url(url).headers(Headers.of(headers)).post(requestBody).build();
        deliverRequest(contextWeakReference, callback, request);
    }

    public void postQueryStringRequest(WeakReference<Context> contextWeakReference, String url, BaseCallback callback,
                                       String params, HashMap<String, String> headers) {
        RequestBody requestBody = buildQueryStringRequestBody(params);
        Request request = new Request.Builder().url(url).headers(Headers.of(headers)).post(requestBody).build();
        deliverRequest(contextWeakReference, callback, request);
    }

    public void postRequest(WeakReference<Context> contextWeakReference, String url, BaseCallback callback,
                            HashMap<String, String> params, HashMap<String, String> headers) {
        RequestBody requestBody = buildRequestBody(params);
        Request request = new Request.Builder().url(url).headers(Headers.of(headers)).post(requestBody).build();
        deliverRequest(contextWeakReference, callback, request);
    }

    /**
     * 同步post
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public String callRequest(String url, HashMap<String, String> params, HashMap<String, String> headers) {
        RequestBody requestBody = buildRequestBody(params);
        Request request = new Request.Builder().url(url).headers(Headers.of(headers)).post(requestBody).build();
        Response response;
        String result = "";
        try {
            response = okHttpClient.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 同步get
     *
     * @param url
     * @param headers
     * @return
     */
    public String callRequest(String url, HashMap<String, String> headers) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        Request request = new Request.Builder().url(url).headers(Headers.of(headers)).build();
        Response response;
        String result = "";
        try {
            response = okHttpClient.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void putRequest(WeakReference<Context> contextWeakReference, String url, BaseCallback callback,
                           String params, HashMap<String, String> headers) {
        RequestBody requestBody = buildRequestBody(params);
        Request request = new Request.Builder().url(url).headers(Headers.of(headers)).put(requestBody).build();
        deliverRequest(contextWeakReference, callback, request);
    }

    public void deleteRequest(WeakReference<Context> contextWeakReference, String url, BaseCallback callback,
                              String params, HashMap<String, String> headers) {
        RequestBody requestBody = buildRequestBody(params);
        Request request = new Request.Builder().url(url).headers(Headers.of(headers)).delete(requestBody).build();
        deliverRequest(contextWeakReference, callback, request);
    }

    private RequestBody buildRequestBody(HashMap<String, String> params) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (params != null && params.size() > 0) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    jsonObject.put(entry.getKey(), entry.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buildRequestBody(jsonObject.toString());
    }

    private RequestBody buildRequestBody(String params) {
        MediaType mediaType = MediaType.parse("application/json");
        if (TextUtils.isEmpty(params)) {
            params = "";
        }
        return RequestBody.create(mediaType, params);
    }

    private RequestBody buildQueryStringRequestBody(String params) {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        if (TextUtils.isEmpty(params)) {
            params = "";
        }
        return RequestBody.create(mediaType, params);
    }

    /**
     * 处理请求结果的回调
     *
     * @param context
     * @param callback
     * @param request
     */
    private void deliverRequest(WeakReference<Context> context,
                                final BaseCallback callback, Request request) {
        deliverRequest(context, callback, request, null);
    }

    /**
     * 发送失败回调，UI线程中执行
     *
     * @param callback
     * @param e
     */
    public void sendFailCallback(final BaseCallback callback, final Exception e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }
        });
    }

    /**
     * 添加额外的header 如 Cookie，User-Agent
     *
     * @param headers
     * @return
     */
    public HashMap<String, String> addExtraHeaders(HashMap<String, String> headers) {
        if (headers == null) {
            headers = new HashMap<>();
        }

        //add user agent
        String tempUa = headers.get("User-Agent");
        if (TextUtils.isEmpty(tempUa)) tempUa = "";
        String cacheUA = PreferenceUtils.shareInstance().readWebviewUserAgent();
        String ua = tempUa + cacheUA;
        if (!TextUtils.isEmpty(ua)) {
            headers.put("User-Agent", ua);
        }
        return headers;
    }

    public void downloadRequest(WeakReference<Context> contextWeakReference, String url,
                                FileProps fileProps, BaseCallback callback) {
        Request request = new Request.Builder().url(url).build();
        if (fileProps == null)
            deliverRequest(contextWeakReference, callback, request);
        else
            deliverRequest(contextWeakReference, callback, request, fileProps);
    }

    public byte[] downloadRequest(String url) {
        if (TextUtils.isEmpty(url)) return null;
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                InputStream is = response.body().byteStream();
                byte[] bytes = StreamUtils.streamToByteArray(is);
                return bytes;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public File downloadRequest(String url, FileProps fileProps) {
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                InputStream is = response.body().byteStream();
                long contentLength = response.body().contentLength();
                File file = FileUtils.saveFile(is, contentLength, fileProps, this);
                return file;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void deliverRequest(final WeakReference<Context> contextWeakReference,
                                final BaseCallback callback, Request request, final FileProps fileProps) {
        try {
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, final IOException e) {
                    sendFailCallback(callback, e);
                }

                @Override
                public void onResponse(final Response response) throws IOException {
                    try {
                        if (callback != null) {
                            callback.parseNetworkResponse(contextWeakReference, response, fileProps);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sendFailCallback(callback, e);
                    }
                }
            });
        } catch (Exception e) {
            //java.lang.SecurityException: Permission denied (missing INTERNET permission?)
            e.printStackTrace();
        }

    }

    /**
     * 暂时只支持这四种，有需求另加
     * 不推荐用枚举（参见android官方文档）
     */
    public interface HttpMethod {
        int GET = 1;
        int POST = 2;
        int PUT = 3;
        int DELETE = 4;
    }
}
