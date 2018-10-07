package com.example.dom.firstkotlinproject.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.dom.firstkotlinproject.R
import com.example.dom.firstkotlinproject.interfaces.IApiResponse
import com.example.dom.firstkotlinproject.managers.DataManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import models.SportItemLocation
import retrofit2.Call

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mGetSportItemCall: Call<List<SportItemLocation>>? = null
    private var mListSportItemLocation : List<SportItemLocation>? = null
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        refreshData();
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    fun refreshData(){
        mGetSportItemCall = DataManager.getSport(object : IApiResponse<SportItemLocation>
        {
            override fun onSuccess(list: List<SportItemLocation>) {
                mListSportItemLocation = list;
                for (item in list){
                    for(features in item.features){
                        for(coordinates in features.geometry.coordinates){
                            for(point in coordinates){
                                var pointlngLat : LatLng = LatLng(point.get(1), point.get(0))
                                mMap.addMarker(MarkerOptions().position(pointlngLat).title(features.propertie.nom))
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointlngLat,10.0.toFloat()))
                            }
                        }
                    }
                }

            }

            override fun onError(t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        )
    }
}
