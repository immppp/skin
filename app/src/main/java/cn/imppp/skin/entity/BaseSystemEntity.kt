package cn.imppp.skin.entity

data class BaseSystemEntity(
    val children: List<SystemEntity>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int
)