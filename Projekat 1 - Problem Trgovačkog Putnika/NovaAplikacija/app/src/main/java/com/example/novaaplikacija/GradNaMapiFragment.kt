package com.example.novaaplikacija

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GradNaMapiFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->

        val args =  DetaljiGradaFragmentArgs.fromBundle(arguments!!)
        val gradPozicija = LatLng(args.grad.latituda.toDouble(), args.grad.longituda.toDouble())
        googleMap.addMarker(MarkerOptions().position(gradPozicija).title("Marker u ${args.grad.naziv}"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(gradPozicija))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_grad_na_mapi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }

}