package com.appzzzz.vokabeltrainer.data

data class Vocabulary(
    val germanVocabulary: String = "",
    val englishVocabulary: String = "",
    val firstLetterCore: Int = 0,
    val lastLetterCore: Int = 0,
    val englishVocabularyCore: String = "",
    val vocabularyType: String = "",
    val irregularVerbInfinitive: String = "",
    val irregularVerbSimplePast: String = "",
    val irregularVerbPastParticiple: String = "")