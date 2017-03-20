package com.example.office.http.callback;


import com.example.office.http.entity.FileProps;
import com.example.office.http.manager.HttpManager;
import com.example.office.util.FileUtils;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by YCY on 16/5/5.
 */
public abstract class FileCallback extends BaseCallback {
    /**
     * 请求成功回调
     *
     * @param code
     * @param response
     */
    public abstract void onResponseSuccess(int code, File response);

    /**
     * 下载文件进度回调
     *
     * @param bytesRead     已经读取的文件大小
     * @param contentLength 文件总大小
     */
    public abstract void onProgressChanged(long bytesRead, long contentLength);

    private void parseToFile(final Response response, FileProps fileProps) throws IOException {
        InputStream is = response.body().byteStream();
        long contentLength = response.body().contentLength();
        final File file = FileUtils.saveFile(is, contentLength, fileProps, this);
        HttpManager.getHttpManager().getHandler().post(new Runnable() {
            @Override
            public void run() {
                onResponseSuccess(response.code(), file);
            }
        });
    }

    @Override
    protected void parseNetworkResponse(Response response, Object extra) throws Exception {
        if (extra instanceof FileProps) {
            FileProps fileProps = (FileProps) extra;
            parseToFile(response, fileProps);
        }
    }
}
