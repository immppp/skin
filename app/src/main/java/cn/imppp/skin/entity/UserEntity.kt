package cn.imppp.skin.entity
import androidx.annotation.Keep

@Keep
data class UserEntity(val userid: String,
                      val username: String,
                      val role: Int,
                      val nat: String,
                      val parentid: String)