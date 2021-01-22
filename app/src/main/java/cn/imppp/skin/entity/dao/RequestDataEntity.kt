package cn.imppp.skin.entity.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import cn.imppp.skin.base.BaseDaoEntity

@Entity(tableName = "requestData")
data class RequestDataEntity(
    @ColumnInfo(name = "requestData")
    var requestData: String?,
    @ColumnInfo(name = "pciUrl")
    var pciUrl: String?
) : BaseDaoEntity()