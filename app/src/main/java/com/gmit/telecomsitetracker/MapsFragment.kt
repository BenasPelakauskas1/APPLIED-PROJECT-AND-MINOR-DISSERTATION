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
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {
    // Snippets are hard-coded, should be updated to autofill status.
    private val callback = OnMapReadyCallback { googleMap ->
        // Add a marker in Galway and move the camera
        val galway = LatLng(53.272274, -9.053481)
        googleMap.addMarker(MarkerOptions().position(galway).title("GY069")
            .snippet("Complete")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(galway))

        // Marker Experiments (Two new markers in Galway)
        val mark1 = LatLng(53.261948, -9.071114)
        googleMap.addMarker(MarkerOptions().position(mark1).title("GY420")
            .snippet("Not Complete")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))

        googleMap.addMarker(
            MarkerOptions().position(LatLng(53.277942, -9.010461))
                .title("GY123")
                .snippet("Access Issues")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)))
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