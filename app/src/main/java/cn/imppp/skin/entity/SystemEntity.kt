package cn.imppp.skin.entity

data class SystemEntity(val children: Any,
                        val courseId: Int,
                        val id: Int,
                        val name: String,
                        val order: Int,
                        val parentChapterId: Int,
                        val userControlSetTop: Boolean,
                        val visible: Int)