package com.example.medtime.activities.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medtime.R
import com.example.medtime.activities.model.Medicamento

//essa parte aqui n entendi mt bemkkkk😥
class AdapterMedicamento(private val contexto: Context, private val medicamentos: MutableList<Medicamento>) : RecyclerView.Adapter<AdapterMedicamento.MedicamentoViewHolder>() {

        //metodo responsável por criar os itens
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentoViewHolder {
        val itemLista = LayoutInflater.from(contexto).inflate(R.layout.medicamentos_item, parent, false)
        val holder = MedicamentoViewHolder(itemLista)
        return holder
    }


    //metodo responsavel por exibir as visualizações pro usuario(ver todos os itens da nossa lista)
    override fun onBindViewHolder(holder: MedicamentoViewHolder, position: Int) {

        holder.foto.setImageResource(medicamentos[position].foto)
        holder.nome.text = medicamentos[position].nome
        holder.dosagem.text = medicamentos[position].dosagem
        holder.data.text = medicamentos[position].data

    }

        //passar o tamanho da lista(tamanho total)
    override fun getItemCount(): Int = medicamentos.size


        //inicialização das variaveis(ajeitar data,id e hora)
    inner class  MedicamentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val foto = itemView.findViewById<ImageView>(R.id.fotoGota)
        val nome = itemView.findViewById<TextView>(R.id.titulo_med)
        val dosagem = itemView.findViewById<TextView>(R.id.dosagemMed)
        val data = itemView.findViewById<TextView>(R.id.dataMed)


    }


}