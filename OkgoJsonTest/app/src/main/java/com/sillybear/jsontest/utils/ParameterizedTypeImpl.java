package com.sillybear.jsontest.utils;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 *@author: Lin Xiongqing
 *@date: 2017/11/3 8:41
 *@description: 泛型type 处理
 */

public    class ParameterizedTypeImpl implements ParameterizedType  {
    private final Class raw;
    private final Type[] args;
    public ParameterizedTypeImpl(Class raw, Type[] args) {
        this.raw = raw;
        this.args = args != null ? args : new Type[0];
    }
    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }
    @Override
    public Type getRawType() {
        return raw;
    }
    @Override
    public Type getOwnerType() {return null;}
}
