package com.example.medtime.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.medtime.R
import com.example.medtime.databinding.CadastrarBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class CadastrarActivity : AppCompatActivity() {


    private lateinit var binding: CadastrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = CadastrarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.seta.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
    }

        //selecionarData
        binding.selecionar1.setOnClickListener {
            selecionarData(binding.selecionar1)
        }

        binding.selecionar2.setOnClickListener {
            selecionarData(binding.selecionar2)
        }


        configurarSpinner()
    }

    /*Apenas um teste(mudar conforme o tempo)*/
    fun configurarSpinner(){

        val drop = binding.spinOn
        val drop2 = binding.spinTwo

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

            if(view == binding.selecionar1) {
                binding.selecionar1.text = simpleDateFormat.format(datePicker.time)
            } else if(view == binding.selecionar2) {
                binding.selecionar2.text = simpleDateFormat.format(datePicker.time)
            }
        }
        val datePickerDialog = DatePickerDialog(
            this@CadastrarActivity,
            data,
            datePicker[Calendar.YEAR],
            datePicker[Calendar.MONTH],
            datePicker[Calendar.DAY_OF_MONTH]
        )

        datePickerDialog.show()
    }


}










