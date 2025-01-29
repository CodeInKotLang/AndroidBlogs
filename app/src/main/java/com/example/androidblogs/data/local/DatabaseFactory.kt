package com.example.androidblogs.data.local

import android.content.Context
import androidx.room.Room
import com.example.androidblogs.data.util.Constant.BLOG_DATABASE_NAME

object DatabaseFactory {

    fun create(context: Context): BlogDatabase {
        return Room
            .databaseBuilder(
                context = context.applicationContext,
                klass = BlogDatabase::class.java,
                name = BLOG_DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }
}