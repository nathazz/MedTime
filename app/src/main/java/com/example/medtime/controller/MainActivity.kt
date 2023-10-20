package com.example.medtime.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.medtime.R
import com.example.medtime.databinding.ActivityMainBinding
import com.example.medtime.model.dao.MedicamentoDAO

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var medicamentoDAO: MedicamentoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        medicamentoDAO = MedicamentoDAO(this)

        binding.meusAgend.setOnClickListener {
            startActivity(Intent(this, AgendamentosActivity::class.java))
        }

        binding.novoAgend.setOnClickListener {
            if(medicamentoDAO.listarMedicamentos().isEmpty()){
                Toast.makeText(this@MainActivity, "Para realizar um agendamento você precisa cadastrar um medicamento.", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, CadastrarAgendamentoActivity::class.java))
            }
        }

        binding.medsCadastrados.setOnClickListener {
            startActivity(Intent(this, MedicamentosActivity::class.java))
        }

        val bottomBar = binding.bottomBar


        bottomBar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.medicamentos -> {
                    startActivity(Intent(this, MedicamentosActivity::class.java))
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    finish()
                    true
                }
                R.id.agendamentos -> {
                    startActivity(Intent(this, AgendamentosActivity::class.java))
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
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