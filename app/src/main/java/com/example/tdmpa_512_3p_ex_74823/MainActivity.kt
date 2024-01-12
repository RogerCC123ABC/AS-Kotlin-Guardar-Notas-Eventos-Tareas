package com.example.tdmpa_512_3p_ex_74823

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNotas = findViewById<Button>(R.id.btnNotas)
        val btnEventos = findViewById<Button>(R.id.btnEventos)
        val btnTareas = findViewById<Button>(R.id.btnTareas)

        btnNotas.setOnClickListener{
            val intent = Intent(this@MainActivity, NotaActivity::class.java)
            startActivity(intent)
        }

        btnEventos.setOnClickListener{
            var intent2 = Intent(this@MainActivity, EventoActivity::class.java)
            startActivity(intent2)
        }

        btnTareas.setOnClickListener{
            var intent3 = Intent(this@MainActivity, TareaActivity::class.java)
            startActivity(intent3)
        }




    }

}