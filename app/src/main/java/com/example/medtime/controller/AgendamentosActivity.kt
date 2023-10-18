package com.example.medtime.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medtime.R
import com.example.medtime.adapter.AdapterAgendamento
import com.example.medtime.databinding.ActivityAgendamentoBinding
import com.example.medtime.model.dao.AgendamentoDAO

@Suppress("DEPRECATION")
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

        binding.btnFlutuante.setOnClickListener {
            startActivity(Intent(this, CadastrarAgendamentoActivity::class.java))
        }

        listarAgendamentos()
        navegar()

    }

   private fun listarAgendamentos() {
       val agendamentoDAO = AgendamentoDAO(this)

        val reqAgendamentos = binding.recyclerAgendamentos
        reqAgendamentos.layoutManager = LinearLayoutManager(this)
        reqAgendamentos.setHasFixedSize(false)

        reqAgendamentos.adapter = AdapterAgendamento(this, agendamentoDAO.listarAgendamentos().toMutableList())
    }

   private  fun navegar() {
       val bottomBar = binding.bottomBar
       bottomBar.menu.findItem(R.id.agendamentos).isChecked = true
       bottomBar.setOnNavigationItemSelectedListener { menuItem ->
           when (menuItem.itemId) {
               R.id.agendamentos -> true

               R.id.inicio -> {
                   startActivity(Intent(this, MainActivity::class.java))
                   overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                   finish()
                   true
               }

               R.id.medicamentos -> {
                   startActivity(Intent(this, MedicamentosActivity::class.java))
                   overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                   finish()
                   true
               }

               else -> false
           }
       }

   }


       }




