package com.example.office.http.manager;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.office.R;
import com.example.office.config.CommonKeyValue;
import com.example.office.common.ApiErrorCode;
import com.example.office.http.view.DelayRequestDialog;
import com.example.office.image.GlideService;

import java.lang.ref.WeakReference;

/**
 * Created by YCY on 16/4/26.
 */
public class ApiDelayManager {
    private static ApiDelayManager apiDelayManager = null;
    private Button btnDelayTime;
    private int curTime;
    //是否需要延时
    public static boolean isDelayRequest = false;
    private DelayRequestDialog delayRequestDialog;

    public static ApiDelayManager getInstance() {
        if (apiDelayManager == null) {
            synchronized (ApiDelayManager.class) {
                if (apiDelayManager == null) {
                    apiDelayManager = new ApiDelayManager();
                }
            }
        }
        return apiDelayManager;
    }

    public boolean isNeedApiDelay(int error_code) {
        if (error_code == ApiErrorCode.API_COUNTDOWN) {
            return true;
        }
        return false;
    }

    public void handleApiDelay(WeakReference<Context> contextWeakReference, int error_sec) {
        try {
            if (error_sec == 0)
                error_sec = 60;

            if (handler == null)
                handler = new MyHandler();

            if (contextWeakReference == null || contextWeakReference.get() == null) return;
            showDelayRequestDialog(contextWeakReference.get(), error_sec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDelayRequestDialog(final Context context, int error_sec) {
        if (context == null || !(context instanceof Activity)) return;

        curTime = error_sec;
        delayRequestDialog = new DelayRequestDialog(context);
        if (delayRequestDialog.isShowing()) {
            delayRequestDialog.dismiss();
        }
        ImageView ivDelay = delayRequestDialog.getIvDelay();
        TextView tvDelayDesc = delayRequestDialog.getTvDelayDesc();
        btnDelayTime = delayRequestDialog.getBtnDelayTime();
        btnDelayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDelayRequest = false;
                delayRequestDialog.dismiss();
            }
        });

        delayRequestDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (handler != null) {
                    handler.removeMessages(update_delay_time);
                }
            }
        });

        String imgUrl = "http://pinduoduo.img-cn-shanghai.aliyuncs.com/jsalert/delay_load_icon.png";
        if (context != null) {
            Glide.with(context).load(GlideService.getWebpSupportUrl(imgUrl)).centerCrop().into(ivDelay);
        }
        tvDelayDesc.setText(CommonKeyValue.getInstance().getApiDelayDesc());
        delayRequestDialog.show();
        if (handler != null)
            handler.sendEmptyMessage(update_delay_time);
    }

    private int update_delay_time = 1;
    private MyHandler handler;

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == update_delay_time) {
                try {
                    if (curTime <= 0) {
                        btnDelayTime.setEnabled(true);
                        btnDelayTime.setText(R.string.refresh);
                        btnDelayTime.setBackgroundResource(R.drawable.apidelay_btn_enable);
                        return;
                    } else {
                        btnDelayTime.setEnabled(false);
                    }
                    btnDelayTime.setText(curTime + " s");
                    curTime--;
                    sendEmptyMessageDelayed(update_delay_time, 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    ;

    public void destroy() {
        try {
            isDelayRequest = false;
            if (delayRequestDialog != null && delayRequestDialog.isShowing()) {
                delayRequestDialog.dismiss();
                delayRequestDialog = null;
            }
            if (handler != null) {
                handler.removeMessages(update_delay_time);
                handler = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
