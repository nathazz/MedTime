package com.example.medtime.model.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.medtime.model.DataBase.ConexaoDB
import com.example.medtime.model.dto.Agendamento
import com.example.medtime.model.dto.Medicamento

class AgendamentoDAO(context : Context) {
    private val conexaoDB : ConexaoDB = ConexaoDB(context)
    private val medTimeDB : SQLiteDatabase = conexaoDB.writableDatabase
    private val medicamentoDAO = MedicamentoDAO(context)

    fun cadastrarAgendamento(agendamento: Agendamento){
        val valores = ContentValues()

        valores.put("data_inicio", agendamento.getDataDeInicio())
        valores.put("data_final", agendamento.getDataDoFim())
        valores.put("unidade_de_medida", agendamento.getUnidadeDeMedida())
        valores.put("dosagem", agendamento.getDosagem())
        valores.put("horario", agendamento.getHorario())
        valores.put("fk_medicamento_id", agendamento.getMedicamento().getId())

        medTimeDB.insert("agendamentos", null, valores)
    }

    fun listarAgendamentos(): List<Agendamento>{
        val listaAgendamentos : MutableList<Agendamento> = ArrayList()
        val columns = arrayOf("id", "data_inicio", "data_final", "unidade_de_medida", "dosagem", "horario", "fk_medicamento_id")
        val cursor = medTimeDB.query("agendamentos", columns, null, null, null, null, null)

        while(cursor.moveToNext()){
            val agendamento = Agendamento(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(5),
                medicamentoDAO.pegarMedicamentoPorId(cursor.getInt(6)),
                cursor.getString(3),
                cursor.getFloat(4)
            )

            listaAgendamentos.add(agendamento)
        }

        return listaAgendamentos
    }

    fun atualizarAgendamento(agendamento: Agendamento){
        val valores = ContentValues()

        valores.put("data_inicio", agendamento.getDataDeInicio())
        valores.put("data_final", agendamento.getDataDoFim())
        valores.put("unidade_de_medida", agendamento.getUnidadeDeMedida())
        valores.put("dosagem", agendamento.getDosagem())
        valores.put("horario", agendamento.getHorario())
        valores.put("fk_medicamento_id", agendamento.getMedicamento().getId())

        medTimeDB.update("agendamentos", valores, "id = ?", arrayOf(agendamento.getId().toString()))
    }

    fun excluirAgendamento(agendamento: Agendamento){
        medTimeDB.delete("agendamentos", "id = ?", arrayOf(agendamento.getId().toString()))
    }
}