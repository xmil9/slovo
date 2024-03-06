package net.mikelindner.slovo.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import net.mikelindner.slovo.view.WordDetailView
import net.mikelindner.slovo.view.WordEditorView
import net.mikelindner.slovo.view.WordListView
import net.mikelindner.slovo.view.WordListViewModel
import net.mikelindner.slovo.view.WordQuizView
import net.mikelindner.slovo.view.WordQuizViewModel
import net.mikelindner.slovo.db.WordRepository
import net.mikelindner.slovo.view.WordViewModel
import net.mikelindner.slovo.view.makeBottomBar

@Composable
fun AppNavigation(wordRepo: WordRepository, haveEditing: Boolean) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.WordList.route) {

        // Word list
        composable(AppScreen.WordList.route) {
            WordListView(
                vm = WordListViewModel(wordRepo, haveEditing),
                navToWord = { wordId ->
                    val route = AppScreen.WordDetail.route + "/$wordId"
                    navController.navigate(route)
                },
                navToAddWord = {
                    val route = AppScreen.WordEditor.route + "/0"
                    navController.navigate(route)
                },
                makeBottomBar(AppScreen.WordList, navController)
            )
        }

        // Word detail
        composable(
            route = AppScreen.WordDetail.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ) {entry ->
            // Determine word to show from passed arg.
            val wordId = if (entry.arguments != null && !entry.arguments!!.isEmpty())
                entry.arguments!!.getLong("id")
            else
                0L

            WordDetailView(
                vm = WordViewModel(wordRepo, wordId),
                showEdit = false,
                navBack = { navController.navigateUp() },
                navEdit = {
                    val route = AppScreen.WordEditor.route + "/$wordId"
                    navController.navigate(route)
                },
                makeBottomBar(AppScreen.WordDetail, navController)
            )
        }

        // Word editor
        composable(
            route = AppScreen.WordEditor.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ) {entry ->
            // Determine word to show from passed arg.
            val wordId = if (entry.arguments != null && !entry.arguments!!.isEmpty())
                entry.arguments!!.getLong("id")
            else
                0L

            WordEditorView(
                vm = WordViewModel(wordRepo, wordId),
                navBack = { navController.navigateUp() },
                makeBottomBar(AppScreen.WordEditor, navController)
            )
        }

        // Quiz
        composable(AppScreen.Quiz.route) {
            WordQuizView(
                vm = WordQuizViewModel(wordRepo),
                navBack = { navController.navigateUp() },
                makeBottomBar(AppScreen.Quiz, navController)
            )
        }
    }
}