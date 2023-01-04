package com.appzzzz.vokabeltrainer.stream

import android.content.res.AssetManager
import com.appzzzz.vokabeltrainer.data.Vocabulary
import java.io.BufferedReader
import java.io.InputStreamReader

object CsvImport {
    class CsvImport {
        companion object {
            fun getVocabularyList(assets: AssetManager): MutableList<Vocabulary> {
                val vocFile = assets.open(VocabularyCsv.fileName)
                val inputStreamReader = InputStreamReader(vocFile)
                val reader = BufferedReader(inputStreamReader)
                val header = reader.readLine()
                return reader.lineSequence()
                    .filter { it.isNotBlank() }
                    .map {
                        val (germanVoc, englishVoc) = it.split(';', ignoreCase = false, limit = 2)
                        Vocabulary(germanVoc, englishVoc)
                    }.toMutableList()
            }

            val fileName = "Vokabeln.csv"

        }
    }
}