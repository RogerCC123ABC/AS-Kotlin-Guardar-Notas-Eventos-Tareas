package com.example.tdmpa_512_3p_ex_74823

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseNotes (context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "LoginDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "NoteTable"
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

    fun addNote(noteModel: NoteModel){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_TITULO, noteModel.titulo)
        values.put(KEY_DETALLES, noteModel.detalles)
        values.put(KEY_ESTADO, noteModel.estado)
        values.put(KEY_RECORDATORIO, noteModel.recordatorio)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getNoteByTitle(titulo: String): NoteModel?{
        val db = this.readableDatabase//consultar base de datos
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(KEY_ID, KEY_TITULO, KEY_DETALLES, KEY_ESTADO, KEY_RECORDATORIO),
            "$KEY_TITULO=?",
            arrayOf(titulo),
            null,
            null,
            null
        )
        return if(cursor.moveToFirst()){
            val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
            val detalles = cursor.getString(cursor.getColumnIndex(KEY_DETALLES))
            val estado = cursor.getString(cursor.getColumnIndex(KEY_ESTADO))
            val recordatorio = cursor.getString(cursor.getColumnIndex(KEY_RECORDATORIO))
            NoteModel(id, titulo, detalles, estado, recordatorio)
        }
        else{
            null
        }
    }

    fun UpdateNote(noteModel: NoteModel){
        val db = this.writableDatabase//escribir en la base de datos
        val values = ContentValues();
        values.put(KEY_TITULO, noteModel.titulo)
        values.put(KEY_DETALLES, noteModel.detalles)
        values.put(KEY_ESTADO, noteModel.estado)
        values.put(KEY_RECORDATORIO, noteModel.recordatorio)
        db.update(TABLE_NAME, values, "${KEY_ID}=?", arrayOf(noteModel.id.toString()))

    }

    fun deleteNote(id: Int): Boolean {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "$KEY_ID = ?", arrayOf(id.toString()))
        return result > 0
    }

    @SuppressLint("Range")
    fun getAllNotes(): List<NoteModel> {
        val notesList = mutableListOf<NoteModel>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val titulo = cursor.getString(cursor.getColumnIndex(KEY_TITULO))
                val detalles = cursor.getString(cursor.getColumnIndex(KEY_DETALLES))
                val estado = cursor.getString(cursor.getColumnIndex(KEY_ESTADO))
                val recordatorio = cursor.getString(cursor.getColumnIndex(KEY_RECORDATORIO))

                val note = NoteModel(id, titulo, detalles, estado, recordatorio)
                notesList.add(note)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return notesList
    }

}

data class NoteModel(val id: Int, val titulo: String, val detalles: String,  val estado: String, val recordatorio: String)