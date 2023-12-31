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
        val valores = ContentValues()

        valores.put("nome", medicamento.getNome())
        valores.put("ref_imagem", medicamento.getImagem())

        medTimeDB.insert("medicamentos", null, valores)
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

    fun listarNomesMedicamentos(): List<String>{
        val listaNomes : MutableList<String> = ArrayList()
        val columns = arrayOf("nome")
        val cursor = medTimeDB.query("medicamentos", columns, null, null, null, null, null)

        while(cursor.moveToNext()){
            listaNomes.add(cursor.getString(0))
        }

        return listaNomes
    }

    fun pegarMedicamentoPorId(id: Int): Medicamento {
        val columns = arrayOf("id", "nome", "ref_imagem")
        val cursor = medTimeDB.query("medicamentos", columns, null, null, null, null, null)
        lateinit var medicamento : Medicamento

        while (cursor.moveToNext()){
            if(cursor.getInt(0) == id){
                medicamento = Medicamento(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2).toByte()
                )
                break
            }
        }

        return medicamento
    }

    fun pegarMedicamentoPorNome(nome : String): Medicamento {
        val columns = arrayOf("id", "nome", "ref_imagem")
        val cursor = medTimeDB.query("medicamentos", columns, null, null, null, null, null)
        lateinit var medicamento : Medicamento

        while(cursor.moveToNext()){
            if(cursor.getString(1) == nome){
                medicamento = Medicamento(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2).toByte()
                )
                break
            }
        }

        return medicamento
    }

    fun atualizarMedicamento(medicamento: Medicamento){
        val valores = ContentValues()

        valores.put("nome", medicamento.getNome())
        valores.put("ref_imagem", medicamento.getImagem())

        medTimeDB.update("medicamentos", valores, "id = ?", arrayOf(medicamento.getId().toString()))
    }

    fun excluirMedicamento(medicamento: Medicamento){
        medTimeDB.delete("medicamentos", "id = ?", arrayOf(medicamento.getId().toString()))
    }

}
