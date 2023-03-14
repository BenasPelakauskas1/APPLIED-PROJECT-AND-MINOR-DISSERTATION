package com.gmit.telecomsitetracker

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        // Add a marker in Galway and move the camera
        val galway = LatLng(53.272274, -9.053481)
        googleMap.addMarker(MarkerOptions().position(galway).title("Marker in Galway"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(galway))

        // Marker Experiments (Two new markers in Galway)
        val mark1 = LatLng(53.261948, -9.071114)
        googleMap.addMarker(MarkerOptions().position(mark1).title("Marker 1"))

        googleMap.addMarker(
            MarkerOptions().position(LatLng(53.277942, -9.010461))
                .title("Marker 2"))
    }
    //Used to display fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}