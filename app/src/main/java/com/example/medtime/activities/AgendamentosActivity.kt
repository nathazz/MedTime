package com.example.medtime.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medtime.R
import com.example.medtime.activities.Adapter.AdapterMedicamento
import com.example.medtime.activities.model.Medicamento
import com.example.medtime.databinding.ActivityAgendamentoBinding

class AgendamentosActivity : AppCompatActivity() {

    lateinit var binding: ActivityAgendamentoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.seta2.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        listar()
    }


    fun listar(){
        val re_medicamentos = findViewById<RecyclerView>(R.id.recycler_medicamentos)
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
           "Tratamento diário"
       )
        listaMedicamentos.add(medicamento1)


       val medicamentos2 = Medicamento(
           R.drawable.injecao,
           "Insulina",
           "2x por dia",
           "Tratamento diário"
       )
        listaMedicamentos.add(medicamentos2)

    }

}