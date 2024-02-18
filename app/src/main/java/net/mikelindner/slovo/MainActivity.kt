package net.mikelindner.slovo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import net.mikelindner.slovo.ui.theme.SlovoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlovoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavigation(WordService.wordRepo, AppConfig.HAVE_EDITING)
                }
            }
        }
    }
}
