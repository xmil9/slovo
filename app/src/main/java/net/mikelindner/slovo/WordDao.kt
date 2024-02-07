package net.mikelindner.slovo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface WordDao {
    @Query("Select * from 'words'")
    suspend fun getWords(): List<Word>

    @Query("Select * from 'words' where id=:id")
    suspend fun getWord(id: Long): Word

    @Query("Select * from 'words' order by random() limit 1")
    suspend fun getRandomWord(): Word

    @Query("Select * from 'words' where " +
            "ru like '%'|| :pattern || '%' or " +
            "en like '%'|| :pattern || '%'")
    suspend fun searchWords(pattern: String): List<Word>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWord(wordEntity: Word)

    @Update
    suspend  fun updateWord(wordEntity: Word)

    @Delete
    suspend fun deleteWord(wordEntity: Word)
}