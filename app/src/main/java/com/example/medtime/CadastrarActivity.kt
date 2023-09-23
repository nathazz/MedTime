package com.example.medtime

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.medtime.databinding.CadastrarBinding


class CadastrarActivity : AppCompatActivity() {

    private lateinit var binding: CadastrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = CadastrarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        voltarMain()
    }
    fun voltarMain() {

        val setaVoltar = binding.seta

        setaVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}