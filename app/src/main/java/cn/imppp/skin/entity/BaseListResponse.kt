package cn.imppp.skin.entity

import androidx.annotation.Keep
import cn.imppp.skin.http.ApiException

@Keep
data class BaseListResponse<T>(
    val curPage: Int,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total : Int,
    private val datas: T?
) {
    fun datas(): T {
        if (datas != null) {
            return datas
        } else {
            throw ApiException(0, "列表数据为空")
        }
    }
}