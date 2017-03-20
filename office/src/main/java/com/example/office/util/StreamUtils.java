package com.example.office.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by YCY on 16/5/10.
 */
public class StreamUtils {
    public static byte[] streamToByteArray(InputStream is) throws IOException {
        byte[] buf = new byte[1024];
        int len = 0;
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
                baos.flush();
            }
            baos.flush();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (Exception e) {
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
            }
        }
        return baos.toByteArray();
    }
}
