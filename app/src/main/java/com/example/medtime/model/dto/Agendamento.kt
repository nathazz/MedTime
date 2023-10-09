package com.example.medtime.model.dto

import java.time.LocalTime
import java.util.Date

class Agendamento(
    private var id : Int,
    private var dataDeInicio : Date,
    private var dataDoFim : Date,
    private var horario : LocalTime,
    private var medicamento : Medicamento,
    private var unidadeDeMedida : String,
    private var dosagem : Float
) {
    fun getId() : Int {
        return id
    }

    fun getDataDeInicio() : Date {
        return dataDeInicio
    }

    fun setDataDeInicio(dataDeInicio : Date) {
        this.dataDeInicio = dataDeInicio
    }

    fun getDataDoFim() : Date {
        return dataDoFim
    }

    fun setDataDoFim(dataDoFim : Date) {
        this.dataDoFim = dataDoFim
    }

    fun getHorario() : LocalTime {
        return horario
    }

    fun setHorario(horario : LocalTime) {
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