package cn.imppp.skin.http

import cn.imppp.skin.entity.*
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("user/login")
//    @Headers("Content-Type:application/json;charset=UTF-8")
    suspend fun login(@FieldMap value : Map<String, String>): BaseResponse<UserEntity>

    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(@FieldMap value : Map<String, String>): BaseResponse<RegisterEntity>

    @GET("article/list/{page}/json")
    suspend fun articleList(@Path("page") page: Int): BaseResponse<BaseListResponse<List<ArticleEntity>>>

    @GET("user_article/list/{page}/json")
    suspend fun squareList(@Path("page") page: Int): BaseResponse<BaseListResponse<List<SquareEntity>>>

    @GET("/wenda/list/{page}/json")
    suspend fun questionList(@Path("page") page: Int): BaseResponse<BaseListResponse<List<QuestionEntity>>>

    @GET("wxarticle/chapters/json")
    suspend fun accountList(): BaseResponse<List<OfficialAccountEntity>>
}