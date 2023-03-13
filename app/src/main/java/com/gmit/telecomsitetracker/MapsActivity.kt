package com.gmit.telecomsitetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.gmit.telecomsitetracker.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

      private lateinit var mMap: GoogleMap
      private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

      override fun onMapReady(googleMap: GoogleMap) {
         mMap = googleMap

          // Add a marker in Galway and move the camera
          val galway = LatLng(53.272274, -9.053481)
          mMap.addMarker(MarkerOptions().position(galway).title("Marker in Galway"))
          mMap.moveCamera(CameraUpdateFactory.newLatLng(galway))

          // Marker Experiments (Two new markers in Galway)
          val mark1 = LatLng(53.261948, -9.071114)
          mMap.addMarker(MarkerOptions().position(mark1).title("Marker 1"))

          mMap.addMarker(MarkerOptions().position(LatLng(53.277942,-9.010461))
              .title("Marker 2"))
      }
}