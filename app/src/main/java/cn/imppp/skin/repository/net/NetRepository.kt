package cn.imppp.skin.repository.net

import cn.imppp.skin.entity.*
import cn.imppp.skin.http.RetrofitClient

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

    /**
     * 获取体系列表
     */
    suspend fun systemList(): List<BaseSystemEntity> {
        return RetrofitClient.apiService.systemList().data()
    }

    /**
     * 获取项目分类列表
     */
    suspend fun projectTypeList(): List<ProjectTypeEntity> {
        return RetrofitClient.apiService.projectNameList().data()
    }

    /**
     * 获取类型项目列表
     */
    suspend fun projectList(page: Int, cid: Int): List<ProjectEntity> {
        return RetrofitClient.apiService.projectList(page, cid).data().datas()
    }
}