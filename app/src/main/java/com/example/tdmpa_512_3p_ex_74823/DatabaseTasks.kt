package com.example.tdmpa_512_3p_ex_74823

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseTasks (context:Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "TaskDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "TaskTable"
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

    fun addTasks(taskModel: TaskModel){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_TITULO, taskModel.titulo)
        values.put(KEY_DETALLES, taskModel.detalles)
        values.put(KEY_ESTADO, taskModel.estado)
        values.put(KEY_RECORDATORIO, taskModel.recordatorio)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getTasksByTitle(titulo: String): TaskModel?{
        val db = this.readableDatabase//consultar base de datos
        val cursorTask = db.query(
            TABLE_NAME,
            arrayOf(KEY_ID, KEY_TITULO, KEY_DETALLES, KEY_ESTADO, KEY_RECORDATORIO),
            "$KEY_TITULO=?",
            arrayOf(titulo),
            null,
            null,
            null
        )
        return if(cursorTask.moveToFirst()){
            val id = cursorTask.getInt(cursorTask.getColumnIndex(KEY_ID))
            val detalles = cursorTask.getString(cursorTask.getColumnIndex(KEY_DETALLES))
            val estado = cursorTask.getString(cursorTask.getColumnIndex(KEY_ESTADO))
            val recordatorio = cursorTask.getString(cursorTask.getColumnIndex(KEY_RECORDATORIO))
            TaskModel(id, titulo, detalles, estado, recordatorio)
        }
        else{
            null
        }
    }

    fun UpdateTasks(taskModel: TaskModel){
        val db = this.writableDatabase//escribir en la base de datos
        val values = ContentValues();
        values.put(KEY_TITULO, taskModel.titulo)
        values.put(KEY_DETALLES, taskModel.detalles)
        values.put(KEY_ESTADO, taskModel.estado)
        values.put(KEY_RECORDATORIO, taskModel.recordatorio)
        db.update(TABLE_NAME, values, "${KEY_ID}=?", arrayOf(taskModel.id.toString()))

    }

    fun deleteTasks(id: Int): Boolean {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "$KEY_ID = ?", arrayOf(id.toString()))
        return result > 0
    }

    @SuppressLint("Range")
    fun GetAllTasks(): List<TaskModel> {
        val TasksList = mutableListOf<TaskModel>()
        val db = this.readableDatabase
        val cursorTask = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursorTask.moveToFirst()) {
            do {
                val id = cursorTask.getInt(cursorTask.getColumnIndex(KEY_ID))
                val titulo = cursorTask.getString(cursorTask.getColumnIndex(KEY_TITULO))
                val detalles = cursorTask.getString(cursorTask.getColumnIndex(KEY_DETALLES))
                val estado = cursorTask.getString(cursorTask.getColumnIndex(KEY_ESTADO))
                val recordatorio = cursorTask.getString(cursorTask.getColumnIndex(KEY_RECORDATORIO))

                val task = TaskModel(id, titulo, detalles, estado, recordatorio)
                TasksList.add(task)
            } while (cursorTask.moveToNext())
        }

        cursorTask.close()
        db.close()

        return TasksList
    }

}

data class TaskModel(val id: Int, val titulo: String, val detalles: String,  val estado: String, val recordatorio: String)