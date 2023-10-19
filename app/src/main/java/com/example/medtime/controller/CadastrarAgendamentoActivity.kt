package com.example.medtime.controller

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.medtime.R
import com.example.medtime.databinding.ActivityCadastrarBinding
import com.example.medtime.model.dao.MedicamentoDAO
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

@Suppress("DEPRECATION")
class CadastrarAgendamentoActivity : AppCompatActivity() {
   private lateinit var binding: ActivityCadastrarBinding
   private lateinit var medicamentoDAO: MedicamentoDAO

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCadastrarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.seta.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.dataInicio.setOnClickListener {
            selecionarData(binding.dataInicio)
        }

        binding.dataFinal.setOnClickListener {
            selecionarData(binding.dataFinal)
        }

        binding.btnSalvar.setOnClickListener{
             binding.btnSalvar.text = binding.txtHora.text
        }


        configurarSpinners()
        selecionarHorario()

    }

    private fun configurarSpinners(){
            medicamentoDAO = MedicamentoDAO(this)

            val dropMedicamentos = binding.spinMedicamento
            val dropUnidadeMedida = binding.spinMedida

            val dosagem = arrayOf("Miligramas(mg)", "Militlitros(ml)","Unidades internacionais(UI)", "Microgramas (µg)", "Gramas(g)")

            val adapterDropMed = ArrayAdapter<String>(this, R.layout.text_drop, medicamentoDAO.listarNomesMedicamentos())
            adapterDropMed.setDropDownViewResource(R.layout.text_drop)
            dropMedicamentos.adapter = adapterDropMed

            val adapterDropUniMedida = ArrayAdapter<String>(this, R.layout.text_drop, dosagem)
            adapterDropUniMedida.setDropDownViewResource(R.layout.text_drop)
            dropUnidadeMedida.adapter = adapterDropUniMedida


    }

    private fun selecionarData(view: View){
            val datePicker = Calendar.getInstance()

            val data = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                datePicker[Calendar.YEAR] = year
                datePicker[Calendar.MONTH] = month
                datePicker[Calendar.DAY_OF_MONTH] = dayOfMonth

                val formato = "dd/MM/yyyy"
                val simpleDateFormat =  SimpleDateFormat(formato, Locale("pt", "br"))

                if(view == binding.dataInicio) {
                    binding.dataInicio.text = simpleDateFormat.format(datePicker.time)
                } else if(view == binding.dataFinal) {
                    binding.dataFinal.text = simpleDateFormat.format(datePicker.time)
                }

            }

            val datePickerDialog = DatePickerDialog(
                this@CadastrarAgendamentoActivity,
                data,
                datePicker[Calendar.YEAR],
                datePicker[Calendar.MONTH],
                datePicker[Calendar.DAY_OF_MONTH]
            )

            datePickerDialog.show()
    }

    private fun selecionarHorario() {
        val txtHora = binding.txtHora

        txtHora.setOnClickListener {
            val cal = Calendar.getInstance()

            val timeSetListener =
                TimePickerDialog.OnTimeSetListener { timePicker, hora, minuto ->
                    cal.set(Calendar.HOUR_OF_DAY, hora)
                    cal.set(Calendar.MINUTE, minuto)

                    txtHora.text = SimpleDateFormat("HH:mm").format(cal.time)

                }

            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()

        }
    }
}














