package com.example.novaaplikacija

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.novaaplikacija.databinding.FragmentDodajGradBinding

class DodajGradFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDodajGradBinding>(inflater,
            R.layout.fragment_dodaj_grad, container, false)

        binding.dugmeDodajGrad.setOnClickListener({

            hideKeyboard()

            var naziv = binding.fragmentDodajGradNaziv.text.toString()
            var drzava = binding.fragmentDodajGradDrzava.text.toString()
            var latitude = binding.fragmentDodajGradLatituda.text.toString()
            var longitude = binding.fragmentDodajGradLongituda.text.toString()
            var opis = binding.fragmentDodajGradOpis.text.toString()

            if(naziv.isEmpty() || drzava.isEmpty() || latitude.isEmpty() || longitude.isEmpty() || opis.isEmpty()){
                Toast.makeText(activity, "Sva polja moraju biti popunjena", Toast.LENGTH_LONG).show()
            } else {
                val dodaniGrad = Grad(naziv, longitude, latitude, drzava, opis)
                GlobalnaKlasa.gradovi.add(dodaniGrad)

                Log.d(dodaniGrad.naziv, "aaaa")
                Log.d(GlobalnaKlasa.gradovi[GlobalnaKlasa.gradovi.size-1].naziv, "bbbb")

                it.findNavController().navigate(com.example.novaaplikacija.DodajGradFragmentDirections.actionIdDodajGradFragmentToIdListaGradovaFragment())
            }


        })

        return binding.root
    }

    // da se sakrije tastatura
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}