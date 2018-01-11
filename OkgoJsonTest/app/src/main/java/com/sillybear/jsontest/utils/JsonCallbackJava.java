package com.sillybear.jsontest.utils;

import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * @author: Lin Xiongqing
 * @date: 2017/12/12 20:31
 * @description: 带loading的请求
 */

public abstract class JsonCallbackJava<T> extends JsonBaseCallbackJava<T> {
    private boolean show;

    public JsonCallbackJava(boolean show) {
        this.show = show;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if (show) {
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
    }

    @Override
    public void onError(Response<T> response) {
        Throwable exception = response.getException();
        exception.printStackTrace();
//        if (exception instanceof UnknownHostException) {
//            ToastUtil.shortToast("网络连接失败，请连接网络");
//        } else if (exception instanceof SocketTimeoutException) {
//            ToastUtil.shortToast("网络请求超时");
//        } else if (exception instanceof HttpException) {
//            ToastUtil.shortToast("服务端响应异常");
//        } else if (exception instanceof IllegalStateException) {
//            parseCode(exception);
//        } else if (exception instanceof ConnectException){
//            ToastUtil.shortToast("服务器连接失败,请检查网络");
//        }else
//        {
//            ToastUtil.shortToast("请求出错，错误状态码：" + exception.getMessage());
//        }
    }

}
