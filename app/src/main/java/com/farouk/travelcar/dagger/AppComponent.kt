package com.farouk.travelcar.dagger

import android.app.Application
import com.farouk.travelcar.TravelCarApplication
import com.farouk.travelcar.data.dao.UserDao
import com.farouk.travelcar.data.database.AppDataBase
import com.farouk.travelcar.data.repository.UserRepository
import com.farouk.travelcar.view.ui.activity.MainActivity
import com.farouk.travelcar.view.ui.fragment.ProfilFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules= [AppModule::class, RoomModule::class])
interface AppComponent {


    fun inject(mainActivity: MainActivity)
    fun inject(profilFragment: ProfilFragment)

    fun inject(appComponent: TravelCarApplication)


    fun userDao(): UserDao

    fun appDatabase(): AppDataBase

    fun userRepository(): UserRepository

    fun application(): Application

}