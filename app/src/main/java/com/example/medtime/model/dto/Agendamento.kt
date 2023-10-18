package com.example.medtime.model.dto

import java.io.Serializable
import java.time.LocalTime
import java.util.Date

class Agendamento(
    private var id: Int,
    private var dataDeInicio: String,
    private var dataDoFim: String,
    private var horario: String,
    private var medicamento: Medicamento,
    private var unidadeDeMedida: String,
    private var dosagem: Float

) : Serializable {
    fun getId() : Int {
        return id
    }

    fun getDataDeInicio() : String {
        return dataDeInicio
    }

    fun setDataDeInicio(dataDeInicio : String) {
        this.dataDeInicio = dataDeInicio
    }

    fun getDataDoFim() : String {
        return dataDoFim
    }

    fun setDataDoFim(dataDoFim : String) {
        this.dataDoFim = dataDoFim
    }

    fun getHorario() : String {
        return horario
    }

    fun setHorario(horario : String) {
        this.horario = horario
    }

    fun getMedicamento() : Medicamento {
        return medicamento
    }

    fun setMedicamento(medicamento : Medicamento) {
        this.medicamento = medicamento
    }

    fun getUnidadeDeMedida() : String {
        return unidadeDeMedida
    }

    fun setUnidadeDeMedida(unidadeDeMedida : String) {
        this.unidadeDeMedida = unidadeDeMedida
    }

    fun getDosagem() : Float {
        return dosagem
    }

    fun setDosagem(dosagem : Float) {
        this.dosagem = dosagem
    }

    override fun toString(): String {
        return "id: $id\ndataDeInicio: $dataDeInicio\ndataDoFim: $dataDoFim\nhorario: $horario\nmedicamento: $medicamento\nunidadeDeMedida: $unidadeDeMedida\ndosagem: $dosagem\n"
    }
}