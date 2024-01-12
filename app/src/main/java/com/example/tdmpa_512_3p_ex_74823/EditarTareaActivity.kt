package com.example.tdmpa_512_3p_ex_74823

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditarTareaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_tarea)

        val dbTask = DatabaseTasks(this)
        val txtTitulo = findViewById<EditText>(R.id.txtEditarTituloTarea)
        val txtEditarDetalles = findViewById<EditText>(R.id.txtmEditarDetallesTarea)
        val txtEditarEstado = findViewById<EditText>(R.id.txtEditarEstadoTarea)
        val txtEditarRecordatorio = findViewById<EditText>(R.id.txtEditarRecordatorioTarea)
        val btnEditar = findViewById<Button>(R.id.btnActualizarTarea)

        btnEditar.setOnClickListener {
            if(txtTitulo.text.isNotEmpty() && txtEditarDetalles.text.isNotEmpty() && txtEditarEstado.text.isNotEmpty() && txtEditarRecordatorio.text.isNotEmpty()){

                val retrivedTask = dbTask.getTasksByTitle(txtTitulo.text.toString())
                if (retrivedTask != null) {
                    if(retrivedTask.titulo.equals(txtTitulo.text.toString())){
                        var taskModel = TaskModel(
                            retrivedTask.id.toString().toInt(),
                            retrivedTask.titulo,
                            txtEditarDetalles.text.toString(),
                            txtEditarEstado.text.toString(),
                            txtEditarRecordatorio.text.toString()
                        )
                        dbTask.UpdateTasks(taskModel)
                        Toast.makeText(this@EditarTareaActivity, "Se modificaron los datos de la tarea", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@EditarTareaActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this@EditarTareaActivity, "No coinciden el titulo de la tarea con el titulo introducido en la caja de texto 'Título'", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            else{
                Toast.makeText(this@EditarTareaActivity, "Llene todos los campos para continuar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}