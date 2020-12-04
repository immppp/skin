package cn.imppp.skin.entity

import androidx.annotation.Keep

@Keep
data class OfficialAccountEntity(
    val children: Any,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)