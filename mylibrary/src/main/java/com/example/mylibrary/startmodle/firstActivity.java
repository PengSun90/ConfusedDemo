package com.example.mylibrary.startmodle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.mylibrary.R;

public class firstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        findViewById(R.id.first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tast", "firstActivity" + activityUtil.getTopActivity(firstActivity.this));
                Intent i = new Intent(firstActivity.this, secondActivity.class);
                startActivity(i);
            }
        });
    }
}
