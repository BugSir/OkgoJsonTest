package com.sillybear.jsontest.bean;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
@author: Lin Xiongqing
 * @date: 2017/11/2 16:05
 * @description: 自定义解析器，解决特殊情况下的处理。如：jsonarray,json 返回不规则数据 null 导致gson解析gg
*/

public class CommonResultDeserializerJava implements JsonDeserializer<CommonResult> {

    @Override
    public CommonResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Type argument = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
        JsonObject jsonObject = json.getAsJsonObject();
        CommonResult commonResult = new CommonResult();
        //读出state内容直接解析
        commonResult.setState((CommonState) context.deserialize(jsonObject.getAsJsonObject("state"),CommonState.class));
        JsonElement element = jsonObject.get("data");
        Type argumentRawType = null;
        if (!(argument instanceof Class)) {
            //判断是否还有第二层泛型list<Bean>的情况
            argumentRawType = ((ParameterizedType) argument).getRawType();
        }

        if (argumentRawType != null && argumentRawType == List.class) {
            if (element.isJsonArray()) {
                commonResult.setData(context.deserialize(element, argument));
            } else {
                commonResult.setData(null);
            }
        } else if (argument == String.class) {
            if (element.isJsonNull()) {
                commonResult.setData("");
            } else {
                commonResult.setData(element.toString());
            }
        } else if (argument == Void.class) {
            commonResult.setData("");
        } else {
            if (element.isJsonNull()) {
                commonResult.setData(null);
            } else {
                commonResult.setData(context.deserialize(element, argument));
            }
        }

        return commonResult;
    }
}