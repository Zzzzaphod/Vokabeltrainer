package com.appzzzz.vokabeltrainer.data

import android.util.Log

/**
 * Class of Vocabularies.
 */
class VocabularyDict(vocList: MutableList<Vocabulary>) {

    private var vocabularyList: MutableList<Vocabulary>
    private lateinit var vocabularyLearnList: MutableList<Vocabulary>
    private var _selectedVocabulary: Vocabulary? = null
    private var _falseVocabularies: MutableList<Vocabulary>? = null

    public val selectedVocabulary get() = _selectedVocabulary

    init {
        vocabularyList = vocList
        resetVocabularyLearnList()
    }

    /**
     * Selects a random Vocabulary out of the [vocabularyLearnList].
     * The selected item will be removed from this list and returned.
     */
    fun selectRandomLearnVocabulary(): Vocabulary? {
        if (vocabularyLearnList.size <= 0) {
            resetVocabularyLearnList()
        }
        _selectedVocabulary = vocabularyLearnList.random()
        vocabularyLearnList.remove(_selectedVocabulary)

        _falseVocabularies = null
        return _selectedVocabulary
    }

    /**
     * Resets the [vocabularyLearnList] to the full Copy of the original [vocabularyList].
     */
    fun resetVocabularyLearnList() {
        vocabularyLearnList = getCopyOfVocabularyList()
        vocabularyLearnList.shuffle()
    }

    /**
     * Returns a (sub)copy of the [vocabularyList].
     * By a given [type] the selection will contain all Vocabularies of the same type.
     */
    fun getCopyOfVocabularyList(type: String = ""): MutableList<Vocabulary> {
        if (type == "")
            return vocabularyList.toMutableList()
        else
            return vocabularyList.filter { v -> v.vocabularyType == type }.toMutableList()
    }

    /**
     * Returns a list of [cntFalseAnswers] Vocabularies unequal to the [selectedVocabulary].
     * If [sameType] is true, the list only contains Vocabularies of the same type as the [selectedVocabulary].
     *
     * Returns null if [selectedVocabulary] is null.
     */
    fun selectFalseVocabularies(cntFalseAnswers: Int, sameType: Boolean = false): List<Vocabulary>? {
        if (_selectedVocabulary == null) {
            Log.e("selectFalseAnswers:", "No Vocabulary selected (selectedVocabulary == null)")
            return null
        }

        var vocabularyListCopy: MutableList<Vocabulary>
        if (sameType) {
            vocabularyListCopy = getCopyOfVocabularyList(selectedVocabulary!!.vocabularyType)
        } else {
            vocabularyListCopy = getCopyOfVocabularyList()
        }
        vocabularyListCopy.remove(_selectedVocabulary)

        _falseVocabularies = mutableListOf<Vocabulary>()

        for (index in 0 until cntFalseAnswers) {
            val falseVocabulary = vocabularyListCopy.random()
            _falseVocabularies!!.add(falseVocabulary)
            vocabularyListCopy.remove(falseVocabulary)
        }

        return _falseVocabularies
    }

    /**
     * Returns a shuffeled list of [cntAnswers] Vocabularies including the [selectedVocabulary].
     */
    fun selectAndShuffleAllPossibleVocabularies(cntAnswers: Int): List<Vocabulary>? {
        selectRandomLearnVocabulary()
        selectFalseVocabularies(cntAnswers-1, true)

        if (selectedVocabulary == null) {
            Log.e("selectAllVocabularies", "No Vocabulary selected (selectedVocabulary == null)")
            return null
        }
        if (_falseVocabularies == null) {
            Log.e(
                "selectAllVocabularies",
                "No false Vocabularies selected (_falseVocabularies == null)"
            )
            return null
        }
        var allVocs = mutableListOf<Vocabulary>()
        allVocs.add(_selectedVocabulary!!)
        for (v in _falseVocabularies!!) {
            allVocs.add(v)
        }
        allVocs.shuffle()
        return allVocs
    }

    /**
     * Returns a list of [cntAnswers] Strings containing the english Vocabularies of the
     * result of [selectAndShuffleAllPossibleVocabularies].
     */
    fun getAllPossibleAnswers(cntAnswers: Int): MutableList<String>? {
        var allPossibleAnswerStrings = mutableListOf<String>()
        var allPossibleAnswers = selectAndShuffleAllPossibleVocabularies(cntAnswers)
        if (allPossibleAnswers == null) {
            Log.e("getAllPossibleAnswers", "No possible Answers! (allPossibleAnswers == null)")
            return null
        }
        for (a in allPossibleAnswers) {
            allPossibleAnswerStrings.add(a.englishVocabulary)
        }
        return allPossibleAnswerStrings

    }

}