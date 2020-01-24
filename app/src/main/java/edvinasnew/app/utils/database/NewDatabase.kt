package edvinasnew.app.utils.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SourceEntity::class, ArticleEntity::class], version = 7)
abstract class NewsDatabase : RoomDatabase() {
    abstract val sourceDao: SourceDao
    abstract val articleDao: ArticleDao

    companion object {
        fun getInstance(context: Context) =
            Room.databaseBuilder(context, NewsDatabase::class.java, "main.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}