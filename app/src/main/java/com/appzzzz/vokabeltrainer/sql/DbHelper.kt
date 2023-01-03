package com.appzzzz.vokabeltrainer.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.appzzzz.vokabeltrainer.data.Vocabulary

class DbHelper(val context: Context, val dbName: String)
    : SQLiteOpenHelper(context, dbName, null,1) {

    val VOCABULARY_TABLE_NAME : String = "Vocabulary"

    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL(SQL_CREATE_ENTRIES)
        Log.i("onCreate:", "db: ${db.toString()}")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun insertVoc(voc: Vocabulary) {
        val db = writableDatabase

        val values = ContentValues()
        values.put(DBContract.VocabularyEntry.COLUMN_GERMAN_VOC, voc.germanVocabulary)
        values.put(DBContract.VocabularyEntry.COLUMN_ENGLISH_VOC, voc.englishVocabulary)

    }
    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Vocabularies.db"

        private val SQL_CREATE_ENTRIES =
            "create table " + DBContract.VocabularyEntry.TABLE_NAME + " (" +
                    DBContract.VocabularyEntry.COLUMN_GERMAN_VOC + " varchar, " +
                    DBContract.VocabularyEntry.COLUMN_ENGLISH_VOC + " varchar)"

        private val SQL_DELETE_ENTRIES =
            "drop table if exists " + DBContract.VocabularyEntry.TABLE_NAME
    }
}