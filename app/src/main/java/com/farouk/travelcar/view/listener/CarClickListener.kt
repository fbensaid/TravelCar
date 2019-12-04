package com.farouk.travelcar.view.listener

import android.view.View
import com.farouk.travelcar.data.model.CarResponse

interface CarClickListener {
    fun onRecyclerViewItemClick(view: View, labo: CarResponse)

}
