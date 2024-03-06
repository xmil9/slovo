package net.mikelindner.slovo.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.mikelindner.slovo.db.WordRepository
import net.mikelindner.slovo.domain.Word

class WordViewModel(
    private val wordRepo: WordRepository,
    private var wordId: Long
) : ViewModel() {
    private var word = Word();
    private var _ru = mutableStateOf("")
    private var _en = mutableStateOf("")
    private var _wordClass = mutableStateOf("")

    init {
        if (isUpdatingWord()) {
            viewModelScope.launch {
                val w = wordRepo.getWord(wordId)
                if (w != null) {
                    word = w
                    _ru.value = word.ru
                    _en.value = word.en
                    _wordClass.value = word.wordClass
                }
            }
        }
    }

    var russian: String
        get() = _ru.value
        set(value) { _ru.value = value }

    var english: String
        get() = _en.value
        set(value) { _en.value = value }

    var wordClass: String
        get() = _wordClass.value
        set(value) { _wordClass.value = value }

    fun isAddingWord(): Boolean {
        return wordId == 0L
    }

    fun isUpdatingWord(): Boolean {
        return !isAddingWord()
    }

    fun onDelete() {
        viewModelScope.launch(Dispatchers.IO) {
            wordRepo.deleteWord(word)
        }
    }

    fun onEditConfirmed() {
        viewModelScope.launch(Dispatchers.IO) {
            if (isAddingWord())
                wordRepo.addWord(Word(ru = russian, en = english, wordClass = wordClass))
            else
                wordRepo.updateWord(Word(id = wordId, ru = russian, en = english, wordClass = wordClass))
        }
    }

    fun onEditCanceled() {
        // Nothing to do.
    }
}