package com.example.medtime.activities.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medtime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.meusAgend.setOnClickListener {
            startActivity(Intent(this, AgendamentosActivity::class.java))
        }

        binding.novoAgend.setOnClickListener {
            startActivity(Intent(this, CadastrarActivity::class.java))
        }

        binding.medsCadastrados.setOnClickListener {
            startActivity(Intent(this, MedCadastrados::class.java))
        }

    }




}