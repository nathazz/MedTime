package com.example.medtime.activities.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medtime.R
import com.example.medtime.activities.adapter.AdapterMedicamento
import com.example.medtime.activities.model.Medicamento
import com.example.medtime.databinding.ActivityAgendamentoBinding

class AgendamentosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.seta2.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnFlutuante.setOnClickListener{
            startActivity(Intent(this, CadastrarActivity::class.java))
        }

        listarMedicamentos()
    }


    fun listarMedicamentos(){
        val re_medicamentos = binding.recyclerMedicamentos
            //lista na vertical
        re_medicamentos.layoutManager = LinearLayoutManager(this )
        re_medicamentos.setHasFixedSize(true)


        //configurar o Adapter

        val listaMedicamentos: MutableList<Medicamento> = mutableListOf()
        val adapterMedicamento = AdapterMedicamento(this, listaMedicamentos)

        re_medicamentos.adapter = adapterMedicamento

       val medicamento1 = Medicamento(
            R.drawable.caixinha,
            "Vitamina C",
           "1x por dia",
           "",
           "",
           ""

       )
        listaMedicamentos.add(medicamento1)


       val medicamentos2 = Medicamento(
           R.drawable.injecao,
           "Insulina",
           "2x por dia",
           "20 Jun",
           "30 Out",
           "10:00"

       )
        listaMedicamentos.add(medicamentos2)



    }

}