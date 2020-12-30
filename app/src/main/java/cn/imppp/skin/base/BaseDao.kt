package cn.imppp.skin.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

/**
 * 基类dao操作
 */
interface BaseDao<T:BaseDaoEntity> {

    // 插入时设置时间
    @Insert
    fun insertData(t: T)

    // 更新数据
    @Update
    fun updateData(t: T)

    // 删除数据
    @Delete
    fun deleteData(t: T)

}