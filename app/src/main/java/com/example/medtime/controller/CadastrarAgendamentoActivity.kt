package com.example.medtime.controller

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.medtime.R
import com.example.medtime.databinding.ActivityCadastrarBinding
import com.example.medtime.model.dao.AgendamentoDAO
import com.example.medtime.model.dao.MedicamentoDAO
import com.example.medtime.model.dto.Agendamento
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

@Suppress("DEPRECATION")
class CadastrarAgendamentoActivity : AppCompatActivity() {
   private lateinit var binding: ActivityCadastrarBinding
   private lateinit var medicamentoDAO: MedicamentoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCadastrarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val i = intent

        if(i.hasExtra("agendamento_alterar")){
            val agendamento = i.getSerializableExtra("agendamento_alterar") as Agendamento
            binding.btnSalvar.text = "Alterar"
            binding.tituloNewAgendamento.text = "Editar Agendamento"

            binding.edtDosagem.setText(agendamento.getDosagem().toString())
            binding.dataInicio.text = agendamento.getDataDeInicio()
            binding.dataFinal.text = agendamento.getDataDoFim()
            binding.txtHora.text = agendamento.getHorario()


            when(agendamento.getUnidadeDeMedida()){
                "mg" -> binding.spinMedida.setSelection(0)
                "ml" -> binding.spinMedida.setSelection(1)
                "UI" -> binding.spinMedida.setSelection(2)
                "µg" -> binding.spinMedida.setSelection(3)
                "g" -> binding.spinMedida.setSelection(4)
            }

            binding.btnSalvar.setOnClickListener {

                agendamento.setDataDeInicio(binding.dataInicio.text.toString())
                agendamento.setDataDoFim(binding.dataFinal.text.toString())
                agendamento.setHorario(binding.txtHora.text.toString())
                agendamento.setDosagem(binding.edtDosagem.text.toString().toFloat())
                agendamento.setUnidadeDeMedida(binding.spinMedida.selectedItem.toString())
                agendamento.setMedicamento(medicamentoDAO.pegarMedicamentoPorNome(binding.spinMedicamento.selectedItem.toString()))

                editarAgendamento(agendamento)
            }

        } else {

            binding.btnSalvar.setOnClickListener {
                salvarAgendamento()
            }

        }

        binding.seta.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.dataInicio.setOnClickListener {
            selecionarData(binding.dataInicio)
        }

        binding.dataFinal.setOnClickListener {
            selecionarData(binding.dataFinal)
        }

        configurarSpinners()
        selecionarHorario()

    }

    fun configurarSpinners(){
            medicamentoDAO = MedicamentoDAO(this)

            val dropMedicamentos = binding.spinMedicamento
            val dropUnidadeMedida = binding.spinMedida

            val dosagem = arrayOf("mg", "ml","UI", "µg", "g")

            val adapterDropMed = ArrayAdapter<String>(this, R.layout.text_drop, medicamentoDAO.listarNomesMedicamentos())
            adapterDropMed.setDropDownViewResource(R.layout.text_drop)
            dropMedicamentos.adapter = adapterDropMed

            val adapterDropUniMedida = ArrayAdapter<String>(this, R.layout.text_drop, dosagem)
            adapterDropUniMedida.setDropDownViewResource(R.layout.text_drop)
            dropUnidadeMedida.adapter = adapterDropUniMedida


    }

    fun selecionarData(view: View){
            val datePicker = Calendar.getInstance()

            val data = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                datePicker[Calendar.YEAR] = year
                datePicker[Calendar.MONTH] = month
                datePicker[Calendar.DAY_OF_MONTH] = dayOfMonth

                val formato = "dd/MM"
                val simpleDateFormat =  SimpleDateFormat(formato, Locale("pt", "br"))

                if(view == binding.dataInicio) {
                    binding.dataInicio.text = simpleDateFormat.format(datePicker.time)
                } else if(view == binding.dataFinal) {
                    binding.dataFinal.text = simpleDateFormat.format(datePicker.time)
                }

            }

            val datePickerDialog = DatePickerDialog(
                this@CadastrarAgendamentoActivity,
                data,
                datePicker[Calendar.YEAR],
                datePicker[Calendar.MONTH],
                datePicker[Calendar.DAY_OF_MONTH]
            )

            datePickerDialog.show()
    }

    fun selecionarHorario() {
        val txtHora = binding.txtHora

        txtHora.setOnClickListener {
            val cal = Calendar.getInstance()

            val timeSetListener =
                TimePickerDialog.OnTimeSetListener { timePicker, hora, minuto ->
                    cal.set(Calendar.HOUR_OF_DAY, hora)
                    cal.set(Calendar.MINUTE, minuto)

                    txtHora.text = SimpleDateFormat("HH:mm").format(cal.time)

                }

            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()

        }
    }

    fun salvarAgendamento(){
        var erro : Int = 0
        var agendamentoDAO = AgendamentoDAO(this@CadastrarAgendamentoActivity)

        if(binding.dataInicio.text.toString().equals("Selecionar data de inicio")){
            binding.dataInicio.error = "Campo obrigatório"
            erro++
        }

        if(binding.dataFinal.text.toString().equals("Selecionar data de fim")){
            binding.dataFinal.error = "Campo obrigatório"
            erro++
        }

        if(binding.txtHora.text.toString().equals("Selecionar horário")){
            binding.txtHora.error = "Campo obrigatório"
            erro++
        }

        if(binding.edtDosagem.text.toString().isEmpty()){
            binding.edtDosagem.error = "Campo obrigatório"
            erro++
        }

        if(erro == 0){

            agendamentoDAO.cadastrarAgendamento(
                Agendamento(
                    null,
                    binding.dataInicio.text.toString(),
                    binding.dataFinal.text.toString(),
                    binding.txtHora.text.toString(),
                    medicamentoDAO.pegarMedicamentoPorNome(binding.spinMedicamento.selectedItem.toString()),
                    binding.spinMedida.selectedItem.toString(),
                    binding.edtDosagem.text.toString().toFloat()
                )
            )

            limparCampos()
            voltarAgendamentosCadastrados()

        }


    }

    private fun editarAgendamento(agendamento: Agendamento){
        var erro : Int = 0
        var agendamentoDAO = AgendamentoDAO(this@CadastrarAgendamentoActivity)

        if(binding.dataInicio.text.toString().equals("Selecionar data de inicio")){
            binding.dataInicio.error = "Campo obrigatório"
            erro++
        }

        if(binding.dataFinal.text.toString().equals("Selecionar data de fim")){
            binding.dataFinal.error = "Campo obrigatório"
            erro++
        }

        if(binding.txtHora.text.toString().equals("Selecionar horário")){
            binding.txtHora.error = "Campo obrigatório"
            erro++
        }

        if(binding.edtDosagem.text.toString().isEmpty()){
            binding.edtDosagem.error = "Campo obrigatório"
            erro++
        }

        if(erro == 0){

            agendamentoDAO.atualizarAgendamento(agendamento)

            limparCampos()
            voltarAgendamentosCadastrados()

        }
    }

    private fun limparCampos(){
        binding.dataInicio.text = "Selecionar data de inicio"
        binding.dataFinal.text = "Selecionar data de fim"
        binding.txtHora.text = "Selecionar horário"
        binding.edtDosagem.text = null
    }

    private fun voltarAgendamentosCadastrados(){
        startActivity(Intent(this, AgendamentosActivity::class.java))
        finish()
    }
}














