package com.example.novaaplikacija

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novaaplikacija.databinding.FragmentListaGradovaBinding

class ListaGradovaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentListaGradovaBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_lista_gradova, container, false)

        val cityAdapter = GradAdapter()
        cityAdapter.listaGradova = GlobalnaKlasa.gradovi

        Log.d(GlobalnaKlasa.gradovi[GlobalnaKlasa.gradovi.size-1].latituda, "cccc")

        val linearLayoutManager = LinearLayoutManager (getContext());

        var recyclerView: RecyclerView = binding.myRecyclerView;
        recyclerView.layoutManager = linearLayoutManager;
        recyclerView.adapter = cityAdapter;
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        return binding.root

    }

}