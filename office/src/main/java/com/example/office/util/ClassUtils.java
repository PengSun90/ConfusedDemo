package com.example.office.util;

import com.example.office.util.Log.LogUtils;
import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/3/17.
 */

public class ClassUtils {
    /**
     * 获取泛型的类型
     *
     * @param cls
     * @return
     */
    public static Type getType(Class cls) {
        try {
            Type superclass = cls.getGenericSuperclass();
            if (!(superclass instanceof ParameterizedType)) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            Type type = $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
            LogUtils.d("type = " + type);
            return type;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
