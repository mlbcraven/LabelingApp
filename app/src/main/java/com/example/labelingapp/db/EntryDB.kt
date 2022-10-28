package com.example.labelingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entry::class], version = 1)
abstract class EntryDB: RoomDatabase() {
    abstract fun entryDao() : EntryDao?
    companion object {
        private var INSTANCE : EntryDB? = null

        fun getAppDatabase(context: Context): EntryDB? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<EntryDB>(
                    context.applicationContext, EntryDB::class.java, "Database"
                ).allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
        fun  destroyInstance() {
            INSTANCE = null
        }
    }
}