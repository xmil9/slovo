package net.mikelindner.slovo.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.mikelindner.slovo.db.WordRepository
import net.mikelindner.slovo.domain.Word

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