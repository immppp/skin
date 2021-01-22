package cn.imppp.skin.base

import androidx.room.*
import cn.imppp.skin.utils.convert.DateConverter
import java.io.Serializable

/**
 * 基类数据类
 */

@Entity
@TypeConverters(DateConverter::class)
open class BaseDaoEntity : Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "seqNum")
    var seqNum: Long = 0                                                   // 主键

    @ColumnInfo(name = "createTimes")
    var createTimes: Long = System.currentTimeMillis()                     // 创建时间

    @ColumnInfo(name = "updateTimes")
    var updateTimes: Long = System.currentTimeMillis()                     // 更新时间

//    @ColumnInfo(name = "createTime")
//    var createTime = createTimes
//
//    @ColumnInfo(name = "updateTime")
//    var updateTime = updateTimes

    @ColumnInfo(name = "delFlag", defaultValue = "0")
    var delFlag: Int = 0                                                     // 删除标记默认为0

}