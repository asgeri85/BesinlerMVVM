package com.example.besinlermvvmbtk.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.besinlermvvmbtk.model.Besin

@Database(entities = [Besin::class], version = 1)
abstract class BesinDataBase:RoomDatabase() {
    abstract fun besinDAO():BesinlerDAO

    companion object{
        @Volatile private var  instance:BesinDataBase?=null

        private val lock=Any()

        operator fun invoke(context: Context)= instance?: synchronized(lock){
            instance?: createDB(context).also {
                instance=it
            }
        }

        private fun createDB(context: Context)=Room.databaseBuilder(
            context.applicationContext,BesinDataBase::class.java,"besindatabase"
        ).allowMainThreadQueries().build()
    }
}