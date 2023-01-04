package com.appzzzz.vokabeltrainer.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.appzzzz.vokabeltrainer.data.Vocabulary

class DbHelper(val context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null,1) {


    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL(SQL_CREATE_ENTRIES)
        Log.i("onCreate:", "db: ${db.toString()}")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL(SQL_DELETE_TABLE)
        onCreate(db)
    }

    fun insertVoc(voc: Vocabulary) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(DBContract.VocabularyEntry.COLUMN_GERMAN_VOC, voc.germanVocabulary)
            put(DBContract.VocabularyEntry.COLUMN_ENGLISH_VOC, voc.englishVocabulary)
        }
        val newRowId = db.insert(DBContract.VocabularyEntry.TABLE_NAME,null,values)
    }

    fun importVocs(vocList: List<Vocabulary>){
        val db = writableDatabase
        db.execSQL(SQL_DELETE_ENTRIES)
        for(v in vocList) {
            db.execSQL(SqlInsertEntry(v))
        }
    }

    fun getVocabularyList() : MutableList<Vocabulary> {
        val returnList = mutableListOf<Vocabulary>()
        val db = readableDatabase
        val c = readableDatabase.rawQuery(SQL_SELECT_VOCABULARIES, null)
        c.moveToFirst()

        Log.i("getVocabularyList:", "count(c): ${c.count}")
        Log.i("getVocabularyList:", "readableDatabase): ${readableDatabase.toString()}")
        return returnList
    }
    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Vocabularies.db"

        private val SQL_CREATE_ENTRIES =
            "create table ${DBContract.VocabularyEntry.TABLE_NAME} " +
                    "(${DBContract.VocabularyEntry.COLUMN_GERMAN_VOC} varchar, " +
                    "${DBContract.VocabularyEntry.COLUMN_ENGLISH_VOC} varchar)"

        private val SQL_DELETE_TABLE =
            "drop table if exists " + DBContract.VocabularyEntry.TABLE_NAME

        private val SQL_DELETE_ENTRIES =
            "delete from ${DBContract.VocabularyEntry.TABLE_NAME}"

        private val SQL_SELECT_VOCABULARIES =
            "select " +
                    "${DBContract.VocabularyEntry.COLUMN_GERMAN_VOC}, " +
                    "${DBContract.VocabularyEntry.COLUMN_ENGLISH_VOC} " +
                    "from ${DBContract.VocabularyEntry.TABLE_NAME}"

        private fun SqlInsertEntry(v: Vocabulary) : String {
            return "insert into ${DBContract.VocabularyEntry.TABLE_NAME} " +
                    "(${DBContract.VocabularyEntry.COLUMN_GERMAN_VOC}, " +
                    "${DBContract.VocabularyEntry.COLUMN_ENGLISH_VOC}) " +
                    "values ('${v.germanVocabulary}', '${v.englishVocabulary}')"
        }
    }
}