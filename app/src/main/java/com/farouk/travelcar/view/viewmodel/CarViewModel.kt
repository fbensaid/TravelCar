package com.farouk.travelcar.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.farouk.travelcar.data.model.CarResponse
import com.farouk.travelcar.data.remoteApi.Apifactory
import com.farouk.travelcar.data.repository.CarRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CarViewModel : ViewModel() {

    //create a new Job
    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)
    //initialize news repo
    private val carRepository: CarRepository = CarRepository(Apifactory.Api)
    //live data that will be populated as news updates
    val labsLiveData = MutableLiveData<MutableList<CarResponse>>()

    fun getCars() {
        ///launch the coroutine scope
        scope.launch {
            //get latest news from news repo
            val latescar = carRepository.getcar()
            //post the value inside live data
            labsLiveData.postValue(latescar)

        }
    }

    fun cancelRequests() = coroutineContext.cancel()


    override fun onCleared() {
        super.onCleared()

        this.parentJob.cancel()
    }
}