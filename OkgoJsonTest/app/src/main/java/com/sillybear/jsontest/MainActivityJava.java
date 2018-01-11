package com.sillybear.jsontest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lzy.okgo.model.Response;
import com.sillybear.jsontest.bean.CommonResult;
import com.sillybear.jsontest.bean.TestBean;
import com.sillybear.jsontest.utils.HttpUtilsJava;
import com.sillybear.jsontest.utils.JsonCallbackJava;

import java.util.List;

/**
 *@author: Lin Xiongqing
 *@date: 2018/1/11 09:14
 *@description: 
 */

public    class MainActivityJava extends Activity   {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //普通请求
        HttpUtilsJava.getInstance().getRequest(this, "url",new JsonCallbackJava<CommonResult<TestBean>>(true)
        {
            @Override
            public void onSuccess(Response<CommonResult<TestBean>> response) {

            }

            @Override
            public void onError(Response<CommonResult<TestBean>> response) {
                super.onError(response);
            }
        });
        //list请求
        HttpUtilsJava.getInstance().getRequest(this, "url",new JsonCallbackJava<CommonResult<List<TestBean>>>(true)
        {
            @Override
            public void onSuccess(Response<CommonResult<List<TestBean>>> response) {

            }
        });
    }
}
