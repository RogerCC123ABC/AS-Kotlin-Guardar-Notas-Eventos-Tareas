package com.example.tdmpa_512_3p_ex_74823

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AgregarTareaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_tarea)

        val dbTask = DatabaseTasks(this)
        val txtTitulo = findViewById<EditText>(R.id.txtTituloTarea)
        val txtDetalles = findViewById<EditText>(R.id.txtmDetallesTarea)
        val txtEstado = findViewById<EditText>(R.id.txtEstadoTarea)
        val txtRecordatorio = findViewById<EditText>(R.id.txtRecordatorioTarea)
        val btnAgregar = findViewById<Button>(R.id.btnGuardarTarea)

        btnAgregar.setOnClickListener{
            if(txtTitulo.text.isNotEmpty() && txtDetalles.text.isNotEmpty() && txtEstado.text.isNotEmpty()
                && txtRecordatorio.text.isNotEmpty()){

                var taskModel = TaskModel(
                    0,
                    txtTitulo.text.toString(),
                    txtDetalles.text.toString(),
                    txtEstado.text.toString(),
                    txtRecordatorio.text.toString()
                )
                dbTask.addTasks(taskModel);
                Toast.makeText(this@AgregarTareaActivity, "Tarea agregada", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AgregarTareaActivity, MainActivity::class.java)
                startActivity(intent)

            }
            else{
                Toast.makeText(this@AgregarTareaActivity, "Llene todos los campos para continuar", Toast.LENGTH_SHORT).show()
            }

        }
    }
}