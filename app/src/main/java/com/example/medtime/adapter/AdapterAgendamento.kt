package com.example.medtime.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medtime.R
import com.example.medtime.model.dto.Agendamento

//adaptando o recyclerView para um MutableList
class AdapterAgendamento(private val contexto: Context, private val agendamentos: MutableList<Agendamento>) : RecyclerView.Adapter<AdapterAgendamento.MedicamentoViewHolder>() {

        //metodo responsável por criar os itens
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentoViewHolder {
        val itemLista = LayoutInflater.from(contexto).inflate(R.layout.agendamento_item, parent, false)
        val holder = MedicamentoViewHolder(itemLista)
        return holder
    }


    //metodo responsavel por exibir as visualizações pro usuario(ver todos os itens da nossa lista)
    override fun onBindViewHolder(holder: MedicamentoViewHolder, position: Int) {

        holder.foto.setImageResource(agendamentos[position].getMedicamento().getImagem().toInt())
        holder.dataI.text = agendamentos[position].getDataDeInicio().toString()
        holder.dataF.text = agendamentos[position].getDataDoFim().toString()
        holder.hora.text = agendamentos[position].getHorario().toString()

    }

        //passar o tamanho da lista(tamanho total)
    override fun getItemCount(): Int = agendamentos.size


        //inicialização das variaveis(ajeitar data,id e hora)
    inner class  MedicamentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            //da pra usar o binding, mas usei esse por causa de segurança mesmo

        val foto = itemView.findViewById<ImageView>(R.id.fotoGota)
        val nome = itemView.findViewById<TextView>(R.id.titulo_med)
        val dataI = itemView.findViewById<TextView>(R.id.dtIncio)
        val dataF = itemView.findViewById<TextView>(R.id.dtFinal)
        val hora = itemView.findViewById<TextView>(R.id.txtHorario)

    }


}

