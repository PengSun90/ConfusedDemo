package com.example.mylibrary.viewPager;

/**
 * Created by Administrator on 2016/12/15.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mylibrary.R;

public class ButtonFragment extends Fragment {
    EditText myButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.guide_1, container, false);//关联布局文件

        myButton = (EditText) rootView.findViewById(R.id.mybutton);//根据rootView找到button

        //设置按键监听事件
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(ButtonFragment.this.getActivity(), "button is click!", Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }
}
