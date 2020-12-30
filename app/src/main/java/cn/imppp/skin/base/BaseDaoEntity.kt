package cn.imppp.skin.base

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.imppp.skin.utils.DateUtils
import java.io.Serializable

/**
 * 基类数据类
 */

@Entity
open class BaseDaoEntity : Serializable {

    @PrimaryKey(autoGenerate = true)
    var seqNum: Long = 0                                                    // 主键

    @ColumnInfo(name = "createTimes")
    var createTimes: String = DateUtils.getCurrentDataDefault()             // 创建时间

    @ColumnInfo(name = "updateTimes")
    var updateTimes: String = DateUtils.getCurrentDataDefault()              // 更新时间

    @ColumnInfo(name = "delFlag", defaultValue = "0")
    var delFlag: Int = 0                                                     // 删除标记默认为0

}