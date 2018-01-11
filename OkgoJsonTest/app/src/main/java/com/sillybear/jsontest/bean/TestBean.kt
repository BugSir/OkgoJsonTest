package com.sillybear.jsontest.bean

import java.io.Serializable

/**
 * @author: Lin Xiongqing
 * @date: 2018/01/10
 * @description: 所有请求的公共类，只有data部分是数据是变动的
 */
class TestBean : Serializable {

    var name = ""
    var phone = ""
}
