package com.example.medtime.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medtime.R
import com.example.medtime.adapter.AdapterMedicamentos
import com.example.medtime.databinding.ActivityMedCadastradosBinding
import com.example.medtime.model.dao.MedicamentoDAO

@Suppress("DEPRECATION")
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




        exibirMedicamentos()
        navegar()
    }

   private  fun exibirMedicamentos(){
        val medicamentoDAO = MedicamentoDAO(this)

        val reqCadastrados = binding.recyclerCadastrados
        reqCadastrados.layoutManager = LinearLayoutManager(this)
        reqCadastrados.setHasFixedSize(false)

        reqCadastrados.adapter =  AdapterMedicamentos(this, medicamentoDAO.listarMedicamentos().toMutableList())
    }


    private fun navegar(){
        val bottomBar = binding.bottomBar

        bottomBar.menu.findItem(R.id.medicamentos).isChecked = true

        bottomBar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.medicamentos -> true
                R.id.agendamentos -> {
                    startActivity(Intent(this, AgendamentosActivity::class.java))
                    finish()
                    true
                }
                R.id.inicio -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }


    }
}