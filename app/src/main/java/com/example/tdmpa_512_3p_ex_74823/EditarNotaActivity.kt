package com.example.tdmpa_512_3p_ex_74823

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditarNotaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_nota)

        val dbNote = DatabaseNotes(this)
        val txtTitulo = findViewById<EditText>(R.id.txtEditarTituloNota)
        val txtEditarDetalles = findViewById<EditText>(R.id.txtmEditarDetallesNota)
        val txtEditarEstado = findViewById<EditText>(R.id.txtEditarEstadoNota)
        val txtEditarRecordatorio = findViewById<EditText>(R.id.txtEditarRecordatorioNota)
        val btnEditar = findViewById<Button>(R.id.btnActualizarNota)

        btnEditar.setOnClickListener {
            if(txtTitulo.text.isNotEmpty() && txtEditarDetalles.text.isNotEmpty() && txtEditarEstado.text.isNotEmpty() && txtEditarRecordatorio.text.isNotEmpty()){

                val retrivedNote = dbNote.getNoteByTitle(txtTitulo.text.toString())
                if (retrivedNote != null) {
                    if(retrivedNote.titulo.equals(txtTitulo.text.toString())){
                        var noteModel = NoteModel(
                            retrivedNote.id.toString().toInt(),
                            retrivedNote.titulo,
                            txtEditarDetalles.text.toString(),
                            txtEditarEstado.text.toString(),
                            txtEditarRecordatorio.text.toString()
                        )
                        dbNote.UpdateNote(noteModel)
                        Toast.makeText(this@EditarNotaActivity, "Se modificaron los datos de la nota", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@EditarNotaActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this@EditarNotaActivity, "No coinciden el titulo de la nota con el titulo introducido en la caja de texto 'Título'", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            else{
                Toast.makeText(this@EditarNotaActivity, "Llene todos los campos para continuar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}