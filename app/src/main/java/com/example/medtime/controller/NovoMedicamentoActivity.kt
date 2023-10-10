package com.example.medtime.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medtime.databinding.ActivityNovoMedicamentoBinding
import com.example.medtime.model.dao.MedicamentoDAO
import com.example.medtime.model.dto.Medicamento

class NovoMedicamentoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNovoMedicamentoBinding
    private lateinit var medicamentoDAO: MedicamentoDAO
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

        binding.btnSalvarNovo.setOnClickListener {
            salvarMedicamento()
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

    fun imageSelecionada(): Byte {
        val checkCapsula = binding.checkCapsula
        val checkInjecao = binding.checkInjecao
        val checkCaixinha = binding.checkCaixinha
        val checkGotas = binding.checkGotas

        var imagem: Byte = 0

        if (checkCapsula.isChecked) {
            imagem = 1
        } else if (checkInjecao.isChecked) {
            imagem = 2
        } else if (checkCaixinha.isChecked) {
            imagem = 3
        } else if (checkGotas.isChecked) {
            imagem = 4
        }

        return imagem
    }

    fun salvarMedicamento() {
        medicamentoDAO = MedicamentoDAO(this@NovoMedicamentoActivity)

        medicamentoDAO.cadastrarMedicamento(
            Medicamento(
                null,
                binding.edtNovoNome.text.toString(),
                imageSelecionada()
            )
        )

        limparCampos()
        voltarMedCadastrados()

    }

    fun limparCampos() {
        binding.edtNovoNome.setText("")
        binding.checkCaixinha.isChecked = false
        binding.checkCapsula.isChecked = false
        binding.checkInjecao.isChecked = false
        binding.checkGotas.isChecked = false
    }

    fun voltarMedCadastrados() {
        startActivity(Intent(this, MedCadastrados::class.java))
        finish()
    }

}