package net.mikelindner.slovo

import android.content.Context
import androidx.room.Room

object WordService {
    lateinit var db: WordDB

    val wordRepo: WordRepository by lazy {
        DBWordRepository(wordDao = db.wordDao())
    }

    fun provide(context: Context) {
        db = Room.databaseBuilder(context, WordDB::class.java, "wordlist.db")
            .createFromAsset("db/russian.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}