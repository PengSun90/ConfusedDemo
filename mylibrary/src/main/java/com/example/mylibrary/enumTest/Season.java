package com.example.mylibrary.enumTest;

/**
 * Created by Administrator on 2017/1/17.
 */

public enum Season {

    spring(3, "spring"),
    summer(4, "summer"),
    autumn(5, "autumn"),
    winter(6, "winter");

    private int howLong;
    private String des;

    private Season(int howLong, String des) {
        this.howLong = howLong;
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public int getHowLong() {
        return howLong;
    }

    // 普通方法
    public static Season getHowLong(String des) {
        for (Season c : Season.values()) {
            if (c.getDes().equals(des)) {
                return c;
            }
        }
        return null;
    }
}
