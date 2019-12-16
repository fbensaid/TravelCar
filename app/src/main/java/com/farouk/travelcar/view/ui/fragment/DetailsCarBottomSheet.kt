package com.farouk.travelcar.view.ui.fragment

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.Nullable
import com.farouk.travelcar.R
import com.farouk.travelcar.data.model.CarResponse
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.farouk.travelcar.databinding.BottomSheetDetailsCarBinding
import android.R.attr.data
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil.*
import com.squareup.picasso.Picasso


open class DetailsCarBottomSheet : BottomSheetDialogFragment() {



    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        var car =arguments!!.get("car") as CarResponse

        val binding:BottomSheetDetailsCarBinding = inflate(
            inflater, R.layout.bottom_sheet_details_car, container, false
        )
        val view = binding.root
        //here data must be an instance of the class MarsDataProvider
        binding.temp=car
        return view
    }

        companion object {
            fun newInstance(catId: CarResponse) = DetailsCarBottomSheet().apply {
                arguments = Bundle().apply {
                    putSerializable("car",catId)
                }
            }
            @JvmStatic
            @BindingAdapter("android:src")
            fun setPictureUri(view: ImageView, imageUri: String?) {
                Picasso.get().load(imageUri).into(view)
            }

    }
}