package com.example.medtime.model.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.medtime.model.DataBase.ConexaoDB
import com.example.medtime.model.dto.Medicamento

class MedicamentoDAO(context: Context) {
    private val conexaoDB : ConexaoDB = ConexaoDB(context)
    private val medTimeDB : SQLiteDatabase = conexaoDB.writableDatabase

    fun cadastrarMedicamento(medicamento: Medicamento){
        val values = ContentValues()

        values.put("nome", medicamento.getNome())
        values.put("ref_imagem", medicamento.getImagem())

        medTimeDB.insert("medicamentos", null, values)
    }

    fun listarMedicamentos(): List<Medicamento> {
        val listaMedicamentos : MutableList<Medicamento> = ArrayList()
        val columns = arrayOf("id", "nome", "ref_imagem")
        val cursor = medTimeDB.query("medicamentos", columns, null, null, null, null, null)

        while(cursor.moveToNext()){
            val medicamento = Medicamento(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2).toByte()
            )

            listaMedicamentos.add(medicamento)
        }

        return listaMedicamentos
    }

    fun atualizarMedicamento(){

    }

    fun excluirMedicamento(){

    }


}
