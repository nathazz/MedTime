package com.example.medtime.activities.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medtime.R
import com.example.medtime.databinding.ActivityNovoMedicamentoBinding

class NovoMedicamentoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNovoMedicamentoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNovoMedicamentoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.flechaMedicamento.setOnClickListener {
            startActivity(Intent(this,MedCadastrados::class.java))
            finish()
        }

        binding.voltarMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}