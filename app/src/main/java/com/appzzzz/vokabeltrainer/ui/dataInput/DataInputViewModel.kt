package com.appzzzz.vokabeltrainer.ui.dataInput

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataInputViewModel : ViewModel() {

    private val _textGermanVocabulary = MutableLiveData<String>().apply {
        value = "Deutsche Vokabel"
    }
    private val _textEnglishVocabulary = MutableLiveData<String>().apply {
        value = "Englische Vokabel"
    }
    val textGermanVocabulary: LiveData<String> = _textGermanVocabulary
    val textEnglishVocabulary: LiveData<String> = _textEnglishVocabulary
}