package net.hwyz.iov.data.user.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [UserEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: UserDB? = null

        @JvmStatic
        @Synchronized
        fun getInstance(applicationContext: Context): UserDB {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(applicationContext, UserDB::class.java, "user.db")
                        .fallbackToDestructiveMigration()
                        .build()
            }

            return instance!!
        }
    }
}