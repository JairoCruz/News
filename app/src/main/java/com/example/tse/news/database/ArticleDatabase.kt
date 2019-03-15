package com.example.tse.news.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.example.tse.news.model.Article
import com.example.tse.news.model.Source
import com.example.tse.news.model.SourceUser
import com.example.tse.news.utils.Converters

@Database(entities = [Article::class, Source::class, SourceUser::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {


    abstract fun articlesDao(): ArticleDao
    abstract fun sourceDao(): SourceDao
    abstract  fun sourceUserDao(): SourceUserDao

    // Singlenton
    companion object {

        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getInstance(context: Context): ArticleDatabase = INSTANCE ?: synchronized(this){
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        // Create database
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java, "News.db").build()
    }

}