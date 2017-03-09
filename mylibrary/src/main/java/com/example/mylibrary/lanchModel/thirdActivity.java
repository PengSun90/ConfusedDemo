package com.example.mylibrary.lanchModel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.mylibrary.R;

public class thirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        findViewById(R.id.third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tast", "thirdActivity" + activityUtil.getTopActivity(thirdActivity.this));
                Intent i = new Intent(thirdActivity.this, firstActivity.class);
                startActivity(i);
            }
        });
    }
}
