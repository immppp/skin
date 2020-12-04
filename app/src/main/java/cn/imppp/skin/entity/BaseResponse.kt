package cn.imppp.skin.entity

import android.text.TextUtils
import android.util.Log
import androidx.annotation.Keep
import cn.imppp.skin.http.ApiException

@Keep
data class BaseResponse<T>(val errorCode: Int,
                           val errorMsg: String,
                           private val data: T?) {
    fun data(): T {
        Log.i("BaseResponse", "code:$errorCode \n" +
                "msg:$errorMsg  \n" +
                "data:$data")
        if (TextUtils.isEmpty(errorMsg) && data != null) {
            return data
        } else {
            throw ApiException(0, errorMsg)
        }
    }
}