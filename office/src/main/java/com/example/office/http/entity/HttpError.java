package com.example.office.http.entity;

/**
 * Created by YCY on 16/5/9.
 */
public class HttpError {
    private int error_code;
    private String error_msg;
    private int error_sec;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public int getError_sec() {
        return error_sec;
    }

    public void setError_sec(int error_sec) {
        this.error_sec = error_sec;
    }

    @Override
    public String toString() {
        return "HttpError{" +
                "error_code=" + error_code +
                ", error_msg='" + error_msg + '\'' +
                ", error_sec='" + error_sec + '\'' +
                '}';
    }
}
