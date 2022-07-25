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
import com.google.android.gms.maps.model.PolylineOptions

class NajkracaRutaFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->

        val args = com.example.novaaplikacija.NajkracaRutaFragmentArgs.fromBundle(arguments!!)

        val grad = args.grad
        val gradovi = args.gradovi.toMutableList<Grad>()
        val gradPozicija = LatLng(grad.latituda.toDouble(), grad.longituda.toDouble())

        googleMap.addMarker(MarkerOptions().position(gradPozicija).title("Marker u ${grad.naziv}"))

        googleMap.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(
                    LatLng(grad.latituda.toDouble(), grad.longituda.toDouble()),
                    LatLng(gradovi[0].latituda.toDouble(), gradovi[0].longituda.toDouble())
                ))

        for(i in 1..(gradovi.size-1)){
            googleMap.addPolyline(
                PolylineOptions()
                    .clickable(true)
                    .add(
                        LatLng(gradovi[i-1].latituda.toDouble(), gradovi[i-1].longituda.toDouble()),
                        LatLng(gradovi[i].latituda.toDouble(), gradovi[i].longituda.toDouble())
                    ))
        }

        googleMap.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(
                    LatLng(gradovi[gradovi.size-1].latituda.toDouble(), gradovi[gradovi.size-1].longituda.toDouble()),
                    LatLng(grad.latituda.toDouble(), grad.longituda.toDouble())
                ))

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(gradPozicija))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_najkraca_ruta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }

}