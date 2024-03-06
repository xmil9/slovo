package net.mikelindner.slovo.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import net.mikelindner.slovo.app.AppScreen
import net.mikelindner.slovo.db.NullWordRepository
import net.mikelindner.slovo.domain.EnglishTranslation
import net.mikelindner.slovo.domain.RussianTranslation
import net.mikelindner.slovo.domain.Translation
import net.mikelindner.slovo.domain.toEnglish
import net.mikelindner.slovo.domain.toRussian
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
    var showCheckButton = remember { mutableStateOf(true) }
    var showNextButton = remember { mutableStateOf(false) }
    var showTranslation = remember { mutableStateOf(false) }
    var showResult = remember { mutableStateOf(false) }
    var isCorrect = remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    fun onCheckTranslation() {
        focusManager.clearFocus()
        isCorrect.value = vm.checkTranslation()
        showResult.value = true
        showTranslation.value = true
        showCheckButton.value = false
        showNextButton.value = true
    }

    fun onNextWord() {
        showTranslation.value = false
        showResult.value = false
        showCheckButton.value = true
        showNextButton.value = false
        vm.nextWord()
        focusRequester.requestFocus()
    }

    // Sets focus to focus requester initially.
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

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
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .padding(top = 8.dp, bottom = 8.dp),
                onValueChange = { vm.enteredTranslation = it },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { onCheckTranslation() }
                ),
                singleLine = true,
                value = vm.enteredTranslation
            )

            Row() {
                Spacer(modifier = Modifier.weight(1f))

                if (showCheckButton.value) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { onCheckTranslation() }
                    ) {
                        Text(
                            text = "Check",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                    }
                }

                if (showNextButton.value) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { onNextWord() },
                    ) {
                        Text(
                            text= "Next >",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
            }

            if (showTranslation.value) {
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