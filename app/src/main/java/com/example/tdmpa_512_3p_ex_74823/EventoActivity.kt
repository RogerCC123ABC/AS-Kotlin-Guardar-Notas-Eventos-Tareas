package com.example.tdmpa_512_3p_ex_74823

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class EventoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evento)

        val dbEvents = DatabaseEvents(this)
        val btnAgregarEvento = findViewById<Button>(R.id.btnAgregarEvento)
        val btnEditarEvento = findViewById<Button>(R.id.btnEditarEvento)

        btnAgregarEvento.setOnClickListener{
            val intent = Intent(this@EventoActivity, AgregarEventoActivity::class.java)
            startActivity(intent)
        }

        btnEditarEvento.setOnClickListener{
            val intent = Intent(this@EventoActivity, EditarEventoActivity::class.java)
            startActivity(intent)
        }

        cargarEventos(dbEvents)


    }

    fun cargarEventos(db: DatabaseEvents) {
        val chgEventos = findViewById<ChipGroup>(R.id.chgEvento)


        chgEventos.removeAllViews()


        val eventos = db.getAllEvents()


        for (evento in eventos) {
            agregarChipEvento(db, evento.titulo, chgEventos)
        }
    }

    fun agregarChipEvento(db:DatabaseEvents, titulo: String, chpGroup: ChipGroup): Chip {
        val retrievedEvent = db.getEventsByTitle(titulo)
        val chipEvent = Chip(this@EventoActivity)
        chipEvent.text = retrievedEvent?.titulo.toString()
        chipEvent.isClickable = true;
        chipEvent.isCheckable = false;
        chpGroup.addView(chipEvent as View)

        chipEvent.isCloseIconVisible = true;

        chipEvent.setOnCloseIconClickListener {
            chpGroup.removeView(chipEvent);
            db.deleteEvents(retrievedEvent?.id.toString().toInt())
        }

        chipEvent.setOnClickListener{
            val intent = Intent(this@EventoActivity, VerEventoActivity::class.java)
            intent.putExtra("tituloEvento", titulo)
            intent.putExtra("detallesEvento", retrievedEvent?.detalles.toString())
            intent.putExtra("estadoEvento", retrievedEvent?.estado.toString())
            intent.putExtra("recordatorioEvento", retrievedEvent?.recordatorio.toString())
            startActivity(intent)
        }
        return chipEvent
    }




}