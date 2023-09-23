package com.example.medtime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medtime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navegarLayout()
    }

    fun navegarLayout(){
        val botao = binding.meusAgend
        val botao2 = binding.novoAgend

        botao.setOnClickListener {
            val intent1 = Intent(this, AgendamentoActivity::class.java)
            startActivity(intent1)
        }

        botao2.setOnClickListener {
            val intent2 = Intent(this, CadastrarActivity::class.java)
            startActivity(intent2)
        }

    }


}