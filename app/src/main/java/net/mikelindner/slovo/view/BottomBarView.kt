package net.mikelindner.slovo.view

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import net.mikelindner.slovo.app.AppScreen

val bottomBarScreens = listOf(
    AppScreen.WordList,
    AppScreen.Quiz
)

fun makeBottomBar(activeScreen: AppScreen, nav: NavHostController): @Composable () -> Unit {
    val bar: @Composable () -> Unit = {
        NavigationBar(modifier = Modifier.wrapContentSize()) {
            bottomBarScreens.forEach { screen ->
                NavigationBarItem(
                    selected = activeScreen == screen,
                    onClick = { nav.navigate(route = screen.route) },
                    icon = { Icon(painter = painterResource(id = screen.icon), contentDescription = screen.title) }
                )
            }
        }
    }
    return bar;
}
