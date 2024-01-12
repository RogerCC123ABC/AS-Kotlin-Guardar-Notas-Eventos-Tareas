package com.example.tdmpa_512_3p_ex_74823

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class VerEventoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_evento)

        val txtTitulo = findViewById<TextView>(R.id.txtVerTituloEvento)
        val txtDetalles = findViewById<TextView>(R.id.txtVerDetallesEvento)
        val txtEstado = findViewById<TextView>(R.id.txtVerEstadoEvento)
        val txtRecordatorio = findViewById<TextView>(R.id.txtVerRecordatorioEvento)
        val btnRegresar = findViewById<Button>(R.id.btnRegresarEvento)

        val titulo = intent.getStringExtra("tituloEvento")
        val detalles = intent.getStringExtra("detallesEvento")
        val estado = intent.getStringExtra("estadoEvento")
        val recordatorio = intent.getStringExtra("recordatorioEvento")

        txtTitulo.text = "Título: " + titulo;
        txtDetalles.text = "Detalles :" + detalles
        txtEstado.text = "Estado: " + estado
        txtRecordatorio.text = "Recordatorio: " + recordatorio

        btnRegresar.setOnClickListener{
            finish();
        }


    }
}