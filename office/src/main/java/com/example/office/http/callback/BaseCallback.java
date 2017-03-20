package com.example.office.http.callback;

import android.content.Context;
import android.support.annotation.Nullable;

import com.example.office.http.entity.FileProps;
import com.example.office.http.entity.HttpError;
import com.example.office.http.manager.ApiDelayManager;
import com.example.office.http.manager.HttpManager;
import com.google.gson.Gson;
import com.squareup.okhttp.Response;

import java.lang.ref.WeakReference;


/**
 * Created by YCY on 16/5/5.
 */
public abstract class BaseCallback {
    /**
     * 请求失败回调
     *
     * @param e
     */
    public abstract void onFailure(Exception e);

    /**
     * 解析响应数据
     *
     * @param response
     * @param extra
     */
    protected abstract void parseNetworkResponse(Response response, Object extra) throws Exception;

    /**
     * code < 200 || code >= 300
     *
     * @param code
     * @param httpError HttpError实体
     */
    public abstract void onResponseError(int code, @Nullable HttpError httpError);

    public void parseNetworkResponse(final WeakReference<Context> contextWeakReference,
                                     final Response response, final FileProps fileProps) throws Exception {
//        if (response != null && response.isSuccessful()) {
        if(response == null){
            HttpManager.getHttpManager().getHandler().post(new Runnable() {
                @Override
                public void run() {
                    onFailure(new RuntimeException("HttpError"));
                }
            });
            return;
        }
        if (response.code() == 200) {
            parseNetworkResponse(response, fileProps);
        } else {
            final int code = response.code();
            final HttpError httpError = new Gson().fromJson(response.body().string(), HttpError.class);
            if (httpError == null) {
                HttpManager.getHttpManager().getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        onFailure(new RuntimeException("HttpError"));
                    }
                });
                return;
            }
            HttpManager.getHttpManager().getHandler().post(new Runnable() {
                @Override
                public void run() {
                    onResponseError(contextWeakReference, code, httpError);
                }
            });
        }
    }

    private void onResponseError(WeakReference<Context> contextWeakReference, int code,
                                 HttpError httpError) {
        ApiDelayManager apiDelayManager = ApiDelayManager.getInstance();
        boolean needApiDelay = apiDelayManager.isNeedApiDelay(httpError.getError_code());
        if (needApiDelay) {
            apiDelayManager.handleApiDelay(contextWeakReference, httpError.getError_sec());
        } else {
            onResponseError(code, httpError);
        }
    }
}
