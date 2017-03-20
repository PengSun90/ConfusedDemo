package com.example.office.http.callback;


import com.example.office.http.manager.HttpManager;
import com.example.office.util.StreamUtils;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by YCY on 16/5/9.
 */
public abstract class ByteCallback extends BaseCallback {

    /**
     * 请求成功回调
     *
     * @param code
     * @param response
     */
    public abstract void onResponseSuccess(int code, byte[] response);

    @Override
    protected void parseNetworkResponse(Response response, Object extra) throws Exception {
        parseToByte(response);
    }

    private void parseToByte(final Response response) throws IOException {
        InputStream is = response.body().byteStream();
        final byte[] bytes = StreamUtils.streamToByteArray(is);
        HttpManager.getHttpManager().getHandler().post(new Runnable() {
            @Override
            public void run() {
                onResponseSuccess(response.code(), bytes);
            }
        });
    }
}
