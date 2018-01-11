package com.sillybear.jsontest.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.sillybear.jsontest.bean.CommonResult;
import com.sillybear.jsontest.bean.CommonResultDeserializer;
import com.sillybear.jsontest.bean.CommonResultDeserializerJava;

import java.lang.reflect.Type;
import java.util.List;


/**
 *@author: Lin Xiongqing
 *@date: 2017/11/2 16:32
 *@description: 
 */

public    class AppGsonUtil extends GsonUtil {

    /**公共类解析
     * @param jsondata
     * @param clazz
     * @param <T> 任意类型
     * @return
     */
    public static <T> CommonResult<T> fromJsonObject(String jsondata, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(CommonResult.class, new Class[]{clazz});
        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(CommonResult.class,new CommonResultDeserializer());
        builder.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory());
        Gson gson=builder.create();

        return gson.fromJson(jsondata, type);
    }
    /**公共类解析
     * @param jsondata
     * @param clazz
     * @param <T> 任意类型
     * @return
     */
    public static <T> CommonResult<T> fromJsonObjectJava(String jsondata, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(CommonResult.class, new Class[]{clazz});
        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(CommonResult.class,new CommonResultDeserializerJava());
        builder.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory());
        Gson gson=builder.create();

        return gson.fromJson(jsondata, type);
    }


    /**公共类型解析 list类型
     * @param jsondata
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> CommonResult<List<T>> fromJsonArray(String jsondata, Class<T> clazz) {
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(CommonResult.class, new Type[]{listType});

        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(CommonResult.class,new CommonResultDeserializer());
        builder.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory());
        Gson gson=builder.create();

        return gson.fromJson(jsondata, type);
    }


    public static <T> CommonResult<T> fromJsonObject(JsonReader reader, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(CommonResult.class, new Class[]{clazz});
        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(CommonResult.class,new CommonResultDeserializer());
        builder.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory());
        Gson gson=builder.create();

        return gson.fromJson(reader, type);
    }

    public static <T> T fromJsonArray(JsonReader reader, Class<T> clazz) {
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(CommonResult.class, new Type[]{listType});

        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(CommonResult.class,new CommonResultDeserializer());
        builder.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory());
        Gson gson=builder.create();

        return gson.fromJson(reader, type);
    }

    public static <T> T fromJsonObject(JsonReader reader, Type type) {
        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(CommonResult.class,new CommonResultDeserializer());
        builder.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory());
        Gson gson=builder.create();

        return gson.fromJson(reader, type);
    }

    public static <T> T fromJsonObject(String reader, Type type) {
        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(CommonResult.class,new CommonResultDeserializer());
        builder.registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory());
        Gson gson=builder.create();

        return gson.fromJson(reader, type);
    }

}
