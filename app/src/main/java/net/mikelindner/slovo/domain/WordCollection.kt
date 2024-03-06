package net.mikelindner.slovo.domain

import net.mikelindner.slovo.db.Words
import kotlin.random.Random

class WordCollection : Words {
    private var words: ArrayList<Word> = ArrayList()
    private var nextId: Long = 0L;

    init {
        words = arrayListOf(
            Word(nextId++,"слово", "word"),
            Word(nextId++,"нос", "nose"),
            Word(nextId++,"машина", "car"),
            Word(nextId++,"идти", "go")
        )
    }

    override fun nextWord(): Word {
        val rndIdx = Random.nextInt(words.size)
        return words[rndIdx]
    }

    override fun allWords(): List<Word> {
        return words
    }

    override fun getWord(id: Long): Word {
        return words[id.toInt()];
    }

    override fun add(word: Word) {
        word.id = nextId++;
        words.add(word)
    }

    override fun remove(id: Long) {
        words.removeAt(id.toInt())
    }

    override fun update(word: Word) {
        words[word.id.toInt()] = word
    }
}