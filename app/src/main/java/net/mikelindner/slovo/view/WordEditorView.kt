package net.mikelindner.slovo.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import net.mikelindner.slovo.app.AppScreen
import net.mikelindner.slovo.db.NullWordRepository
import net.mikelindner.slovo.domain.wordClasses
import net.mikelindner.slovo.ui.theme.SlovoTheme

@Composable
fun WordEditorView(
    vm: WordViewModel,
    navBack: () -> Unit,
    bottomBar: @Composable () -> Unit
) {
    var isWordClassExpanded = remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = bottomBar,
        topBar = {
            AppBar(title = AppScreen.WordDetail.title, showBackButton = true) {
                vm.onEditCanceled()
                navBack()
            }
        },
    ) { padding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(padding)
                .wrapContentSize(),
            verticalArrangement = Arrangement.Center
        ) {

            OutlinedTextField(
                label = { Text("Russian") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                onValueChange = { value -> vm.russian = value },
                singleLine = true,
                value = vm.russian
            )
            OutlinedTextField(
                label = { Text("English") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                onValueChange = { value -> vm.english = value },
                singleLine = true,
                value = vm.english
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .wrapContentSize(Alignment.TopCenter)
            ) {
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        isWordClassExpanded.value = !isWordClassExpanded.value
                    }
                ) {
                    Text(
                        text = if (!vm.wordClass.isEmpty()) vm.wordClass else wordClasses[0].display
                    )
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                }

                DropdownMenu(
                    expanded = isWordClassExpanded.value,
                    onDismissRequest = { isWordClassExpanded.value = false }
                ) {
                    wordClasses.forEach { wordClass ->
                        DropdownMenuItem(
                            text = { Text(wordClass.display) },
                            onClick = {
                                vm.wordClass = wordClass.name
                                isWordClassExpanded.value = false
                            }
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                IconButton(onClick = {
                    vm.onEditConfirmed()
                    navBack()
                }) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null)
                }

                IconButton(onClick = {
                    vm.onEditCanceled()
                    navBack()
                }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WordEditorViewPreview() {
    SlovoTheme {
        val context = LocalContext.current
        val vm = WordViewModel(NullWordRepository(), 0L)
        WordEditorView(
            vm,
            {},
            makeBottomBar(AppScreen.WordEditor, NavHostController(context))
        )
    }
}
