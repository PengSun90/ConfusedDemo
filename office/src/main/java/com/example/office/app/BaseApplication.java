package com.example.office.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/3/17.
 */

public class BaseApplication extends Application {

    private static Application APPLICATION;

    @Override
    public void onCreate() {
        super.onCreate();
        if (APPLICATION == null) {
            APPLICATION = this;
        }
    }

    public static Context getContext(){
        return APPLICATION == null? null: APPLICATION.getApplicationContext();
    }
}
