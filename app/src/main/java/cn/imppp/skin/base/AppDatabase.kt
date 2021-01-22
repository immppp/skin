package cn.imppp.skin.base

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import cn.imppp.skin.entity.dao.RequestDataEntity
import cn.imppp.skin.entity.dao.User
import cn.imppp.skin.repository.local.RequestDataDao
import cn.imppp.skin.repository.local.UserDao
import cn.imppp.skin.utils.convert.DateConverter

@Database(entities = [User::class, RequestDataEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao?                    // 用户表
    abstract fun requestDataDao(): RequestDataDao       // 数据请求表

    companion object {

        var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                Log.i("AppDataBase", "添加数据1")
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "skin.db"                       //数据库名称
                ).allowMainThreadQueries()
                    .addMigrations(migration1To2, migration2To3)
                    .build()
            }
            return instance
        }

        // 数据库的升级1-2,增加栏位5
        private var migration1To2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 新增一张表
                val string = "CREATE TABLE IF NOT EXISTS `requestData` (`requestData` TEXT, `pciUrl` TEXT, `seqNum` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `createTimes` TEXT NOT NULL, `updateTimes` TEXT NOT NULL, `delFlag` INTEGER NOT NULL DEFAULT 0)"
                database.execSQL(string)
            }
        }

        // 数据库升级2-3，增加栏位
        private var migration2To3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 给user表新增栏位,新增了motherName栏位，默认值为motherName
                database.execSQL("ALTER TABLE `user` ADD COLUMN `motherName` TEXT NOT NULL default 'motherName' ")
            }
        }
    }

}