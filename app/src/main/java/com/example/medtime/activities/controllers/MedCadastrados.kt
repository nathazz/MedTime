package com.example.medtime.activities.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medtime.R
import com.example.medtime.activities.adapter.AdapterCadastrados
import com.example.medtime.activities.model.MedCadastrado
import com.example.medtime.databinding.ActivityMedCadastradosBinding

class MedCadastrados : AppCompatActivity() {

    lateinit var binding: ActivityMedCadastradosBinding
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

        listaCadastrados()
    }

    fun listaCadastrados(){

        val req_cadastrados = binding.recyclerCadastrados
        req_cadastrados.layoutManager = LinearLayoutManager(this)
        req_cadastrados.setHasFixedSize(true)

        //configurar adapter

        val listCadastrados: MutableList<MedCadastrado> = mutableListOf()
        val adapterCadastrados = AdapterCadastrados(this, listCadastrados)
        req_cadastrados.adapter = adapterCadastrados

        val item1 = MedCadastrado(
            R.drawable.gotas,
            "Clonazepam"
        )

        listCadastrados.add(item1)


        val item2 = MedCadastrado(
            R.drawable.injecao,
            "Injeção Mounjaro"
        )

        listCadastrados.add(item2)

        val item3 = MedCadastrado(
            R.drawable.capsula,
            "Floratil"
        )

        listCadastrados.add(item3)

    }
}