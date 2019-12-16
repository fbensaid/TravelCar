package com.farouk.travelcar.view.ui.activity

import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.farouk.travelcar.R
import com.farouk.travelcar.TravelCarApplication
import com.farouk.travelcar.data.repository.UserRepository
import com.farouk.travelcar.view.adapter.SectionsPagerAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var workoutRepository: UserRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //init dagger
        TravelCarApplication.appComponent.inject(this)
        setupPager()
    }

    private fun setupPager() {
        val sectionsPagerAdapter =
            SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //send data to all fragment if need
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }




}