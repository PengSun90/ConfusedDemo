package com.example.mylibrary.viewPager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/1/4.
 */

public class testView extends TextView {

    public testView(Context context) {
        super(context);
    }

    public testView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public testView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean performLongClick() {
        Log.e("sp", "performLongClick");
        return super.performLongClick();
    }
}
