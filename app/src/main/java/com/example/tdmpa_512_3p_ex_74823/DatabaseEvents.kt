package com.example.tdmpa_512_3p_ex_74823

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseEvents (context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "EventDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "EventTable"
        private const val KEY_ID = "_id"
        private const val KEY_TITULO = "titulo"
        private const val KEY_DETALLES = "detalles"
        private const val KEY_ESTADO = "estado"
        private const val KEY_RECORDATORIO = "recordatorio"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ($KEY_ID INTEGER PRIMARY KEY, $KEY_TITULO TEXT, $KEY_DETALLES TEXT, $KEY_ESTADO TEXT, $KEY_RECORDATORIO TEXT);")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addEvents(eventModel: EventModel){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_TITULO, eventModel.titulo)
        values.put(KEY_DETALLES, eventModel.detalles)
        values.put(KEY_ESTADO, eventModel.estado)
        values.put(KEY_RECORDATORIO, eventModel.recordatorio)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getEventsByTitle(titulo: String): EventModel?{
        val db = this.readableDatabase//consultar base de datos
        val cursorEvent = db.query(
            TABLE_NAME,
            arrayOf(KEY_ID, KEY_TITULO, KEY_DETALLES, KEY_ESTADO, KEY_RECORDATORIO),
            "$KEY_TITULO=?",
            arrayOf(titulo),
            null,
            null,
            null
        )
        return if(cursorEvent.moveToFirst()){
            val id = cursorEvent.getInt(cursorEvent.getColumnIndex(KEY_ID))
            val detalles = cursorEvent.getString(cursorEvent.getColumnIndex(KEY_DETALLES))
            val estado = cursorEvent.getString(cursorEvent.getColumnIndex(KEY_ESTADO))
            val recordatorio = cursorEvent.getString(cursorEvent.getColumnIndex(KEY_RECORDATORIO))
            EventModel(id, titulo, detalles, estado, recordatorio)
        }
        else{
            null
        }
    }

    fun UpdateEvents(eventModel: EventModel){
        val db = this.writableDatabase//escribir en la base de datos
        val values = ContentValues();
        values.put(KEY_TITULO, eventModel.titulo)
        values.put(KEY_DETALLES, eventModel.detalles)
        values.put(KEY_ESTADO, eventModel.estado)
        values.put(KEY_RECORDATORIO, eventModel.recordatorio)
        db.update(TABLE_NAME, values, "${KEY_ID}=?", arrayOf(eventModel.id.toString()))

    }

    fun deleteEvents(id: Int): Boolean {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "$KEY_ID = ?", arrayOf(id.toString()))
        return result > 0
    }

    @SuppressLint("Range")
    fun getAllEvents(): List<EventModel> {
        val EventsList = mutableListOf<EventModel>()
        val db = this.readableDatabase
        val cursorEvent = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursorEvent.moveToFirst()) {
            do {
                val id = cursorEvent.getInt(cursorEvent.getColumnIndex(KEY_ID))
                val titulo = cursorEvent.getString(cursorEvent.getColumnIndex(KEY_TITULO))
                val detalles = cursorEvent.getString(cursorEvent.getColumnIndex(KEY_DETALLES))
                val estado = cursorEvent.getString(cursorEvent.getColumnIndex(KEY_ESTADO))
                val recordatorio = cursorEvent.getString(cursorEvent.getColumnIndex(KEY_RECORDATORIO))

                val event = EventModel(id, titulo, detalles, estado, recordatorio)
                EventsList.add(event)
            } while (cursorEvent.moveToNext())
        }

        cursorEvent.close()
        db.close()

        return EventsList
    }

}

data class EventModel(val id: Int, val titulo: String, val detalles: String,  val estado: String, val recordatorio: String)