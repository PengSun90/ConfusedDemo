package com.example.mylibrary.asynctask;

import android.os.AsyncTask;

/**
 * Created by Administrator on 2017/3/12.
 */

public class ast extends AsyncTask<Integer,Float,String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Float... values) { //泛型参数2
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(Integer... integers) { //泛型参数1
        return null;
    }

    @Override
    protected void onPostExecute(String s) {  //泛型参数3，doInBackground的返回结果。
        super.onPostExecute(s);
    }
}
