package com.sillybear.jsontest.utils;

import com.google.gson.stream.JsonReader;
import com.sillybear.jsontest.bean.CommonResult;

import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author: Lin Xiongqing
 * @date: 2017/12/12 20:11
 * @description: 测试类
 */

public abstract   class JsonBaseCallbackJavaTest<T> {
    public JsonBaseCallbackJavaTest()
    {

    }
    public T convertResponse(String strJson) throws Throwable {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
        JsonReader reader = new JsonReader(new StringReader(strJson));
        T data = AppGsonUtil.fromJsonObject(reader, type);
        if (data instanceof CommonResult)
        {
            System.out.println(((CommonResult) data).getState().getMessage());
        }
        return data;
    }
}
