package com.example.tdmpa_512_3p_ex_74823

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AgregarNotaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_nota)

        val dbNote = DatabaseNotes(this)
        val txtTitulo = findViewById<EditText>(R.id.txtTituloNota)
        val txtDetalles = findViewById<EditText>(R.id.txtmDetallesNota)
        val txtEstado = findViewById<EditText>(R.id.txtEstadoNota)
        val txtRecordatorio = findViewById<EditText>(R.id.txtRecordatorioNota)
        val btnAgregar = findViewById<Button>(R.id.btnGuardarNota)

        btnAgregar.setOnClickListener{
            if(txtTitulo.text.isNotEmpty() && txtDetalles.text.isNotEmpty() && txtEstado.text.isNotEmpty()
                && txtRecordatorio.text.isNotEmpty()){

                var noteModel = NoteModel(
                    0,
                    txtTitulo.text.toString(),
                    txtDetalles.text.toString(),
                    txtEstado.text.toString(),
                    txtRecordatorio.text.toString()
                )
                dbNote.addNote(noteModel);
                Toast.makeText(this@AgregarNotaActivity, "Nota agregada", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AgregarNotaActivity, MainActivity::class.java)
                startActivity(intent)

            }
            else{
                Toast.makeText(this@AgregarNotaActivity, "Llene todos los campos para continuar", Toast.LENGTH_SHORT).show()
            }

        }
    }
}