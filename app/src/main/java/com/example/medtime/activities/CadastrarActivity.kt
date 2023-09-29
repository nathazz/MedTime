package com.example.medtime

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.medtime.databinding.CadastrarBinding


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





}