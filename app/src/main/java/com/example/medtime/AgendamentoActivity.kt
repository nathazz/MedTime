package com.example.medtime

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.medtime.databinding.AgendamentosBinding

class AgendamentoActivity : AppCompatActivity() {

    private lateinit var  binding: AgendamentosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AgendamentosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        voltarMain()
    }


    fun voltarMain(){
        val setaVoltar = binding.flecha

        setaVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

}
