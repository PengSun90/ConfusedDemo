package com.example.office.util;


import com.example.office.http.callback.FileCallback;
import com.example.office.http.entity.FileProps;
import com.example.office.http.manager.HttpManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static File saveFile(InputStream is, final long contentLength,
                                FileProps fileProps, Object extra) throws IOException {
        byte[] buf = new byte[1024];
        int len = 0;
        File dir = new File(fileProps.getDestDir());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, fileProps.getDestName());
        FileOutputStream fos = new FileOutputStream(file);
        FileCallback fileCallback = null;
        if (extra != null && extra instanceof FileCallback) {
            fileCallback = (FileCallback) extra;
        }
        final FileCallback finalFileCallback = fileCallback;
        try {
            long sum = 0;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
                fos.flush();

                if (finalFileCallback != null) {
                    sum += len;
                    final long bytesRead = sum;
                    HttpManager.getHttpManager().getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            finalFileCallback.onProgressChanged(bytesRead, contentLength);
                        }
                    });
                }
            }

            fos.flush();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
            }

        }
        return file;
    }
}
