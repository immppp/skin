package cn.imppp.skin.entity.dao

import androidx.room.*
import cn.imppp.skin.base.BaseDaoEntity
import cn.imppp.skin.utils.DateUtils
import cn.imppp.skin.utils.convert.DateConverter
import cn.imppp.skin.utils.convert.ListConverter
import java.util.*

//// 主键,自增形式的
//@PrimaryKey(autoGenerate = true)
//var uid : Int = 0
//
//// 栏位名称和默认参数
//@ColumnInfo(name = "first_name")
//var firstName: String = ""
//
//// 注解用来标示忽略这个字段，使用了这个注解的字段将不会在数据库中生成对应的列信息，不想让他持久化存储
//@Ignore
//var sex: Boolean = false
//
//// 当设置默认值可为空时，则该字段为可为空字段
//@ColumnInfo(name = "fatherName", defaultValue = "father")
//var fatherName: String? = null
//
//// 当赋予默认值时，则该字段被默认为非空字段
//@ColumnInfo(name = "age", defaultValue = "5")
//var age: String = ""
//
//@ColumnInfo(name = "createTime")
//var createTime: Date = Date()
@Entity
@TypeConverters(ListConverter::class)
data class User(
    @ColumnInfo(name = "firstName", defaultValue = "")
    var firstName: String,                              // 姓名
    @ColumnInfo(name = "fatherName", defaultValue = "fatherName")
    var fatherName: String,                             // 父亲姓名
    @ColumnInfo(name = "age", defaultValue = "5")
    var age: Int,                                        // 年龄大小
    @ColumnInfo(name = "motherName")
    var motherName: String = "motherName",
    var list: List<String>                               // 数据列表
) : BaseDaoEntity()