package com.example.tdmpa_512_3p_ex_74823

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class VerTareaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_tarea)

        val txtTitulo = findViewById<TextView>(R.id.txtVerTituloTarea)
        val txtDetalles = findViewById<TextView>(R.id.txtVerDetallesTarea)
        val txtEstado = findViewById<TextView>(R.id.txtVerEstadoTarea)
        val txtRecordatorio = findViewById<TextView>(R.id.txtVerRecordatorioTarea)
        val btnRegresar = findViewById<Button>(R.id.btnRegresarTarea)

        val titulo = intent.getStringExtra("tituloTarea")
        val detalles = intent.getStringExtra("detallesTarea")
        val estado = intent.getStringExtra("estadoTarea")
        val recordatorio = intent.getStringExtra("recordatorioTarea")

        txtTitulo.text = "Título: " + titulo;
        txtDetalles.text = "Detalles :" + detalles
        txtEstado.text = "Estado: " + estado
        txtRecordatorio.text = "Recordatorio: " + recordatorio

        btnRegresar.setOnClickListener{
            finish();
        }
    }
}