package com.farouk.travelcar.data.repository


import com.farouk.travelcar.data.model.CarResponse
import com.farouk.travelcar.data.remoteApi.ApiInterface


class CarRepository(private val api: ApiInterface) : BaseRepository() {

    //get latest news using safe api call
    suspend fun getcar() :  MutableList<CarResponse>?{
        return safeApiCall(
            //await the result of deferred type
            call = {api.getCarList().await()},
            error = "Error fetching news"
            //convert to mutable list
        )?.toMutableList()
    }


}