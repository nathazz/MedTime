package com.example.medtime.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medtime.adapter.AdapterMedicamentos
import com.example.medtime.databinding.ActivityMedCadastradosBinding
import com.example.medtime.model.dto.Medicamento

class MedCadastrados : AppCompatActivity() {

    private lateinit var binding: ActivityMedCadastradosBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMedCadastradosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.flechaCadastrado.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnFlutuanteCadastro.setOnClickListener {
            startActivity(Intent(this, NovoMedicamentoActivity::class.java))
        }

        listarMedicamentos()
    }

    fun listarMedicamentos(){

        val req_cadastrados = binding.recyclerCadastrados
        req_cadastrados.layoutManager = LinearLayoutManager(this)
        req_cadastrados.setHasFixedSize(false)

        //configurar adapter

        val medicamentos: MutableList<Medicamento> = mutableListOf()
        medicamentos.add(Medicamento(20,"Dipirona", 3))
        medicamentos.add(Medicamento(22,"Dipirona", 2))
        medicamentos.add(Medicamento(22,"Dipirona", 1))
        medicamentos.add(Medicamento(22,"Dipirona", 4))

        val adapterMedicamentos = AdapterMedicamentos(this, medicamentos)
        req_cadastrados.adapter = adapterMedicamentos
    }
}