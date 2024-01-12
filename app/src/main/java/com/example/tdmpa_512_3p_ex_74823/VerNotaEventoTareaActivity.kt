package com.example.tdmpa_512_3p_ex_74823

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class VerNotaEventoTareaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_nota_evento_tarea)

        val txtTitulo = findViewById<TextView>(R.id.txtTitulo)
        val txtDetalles = findViewById<TextView>(R.id.txtDetalles)
        val txtEstado = findViewById<TextView>(R.id.txtEstado)
        val txtRecordatorio = findViewById<TextView>(R.id.txtRecordatorio)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        val titulo = intent.getStringExtra("titulo")
        val detalles = intent.getStringExtra("detalles")
        val estado = intent.getStringExtra("estado")
        val recordatorio = intent.getStringExtra("recordatorio")

        txtTitulo.text = "Título: " + titulo;
        txtDetalles.text = "Detalles :" + detalles
        txtEstado.text = "Estado: " + estado
        txtRecordatorio.text = "Recordatorio: " + recordatorio

        btnRegresar.setOnClickListener{
            finish();
        }
    }
}