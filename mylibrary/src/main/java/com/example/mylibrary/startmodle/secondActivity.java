package com.example.mylibrary.startmodle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.mylibrary.R;

public class secondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tast", "secondActivity" + activityUtil.getTopActivity(secondActivity.this));
                Intent i = new Intent(secondActivity.this, thirdActivity.class);
                startActivity(i);
            }
        });
    }
}
