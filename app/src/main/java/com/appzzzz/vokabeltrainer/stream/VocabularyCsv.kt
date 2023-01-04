package com.appzzzz.vokabeltrainer.stream

import android.content.res.AssetManager
import android.system.Os.open
import com.appzzzz.vokabeltrainer.data.Vocabulary
import java.io.BufferedReader
import java.io.InputStreamReader


class VocabularyCsv(val assets: AssetManager) {

    fun getVocabularyList(): List<Vocabulary> {
        val vocFile = assets.open(fileName)
        val inputStreamReader = InputStreamReader(vocFile)
        val reader = BufferedReader(inputStreamReader)
        val header = reader.readLine()
        return reader.lineSequence()
            .filter { it.isNotBlank() }
            .map {
                val (germanVoc, englishVoc) = it.split(';', ignoreCase = false, limit = 2)
                Vocabulary(germanVoc, englishVoc)
            }.toList()
    }

    companion object {

        val fileName = "Vokabeln.csv"
    }
    //val movies = readCsv(/*Open a stream to CSV file*/)
}