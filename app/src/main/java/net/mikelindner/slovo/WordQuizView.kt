package net.mikelindner.slovo

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocalMap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import net.mikelindner.slovo.ui.theme.SlovoTheme

@Composable
fun getTranslationButtonColors(button: Translation, currentDirection: String): ButtonColors {
    val isActive = button.direction == currentDirection
    return ButtonDefaults.buttonColors(
        containerColor = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
        contentColor = if (isActive) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
    );
}

@Composable
fun WordQuizView(
    vm: WordQuizViewModel,
    navBack: () -> Unit,
    bottomBar: @Composable () -> Unit
) {
    var revealTranslation = remember { mutableStateOf(false) }
    var showResult = remember { mutableStateOf(false) }
    var isCorrect = remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = bottomBar,
        topBar = {
            AppBar(title = AppScreen.Quiz.title, showBackButton = true) { navBack() }
        },
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
            ) {
                OutlinedButton(
                    onClick = { vm.direction = toEnglish },
                    shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                    modifier = Modifier.weight(1f, true),
                    colors = getTranslationButtonColors(button = EnglishTranslation(), currentDirection = vm.direction),
                ) { Text("English") }

                OutlinedButton(
                    onClick = { vm.direction = toRussian },
                    shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                    modifier = Modifier.weight(1f, true),
                    colors = getTranslationButtonColors(button = RussianTranslation(), currentDirection = vm.direction),
                ) { Text("Russian") }
            }

            Text(vm.fromLanguage, modifier = Modifier.padding(top = 16.dp))
            Text(
                vm.from,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                "(${vm.wordClass})",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(vm.toLanguage)
            OutlinedTextField(
                label = { Text("Enter Translation") },
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                onValueChange = { vm.enteredTranslation = it },
                singleLine = true,
                value = vm.enteredTranslation
            )

            Row {
                IconButton(
                    onClick = {
                        isCorrect.value = vm.checkTranslation()
                        showResult.value = true
                        revealTranslation.value = true
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "Check")
                }

                IconButton(
                    onClick = {
                        revealTranslation.value = true
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.question_mark_24),
                        contentDescription = "Reveal"
                    )
                }

                IconButton(
                    onClick = {
                        revealTranslation.value = false
                        showResult.value = false
                        vm.nextWord()
                    }
                ) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "Next")
                }
            }

            if (revealTranslation.value) {
                Text(
                    vm.to,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            if (showResult.value) {
                val image = if (isCorrect.value) Icons.Filled.Check else Icons.Filled.Close
                val tint = if (isCorrect.value) Color(0xff007f00) else Color(0xff7f0000)
                Icon(
                    contentDescription = null,
                    imageVector = image,
                    modifier = Modifier
                        .padding(16.dp)
                        .scale(3.0f),
                    tint = tint
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WordQuizPreview() {
    SlovoTheme {
        val context = LocalContext.current
        WordQuizView(
            WordQuizViewModel(NullWordRepository()),
            {},
            makeBottomBar(AppScreen.Quiz, NavHostController(context))
        )
    }
}