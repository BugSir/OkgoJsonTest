package com.sillybear.jsontest.utils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;

/**
 *@author: Lin Xiongqing
 *@date: 2017/12/12 18:36
 *@description: http请求管理类 java版
 */

public    class HttpUtilsJava<T>  {
    private static HttpUtils mInstance;

    public HttpUtilsJava() {

    }

    public static synchronized HttpUtils getInstance()
    {
        if (mInstance==null)
        {
            mInstance=new HttpUtils();
        }
        return mInstance;
    }

    public void postRequest(Object obj,String url, AbsCallback callback)
    {
        OkGo.<T>post(url).tag(obj).execute(callback);
    }

    public void getRequest(Object obj,String url,AbsCallback callback)
    {
        OkGo.<T>get(url).tag(obj).execute(callback);
    }

    public void stopRequest(Object obj)
    {
        OkGo.getInstance().cancelTag(obj);
    }

}
