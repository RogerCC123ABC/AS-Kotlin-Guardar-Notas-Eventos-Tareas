package com.example.tdmpa_512_3p_ex_74823

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AgregarEventoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_evento)

        val dbEvent = DatabaseEvents(this)
        val txtTitulo = findViewById<EditText>(R.id.txtTituloEvento)
        val txtDetalles = findViewById<EditText>(R.id.txtmDetallesEvento)
        val txtEstado = findViewById<EditText>(R.id.txtEstadoEvento)
        val txtRecordatorio = findViewById<EditText>(R.id.txtRecordatorioEvento)
        val btnAgregar = findViewById<Button>(R.id.btnGuardarEvento)

        btnAgregar.setOnClickListener{
            if(txtTitulo.text.isNotEmpty() && txtDetalles.text.isNotEmpty() && txtEstado.text.isNotEmpty()
                && txtRecordatorio.text.isNotEmpty()){

                var eventModel = EventModel(
                    0,
                    txtTitulo.text.toString(),
                    txtDetalles.text.toString(),
                    txtEstado.text.toString(),
                    txtRecordatorio.text.toString()
                )
                dbEvent.addEvents(eventModel);
                Toast.makeText(this@AgregarEventoActivity, "Evento agregado", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AgregarEventoActivity, MainActivity::class.java)
                startActivity(intent)

            }
            else{
                Toast.makeText(this@AgregarEventoActivity, "Llene todos los campos para continuar", Toast.LENGTH_SHORT).show()
            }

        }
    }
}