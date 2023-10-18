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
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.medtime.R
import com.example.medtime.controller.CadastrarMedicamentoActivity
import com.example.medtime.model.dao.MedicamentoDAO
import com.example.medtime.model.dto.Medicamento

class AdapterMedicamentos(private val context : Context, private val medicamentos: MutableList<Medicamento>): RecyclerView.Adapter<AdapterMedicamentos.MedicamentosViewHolder>() {

    fun selecionarImagem(ref_imagem : Byte) : Int {
        var imagem : Int = 0

        when(ref_imagem){
            1.toByte() -> imagem = R.drawable.capsula
            2.toByte() -> imagem = R.drawable.injecao
            3.toByte() -> imagem = R.drawable.caixinha
            4.toByte() -> imagem = R.drawable.gotas
        }

        return imagem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentosViewHolder {
        val item = LayoutInflater.from(context).inflate(R.layout.medicamento_item, parent,false)
        return MedicamentosViewHolder(item)
    }

    override fun onBindViewHolder(holder: MedicamentosViewHolder, position: Int) {
        holder.foto.setImageResource(selecionarImagem(medicamentos[position].getImagem()))
        holder.nome.text = medicamentos[position].getNome()

        holder.deletar.setOnClickListener(View.OnClickListener {
            val medicamentoDAO = MedicamentoDAO(context)

            AlertDialog.Builder(context)
                .setTitle("Excluir Medicamento")
                .setMessage("Deseja excluir o medicamento ${medicamentos[position].getNome()}?")
                .setPositiveButton("Sim") { dialog, which ->

                    medicamentoDAO.excluirMedicamento(medicamentos[position])
                    medicamentos.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, medicamentos.size)

                    Toast.makeText(context, "Medicamento excluído com sucesso!", Toast.LENGTH_SHORT).show()

                }
                .setNegativeButton("Não", null)
                .show()

        })

        holder.editar.setOnClickListener(View.OnClickListener {
            startActivity(
                context,
                Intent(context, CadastrarMedicamentoActivity::class.java).putExtra("medicamento_alterar", medicamentos[position]),
                null
            )
        })

    }

    override fun getItemCount(): Int  = medicamentos.size

    inner  class MedicamentosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foto = itemView.findViewById<ImageView>(R.id.fotoGota2)
        val nome = itemView.findViewById<TextView>(R.id.txtNome)

        val deletar = itemView.findViewById<ImageView>(R.id.deletarCadastro)
        val editar = itemView.findViewById<ImageView>(R.id.editarCadastro)
    }

}
