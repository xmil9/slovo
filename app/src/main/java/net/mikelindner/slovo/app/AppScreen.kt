package net.mikelindner.slovo.app

import androidx.annotation.DrawableRes
import net.mikelindner.slovo.R

sealed class AppScreen(val title: String, val route: String, @DrawableRes val icon: Int) {
    data object WordList: AppScreen("Word List", "word-list", R.drawable.list_24)
    data object WordDetail: AppScreen("Word", "word-detail", R.drawable.detail_24)
    data object WordEditor: AppScreen("Edit Word", "word-editor", R.drawable.edit_24)
    data object Quiz: AppScreen("Quiz","quiz", R.drawable.quiz_24)
}