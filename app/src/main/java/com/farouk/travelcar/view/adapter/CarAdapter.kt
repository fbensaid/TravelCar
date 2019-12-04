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
import com.farouk.travelcar.databinding.RecycleviewLaboratoryBinding
import com.farouk.travelcar.view.listener.CarClickListener
import com.squareup.picasso.Picasso
import com.farouk.travelcar.R


class CarAdapter(
    private val listofLaboratory: List<CarResponse>,
    private val listner: CarClickListener

) : RecyclerView.Adapter<CarAdapter.CarViewHolder>(), Filterable {
    private var filtredListofLaboratory = listofLaboratory
    private var resultListOfSearch = arrayListOf<CarResponse>()

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                filtredListofLaboratory = if (p0.isNullOrEmpty())
                    listofLaboratory
                else {
                    resultListOfSearch.clear()
                    listofLaboratory.forEach {
                        if (it.picture.toLowerCase().contains(p0.toString()))
                            resultListOfSearch.add(it)
                    }
                    resultListOfSearch
                }
                var filtredResult = FilterResults()
                filtredResult.values = filtredListofLaboratory
                return filtredResult
            }

            override fun publishResults(p0: CharSequence?, filtredResult: FilterResults?) {
                filtredListofLaboratory = listOf()
                filtredListofLaboratory = filtredResult!!.values as List<CarResponse>
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
        return filtredListofLaboratory.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CarViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycleview_laboratory,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.recycleviewLaboratoryBinding.laboratoryListResponseData =
            filtredListofLaboratory[position]

        holder.recycleviewLaboratoryBinding.cardView.setOnClickListener {
            listner.onRecyclerViewItemClick(
                holder.recycleviewLaboratoryBinding.cardView,
                filtredListofLaboratory[position]
            )
        }

    }


    inner class CarViewHolder(
        val recycleviewLaboratoryBinding: RecycleviewLaboratoryBinding
    ) : RecyclerView.ViewHolder(recycleviewLaboratoryBinding.root)
}

