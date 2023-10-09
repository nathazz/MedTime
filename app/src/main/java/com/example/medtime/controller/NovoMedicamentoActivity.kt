package com.example.medtime.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medtime.databinding.ActivityNovoMedicamentoBinding

class NovoMedicamentoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNovoMedicamentoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNovoMedicamentoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.flechaMedicamento.setOnClickListener {
            startActivity(Intent(this, MedCadastrados::class.java))
            finish()
        }

        binding.voltarMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        selecionarUmCheck()
    }

    fun selecionarUmCheck() {
        val checkCapsula = binding.checkCapsula
        val checkInjecao = binding.checkInjecao
        val checkCaixinha = binding.checkCaixinha
        val checkGotas = binding.checkGotas

        val checkBoxes = listOf(checkCapsula, checkCaixinha, checkGotas, checkInjecao)

        for (checkBox in checkBoxes) {
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    for (otherCheckBox in checkBoxes) {
                        if (otherCheckBox != buttonView) {
                            otherCheckBox.isChecked = false
                                }
                            }
                        }
                    }

                }
            }
    }