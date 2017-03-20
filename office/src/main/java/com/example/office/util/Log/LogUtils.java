package com.example.office.util.Log;

import android.util.Log;

/**
 * 日志输出控制 (Description)
 */
public class LogUtils {
    /**
     * 是否允许输出log，true表示可以，false表示禁掉
     */
    public static boolean isDebug = true;
    private static Exception exception;
    private static StackTraceElement traceElement;

    /**
     * 以级别为 v 的形式输出LOG
     */
    public static void v(String mTag, String msg) {
        if (isDebug) {
            Log.v(mTag, "" + msg);
        }
        if (allowedWriteLogToFile()) {
            write(LogType.V, mTag, msg, null);
        }
    }

    /**
     * 默认带上文件和行数信息
     *
     * @param msg
     */
    public static void v(String msg) {
        if (isDebug) {
            Log.v(_FILE_(), getLineMethod() + " " + msg);
        }

        if (allowedWriteLogToFile()) {
            write(LogType.V, _FILE_(), getLineMethod() + " " + msg, null);
        }
    }

    /**
     * 以级别为 d 的形式输出LOG
     */
    public static void d(String mTag, String msg) {
        if (isDebug) {
            Log.d(mTag, "" + msg);
        }
        if (allowedWriteLogToFile()) {
            write(LogType.D, mTag, msg, null);
        }
    }

    /**
     * 默认带上文件和行数信息
     *
     * @param msg
     */
    public static void d(String msg) {
        if (isDebug) {
            Log.d(_FILE_(), getLineMethod() + " " + msg);
        }
        if (allowedWriteLogToFile()) {
            write(LogType.D, _FILE_(), getLineMethod() + " " + msg, null);
        }
    }

    /**
     * 以级别为 i 的形式输出LOG
     */
    public static void i(String mTag, String msg) {
        if (isDebug) {
            Log.i(mTag, "" + msg);
        }
        if (allowedWriteLogToFile()) {
            write(LogType.I, mTag, msg, null);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Log.i(_FILE_(), getLineMethod() + " " + msg);
        }
        if (allowedWriteLogToFile()) {
            write(LogType.I, _FILE_(), getLineMethod() + " " + msg, null);
        }
    }

    /**
     * 以级别为 w 的形式输出LOG
     */
    public static void w(String mTag, String msg) {
        if (isDebug) {
            Log.w(mTag, "" + msg);
        }
        if (allowedWriteLogToFile()) {
            write(LogType.W, mTag, msg, null);
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Log.w(_FILE_(), getLineMethod() + " " + msg);
        }
        if (allowedWriteLogToFile()) {
            write(LogType.W, _FILE_(), getLineMethod() + " " + msg, null);
        }
    }


    /**
     * 以级别为 w 的形式输出Throwable
     */
    public static void w(String mTag, Throwable tr) {
        if (isDebug) {
            Log.w(mTag, "", tr);
        }
        if (allowedWriteLogToFile()) {
            write(LogType.W, mTag, "", tr);
        }
    }

    /**
     * 以级别为 w 的形式输出LOG信息和Throwable
     */
    public static void w(String mTag, String msg, Throwable tr) {
        if (isDebug) {
            Log.w(mTag, "" + msg, tr);
        }
        if (allowedWriteLogToFile()) {
            write(LogType.W, mTag, msg, tr);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(_FILE_(), getLineMethod() + " " + msg);
        }
        if (allowedWriteLogToFile()) {
            write(LogType.E, _FILE_(), getLineMethod() + " " + msg, null);
        }
    }

    /**
     * 以级别为 e 的形式输出LOG
     */
    public static void e(String mTag, String msg) {
        if (isDebug) {
            Log.e(mTag, "" + msg);
        }
        if (allowedWriteLogToFile()) {
            write(LogType.E, mTag, msg, null);
        }
    }

    /**
     * 以级别为 e 的形式输出Throwable
     */
    public static void e(String mTag, Throwable tr) {
        if (isDebug) {
            Log.e(mTag, "", tr);
        }
        if (allowedWriteLogToFile()) {
            write(LogType.E, mTag, "", tr);
        }
    }

    /**
     * 以级别为 e 的形式输出LOG信息和Throwable
     */
    public static void e(String mTag, String msg, Throwable tr) {
        if (isDebug) {
            Log.e(mTag, "" + msg, tr);
        }
        if (allowedWriteLogToFile()) {
            write(LogType.E, mTag, msg, tr);
        }
    }

    public static String getFileLineMethod() {
        traceElement = ((exception).getStackTrace())[2];
        StringBuffer toStringBuffer = new StringBuffer("[")
                .append(traceElement.getFileName()).append(" | ")
                .append(traceElement.getLineNumber()).append(" | ")
                .append(traceElement.getMethodName()).append("]");
        return toStringBuffer.toString();
    }

    public static String getLineMethod() {
        exception = new Exception();
        traceElement = ((exception).getStackTrace())[2];
        StringBuffer toStringBuffer = new StringBuffer().append(traceElement.getMethodName())
                .append("() [Line ")
                .append(traceElement.getLineNumber())
                .append("] ");
        return toStringBuffer.toString();
    }

    public static String _FILE_() {
        exception = new Exception();
        traceElement = ((exception).getStackTrace())[2];
        String fileName = traceElement.getFileName();
        return fileName;
    }

    public static String _FUNC_() {
        traceElement = ((exception).getStackTrace())[1];
        return traceElement.getMethodName();
    }

    public static int _LINE_() {
        traceElement = ((exception).getStackTrace())[1];
        return traceElement.getLineNumber();
    }

    /**
     * 输出相应的log到文件
     *
     * @param type
     * @param tag
     * @param msg
     * @param tr
     */
    private static void write(LogType type, String tag, String msg, Throwable tr) {
        LogToFileImpl.getInstance().write(type, tag, msg, tr);
    }

    /**
     * 判定是否允许log输出到文件
     *
     * @return
     */
    private static boolean allowedWriteLogToFile() {
        return LogToFileImpl.getInstance().allowedWriteLogToFile();
    }
}
