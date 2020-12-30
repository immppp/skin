package cn.imppp.skin.base

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import cn.imppp.skin.entity.dao.User
import cn.imppp.skin.repository.local.UserDao


@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao?

    companion object {

        var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                Log.i("AppDataBase", "添加数据1")
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "skin.db" //数据库名称
                ).allowMainThreadQueries()
                    .addMigrations(migration1To2)
                    .build()
            }
            return instance
        }

        // 数据库的升级1-2,增加栏位5
        private var migration1To2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.i("AppDataBase", "添加数据2")
                // 给user表新增栏位,新增了age栏位
                database.execSQL("ALTER TABLE 'user' ADD COLUMN 'age' TEXT NOT NULL default '5' ")
            }
        }
    }

}