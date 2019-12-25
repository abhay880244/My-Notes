package com.abhay.mynotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun getNoteDao() : NoteDao

    companion object{
        //volatile is used becuase instance is immediately available to all the threads
        @Volatile private var instance : NoteDatabase? =null
        private val LOCK=Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?: buildDatabase(context).also {
                instance=it
            }
        }

        private fun buildDatabase(context: Context)=Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "notedatabse"
        ).build()
    }

}