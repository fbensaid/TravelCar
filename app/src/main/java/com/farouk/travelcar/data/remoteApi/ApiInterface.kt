package com.farouk.travelcar.data.remoteApi

import com.farouk.travelcar.data.model.CarResponse
import com.mtp.laboproject.global.Constants

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET(Constants.EndPoints.CAR_ENDPOINT)
    fun getCarList(): Deferred<Response<List<CarResponse>>>




}