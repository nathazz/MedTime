package com.example.medtime.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.medtime.R
import com.example.medtime.databinding.ActivityNovoMedicamentoBinding
import com.example.medtime.model.dao.MedicamentoDAO
import com.example.medtime.model.dto.Medicamento

@Suppress("DEPRECATION")
class CadastrarMedicamentoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNovoMedicamentoBinding
    private lateinit var medicamentoDAO: MedicamentoDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNovoMedicamentoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val i = intent


        if(i.hasExtra("medicamento_alterar")){
            val medicamento = i.getSerializableExtra("medicamento_alterar") as Medicamento
            binding.btnSalvarNovo.text = "Alterar"
            binding.titulo.text = "Editar Medicamento"

            binding.edtNovoNome.setText(medicamento.getNome())

            when(medicamento.getImagem()){
                1.toByte() -> binding.checkCapsula.isChecked = true
                2.toByte() -> binding.checkInjecao.isChecked = true
                3.toByte() -> binding.checkCaixinha.isChecked = true
                4.toByte() -> binding.checkGotas.isChecked = true
            }

            binding.btnSalvarNovo.setOnClickListener {

                medicamento.setNome(binding.edtNovoNome.text.toString())
                medicamento.setImagem(imageSelecionada())

                editarMedicamento(medicamento)
            }

        } else {

            binding.btnSalvarNovo.setOnClickListener {
                salvarMedicamento()
            }

        }

        binding.flechaMedicamento.setOnClickListener {
            startActivity(Intent(this, MedicamentosActivity::class.java))
            finish()
        }

        selecionarUmCheck()

    }

    private fun selecionarUmCheck() {
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

    private fun imageSelecionada(): Byte {
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

    private fun salvarMedicamento() {
        medicamentoDAO = MedicamentoDAO(this@CadastrarMedicamentoActivity)

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

    private fun editarMedicamento(medicamento: Medicamento){
        medicamentoDAO = MedicamentoDAO(this@CadastrarMedicamentoActivity)

        medicamentoDAO.atualizarMedicamento(medicamento)

        Toast.makeText(this, "Medicamento alterado com sucesso!", Toast.LENGTH_SHORT).show()
        limparCampos()
        voltarMedCadastrados()
    }

   private fun limparCampos() {
        binding.edtNovoNome.setText("")
        binding.checkCaixinha.isChecked = false
        binding.checkCapsula.isChecked = false
        binding.checkInjecao.isChecked = false
        binding.checkGotas.isChecked = false
    }

    private fun voltarMedCadastrados() {
        startActivity(Intent(this, MedicamentosActivity::class.java))
        finish()
    }

  }