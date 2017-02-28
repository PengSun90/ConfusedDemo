package com.example.mylibrary.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * Creatd by Administrator on 2017/1/17.
 */

public class GenericityClassTest<R> {

    private List<R> mlist = new ArrayList<R>();

    /**
     * 往顶部添加数据
     *
     * @param list
     */
    public void add2Head(List<R> list) {
        mlist.addAll(0, list);
    }


    public void add(R list) {
        mlist.add(list);
    }

    public R getAllList() {
        return mlist.get(0);
    }

}
