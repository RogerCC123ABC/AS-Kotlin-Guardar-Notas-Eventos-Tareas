package com.example.tdmpa_512_3p_ex_74823

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class TareaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarea)


        val dbTasks = DatabaseTasks(this)
        val btnAgregarTarea = findViewById<Button>(R.id.btnAgregarTarea)
        val btnEditarTarea = findViewById<Button>(R.id.btnEditarTarea)

        btnAgregarTarea.setOnClickListener{
            val intent = Intent(this@TareaActivity, AgregarTareaActivity::class.java)
            startActivity(intent)
        }

        btnEditarTarea.setOnClickListener{
            val intent = Intent(this@TareaActivity, EditarTareaActivity::class.java)
            startActivity(intent)
        }

        cargarTareas(dbTasks)

    }

    fun cargarTareas(db: DatabaseTasks) {
        val chgTareas = findViewById<ChipGroup>(R.id.chpTareas)


        chgTareas.removeAllViews()


        val tareas = db.GetAllTasks()


        for (tarea in tareas) {
            agregarChipTarea(db, tarea.titulo, chgTareas)
        }
    }

    fun agregarChipTarea(db:DatabaseTasks, titulo: String, chpGroup: ChipGroup): Chip {
        val retrievedTask = db.getTasksByTitle(titulo)
        val chipTask = Chip(this@TareaActivity)
        chipTask.text = retrievedTask?.titulo.toString()
        chipTask.isClickable = true;
        chipTask.isCheckable = false;
        chpGroup.addView(chipTask as View)

        chipTask.isCloseIconVisible = true;

        chipTask.setOnCloseIconClickListener {
            chpGroup.removeView(chipTask);
            db.deleteTasks(retrievedTask?.id.toString().toInt())
        }

        chipTask.setOnClickListener{
            val intent = Intent(this@TareaActivity, VerTareaActivity::class.java)
            intent.putExtra("tituloTarea", titulo)
            intent.putExtra("detallesTarea", retrievedTask?.detalles.toString())
            intent.putExtra("estadoTarea", retrievedTask?.estado.toString())
            intent.putExtra("recordatorioTarea", retrievedTask?.recordatorio.toString())
            startActivity(intent)
        }
        return chipTask
    }

}