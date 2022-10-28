package com.example.labelingapp.db

import android.widget.EditText
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entry (
    @PrimaryKey(autoGenerate = true) var ID : Int = 0,
    @ColumnInfo(name = "Worker") var name : String,
    @ColumnInfo(name = "Item") var item : String,
    @ColumnInfo(name = "Number of Items" ) var nItems : String,
    @ColumnInfo(name = "Problems Occured") var problems: String?,
    @ColumnInfo(name = "time") var time : String

        )