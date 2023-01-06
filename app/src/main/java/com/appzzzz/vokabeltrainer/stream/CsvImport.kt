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
                        val (englishVoc, germanVoc,
                            _,
                            _,
                            _,
                            firstLetterCore,
                            lastLetterCore,
                            englishVocabularyCore,
                            vocabularyType,
                            irregularVerbInfinitive,
                            irregularVerbSimplePast,
                            irregularVerbPastParticiple) = it.split(';', ignoreCase = false, limit = 12)
                        Vocabulary(germanVoc, englishVoc,
                            firstLetterCore.toInt(),
                            lastLetterCore.toInt(),
                            englishVocabularyCore,
                            vocabularyType,
                            irregularVerbInfinitive,
                            irregularVerbSimplePast,
                            irregularVerbPastParticiple)
                    }.toMutableList()
            }

            val fileName = "Vokabeln.csv"

            private operator fun <E> List<E>.component10(): E = get(9)
            private operator fun <E> List<E>.component11(): E = get(10)
            private operator fun <E> List<E>.component12(): E = get(11)
            private operator fun <E> List<E>.component9(): E = get(8)
            private operator fun <E> List<E>.component8(): E = get(7)
            private operator fun <E> List<E>.component7(): E = get(6)
            private operator fun <E> List<E>.component6(): E = get(5)

        }
    }
}

