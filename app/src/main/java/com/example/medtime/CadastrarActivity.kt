package com.example.medtime

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
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

        listarArrays()

    }


    /*Apenas um teste(mudar conforme o tempo)*/
    fun listarArrays(){

        val drop = binding.spinner
        var medicamentos = arrayOf("Rivotril", "Colírio", "Dramin")

        drop.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, medicamentos)




    }




}