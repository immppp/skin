package cn.imppp.skin.repository

import android.util.Log
import cn.imppp.skin.entity.UserEntity
import cn.imppp.skin.http.RetrofitClient
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody

class NetRepository {
    // 创建该对象的单例
    companion object {
        fun getInstance(): NetRepository {
            return Helper.instance
        }
    }

    private object Helper {
        val instance = NetRepository()
    }

    suspend fun login(): UserEntity {
        Log.i("ThreadTag", "NetRepository ${Thread.currentThread().id}")
        val gson = Gson()
        val paramsMap =
            HashMap<String, Any>()
        paramsMap["pn"] = "login"
        paramsMap["username"] = "lhy1"
        paramsMap["password"] = "123456"
        val strEntity = gson.toJson(paramsMap)
        val body = RequestBody.create(
            MediaType.parse("application/json;charset=UTF-8"),
            strEntity
        )
        return RetrofitClient.apiService.login(body).data()
    }
}