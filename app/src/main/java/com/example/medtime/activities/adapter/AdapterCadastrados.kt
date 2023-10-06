package com.example.medtime.activities.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medtime.R
import com.example.medtime.activities.model.MedCadastrado

class AdapterCadastrados(private val contexto : Context, private val cadastro: MutableList<MedCadastrado>): RecyclerView.Adapter<AdapterCadastrados.CadastradosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CadastradosViewHolder {

            val item = LayoutInflater.from(contexto).inflate(R.layout.cadastrados_item, parent,false)
            val viewHolder = CadastradosViewHolder(item)

            return viewHolder
    }

    override fun onBindViewHolder(holder: CadastradosViewHolder, position: Int) {

        holder.fotoCad.setImageResource(cadastro[position].fotoCad)
        holder.nomeCad.text = cadastro[position].nomeCad

    }

    override fun getItemCount(): Int  = cadastro.size


    inner  class CadastradosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val fotoCad = itemView.findViewById<ImageView>(R.id.fotoGota2)
            val nomeCad = itemView.findViewById<TextView>(R.id.txtNome)
    }

}