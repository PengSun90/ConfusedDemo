package com.example.office.util.Log;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/6/5.
 */
public final class LogToFileImpl implements LogToFile {

    /**
     * 单例工具类
     */
    private static LogToFileImpl mInstance;

    /**
     * 文件夹时间格式
     */
    private final String FILE_FORMAT = "yyyyMMdd";

    /**
     * 消息时间格式
     */
    private final String MESSAGE_FORMAT = "MM-dd HH:mm:ss.ms";

    /**
     * ThreadLocal messageFormat
     */
    private final ThreadLocal<DateFormat> messageFormat = new ThreadLocal<DateFormat>();

    /**
     * ThreadLocal fileFormat
     */
    private final ThreadLocal<DateFormat> fileFormat = new ThreadLocal<DateFormat>();

    /**
     * 单线程线程池
     */
    private final Executor logger = Executors.newSingleThreadExecutor();

    /**
     * 文件名字后缀
     */
    private final String SUFFIX = ".log";

    private final String SEPARATOR = ".";

    /**
     * 默认log输出等级
     */
    private int level = Log.DEBUG;

    /**
     * 默认是否允许输出到文件(默认不允许)
     */
    private boolean allowedWriteLogToFile = false;

    /**
     * 单例构造
     *
     * @return
     */
    public static synchronized LogToFileImpl getInstance() {
        if (null == mInstance) {
            mInstance = new LogToFileImpl();
        }
        return mInstance;
    }

    /**
     * 是否允许输出到File
     *
     * @return
     */
    public boolean allowedWriteLogToFile() {
        return allowedWriteLogToFile;
    }

    /**
     * 设定是否输出到File
     *
     * @param allowed true 允许 ，false 禁止
     */
    public void setallowedWriteLogToFile(boolean allowed) {
        this.allowedWriteLogToFile = allowed;
    }

    /**
     * 创建SimpleDateFormat 每个线程只有一个
     *
     * @return
     */
    private final DateFormat messageFormat() {
        DateFormat format = messageFormat.get();
        if (format == null) {
            format = new SimpleDateFormat(MESSAGE_FORMAT, Locale.getDefault());
            messageFormat.set(format);
        }

        return format;
    }

    /**
     * 创建SimpleDateFormat 每个线程只有一个
     *
     * @return
     */
    private final DateFormat fileFormat() {
        DateFormat format = fileFormat.get();
        if (format == null) {
            format = new SimpleDateFormat(FILE_FORMAT, Locale.getDefault());
            fileFormat.set(format);
        }
        return format;
    }

    /**
     * 得到允许输出的log等级
     *
     * @return
     */
    public String getLevel() {
        for (LogType logtype : LogType.values()) {
            if (logtype.getLevel() == level) {
                return logtype.getName();
            }
        }
        return "";
    }

    /**
     * 设定允许输出的log等级
     *
     * @param levelName 只能是“V,I,D,E,W”中任意一个
     */
    public void setLevel(String levelName) {
        for (LogType logtype : LogType.values()) {
            if (logtype.getName().equals(levelName)) {
                this.level = logtype.getLevel();
                break;
            }
        }
    }

    /**
     * 写log到File
     *
     * @param type
     * @param tag
     * @param msg
     * @param tr
     */
    @Override
    public void write(LogType type, String tag, String msg, Throwable tr) {
        o(type, tag, msg, tr);
    }

    /**
     * 写log到File
     */
    private void o(final LogType type, final String tag, final String msg, final Throwable tr) {
        final String time = messageFormat().format(new Date());
        final long threadId = Thread.currentThread().getId();
        logger.execute(new Runnable() {
            @Override
            public void run() {

                if (level == type.getLevel()) {
                    outMessage(type, tag, time, msg, tr);
                }
            }
        });
    }

    /**
     * 写log到File
     */
    private void outMessage(LogType type, String tag, String time, String msg, Throwable tr) {
        outMessage(type, "", tag, time, msg, tr);
    }

    /**
     * 写log到File
     */
    private void outMessage(LogType type, String cat, String tag, String time, String msg, Throwable tr) {
        outputToFile(formatMessage(tag, time, msg, tr), getLogFilePath(type, cat));
    }

    /**
     * 创建msg
     *
     * @param tag
     * @param time
     * @param msg
     * @param tr
     * @return
     */
    private static String formatMessage(String tag, String time, String msg, Throwable tr) {
        StringBuilder sb = new StringBuilder();

        // time
        if (TextUtils.isEmpty(time)) {
            sb.append("have no time ");
        } else {
            sb.append(time);
        }
        sb.append(": ");

        // tag
        if (TextUtils.isEmpty(tag)) {
            sb.append("have no tag ");
        } else {
            sb.append(tag);
        }
        sb.append(": ");

        // message
        if (TextUtils.isEmpty(msg)) {
            sb.append("have no message ");
        } else {
            sb.append(msg);
        }
        sb.append("\n");

        // Throwable
        if (tr != null) {
            sb.append(Log.getStackTraceString(tr));
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * 创建文件名
     *
     * @param type
     * @param cat
     * @return
     */
    private String getLogFileName(LogType type, String cat) {
        StringBuilder sb = new StringBuilder();

        sb.append(fileFormat().format(new Date()));

        if (type != null) {
            sb.append(SEPARATOR);
            sb.append(type.getName());
        }

        if (!TextUtils.isEmpty(cat)) {
            sb.append(SEPARATOR);
            sb.append(cat);
        }

        sb.append(SUFFIX);

        return sb.toString();
    }

    /**
     * 得到文件的绝对路径
     *
     * @param type
     * @param cat
     * @return
     */
    private String getLogFilePath(LogType type, String cat) {
//        return StorageUtil.getWritePath(getLogFileName(type, cat), StorageType.TYPE_LOG);
        return null;
    }

    /**
     * 写文件到file
     *
     * @param message
     * @param path
     * @return
     */
    private boolean outputToFile(String message, String path) {
        if (TextUtils.isEmpty(message)) {
            return false;
        }

        if (TextUtils.isEmpty(path)) {
            return false;
        }

        boolean written = false;
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(path, true));
            fw.write(message);
            fw.flush();
            fw.close();

            written = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return written;
    }
}
