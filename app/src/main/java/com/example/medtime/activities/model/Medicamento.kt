package com.example.medtime.activities.model

import android.app.DatePickerDialog
import java.sql.Time
import java.util.Date


data class Medicamento(
    //adicionar hora,ide ajeitar a data (provavelmente ja expliquei sobre)
    val foto: Int,
    val nome: String,
    val dosagem: String,
    val data: String


)