package com.example.deenmap.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.deenmap.databinding.FragmentHomeBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HomeFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private var googleMap: GoogleMap? = null

    // ---------- Fragment lifecycle -------------------------------------------------------------

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        // MapView must receive its own savedInstanceState bundle
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)

        // If you still need text from the ViewModel, observe it here
        // homeViewModel.text.observe(viewLifecycleOwner) { binding.myTextView.text = it }

        return binding.root
    }

    // ---------- Google Map callback --------------------------------------------------------------

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Sample marker – replace with your own logic
        val riyadh = LatLng(24.7136, 46.6753)
        map.addMarker(MarkerOptions().position(riyadh).title("Marker in Riyadh"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(riyadh, 12f))
    }

    // ---------- Forward MapView’s lifecycle ------------------------------------------------------

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        binding.mapView.onPause()
        super.onPause()
    }

    override fun onDestroyView() {
        binding.mapView.onDestroy()
        _binding = null
        super.onDestroyView()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }
}
