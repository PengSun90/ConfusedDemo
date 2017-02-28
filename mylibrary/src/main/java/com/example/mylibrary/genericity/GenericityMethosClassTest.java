package com.example.mylibrary.genericity;

/**
 * Creatd by Administrator on 2017/1/17.
 */

public class GenericityMethosClassTest<R> extends GenericityClassTest<R> {

    public <T> T GenericityMethod(T a) {
        return a;
    }

    @Override
    public void add(R list) {
        super.add(list);
    }
}
