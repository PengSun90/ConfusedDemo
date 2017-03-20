package com.example.office.util.Log;

/**
 * Created by Administrator on 2016/6/5.
 */
public interface LogToFile {
    /**
     * 写Log内容到File中
     *
     * @param type
     * @param tag
     * @param msg
     * @param tr
     */
    void write(LogType type, String tag, String msg, Throwable tr);

}
