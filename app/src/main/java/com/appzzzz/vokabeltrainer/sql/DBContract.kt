package com.appzzzz.vokabeltrainer.sql

import android.provider.BaseColumns

object DBContract {
    class VocabularyEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "Vocabulary"
            val COLUMN_GERMAN_VOC = "germanVoc"
            val COLUMN_ENGLISH_VOC = "englishVoc"

        }
    }
}