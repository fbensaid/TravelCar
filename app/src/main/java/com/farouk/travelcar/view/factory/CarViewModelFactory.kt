package com.farouk.travelcar.view.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.farouk.travelcar.view.viewmodel.CarViewModel

@Suppress("UNCHECKED_CAST")
class CarViewModelFactory : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CarViewModel() as T
    }
}