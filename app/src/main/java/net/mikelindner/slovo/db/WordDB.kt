package net.mikelindner.slovo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import net.mikelindner.slovo.domain.Word

@Database(
    entities = [Word::class],
    exportSchema = false,
    version = 3
)
abstract class WordDB : RoomDatabase() {
    abstract fun wordDao(): WordDao
}