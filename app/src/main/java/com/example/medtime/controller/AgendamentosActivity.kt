package com.example.medtime.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medtime.R
import com.example.medtime.adapter.AdapterAgendamento
import com.example.medtime.model.dto.Medicamento
import com.example.medtime.databinding.ActivityAgendamentoBinding
import com.example.medtime.model.dto.Agendamento

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

        listarAgendamentos()
    }


    fun listarAgendamentos(){

        val re_medicamentos = binding.recyclerMedicamentos
            //lista na vertical
        re_medicamentos.layoutManager = LinearLayoutManager(this )
        re_medicamentos.setHasFixedSize(true)


        val listaAgendamento: MutableList<Agendamento> = mutableListOf()
        val adapterAgendamento = AdapterAgendamento(this, listaAgendamento)

        re_medicamentos.adapter = adapterAgendamento






    }

}