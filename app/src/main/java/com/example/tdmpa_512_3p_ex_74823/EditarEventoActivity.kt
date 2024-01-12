package com.example.tdmpa_512_3p_ex_74823

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditarEventoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_evento)

        val dbEvent = DatabaseEvents(this)
        val txtTitulo = findViewById<EditText>(R.id.txtEditarTituloEvento)
        val txtEditarDetalles = findViewById<EditText>(R.id.txtmEditarDetallesEvento)
        val txtEditarEstado = findViewById<EditText>(R.id.txtEditarEstadoEvento)
        val txtEditarRecordatorio = findViewById<EditText>(R.id.txtEditarRecordatorioEvento)
        val btnEditar = findViewById<Button>(R.id.btnActualizarEvento)

        btnEditar.setOnClickListener {
            if(txtTitulo.text.isNotEmpty() && txtEditarDetalles.text.isNotEmpty() && txtEditarEstado.text.isNotEmpty() && txtEditarRecordatorio.text.isNotEmpty()){

                val retrivedEvent = dbEvent.getEventsByTitle(txtTitulo.text.toString())
                if (retrivedEvent != null) {
                    if(retrivedEvent.titulo.equals(txtTitulo.text.toString())){
                        var eventModel = EventModel(
                            retrivedEvent.id.toString().toInt(),
                            retrivedEvent.titulo,
                            txtEditarDetalles.text.toString(),
                            txtEditarEstado.text.toString(),
                            txtEditarRecordatorio.text.toString()
                        )
                        dbEvent.UpdateEvents(eventModel)
                        Toast.makeText(this@EditarEventoActivity, "Se modificaron los datos del evento", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@EditarEventoActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this@EditarEventoActivity, "No coinciden el titulo del evento con el titulo introducido en la caja de texto 'Título'", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            else{
                Toast.makeText(this@EditarEventoActivity, "Llene todos los campos para continuar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}