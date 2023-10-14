package com.example.medtime.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medtime.R
import com.example.medtime.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
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
            startActivity(Intent(this, CadastrarAgendamentoActivity::class.java))
        }

        binding.medsCadastrados.setOnClickListener {
            startActivity(Intent(this, MedicamentosActivity::class.java))
        }

        val bottomBar = binding.bottomBar


        bottomBar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.medicamentos -> {
                    startActivity(Intent(this, MedicamentosActivity::class.java))
                    finish()
                    true
                }
                R.id.agendamentos -> {
                    startActivity(Intent(this, AgendamentosActivity::class.java))
                    finish()
                    true
                }
                R.id.inicio -> {
                    bottomBar.menu.findItem(R.id.inicio).isChecked = true
                    true
                }
                else -> false
            }
        }

    }






}