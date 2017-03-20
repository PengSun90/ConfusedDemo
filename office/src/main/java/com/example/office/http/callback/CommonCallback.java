package com.example.office.http.callback;

import com.example.office.http.manager.HttpManager;
import com.example.office.util.ClassUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Response;

import java.lang.reflect.Type;


/**
 * Created by YCY on 16/5/5.
 */
public abstract class CommonCallback<T> extends BaseCallback {
    /**
     * code >= 200 && code < 300
     *
     * @param code     可以不处理该code
     * @param response 已经解析过的响应数据
     */
    public abstract void onResponseSuccess(int code, T response);

    @Override
    protected void parseNetworkResponse(Response response, Object extra) throws Exception {
        String responseStr = response.body().string();
        Type type = ClassUtils.getType(CommonCallback.this.getClass());
        final int code = response.code();

        T bean;
        if ("class java.lang.String".equals(type.toString())) {
            bean = (T) responseStr;
        } else {
            bean = new Gson().fromJson(responseStr, type);
        }
        final T final_bean = bean;


        HttpManager.getHttpManager().getHandler().post(new Runnable() {
            @Override
            public void run() {
                onResponseSuccess(code, final_bean);
            }
        });

    }
}
