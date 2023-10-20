package com.example.medtime.model.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ConexaoDB(context : Context) : SQLiteOpenHelper(context, NAME,null, VERSION) {
    companion object{
        private val NAME : String = "medtime_db"
        private val VERSION : Int = 1
    }

    override fun onCreate(medtime_db : SQLiteDatabase) {
        medtime_db.execSQL("CREATE TABLE IF NOT EXISTS medicamentos (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    nome VARCHAR(64) NOT NULL,\n" +
                "    ref_imagem TINYINT NOT NULL\n" +
                ");")

        medtime_db.execSQL("CREATE TABLE IF NOT EXISTS agendamentos (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    data_inicio VARCHAR(20) NOT NULL,\n" +
                "    data_final VARCHAR(20) NOT NULL,\n" +
                "    unidade_de_medida VARCHAR(20) NOT NULL,\n" +
                "    dosagem FLOAT NOT NULL,\n" +
                "    horario VARCHAR(20) NOT NULL,\n" +
                "    fk_medicamento_id INTEGER NOT NULL,\n" +
                "    FOREIGN KEY (fk_medicamento_id) REFERENCES medicamentos(id)\n" +
                ");\n")
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

}

