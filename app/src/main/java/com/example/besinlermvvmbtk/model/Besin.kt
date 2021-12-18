package com.example.besinlermvvmbtk.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "besin")
data class Besin (
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    @Expose
    var isim:String,

    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    @Expose
    var kalori:String,

    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    @Expose
    var karbonhidrat:String,

    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    @Expose
    var protein:String,

    @ColumnInfo(name = "yag")
    @SerializedName("yag")
    @Expose
    var yag:String,

    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    @Expose
    var gorsel:String
    ){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}