package com.example.medtime.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medtime.R
import com.example.medtime.adapter.AdapterAgendamento
import com.example.medtime.databinding.ActivityAgendamentoBinding
import com.example.medtime.model.dao.AgendamentoDAO
import com.example.medtime.model.dao.MedicamentoDAO
import com.example.medtime.model.dto.Agendamento
import com.example.medtime.model.dto.Medicamento

@Suppress("DEPRECATION")
class AgendamentosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAgendamentoBinding
    private lateinit var medicamentoDAO: MedicamentoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        medicamentoDAO = MedicamentoDAO(this)

        binding.seta2.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnFlutuante.setOnClickListener {
            if(medicamentoDAO.listarMedicamentos().isEmpty()){
                Toast.makeText(this@AgendamentosActivity, "Para realizar um agendamento você precisa cadastrar um medicamento.", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, CadastrarAgendamentoActivity::class.java))
            }
        }

        listarAgendamentos()
        navegar()

    }

   private fun listarAgendamentos() {
        val agendamentoDAO = AgendamentoDAO(this@AgendamentosActivity)

        val reqAgendamentos = binding.recyclerAgendamentos
        reqAgendamentos.layoutManager = LinearLayoutManager(this)
        reqAgendamentos.setHasFixedSize(true)

        var lista : MutableList<Agendamento> = arrayListOf(Agendamento(
            1,
            "10/20",
            "10/20",
            "10:20",
            Medicamento(1, "teste", 2.toByte()),
            "ml",
            2.0f
        ))

        reqAgendamentos.adapter = AdapterAgendamento(this, agendamentoDAO.listarAgendamentos().toMutableList())
    }

   private fun navegar() {
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




