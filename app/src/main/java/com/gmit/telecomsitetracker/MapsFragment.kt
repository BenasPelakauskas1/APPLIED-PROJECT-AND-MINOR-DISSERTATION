    package com.gmit.telecomsitetracker

import android.content.ContentValues.TAG
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.GoogleMap.*

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore

    class MapsFragment : Fragment() {
    // Add a marker in Galway and move the camera
    private val callback = OnMapReadyCallback { googleMap ->
        // Add zoom control gestures to map
        googleMap.uiSettings.isZoomControlsEnabled = true
        // Change map type to satellite
        googleMap.mapType = MAP_TYPE_HYBRID

        // Retrieve co-ordinates from DB
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("sites")

        collectionRef.get().addOnSuccessListener { result ->
            for (document in result) {
                // Get coordinate data from Firestore
                val latitude = document.getDouble("latitude")
                val longitude = document.getDouble("longitude")
                val siteID = document.getString("siteID")
                val status = document.getString("status")

                // Create a LatLng object from the coordinate data
                val latLng = LatLng(latitude!!, longitude!!)

                // Add a marker on map with site ID and status.
                googleMap.addMarker(MarkerOptions().position(latLng).title(siteID)
                    .snippet(status))
            }

            // 3 varieties of hard-coded co-ordinates
            /*  val galway = LatLng(53.272274, -9.053481)
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
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)))*/
        }
            // Handle failure
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error getting documents: ", exception)
            }
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