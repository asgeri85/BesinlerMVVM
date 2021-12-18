package com.example.besinlermvvmbtk.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.besinlermvvmbtk.model.Besin

@Dao
interface BesinlerDAO {

    @Insert
    fun insertAllBesin(vararg besin:Besin):List<Long>

    @Query("SELECT * FROM besin")
    fun getAllBesin():List<Besin>

    @Query("SELECT * FROM besin WHERE uuid=:besin_id")
    fun getBesin(besin_id:Int):Besin

    @Query("DELETE FROM besin")
    fun deleteBesin()
}