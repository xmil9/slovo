package net.mikelindner.slovo

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class WordListViewModel(private val wordRepo: WordRepository, val haveEditing: Boolean) :
    ViewModel() {

    var wordList = mutableStateOf(listOf<Word>())
    var searchResults = mutableStateOf(listOf<Word>())

    init {
        allWords()
    }

    fun searchWords(pattern: String) {
        viewModelScope.launch {
            searchResults.value = wordRepo.searchWords(pattern)
        }
    }

    fun allWords() {
        viewModelScope.launch {
            wordList.value = wordRepo.getWords()
        }
    }
}