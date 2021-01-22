package cn.imppp.skin.repository.local

import androidx.room.Dao
import androidx.room.Query
import cn.imppp.skin.base.BaseDao
import cn.imppp.skin.entity.dao.RequestDataEntity

@Dao
interface RequestDataDao : BaseDao<RequestDataEntity> {

    // 查询数据
    @Query("select * from requestData")
    fun loadData(): List<RequestDataEntity>

}