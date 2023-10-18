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


class AdapterAgendamento(private val contexto: Context, private val agendamentos: MutableList<Agendamento>) : RecyclerView.Adapter<AdapterAgendamento.AgendamentosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendamentosViewHolder {
        val item = LayoutInflater.from(contexto).inflate(R.layout.agendamento_item, parent, false)
        return AgendamentosViewHolder(item)
    }

    override fun onBindViewHolder(holder: AgendamentosViewHolder, position: Int) {
        holder.nome.text = agendamentos[position].getMedicamento().getNome()
        holder.foto.setImageResource(agendamentos[position].getMedicamento().getImagem().toInt())
        holder.dataInicio.text = agendamentos[position].getDataDeInicio()
        holder.dataFinal.text = agendamentos[position].getDataDoFim()
        holder.hora.text = agendamentos[position].getHorario()
        holder.dosagem.text = agendamentos[position].getDosagem().toString()
        holder.unidadeMedida.text = agendamentos[position].getUnidadeDeMedida()
    }

    override fun getItemCount(): Int = agendamentos.size

    inner class  AgendamentosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome = itemView.findViewById<TextView>(R.id.titulo_agendamento)
        val foto = itemView.findViewById<ImageView>(R.id.fotoGota)
        val dataInicio = itemView.findViewById<TextView>(R.id.dtIncio)
        val dataFinal = itemView.findViewById<TextView>(R.id.dtFinal)
        val hora = itemView.findViewById<TextView>(R.id.txtHorario)
        val dosagem = itemView.findViewById<TextView>(R.id.dosagem)
        val unidadeMedida = itemView.findViewById<TextView>(R.id.unidadeMedida)

        val deletar = itemView.findViewById<ImageView>(R.id.deletarAgendamento)
        val editar = itemView.findViewById<ImageView>(R.id.editarAgendamento)
    }

}

