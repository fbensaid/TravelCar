package com.farouk.travelcar.view.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.farouk.travelcar.R
import com.farouk.travelcar.data.model.CarResponse
import com.farouk.travelcar.view.adapter.CarAdapter
import com.farouk.travelcar.view.factory.CarViewModelFactory
import com.farouk.travelcar.view.listener.CarClickListener
import com.farouk.travelcar.view.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.fragment_car.*


/**
 * A placeholder fragment containing a simple view.
 */
class CarFragment : Fragment(), CarClickListener {

    private lateinit var labsViewModel: CarViewModel
    private lateinit var laboAdapter: CarAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_car, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRepo()
    }



    private fun setRepo() {
        val factory = CarViewModelFactory()
        labsViewModel = ViewModelProviders.of(this, factory)
            .get(CarViewModel::class.java)
        labsViewModel.getCars()
        labsViewModel.labsLiveData.observe(viewLifecycleOwner, Observer { laboratory ->
            recycleview_laboratory.also {
                it.layoutManager = GridLayoutManager(requireContext(), 2)
                it.setHasFixedSize(true)
                laboAdapter = CarAdapter(laboratory, this)
                it.adapter = laboAdapter
                searchLaboratory()
            }
        })
    }

    private fun searchLaboratory() {
        simpleSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                laboAdapter.filter.filter(newText)
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                laboAdapter.filter.filter(query)
                return false
            }
        })
    }

    override fun onRecyclerViewItemClick(view: View, car: CarResponse) {
        DetailsCarBottomSheet.newInstance(car).show(fragmentManager!!, "tessst")
    }



}