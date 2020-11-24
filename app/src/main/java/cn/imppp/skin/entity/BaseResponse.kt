package cn.imppp.skin.entity

import android.util.Log
import androidx.annotation.Keep
import cn.imppp.skin.http.ApiException

@Keep
data class BaseResponse<T>(val success: Boolean,
                           val msg: String,
                           private val data: T?) {
    fun data(): T {
        Log.i("BaseResponse", "code:$success \n" +
                "msg:$msg  \n" +
                "data:$data")
        if (success && data != null) {
            return data
        } else {
            throw ApiException(0, msg)
        }
    }
}