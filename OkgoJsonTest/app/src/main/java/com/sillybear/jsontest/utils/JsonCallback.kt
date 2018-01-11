package com.sillybear.jsontest.utils

import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request

/**
 *@author: Lin Xiongqing
 *@date: 2017/11/21 15:52
 *@description:
 */
abstract class JsonCallback<T>(private var isShow: Boolean) : JsonBaseCallback<T>() {

    override fun onStart(request: Request<T, out Request<Any, Request<*, *>>>?) {
        if (isShow) {
            //这里显示加载框
        }

    }

    override fun onFinish() {
        //这里可以隐藏加载框
    }

    override fun onError(response: Response<T>?) {
        //这里是抛出错误的地方，可以根据不同的错误处理不同的提示等
        var exception = response?.exception
        exception?.printStackTrace()
        when (exception) {
//            is UnknownHostException -> ToastUtil.shortToast("网络连接失败，请连接网络")
//            is SocketTimeoutException -> ToastUtil.shortToast("网络请求超时")
//            is HttpException -> ToastUtil.shortToast("服务端响应异常")
//            is IllegalStateException -> {
//                    这里是自定义抛的错误,可以根据不同的错误做不周的处理
//                    }
//                ToastUtil.shortToast(exception.message ?: "请求失败")
        }
    }
}