package net.mikelindner.slovo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Word::class],
    exportSchema = false,
    version = 3
)
abstract class WordDB : RoomDatabase() {
    abstract fun wordDao(): WordDao
}