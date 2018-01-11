package com.sillybear.jsontest.utils

import com.google.gson.stream.JsonReader
import com.lzy.okgo.callback.AbsCallback
import com.sillybear.jsontest.bean.CommonResult
import okhttp3.Response
import java.lang.reflect.ParameterizedType

/**
 *@author: Lin Xiongqing
 *@date: 2017/11/24 13:35
 *@description:
 */
abstract class JsonBaseCallback<T>:AbsCallback<T>() {
    @Throws (Throwable::class)
    override fun convertResponse(response: Response): T? {
        val genType=javaClass.genericSuperclass
        val params=((genType as ParameterizedType)).actualTypeArguments
        //获取具体泛型类型
        val type=params[0]
        val body= response.body() ?: return null
        val reader=JsonReader(body.charStream())
        val result= AppGsonUtil.fromJsonObject<T>(reader, type)
        response.close()
        if (result is CommonResult<*>)
        {//是自己定义的类型，进行业务处理
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