package cn.imppp.skin.http

import cn.imppp.skin.entity.BaseResponse
import cn.imppp.skin.entity.UserEntity
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST("action")
    @Headers("Content-Type:application/json;charset=UTF-8")
    suspend fun login(@Body body: RequestBody): BaseResponse<UserEntity>
}