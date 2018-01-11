package com.sillybear.jsontest.utils

import com.google.gson.stream.JsonReader
import com.sillybear.jsontest.bean.CommonResult
import java.io.StringReader
import java.lang.reflect.ParameterizedType

/**
 *@author: Lin Xiongqing
 *@date: 2017/11/24 13:35
 *@description: 测试类
 */
 abstract class JsonBaseCallbackTest<T> {
    @Throws (Throwable::class)
     fun convertResponse(strJson: String): T? {
        val genType=javaClass.genericSuperclass
        val params=((genType as ParameterizedType)).actualTypeArguments
        val type=params[0]

        val reader=JsonReader(StringReader(strJson))

        val result= AppGsonUtil.fromJsonObject<T>(reader, type)
        if (result is CommonResult<*>)
        {

            val code=(result as CommonResult<*>).state?.code
            //以下是根据各自的返回状态字，来处理业务逻辑。比如：0是成功则返回成功success方法中直接处理，其它类型可以抛出异常，然后在onerror方法中处理其它业务
            if ("0"!=(code))
            {
                throw IllegalStateException(result.state?.message)
            }
        }

        return result as T
    }
}