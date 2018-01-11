package com.sillybear.jsontest.utils;

import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.sillybear.jsontest.bean.CommonResult;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author: Lin Xiongqing
 * @date: 2017/12/12 20:11
 * @description: 基本转换类，将错误状态字异常抛出
 */

abstract class JsonBaseCallbackJava<T> extends AbsCallback<T> {
    @Override
    public T convertResponse(Response response) throws Throwable {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
//        获取具体泛型类型
        Type type = params[0];
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        JsonReader reader = new JsonReader(body.charStream());
        //直接解析
        T data = AppGsonUtil.fromJsonObject(reader, type);
        response.close();

        if (data instanceof CommonResult) {
            //是自定义的类型，走自己的业务逻辑
            if (!"0".equals(((CommonResult) data).getState().getCode()))
            {
                throw new IllegalStateException(((CommonResult) data).getState().getMessage());
            }
        }


        return data;
    }
}
