package com.example.besinlermvvmbtk.vieModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.besinlermvvmbtk.model.Besin
import com.example.besinlermvvmbtk.services.BesinDataBase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) :BaseViewModel(application){
    val besinLiveData=MutableLiveData<Besin>()

    fun getDataRoom(id:Int){
        launch {
            val besin=BesinDataBase(getApplication()).besinDAO().getBesin(id)
            besinLiveData.value=besin
        }
    }
}