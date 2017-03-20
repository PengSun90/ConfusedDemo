package com.example.administrator.myapplication1.app;

import com.example.office.app.BaseApplication;
import com.example.office.util.Log.LogUtils;

/**
 * Created by Administrator on 2017/3/17.
 */

public class BaseApp extends BaseApplication {
    private String className = BaseApp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(className,"onCreate");
    }


}
