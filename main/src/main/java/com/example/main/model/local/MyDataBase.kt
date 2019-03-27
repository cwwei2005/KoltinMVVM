package com.example.main.model.local

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.main.model.local.MyDataBase.Companion.DB_VERSION
import com.example.main.model.entity.ArticleEntity
import com.example.main.model.local.dao.ArticleDao

/**
 * 数据库创建和表(need entity, dao)
 */
@Database(entities = [ArticleEntity::class/*, UserEntity::class*/], version = DB_VERSION)
abstract class MyDataBase : RoomDatabase(){
    companion object {
        private const val DB_NAME = "app.db"
        const val DB_VERSION = 1
        const val OLD_VERSION = 1
        @Volatile private var instance: MyDataBase? = null
        val mIsDatabaseCreated = MutableLiveData<Boolean>()
//        fun instance() = instance!!

        fun getInstance(ctx:Context): MyDataBase = instance
            ?:synchronized(this){
            mIsDatabaseCreated.postValue(ctx.getDatabasePath(DB_NAME).exists())//checkDB(ctx)
            instance
                ?: buildDatabase(ctx)
        }

        private fun buildDatabase(ctx:Context): MyDataBase {
            return Room.databaseBuilder(ctx, MyDataBase::class.java,
                DB_NAME
            )
                .addMigrations(MIGRATION_1_2)
                .build()
        }

        // version 3 的users表的字段uId
        // version 4 相应的字段改为了userId
//        @FieldMigrationRule(version1 = 1, version2 = 2, table = "home", field = "uId")
//        private fun migrate_3_4_Object1Dbo_intVal(): String {
//            return "`users`.`userId`"
//        }
        private val MIGRATION_1_2 = object : Migration(OLD_VERSION, DB_VERSION) {  //数据迁移（创建一个新的临时表；把旧表中的数据拷贝到临时表中；丢弃旧表；把临时表重命名）
            override fun migrate(database: SupportSQLiteDatabase) {
                if (OLD_VERSION < DB_VERSION){
//                database.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `productsFts` USING FTS4(" + "`name` TEXT, `description` TEXT, content=`products`)")
//                database.execSQL(migrate_3_4_Object1Dbo_intVal())  //使用Roomigrant方便，不需要写上面的SQL语句
                    //......
                }
            }
        }
    }

    //注意，这里使用fun homeDao()，不是var homeDao()
    abstract fun articleDao(): ArticleDao
}