package cn.imppp.skin.repository

import android.util.Log
import cn.imppp.skin.entity.*
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

    suspend fun login(
        userName: String,
        password: String
    ): UserEntity {
//        val gson = Gson()
        val paramsMap = HashMap<String, String>()
        paramsMap["username"] = userName
        paramsMap["password"] = password
//        val strEntity = gson.toJson(paramsMap)
//        val body = RequestBody.create(
//            MediaType.parse("application/json;charset=UTF-8"),
//            strEntity
//        )
        return RetrofitClient.apiService.login(paramsMap).data()
    }

    /**
     * 注册
     */
    suspend fun register(userName: String, password: String): RegisterEntity {
        val paramsMap = HashMap<String, String>()
        paramsMap["username"] = userName
        paramsMap["password"] = password
        paramsMap["repassword"] = password
        return RetrofitClient.apiService.register(paramsMap).data()
    }

    /**
     * 文章列表
     */
    suspend fun articleList(page: Int): List<ArticleEntity> {
        return RetrofitClient.apiService.articleList(page).data().datas()
    }

    /**
     * 广场数据
     */
    suspend fun squareList(page: Int): List<SquareEntity> {
        return RetrofitClient.apiService.squareList(page).data().datas()
    }

    /**
     * 问答数据
     */
    suspend fun questionList(page: Int): List<QuestionEntity> {
        return RetrofitClient.apiService.questionList(page).data().datas()
    }

    /**
     * 公众号数据
     */
    suspend fun officialAccountList(): List<OfficialAccountEntity> {
        return RetrofitClient.apiService.accountList().data()
    }
}