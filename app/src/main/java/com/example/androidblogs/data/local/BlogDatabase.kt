package com.example.androidblogs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidblogs.data.local.entity.BlogEntity

@Database(
    entities = [BlogEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BlogDatabase : RoomDatabase() {

    abstract fun blogDao(): BlogDao
}