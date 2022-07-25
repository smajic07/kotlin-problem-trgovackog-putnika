package com.example.novaaplikacija

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.novaaplikacija.databinding.FragmentDetaljiGradaBinding

class DetaljiGradaFragment : Fragment() {

    lateinit var binding: FragmentDetaljiGradaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detalji_grada, container, false)

        var args = com.example.novaaplikacija.DetaljiGradaFragmentArgs.fromBundle(arguments!!)
        val grad = args.grad
        val gradovi = args.gradovi.toMutableList<Grad>()

        var noviGradovi = mutableListOf<Grad>()

        for(grad2 in gradovi){
            if(grad.naziv != grad2.naziv){
                noviGradovi.add(grad2)
            }
        }

        binding.fragmentDetaljiGradaNaziv.text = grad.naziv
        binding.fragmentDetaljiGradaDrzava.text = grad.drzava
        binding.fragmentDetaljiGradaLatituda.text = grad.latituda
        binding.fragmentDetaljiGradaLongituda.text = grad.longituda
        binding.fragmentDetaljiGradaOpis.text = grad.opis

        binding.dugmePrikaziNaMapi.setOnClickListener({
            it.findNavController().navigate(
                com.example.novaaplikacija.DetaljiGradaFragmentDirections.actionIdDetaljiGradaFragmentToIdGradNaMapiFragment(
                    grad,
                    noviGradovi.toTypedArray()
                )
            )
        })

        binding.dugmeIdiNaAlgoritamFragment.setOnClickListener({
            it.findNavController().navigate(
                com.example.novaaplikacija.DetaljiGradaFragmentDirections.actionIdDetaljiGradaFragmentToIdAlgoritamFragment(
                    grad,
                    noviGradovi.toTypedArray()
                )
            )
        })

        setHasOptionsMenu(true)

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.share_menu, menu)
    }

    private fun getShareIntent() : Intent {

        val shareIntent = Intent(Intent.ACTION_SEND)
        val grad = binding.fragmentDetaljiGradaNaziv.text
        val latituda = binding.fragmentDetaljiGradaLatituda.text
        val longituda = binding.fragmentDetaljiGradaLongituda.text
        shareIntent.setType("text/plain").
            putExtra(Intent.EXTRA_TEXT, "Trgovački putnik by Edin Smajić & Armin Hadrović:\n ${grad}: (${latituda}, ${longituda})")
        return shareIntent

    }

    private fun dijeljenjeUspjesno() {
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId){
            R.id.podijeli -> dijeljenjeUspjesno()
        }
        return super.onOptionsItemSelected(item)
    }

}