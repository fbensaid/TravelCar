package com.farouk.travelcar.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.farouk.travelcar.R

/**
 * A placeholder fragment containing a simple view.
 */
class ProfilFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): ProfilFragment {
            return ProfilFragment()
        }
    }
}