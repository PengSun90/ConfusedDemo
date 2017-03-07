package com.example.mylibrary.dispatchTouch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/3/7.
 */

public class fatherLinerlayout extends LinearLayout {
    public fatherLinerlayout(Context context) {
        super(context);
    }

    public fatherLinerlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public fatherLinerlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("test", fatherLinerlayout.class.getSimpleName() + " " + "dispatchTouchEvent" + " " + "DOWN");
                return true;
//                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("test", fatherLinerlayout.class.getSimpleName() + " " + "dispatchTouchEvent" + " " + "MOVE");
//                return true;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("test", fatherLinerlayout.class.getSimpleName() + " " + "dispatchTouchEvent" + " " + "UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("test", fatherLinerlayout.class.getSimpleName() + " " + "onInterceptTouchEvent" + " " + "DOWN");
//                return true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("test", fatherLinerlayout.class.getSimpleName() + " " + "onInterceptTouchEvent" + " " + "MOVE");
//                return true;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("test", fatherLinerlayout.class.getSimpleName() + " " + "onInterceptTouchEvent" + " " + "UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("test", fatherLinerlayout.class.getSimpleName() + " " + "onTouchEvent" + " " + "DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("test", fatherLinerlayout.class.getSimpleName() + " " + "onTouchEvent" + " " + "MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("test", fatherLinerlayout.class.getSimpleName() + " " + "onTouchEvent" + " " + "UP");
                break;
        }
        return super.onTouchEvent(event);
    }

//    总结：
//    1.dispatchTouchEvent负责事件分发，在任意case中返回true,则此类事件不在向下分发onInterceptTouchEvent与ontouchEvent方法均不调用。不影响其他case分发。
//    2.onInterceptTouchEvent方法负责拦截，当他任意case返回true，则此类型的事件由自身的ontouchEvent方法处理，不对其他case事件产生影响。
//    3.onTouchEvent任意case返回fale，说明此类事件未消化，返回给上层处理。谁先接到down谁优先处理后续事件，优先的子控件如若未能消化，交给上层处理。。


}
