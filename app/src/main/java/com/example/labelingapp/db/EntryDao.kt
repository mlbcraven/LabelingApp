package com.example.labelingapp.db

import androidx.room.*

@Dao
interface EntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll (vararg entry: Entry )

    @Delete
    fun DeleteEntry(entry: Entry)

    @Query("DELETE From Entry where Worker = :workerName ")
    fun deleteUser(workerName: String)

    @Query("DELETE From Entry where id = :id ")
    fun deleteId(id: String)

    @Query("Delete From Entry")
    fun nukeAll()

    @Query("Select * from Entry")
    fun getAll(): List<Entry>

    @Query("select * from Entry where Worker in (:entryWorker)")
    fun loadAllByName(entryWorker : String): List<Entry>
}