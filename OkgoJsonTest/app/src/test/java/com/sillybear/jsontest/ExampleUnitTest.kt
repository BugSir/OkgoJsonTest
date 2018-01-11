package com.sillybear.jsontest

import com.sillybear.jsontest.bean.CommonResult
import com.sillybear.jsontest.bean.TestBean
import com.sillybear.jsontest.utils.AppGsonUtil
import com.sillybear.jsontest.utils.JsonBaseCallbackTest
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val json="{\"data\": {\"name\": \"测试\",\"phone\":111111123213\"},\"state\":{\"code\": 0,\"success\": true,\"message\": \"请求成功！\"}}"
//        var commonresult= AppGsonUtil.fromJsonObject(json,TestBean::class.java)
//        System.out.println(commonresult.data?.name);
//        val callback=  object :JsonBaseCallbackJavaTest<CommonResult<TestBean>>()
//        {
//
//        }
//        val result=callback.convertResponse(json);
//        System.out.print(result.data?.name)
        val callback=  object : JsonBaseCallbackTest<CommonResult<TestBean>>()
        {

        }
        val result=callback.convertResponse(json);
        System.out.print(result?.data?.name)
    }

    @Test
    fun addition_isCorrectJava() {
        val json="{\"data\": {\"name\": \"测试\",\"phone\":111111123213\"},\"state\":{\"code\": 0,\"success\": true,\"message\": \"请求成功！\"}}"
        var commonresult= AppGsonUtil.fromJsonObjectJava(json,TestBean::class.java)
        System.out.println(commonresult.data?.name);
    }
}
