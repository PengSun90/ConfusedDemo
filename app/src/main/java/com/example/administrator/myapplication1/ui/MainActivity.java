package com.example.administrator.myapplication1.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myapplication1.R;
import com.example.mylibrary.genericity.GenericityClassTest;
import com.example.mylibrary.genericity.GenericityMethosClassTest;
import com.example.mylibrary.log.LogUtils;


import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements View.OnLongClickListener, View.OnClickListener {
    private String className = MainActivity.class.getSimpleName();
    private ViewPager mPager;
    private ArrayList<Fragment> fragmentList;
    private int currIndex;//当前页卡编号
    private int bmpW;//横线图片宽度
    private int offset;//图片移动的偏移量
    private int mCurIndex = 0;
    public GenericityClassTest<Integer> genericityClassTest = new GenericityClassTest<>();
    public GenericityMethosClassTest genericityTest1 = new GenericityMethosClassTest();


    private EditText loginName;
    private Button loginSubmitl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_test);
//        initView();
        LogUtils.d("D");
        LogUtils.i("I");
        LogUtils.v("V");
        LogUtils.e("e");







//        findViewById(R.id.aa).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("tast", "MainActivity" + activityUtil.getTopActivity(MainActivity.this));
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, firstActivity.class);
////                intent.setAction(Intent.ACTION_WEB_SEARCH);
//                startActivity(intent);
//            }
//        });

//        findViewById(R.id.aa).setOnLongClickListener(this);
//        InitViewPager();
//        if (savedInstanceState != null) {
//            Bundle map = savedInstanceState.getBundle(ReportInfoUtils.REPORT_BUNDLE);
//            if (map != null) {
//                mCurIndex = map.getInt(ReportInfoUtils.CURRENT_ITEM);
//            }
//        }

//        genericityClassTest.add(3);
//        Integer a = genericityTest1.GenericityMethod(1);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }
//
//    private void initView() {
//        loginName = (EditText)findViewById(R.id.login_name);
//        loginSubmitl = (Button)findViewById(R.id.login_submit);
//        initOnclick();
//    }
//
//    private void initOnclick() {
//        loginSubmitl.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.login_submit:
//                    submitLoginInfor();
//                    break;
//            }
//    }
//
//    private void submitLoginInfor() {
//        if(!TextUtils.isEmpty(loginName.getText().toString())){
//            String url = HttpConstants.getInstance().getLoginUrl(loginName.getText().toString(),loginName.getText().toString());
//            LogUtils.d(url);
//            HttpUtils.get(new WeakReference<Context>(this), url, HttpConstants.getRequestHeader(), new CommonCallback<String>() {
//                @Override
//                public void onFailure(Exception e) {
//                    LogUtils.d(e.getMessage());
//                }
//
//                @Override
//                public void onResponseError(int code, @Nullable HttpError httpError) {
//
//                    LogUtils.d(httpError.toString());
//                }
//
//                @Override
//                public void onResponseSuccess(int code, String response) {
//
//                }
//            });
//        }
//    }
//
//
//    /*
//     * 初始化ViewPager
//     */
//    public void InitViewPager() {
////        mPager = (ViewPager) findViewById(R.id.viewpager);
////        fragmentList = new ArrayList<Fragment>();
////        Fragment btFragment = new ButtonFragment();
////        Fragment secondFragment = TestFragment.newInstance("this is second fragment");
////        Fragment thirdFragment = TestFragment.newInstance("this is third fragment");
////        Fragment fourthFragment = TestFragment.newInstance("this is fourth fragment");
////        fragmentList.add(btFragment);
////        fragmentList.add(secondFragment);
////        fragmentList.add(thirdFragment);
////        fragmentList.add(fourthFragment);
//        //给ViewPager设置适配器
////        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
////        mPager.setCurrentItem(0);//设置当前显示标签页为第一页
////        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
//    }
//
//    @Override
//    public boolean onLongClick(View v) {
//        Log.e("sp", "onLongClick");
//        return false;
//    }
//
//
//
////    @Override
////    protected void onSaveInstanceState(Bundle outState) {
////        outState.putBundle(ReportInfoUtils.REPORT_BUNDLE, saveState());
////        super.onSaveInstanceState(outState);
////    }
////
////    // save report info like mood level, highlight text, highlight id, wish text, wish id, user defined text.
////    private Bundle saveState() {
////        Bundle map = new Bundle();
////        map.putInt(ReportInfoUtils.CURRENT_ITEM, mCurIndex);
////        return map;
////    }
////
////    @Override
////    protected void onResume() {
////        mPager.setCurrentItem(0);//设置当前显示标签页为第一页
////        super.onResume();
////    }
////
////    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
////
////        @Override
////        public void onPageScrolled(int arg0, float arg1, int arg2) {
////            // TODO Auto-generated method stub
////
////        }
////
////        @Override
////        public void onPageScrollStateChanged(int arg0) {
////            // TODO Auto-generated method stub
////
////        }
////
////        @Override
////        public void onPageSelected(int arg0) {
////            // TODO Auto-generated method stub
////            mCurIndex = arg0;
////        }
////    }
}
