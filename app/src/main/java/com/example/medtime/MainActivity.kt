package com.example.medtime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navegarLayout()
    }

    fun navegarLayout(){
        val botao = findViewById<Button>(R.id.meusAgend)
        val botao2 = findViewById<Button>(R.id.novoAgend)

        botao.setOnClickListener {
            val intent1 = Intent(this, AgendamentoActivity::class.java)
            startActivity(intent1)
        }

        botao2.setOnClickListener {
            val intent2 = Intent(this, CadastrarMedActivity::class.java)
            startActivity(intent2)
        }

    }


}