package com.sillybear.jsontest.utils

import android.app.Activity
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.AbsCallback

/**
 *@author: Lin Xiongqing
 *@date: 2017/11/21 17:15
 *@description:
 */
class HttpUtils {
    companion object {
        private  var mInstance: HttpUtils?=null
        @Synchronized
        fun getInstance():HttpUtils
        {
            if (mInstance == null) {
                mInstance = HttpUtils()
            }
            return mInstance!!
        }
    }
    fun <T> getRequest(ob: Any, url: String,jsonCallback: AbsCallback<T>) {
        OkGo.get<T>(url).tag(ob).execute(jsonCallback)
    }

    fun <T>postRequest(ob: Any, url: String,jsonCallback: AbsCallback<T>) {
        OkGo.post<T>(url).tag(ob).execute(jsonCallback)
    }


    fun stopRequest(activity: Activity) {
        OkGo.getInstance().cancelTag(activity)
    }
}