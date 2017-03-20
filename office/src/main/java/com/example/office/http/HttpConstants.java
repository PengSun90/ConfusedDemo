package com.example.office.http;

import android.net.Uri;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/20.
 */

public class HttpConstants {

    public final String doMain = "http://10.77.17.62/test/HttpService";
//    public final String doMain = "http://192.168.87.1/test/HttpService";
    private static HttpConstants mInstance;

    public static synchronized HttpConstants getInstance() {
        if (null == mInstance) {
            mInstance = new HttpConstants();
        }
        return mInstance;
    }

    private String getApiDomain() {
        return doMain;
    }

    public String getLoginUrl(String name, String passWord) {
        return getApiDomain() + "/http.php?"+"name=" + Uri.encode(name, "UTF-8") + "&passWord=" + Uri.encode(passWord, "UTF-8");
    }

    /**
     * api请求头
     *
     * @return api请求头
     */
    public static HashMap<String, String> getRequestHeader() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
//        headers.put("Referer", "Android");
//        String access_token = PDDUser.getAccessToken();
//        String access_token = "";
//        headers.put("AccessToken", access_token);
        return headers;
    }
}
