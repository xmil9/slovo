package net.mikelindner.slovo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Locale

class WordQuizViewModel(private val wordRepo: WordRepository) : ViewModel() {
    private var word = mutableStateOf(Word())
    private var nextWord = Word()
    private var _enteredTranslation = mutableStateOf("")
    private var _direction =  mutableStateOf(toEnglish)
    private var translation: Translation =  EnglishTranslation()

    init {
        viewModelScope.launch {
            word.value = wordRepo.getRandomWord()
        }
        viewModelScope.launch {
            nextWord = wordRepo.getRandomWord()
        }
    }

    var direction: String
        get() = _direction.value
        set(value) {
            _direction.value = value
            translation = when (value) {
                toRussian -> RussianTranslation()
                else -> EnglishTranslation()
            }
        }

    val fromLanguage: String
        get() = translation.fromLanguage

    val from: String
        get() = translation.from(word.value)

    val toLanguage: String
        get() = translation.toLanguage

    val to: String
        get() = translation.to(word.value)

    val wordClass: String
        get() = word.value.wordClass

    var enteredTranslation: String
        get() = _enteredTranslation.value
        set(value) { _enteredTranslation.value = value }

    fun checkTranslation(): Boolean {
        return _enteredTranslation.value.lowercase(Locale.US) == to.lowercase(Locale.US)
    }

    fun nextWord() {
        word.value = nextWord
        viewModelScope.launch {
            nextWord = wordRepo.getRandomWord()
        }
        enteredTranslation = ""
    }
}