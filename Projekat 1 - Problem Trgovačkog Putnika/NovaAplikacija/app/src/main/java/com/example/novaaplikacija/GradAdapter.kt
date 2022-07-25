package com.example.novaaplikacija

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class GradAdapter: RecyclerView.Adapter<GradAdapter.CityViewHolder>() {

    lateinit var listaGradova : MutableList<Grad>;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {

        val layoutType = R.layout.list_item;
        val view: View = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent, false)

        return CityViewHolder(view);

    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {

        val grad: Grad = listaGradova[position]
        holder.grad.text = grad.naziv;
        holder.latituda.text = grad.latituda
        holder.longituda.text = grad.longituda
        val gradovi = listaGradova.toTypedArray()

        holder.okvirReda.setOnClickListener({
            it.findNavController().navigate(ListaGradovaFragmentDirections.actionIdListaGradovaFragmentToDetaljiGradaFragment(grad, gradovi))
        })

    }

    override fun getItemCount(): Int {
        return listaGradova.size;
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val grad = itemView.findViewById<TextView>(R.id.list_item_nazivGrada);
        val longituda = itemView.findViewById<TextView>(R.id.list_item_latitudaGrada);
        val latituda = itemView.findViewById<TextView>(R.id.list_item_longitudaGrada);
        val okvirReda = itemView.findViewById<View>(R.id.list_item_okvir);

    }

}