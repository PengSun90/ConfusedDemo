package com.example.mylibrary.dispatchTouch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by Administrator on 2017/3/7.
 */

public class childLinerlayout extends Button {
    public childLinerlayout(Context context) {
        super(context);
    }

    public childLinerlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public childLinerlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("test", childLinerlayout.class.getSimpleName() + " " + "dispatchTouchEvent" + " " + "DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("test", childLinerlayout.class.getSimpleName() + " " + "dispatchTouchEvent" + " " + "MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("test", childLinerlayout.class.getSimpleName() + " " + "dispatchTouchEvent" + " " + "UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("test", childLinerlayout.class.getSimpleName() + " " + "onTouchEvent" + " " + "DOWN");
                return false;
//                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("test", childLinerlayout.class.getSimpleName() + " " + "onTouchEvent" + " " + "MOVE");
//                return false;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("test", childLinerlayout.class.getSimpleName() + " " + "onTouchEvent" + " " + "UP");
                break;
        }
        return super.onTouchEvent(event);
    }

//    onTouchEvent任意case返回fale，说明此类事件未消化，返回给上层处理。谁先接到down谁优先处理后续事件，优先的子控件如若未能消化，交给上层处理。。
}