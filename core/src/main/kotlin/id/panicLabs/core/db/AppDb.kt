package id.panicLabs.core.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import id.panicLabs.core.model.CoreModel

/**
 * @author panicLabs
 * @createdOn on 22/7/2018.
 * @Email: panic.inc.dev@gmail.com
 */
@Database(
        entities = [(CoreModel::class)],
        version = 1,
        exportSchema = false
)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDb : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory : Boolean): AppDb {
            val databaseBuilder = if(useInMemory) {
                Room.inMemoryDatabaseBuilder(context, AppDb::class.java)
            } else {
                Room.databaseBuilder(context, AppDb::class.java, "MyDatabase.db")
            }
            return databaseBuilder
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }

}