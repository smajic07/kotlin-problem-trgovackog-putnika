package com.example.novaaplikacija

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.novaaplikacija.databinding.FragmentAlgoritamBinding

class AlgoritamFragment : Fragment() {

    lateinit var args : com.example.novaaplikacija.AlgoritamFragmentArgs
    lateinit var binding: FragmentAlgoritamBinding

    lateinit var grad : Grad
    lateinit var gradovi : MutableList<Grad>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_algoritam, container, false)

        args = com.example.novaaplikacija.AlgoritamFragmentArgs.fromBundle(arguments!!)

        grad = args.grad
        gradovi = args.gradovi.toMutableList<Grad>()

        binding.polazniGrad.text = grad.naziv

        binding.dugmePokreniAlgoritam.setOnClickListener({
            problemTrgovackogPutnika()

            var najboljaRuta : String = ""
            for(i in 0..(gradovi.size-2)){
                najboljaRuta += gradovi[i].naziv + " - "
            }
            najboljaRuta += gradovi[gradovi.size-1].naziv

            binding.najboljaRuta.text = najboljaRuta
            binding.najboljaRuta.setVisibility(View.VISIBLE)
            Log.d(najboljaRuta.toString(), "bbbb")

            binding.najboljaRuta.setTextColor(Color.parseColor("#74f127"));
            Toast.makeText(activity, "Najbolja ruta pronađena", Toast.LENGTH_LONG).show()

            it.setVisibility(View.GONE)
            binding.dugmeRuta.setVisibility(View.VISIBLE)
        })

        binding.dugmeRuta.setOnClickListener({
            it.findNavController().navigate(
                com.example.novaaplikacija.AlgoritamFragmentDirections.actionIdAlgoritamFragmentToNajkracaRutaFragment(
                    grad,
                    gradovi.toTypedArray()
                )
            )
        })

        return binding.root

    }

    fun udaljenostIzdmedjuDvaGrada(A: Grad, B: Grad): Double {

        val R = 6371e3;

        val A_latitude = A.latituda.toDouble()
        val B_latitude = B.latituda.toDouble()
        val A_longitude = A.longituda.toDouble()
        val B_longitude = B.longituda.toDouble()

        val f1 = A_latitude * Math.PI/180;
        val f2 = B_latitude * Math.PI/180;
        val w = (B_latitude-A_latitude) * Math.PI/180;
        val l = (B_longitude-A_longitude) * Math.PI/180;

        val a = Math.sin(w/2) * Math.sin(w/2) + Math.cos(f1) * Math.cos(f2) * Math.sin(l/2) * Math.sin(l/2);
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        val d = R * c;

        return d; // u metrima

    }

    fun problemTrgovackogPutnika() {

        var permutacije = mutableListOf<MutableList<Grad>>()
        var najmanjaUkupnaUdaljenost = 10000000.0
        var gradoviNajkracegPuta = mutableListOf<Grad>()
        var i = 1

        for(j in 0..(gradovi.size-1)){
            if(gradovi[j].naziv == grad.naziv){
                gradovi.removeAt(j)
                break
            }
        }

        fun pomocnaZaPermutacije(permutacija: MutableList<Grad>): Int {

            if (permutacija.size == gradovi.size){

                var trenutnaUdaljenost = 0.0
                var udaljenost = udaljenostIzdmedjuDvaGrada(grad, permutacija[0])
                trenutnaUdaljenost += udaljenost


                for(j in 0..(permutacija.size-2)){
                    udaljenost = udaljenostIzdmedjuDvaGrada(permutacija[j], permutacija[j+1])
                    trenutnaUdaljenost += udaljenost
                }

                udaljenost = udaljenostIzdmedjuDvaGrada(permutacija[permutacija.size-1], grad)
                trenutnaUdaljenost += udaljenost


                if(najmanjaUkupnaUdaljenost > trenutnaUdaljenost){
                    najmanjaUkupnaUdaljenost = trenutnaUdaljenost
                    gradoviNajkracegPuta = permutacija
                    gradovi = gradoviNajkracegPuta

                    var najboljaRuta = mutableListOf<String>()
                    for(grad2 in gradoviNajkracegPuta){
                        najboljaRuta.add(grad2.naziv)
                    }
                    binding.najboljaRuta.text = najboljaRuta.toString()
                    Log.d(najboljaRuta.toString(), "aaaa")

                }

                i++

                permutacije.add(permutacija)
                return permutacije.size

            }

            for(e in gradovi){
                var ima = false;
                for(e2 in permutacija){
                    if(e == e2){
                        ima = true
                        break;
                    }
                }
                var novaPermutacija = mutableListOf<Grad>()
                novaPermutacija.addAll(permutacija)
                novaPermutacija.add(e)
                if(!ima) pomocnaZaPermutacije(novaPermutacija)
            }

            return -1

        }

        pomocnaZaPermutacije(mutableListOf<Grad>())

    }
}