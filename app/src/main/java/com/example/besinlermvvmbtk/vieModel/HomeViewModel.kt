package com.example.besinlermvvmbtk.vieModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.besinlermvvmbtk.model.Besin
import com.example.besinlermvvmbtk.services.ApiUtils
import com.example.besinlermvvmbtk.services.BesinAPI
import com.example.besinlermvvmbtk.services.BesinDataBase
import com.example.besinlermvvmbtk.utils.CustomSP
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): BaseViewModel(application) {
    val besinler=MutableLiveData<List<Besin>>()
    val besinError=MutableLiveData<Boolean>()
    val besinLoading=MutableLiveData<Boolean>()
    private val api:BesinAPI=ApiUtils.getBesinAPI()
    private val disposable=CompositeDisposable()
    private val customSP= CustomSP(getApplication())
    private val reftime=10*60*1000*1000*1000L

    fun refreshData(){
        val upTime=customSP.getTime()
        if (upTime!=null && upTime!=0L && System.nanoTime()-upTime<reftime){
            getBesinSQLite()
        }else{
            getBesinAPI()
        }
    }

    fun refreshLayout(){
        getBesinAPI()
    }

    private fun getBesinAPI(){
        besinLoading.value=true
        disposable.add(
            api.besinGetir()
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Besin>>(){
                    override fun onSuccess(t: List<Besin>) {
                        storeqSQLite(t)
                    }

                    override fun onError(e: Throwable) {
                        besinLoading.value=false
                        besinError.value=true
                    }

                })
        )
    }

    private fun storeqSQLite(list: List<Besin>){
        launch {
            val dao=BesinDataBase(getApplication()).besinDAO()
            dao.deleteBesin()
            val longlist=dao.insertAllBesin(*list.toTypedArray())
            var i=0
            while (i<list.size){
                list[i].uuid=longlist[i].toInt()
                i++
            }
            besinler.value=list
            besinLoading.value=false
            besinError.value=false
        }
        customSP.saveTime(System.nanoTime())
    }

    private fun getBesinSQLite(){
        besinLoading.value=true
        launch {
            val list=BesinDataBase(getApplication()).besinDAO().getAllBesin()
            besinler.value=list
            besinLoading.value=false
            besinError.value=false
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}