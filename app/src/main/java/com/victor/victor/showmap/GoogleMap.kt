package com.victor.victor.showmap

import android.os.Bundle

import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.victor.victor.R
import com.victor.victor.learningRetrofit.Retrofit.Repostitory.Repository
import com.victor.victor.showmap.Base.BaseFragmentActivity
import com.victor.victor.showmap.getPalces.PlaceViewModelFactory
import com.victor.victor.showmap.getPalces.PlacesViewModel


class GoogleMap : BaseFragmentActivity(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap;
    private lateinit var supportMapFragment:SupportMapFragment;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        supportMapFragment.getMapAsync(this)

        val repository = Repository()
        val viewModelFactory = PlaceViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(PlacesViewModel::class.java)

    }



    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val tutorialsPoint = LatLng(21.0, 57.0)
        val tutorialsPoin = LatLng(21.0, 60.0)
        val tutorialsPoi = LatLng(22.0, 57.0)
        val tutorialsPo = LatLng(25.0, 57.0)
        googleMap.addMarker(MarkerOptions().position(tutorialsPoint).title("TutorialsPoint"))
        googleMap.addMarker(MarkerOptions().position(tutorialsPo).title("yu"))
        googleMap.addMarker(MarkerOptions().position(tutorialsPoi).title("jjkkkk"))
        googleMap.addMarker(MarkerOptions().position(tutorialsPoin).title("uiiiinn"))
    }





    override fun showTransition(): Boolean =true

    override fun enableFullScreen(): Boolean= true

    override fun getLayoutId(): Int =R.layout.google_map
}