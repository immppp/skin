package cn.imppp.skin.repository.local

import androidx.room.*
import cn.imppp.skin.base.BaseDao
import cn.imppp.skin.entity.dao.User

/**
 * 本地用户名表
 */

@Dao
interface UserDao : BaseDao<User> {

    // 查询所有数据
    @Query("select * from user order by updateTimes desc ")
    fun getAllData(): List<User>

    // 根据条件查询数据
    @Query("select * from user where seqNum in (:userIds)")
    fun loadDataByIds(userIds: IntArray): List<User>

    // 根据条件删除数据
    @Query("delete from user where seqNum = :uid")
    fun deleteDataById(uid: Int)

}