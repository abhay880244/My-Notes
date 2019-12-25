package com.abhay.mynotes.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity//table note id,title and note are columns
data class Note(//constructor require 2 params title and note
    val title: String,
    val note: String

){
    @PrimaryKey(autoGenerate = true)
    var id: Int=0
}