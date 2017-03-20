package com.example.office.util.Log;

import android.util.Log;

/**
 * Created by jiangnan on 2016/6/5.
 */
public enum LogType {
    V(Log.VERBOSE, "V"),

    D(Log.DEBUG, "D"),

    I(Log.INFO, "I"),

    W(Log.WARN, "W"),

    E(Log.ERROR, "E");

    /**
     * log等级
     */
    private int level;
    /**
     * log name
     */
    private String name;

    /**
     * 构造函数
     *
     * @param level
     * @param name
     */
    LogType(int level, String name) {
        this.level = level;
        this.name = name;
    }

    /**
     * get&&set方法
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
