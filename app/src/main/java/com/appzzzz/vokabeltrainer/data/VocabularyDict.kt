package com.appzzzz.vokabeltrainer.data

import android.util.Log

class VocabularyDict(vocList: MutableList<Vocabulary>) {
    private var vocabularyList: MutableList<Vocabulary>
    private var vocabularyLearnList: MutableList<Vocabulary>
    private var _selectedVocabulary: Vocabulary? = null
    private var _falseVocabularies: MutableList<Vocabulary>? = null

    public val selectedVocabulary get() = _selectedVocabulary

    init {
        vocabularyList = vocList
        vocabularyLearnList = vocabularyList.toMutableList()
        vocabularyLearnList.shuffle()
    }

    fun selectRandomLearnVocabulary() : Vocabulary? {
        if(vocabularyLearnList.size <= 0){
            return null
        }
        _selectedVocabulary = vocabularyLearnList.random()
        vocabularyLearnList.remove(_selectedVocabulary)

        _falseVocabularies = null
        return _selectedVocabulary
    }

    fun resetVocabularyLearnList(vocList: MutableList<Vocabulary>) {
        vocabularyLearnList = vocabularyList.toMutableList()
        vocabularyLearnList.shuffle()
    }

    fun getCopyOfVocabularyList(type: String="") : MutableList<Vocabulary> {
        if(type == "")
            return vocabularyList.toMutableList()
        else
            return vocabularyList.filter {v -> v.vocabularyType == type}.toMutableList()
    }

    fun selectFalseVocabularies(cntAnswers: Int, sameType: Boolean=false) : List<Vocabulary>? {
        if(_selectedVocabulary==null) {
            Log.e("selectFalseAnswers:", "No Vocabulary selected (selectedVocabulary == null)")
            return null
        }
        var vocabularyListCopy: MutableList<Vocabulary>
        if(sameType) {
            vocabularyListCopy = getCopyOfVocabularyList(selectedVocabulary!!.vocabularyType)
        }
        else{
            vocabularyListCopy = getCopyOfVocabularyList()
        }
        _falseVocabularies = mutableListOf<Vocabulary>()
        vocabularyListCopy.remove(_selectedVocabulary)

        for(index in 0 until cntAnswers-1) {
            val falseVocabulary = vocabularyListCopy.random()
            _falseVocabularies!!.add(falseVocabulary)
            vocabularyListCopy.remove(falseVocabulary)
        }

        return _falseVocabularies
    }

    fun selectAndShuffleAllPossibleVocabularies(cntAnswers: Int) : List<Vocabulary>? {
        selectRandomLearnVocabulary()
        selectFalseVocabularies(cntAnswers, true)

        if(selectedVocabulary==null){
            Log.e("selectAllVocabularies", "No Vocabulary selected (selectedVocabulary == null)")
            return null
        }
        if(_falseVocabularies == null) {
            Log.e("selectAllVocabularies", "No false Vocabularies selected (_falseVocabularies == null)")
            return null
        }
        var allVocs = mutableListOf<Vocabulary>()
        allVocs.add(_selectedVocabulary!!)
        for(v in _falseVocabularies!!){
            allVocs.add(v)
        }
        allVocs.shuffle()
        return allVocs
    }

    fun getAllPossibleAnswers(cntAnswers: Int) : MutableList<String>? {
        var allPossibleAnswerStrings = mutableListOf<String>()
        var allPossibleAnswers = selectAndShuffleAllPossibleVocabularies(cntAnswers)
        if(allPossibleAnswers == null){
            Log.e("getAllPossibleAnswers", "No possible Answers! (allPossibleAnswers == null)")
            return null
        }
        for(a in allPossibleAnswers){
            allPossibleAnswerStrings.add(a.englishVocabulary)
        }
        return allPossibleAnswerStrings

    }

}