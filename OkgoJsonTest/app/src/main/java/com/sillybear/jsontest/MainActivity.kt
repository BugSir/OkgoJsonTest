package com.sillybear.jsontest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lzy.okgo.model.Response
import com.sillybear.jsontest.bean.CommonResult
import com.sillybear.jsontest.bean.TestBean
import com.sillybear.jsontest.utils.HttpUtils
import com.sillybear.jsontest.utils.JsonCallback

/**
 * @author: Lin Xiongqing
 * @date: 2018/01/10
 * @description:
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //普通请求
        HttpUtils.getInstance().getRequest(this,"url",object: JsonCallback<CommonResult<TestBean>>(true)
        {
            override fun onSuccess(response: Response<CommonResult<TestBean>>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(response: Response<CommonResult<TestBean>>?) {
                super.onError(response)
            }
        })
        //带list请求
        HttpUtils.getInstance().getRequest(this,"url",object:JsonCallback<CommonResult<MutableList<TestBean>>>(true)
        {
            override fun onSuccess(response: Response<CommonResult<MutableList<TestBean>>>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
