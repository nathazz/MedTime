package com.example.medtime.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.medtime.R
import com.example.medtime.controller.CadastrarAgendamentoActivity
import com.example.medtime.controller.CadastrarMedicamentoActivity
import com.example.medtime.model.dao.AgendamentoDAO
import com.example.medtime.model.dto.Agendamento


class AdapterAgendamento(private val contexto: Context, private val agendamentos: MutableList<Agendamento>) : RecyclerView.Adapter<AdapterAgendamento.AgendamentosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendamentosViewHolder {
        val item = LayoutInflater.from(contexto).inflate(R.layout.agendamento_item, parent, false)
        return AgendamentosViewHolder(item)
    }

    private fun selecionarImagem(ref_imagem : Byte) : Int {
        var imagem : Int = 0

        when(ref_imagem){
            1.toByte() -> imagem = R.drawable.capsula
            2.toByte() -> imagem = R.drawable.injecao
            3.toByte() -> imagem = R.drawable.caixinha
            4.toByte() -> imagem = R.drawable.gotas
        }

        return imagem
    }

    override fun onBindViewHolder(holder: AgendamentosViewHolder, position: Int) {
        holder.nome.text = agendamentos[position].getMedicamento().getNome()
        holder.foto.setImageResource(selecionarImagem(agendamentos[position].getMedicamento().getImagem()))
        holder.dataInicio.text = agendamentos[position].getDataDeInicio()
        holder.dataFinal.text = agendamentos[position].getDataDoFim()
        holder.hora.text = agendamentos[position].getHorario()
        holder.dosagem.text = agendamentos[position].getDosagem().toString()
        holder.unidadeMedida.text = agendamentos[position].getUnidadeDeMedida()

        holder.deletar.setOnClickListener(View.OnClickListener {
            val agendamentoDAO = AgendamentoDAO(contexto)

            AlertDialog.Builder(contexto)
                .setTitle("Excluir Agendamento")
                .setMessage("Tem certeza que deseja excluir este agendamento?")
                .setPositiveButton("Sim") { dialog, which ->

                    agendamentoDAO.excluirAgendamento(agendamentos[position])
                    agendamentos.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, agendamentos.size)

                    Toast.makeText(contexto, "Agendamento excluído com sucesso!", Toast.LENGTH_SHORT).show()

                }
                .setNegativeButton("Não", null)
               .show()
        })

        holder.editar.setOnClickListener(View.OnClickListener {
            ContextCompat.startActivity(
                contexto,
                Intent(
                    contexto,
                    CadastrarAgendamentoActivity::class.java
                ).putExtra("agendamento_alterar", agendamentos[position]),
                null
            )
        })
    }

    override fun getItemCount(): Int = agendamentos.size

    inner class AgendamentosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

