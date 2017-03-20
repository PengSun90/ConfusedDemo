package com.example.office.util;

import android.content.Context;

import com.example.office.http.callback.ByteCallback;
import com.example.office.http.callback.CommonCallback;
import com.example.office.http.callback.FileCallback;
import com.example.office.http.entity.FileProps;
import com.example.office.http.manager.HttpManager;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by YCY on 16/5/5.
 */
public class HttpUtils {
    /**
     * get请求
     */
    public static <T> void get(WeakReference<Context> contextWeakReference, String url,
                               HashMap<String, String> headers, CommonCallback<T> callback) {
        HttpManager.getHttpManager().getRequest(contextWeakReference, url, callback,
                HttpManager.getHttpManager().addExtraHeaders(headers));
    }

    /**
     * post请求
     *
     * @param url
     * @param headers
     * @param params   json格式的字符串
     * @param callback
     */
    public static <T> void postString(WeakReference<Context> contextWeakReference, String url,
                                      HashMap<String, String> headers, String params, CommonCallback<T> callback) {
        HttpManager.getHttpManager().postRequest(contextWeakReference, url, callback, params,
                HttpManager.getHttpManager().addExtraHeaders(headers));
    }


    /**
     * post请求
     *
     * @param url
     * @param headers
     * @param params   querystring
     * @param callback
     */
    public static <T> void postQueryString(WeakReference<Context> contextWeakReference, String url,
                                           HashMap<String, String> headers, String params, CommonCallback<T> callback) {
        HttpManager.getHttpManager().postQueryStringRequest(contextWeakReference, url, callback, params,
                HttpManager.getHttpManager().addExtraHeaders(headers));
    }

    /**
     * post请求
     *
     * @param url
     * @param headers
     * @param params   HashMap
     * @param callback
     */
    public static <T> void postMap(WeakReference<Context> contextWeakReference, String url,
                                   HashMap<String, String> headers,
                                   HashMap<String, String> params, CommonCallback<T> callback) {
        HttpManager.getHttpManager().postRequest(contextWeakReference, url, callback, params,
                HttpManager.getHttpManager().addExtraHeaders(headers));
    }

    /**
     * 同步请求，直接返回结果 post
     *
     * @param url
     * @param headers
     * @param params
     * @return
     */
    public static String call(String url, HashMap<String, String> headers, HashMap<String, String> params) {
        try {
            return HttpManager.getHttpManager().callRequest(url, params, HttpManager.getHttpManager().addExtraHeaders(headers));
        } catch (Exception e) {
            //java.lang.SecurityException: Permission denied (missing INTERNET permission?)
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 同步请求，直接返回结果 get
     *
     * @param url
     * @param headers
     * @return
     */
    public static String call(String url, HashMap<String, String> headers) {
        try {
            return HttpManager.getHttpManager().callRequest(url, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * put请求
     *
     * @param url
     * @param headers
     * @param params   json格式的字符串
     * @param callback
     */
    public static <T> void put(WeakReference<Context> contextWeakReference, String url,
                               HashMap<String, String> headers,
                               String params, CommonCallback<T> callback) {
        HttpManager.getHttpManager().putRequest(contextWeakReference, url, callback, params,
                HttpManager.getHttpManager().addExtraHeaders(headers));
    }

    /**
     * delete请求
     *
     * @param url
     * @param headers
     * @param params   json格式的字符串
     * @param callback
     */
    public static <T> void delete(WeakReference<Context> contextWeakReference, String url,
                                  HashMap<String, String> headers,
                                  String params, CommonCallback<T> callback) {
        HttpManager.getHttpManager().deleteRequest(contextWeakReference, url, callback, params,
                HttpManager.getHttpManager().addExtraHeaders(headers));
    }

    /**
     * 下载文件存储到file
     */
    public static void download(WeakReference<Context> contextWeakReference, String url,
                                FileProps fileProps, FileCallback callback) {
        HttpManager.getHttpManager().downloadRequest(contextWeakReference, url, fileProps, callback);
    }

    /**
     * 下载文件存储到byte[]
     */
    public static void download(WeakReference<Context> contextWeakReference, String url,
                                ByteCallback callback) {
        HttpManager.getHttpManager().downloadRequest(contextWeakReference, url, null, callback);
    }

    /**
     * 同步，下载文件
     *
     * @param url
     * @return byte[] 在非UI线程中
     */
    public static byte[] downloadSync(String url) {
        return HttpManager.getHttpManager().downloadRequest(url);
    }

    /**
     * 同步，下载文件
     *
     * @param url
     * @return file 在非UI线程中
     */
    public static File downloadSync(String url, FileProps fileProps) {
        return HttpManager.getHttpManager().downloadRequest(url, fileProps);
    }

    /**
     * 不限制请求方法
     *
     * @param httpMethod
     * @param url
     * @param headers
     * @param params     json格式的字符串
     * @param callback
     */
    public static <T> void excuteRequest(WeakReference<Context> contextWeakReference,
                                         int httpMethod, String url,
                                         HashMap<String, String> headers,
                                         String params, CommonCallback<T> callback) {
        switch (httpMethod) {
            case HttpManager.HttpMethod.GET:
                get(contextWeakReference, url, headers, callback);
                break;
            case HttpManager.HttpMethod.POST:
                postString(contextWeakReference, url, headers, params, callback);
                break;
            case HttpManager.HttpMethod.PUT:
                put(contextWeakReference, url, headers, params, callback);
                break;
            case HttpManager.HttpMethod.DELETE:
                delete(contextWeakReference, url, headers, params, callback);
                break;
        }
    }
}
