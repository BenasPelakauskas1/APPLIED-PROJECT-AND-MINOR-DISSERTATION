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

                // Create a LatLng object from the coordinate data (!! forces not null)
                val latLng = LatLng(latitude!!, longitude!!)

                // Colour code markers based off status
                var markerColour : Float = 0f
                when (status) {
                    "TODO" -> markerColour = BitmapDescriptorFactory.HUE_BLUE
                    "ACCESS" -> markerColour = BitmapDescriptorFactory.HUE_RED
                    "DONE" -> markerColour = BitmapDescriptorFactory.HUE_GREEN
                    else -> {
                        print("This should not have happened")
                    }
                }

                // Add a marker on map with site ID and status.
                googleMap.addMarker(MarkerOptions().position(latLng).title(siteID)
                    .snippet(status)
                    .icon(BitmapDescriptorFactory.defaultMarker(markerColour)))
            }
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