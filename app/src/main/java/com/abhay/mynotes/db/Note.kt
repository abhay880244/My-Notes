package com.abhay.mynotes.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity//table note id,title and note are columns
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val note: String

)