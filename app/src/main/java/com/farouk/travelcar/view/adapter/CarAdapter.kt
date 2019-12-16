package com.farouk.travelcar.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farouk.travelcar.data.model.CarResponse
import com.farouk.travelcar.databinding.RecycleviewCarBinding
import com.farouk.travelcar.view.listener.CarClickListener
import com.squareup.picasso.Picasso
import com.farouk.travelcar.R

class CarAdapter(
    private val listofCar: List<CarResponse>,
    private val listner: CarClickListener

) : RecyclerView.Adapter<CarAdapter.CarViewHolder>(), Filterable {
    private var filtredListofCar = listofCar
    private var resultListOfSearch = arrayListOf<CarResponse>()

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                filtredListofCar = if (p0.isNullOrEmpty())
                    listofCar
                else {
                    resultListOfSearch.clear()
                    listofCar.forEach {
                        if (it.model.toLowerCase().contains(p0.toString()))
                            resultListOfSearch.add(it)
                    }
                    resultListOfSearch
                }
                var filtredResult = FilterResults()
                filtredResult.values = filtredListofCar
                return filtredResult
            }

            override fun publishResults(p0: CharSequence?, filtredResult: FilterResults?) {
                filtredListofCar= listOf()
                filtredListofCar = filtredResult!!.values as List<CarResponse>
                notifyDataSetChanged()
            }
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun setImageUri(view: ImageView, imageUri: String?) {
            Picasso.get().load(imageUri).into(view)
        }
    }

    override fun getItemCount(): Int {
        return filtredListofCar.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CarViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycleview_car,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.recycleviewCaryBinding.carListResponseData =
            filtredListofCar[position]

        holder.recycleviewCaryBinding.cardView.setOnClickListener {
            listner.onRecyclerViewItemClick(
                holder.recycleviewCaryBinding.cardView,
                filtredListofCar[position]
            )
        }

    }


    inner class CarViewHolder(
        val recycleviewCaryBinding: RecycleviewCarBinding
    ) : RecyclerView.ViewHolder(recycleviewCaryBinding.root)
}


