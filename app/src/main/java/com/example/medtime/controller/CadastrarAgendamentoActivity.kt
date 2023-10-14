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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone


@Suppress("DEPRECATION")
class CadastrarAgendamentoActivity : AppCompatActivity() {


   private lateinit var binding: ActivityCadastrarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCadastrarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.seta.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
    }


        //selecionarData
        binding.dataInicio.setOnClickListener {
            selecionarData(binding.dataInicio)
        }

        binding.dataFinal.setOnClickListener {
            selecionarData(binding.dataFinal)
        }


        configurarSpinner()
        selecionarHorario()




    }

    /*Apenas um teste(mudar conforme o tempo)*/
        private fun configurarSpinner(){

            val drop = binding.spinMedicamento
            val drop2 = binding.spinMedida

            val medicamentos = arrayOf("Rivotril", "Colírio", "Dramin")
            val dosagem = arrayOf("Miligramas(mg)", "Militlitros(ml)","Unidades internacionais(UI)",
                                 "Microgramas (µg)", "Gramas(g)")

            //chamando o text_drop para alterar a cor do fundo e do textp
            val adapter = ArrayAdapter<String>(this, R.layout.text_drop, medicamentos)
            adapter.setDropDownViewResource(R.layout.text_drop)

            // Defina o adapter no spinner
            drop.adapter = adapter

            val adapter2 = ArrayAdapter<String>(this, R.layout.text_drop, dosagem)
            adapter2.setDropDownViewResource(R.layout.text_drop)

            drop2.adapter = adapter2
        }


        private fun selecionarData(view: View){
            val datePicker = Calendar.getInstance()

            val data = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                datePicker[Calendar.YEAR] = year
                datePicker[Calendar.MONTH] = month
                datePicker[Calendar.DAY_OF_MONTH] = dayOfMonth

                val formato = "dd-MMMM-yyyy"
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
                val timeZone = TimeZone.getTimeZone("America/Sao_Paulo")
                cal.timeZone = timeZone

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














