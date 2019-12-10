package com.farouk.travelcar

import android.app.Application
import android.content.Context
import com.farouk.travelcar.dagger.AppComponent
import com.farouk.travelcar.dagger.AppModule
import com.farouk.travelcar.dagger.DaggerAppComponent
import com.farouk.travelcar.dagger.RoomModule


class TravelCarApplication : Application() {

//    lateinit var mAppComponent:AppComponent
//    override fun onCreate() {
//        super.onCreate()
//        instances = this
//        mAppComponent = DaggerAppComponent.builder()
//            .appModule(AppModule(this))
//            .roomModule(RoomModule(this))
//            .build()
//        mAppComponent.inject(this)
//
//    }
//
//    override fun onTerminate() {
//        super.onTerminate()
//    }
//
//    companion object{
//        lateinit var instances:TravelCarApplication
//        fun getContext(): Context {
//            return instances.applicationContext
//
//        }
//    }



    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initDI()
    }

    private fun initDI() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule(this))
            .build()
    }


}