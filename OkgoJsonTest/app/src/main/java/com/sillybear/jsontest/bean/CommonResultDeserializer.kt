package com.sillybear.jsontest.bean

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author: Lin Xiongqing
 * @date: 2018/01/10
 * @description: 自定义解析器，解决特殊情况下的处理。如：jsonarray,json 返回不规则数据 null 导致gson解析gg
 */

class CommonResultDeserializer : JsonDeserializer<CommonResult<Any>> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): CommonResult<Any> {

        val argument = (typeOfT as ParameterizedType).actualTypeArguments[0]
        val jsonObject = json.asJsonObject
        val commonResult = CommonResult<Any>()
        //读出state内容直接解析
        commonResult.state=context.deserialize(jsonObject.getAsJsonObject("state"), CommonState::class.java)
        val element = jsonObject.get("data")
        var argumentRawType: Type? = null
        if (argument !is Class<*>) {
            //判断是否还有第二层泛型list<Bean>的情况
            argumentRawType = (argument as ParameterizedType).rawType
        }

        //有第二层泛型
        if (argumentRawType != null && argumentRawType === List::class.java) {
            //请求的是List<T>类型则按list解析
            if (element.isJsonArray) {
                commonResult.data = context.deserialize<Any>(element, argument)
            } else {
                //服务端坑爹返回的不是jsonarray格式，直接返回错误（请转头爆揍）
                commonResult.data = null
            }
        } else if (argument === String::class.java) {
            //T 是String 类型，按String返回
            if (element.isJsonNull) {
                commonResult.data = ""
            } else {
                commonResult.data = element.toString()
            }
        } else if (argument === Void::class.java) {
            commonResult.data = ""
        } else {
            if (element.isJsonNull) {
                commonResult.data = null
            } else {
                commonResult.data = context.deserialize<Any>(element, argument)
            }
        }

        return commonResult
    }
}
