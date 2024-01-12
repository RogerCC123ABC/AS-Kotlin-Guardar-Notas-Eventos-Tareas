package com.example.tdmpa_512_3p_ex_74823

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class NotaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota)

        val btnAgregarNota = findViewById<Button>(R.id.btnAgregarNota)
        val btnEditarNota = findViewById<Button>(R.id.btnEditarNota)

        btnAgregarNota.setOnClickListener{
            val intent = Intent(this@NotaActivity, AgregarNotaActivity::class.java)
            startActivity(intent)
        }

        btnEditarNota.setOnClickListener{
            val intent = Intent(this@NotaActivity, EditarNotaActivity::class.java)
            startActivity(intent)
        }

        cargarNotas();

    }

    fun cargarNotas() {
        val chgNotas = findViewById<ChipGroup>(R.id.chgNotas)
        val dbNotes = DatabaseNotes(this)

        chgNotas.removeAllViews()


        val notas = dbNotes.getAllNotes()


        for (nota in notas) {
            agregarChipNota(dbNotes, nota.titulo, chgNotas)
        }
    }

    fun agregarChipNota(db:DatabaseNotes,titulo: String, chpGroup: ChipGroup): Chip {
        val retrievedNote = db.getNoteByTitle(titulo)
        val chipNote = Chip(this@NotaActivity)
        chipNote.text = retrievedNote?.titulo.toString()
        chipNote.isClickable = true;
        chipNote.isCheckable = false;
        chpGroup.addView(chipNote as View)

        chipNote.isCloseIconVisible = true;

        chipNote.setOnCloseIconClickListener {
            chpGroup.removeView(chipNote);
            db.deleteNote(retrievedNote?.id.toString().toInt())
        }

        chipNote.setOnClickListener{
            val intent = Intent(this@NotaActivity, VerNotaEventoTareaActivity::class.java)
            intent.putExtra("titulo", titulo)
            intent.putExtra("detalles", retrievedNote?.detalles.toString())
            intent.putExtra("estado", retrievedNote?.estado.toString())
            intent.putExtra("recordatorio", retrievedNote?.recordatorio.toString())
            startActivity(intent)
        }
        return chipNote
    }
}