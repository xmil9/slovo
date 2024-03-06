package net.mikelindner.slovo.db

import net.mikelindner.slovo.domain.Word

interface WordRepository {
    suspend fun getWords(): List<Word>
    suspend fun getWord(id: Long): Word
    suspend fun getRandomWord(): Word
    suspend fun searchWords(pattern: String): List<Word>
    suspend fun addWord(w: Word)
    suspend fun updateWord(w: Word)
    suspend fun deleteWord(w: Word)
}

class DBWordRepository(private val wordDao: WordDao) : WordRepository {
    override suspend fun getWords(): List<Word> {
        return wordDao.getWords()
    }

    override suspend fun getWord(id: Long): Word {
        return wordDao.getWord(id)
    }

    override suspend fun getRandomWord(): Word {
        return wordDao.getRandomWord()
    }

    override suspend fun searchWords(pattern: String): List<Word> {
        return wordDao.searchWords(pattern)
    }

    override suspend fun addWord(w: Word) {
        wordDao.addWord(w)
    }

    override suspend fun updateWord(w: Word) {
        wordDao.updateWord(w)
    }

    override suspend fun deleteWord(w: Word) {
        wordDao.deleteWord(w)
    }
}

class NullWordRepository : WordRepository {
    override suspend fun getWords(): List<Word> {
        return emptyList()
    }

    override suspend fun getWord(id: Long): Word {
        return Word()
    }

    override suspend fun getRandomWord(): Word {
        return Word()
    }

    override suspend fun searchWords(pattern: String): List<Word> {
        return emptyList()
    }

    override suspend fun addWord(w: Word) {
    }

    override suspend fun updateWord(w: Word) {
    }

    override suspend fun deleteWord(w: Word) {
    }
}