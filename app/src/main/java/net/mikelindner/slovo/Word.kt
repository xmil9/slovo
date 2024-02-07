package net.mikelindner.slovo

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @NonNull
    val ru: String = "",
    @NonNull
    val en: String = "",
    @NonNull
    val wordClass: String = ""
) : Parcelable
