package com.farouk.travelcar.view.ui.fragment

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.Nullable
import com.farouk.travelcar.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DetailsCarBottomSheet : BottomSheetDialogFragment() {

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {

// get the views and attach the listener

        return inflater.inflate(
            R.layout.bottomshit_details_car, container,
            false
        )

    }

    companion object {
        fun newInstance(): DetailsCarBottomSheet {
            return DetailsCarBottomSheet()
        }
    }
}