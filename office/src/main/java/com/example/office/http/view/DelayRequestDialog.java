package com.example.office.http.view;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.office.R;


/**
 * Created by YCY on 16/4/25.
 */
public class DelayRequestDialog extends Dialog {
    private ImageView ivDelay;
    private TextView tvDelayDesc;
    private Button btnDelayTime;

    public DelayRequestDialog(Context context) {
        this(context, R.style.standard_dialog);
    }

    public DelayRequestDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    protected DelayRequestDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        this(context, R.style.standard_dialog);
    }

    private void init() {
        this.setContentView(R.layout.delay_request_dialog);
        ivDelay = (ImageView) findViewById(R.id.iv_delay);
        tvDelayDesc = (TextView) findViewById(R.id.tv_delay_desc);
        btnDelayTime = (Button) findViewById(R.id.btn_delay_time);
//        this.setCancelable(false);
    }

    public ImageView getIvDelay() {
        return ivDelay;
    }

    public TextView getTvDelayDesc() {
        return tvDelayDesc;
    }

    public Button getBtnDelayTime() {
        return btnDelayTime;
    }
}
