package net.mikelindner.slovo.db

import net.mikelindner.slovo.domain.Word

interface Words {
    fun nextWord(): Word
    fun allWords(): List<Word>
    fun getWord(id: Long): Word;
    fun add(word: Word)
    fun remove(id: Long)
    fun update(word: Word)
}