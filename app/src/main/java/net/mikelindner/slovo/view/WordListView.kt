package net.mikelindner.slovo.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import net.mikelindner.slovo.app.AppConfig
import net.mikelindner.slovo.app.AppScreen
import net.mikelindner.slovo.db.NullWordRepository
import net.mikelindner.slovo.domain.Word
import net.mikelindner.slovo.ui.theme.SlovoTheme

@Composable
fun WordListView(
    vm: WordListViewModel,
    navToWord: (wordId: Long) -> Unit,
    navToAddWord: () -> Unit,
    bottomBar: @Composable () -> Unit
) {
    var searchPattern = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    fun onSearch() {
        vm.searchWords(searchPattern.value)
    }

    Scaffold(
        bottomBar = bottomBar,
        topBar = { AppBar(title = AppScreen.WordList.title, showBackButton = false) },
        floatingActionButton = {
            if (vm.haveEditing) {
                FloatingActionButton(
                    modifier = Modifier.padding(20.dp),
                    onClick = { navToAddWord() }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
    ) { padding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Top,
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.Top,
            ) {
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            onSearch()
                        }
                    ),
                    label = { Text("Search") },
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { pattern ->
                        searchPattern.value = pattern
                        onSearch()
                    },
                    singleLine = true,
                    value = searchPattern.value,
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                if (searchPattern.value.isNotEmpty()) {
                    Log.d("Result count", vm.searchResults.value.size.toString())
                    items(vm.searchResults.value) { word ->
                        Log.d("Result word", word.en)
                        WordItem(
                            word,
                            onClick = { wordId -> navToWord(wordId) }
                        )
                    }
                }
                else {
                    items(vm.wordList.value) { word ->
                        WordItem(
                            word,
                            onClick = { wordId -> navToWord(wordId) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WordItem(
    word: Word,
    onClick: (wordId: Long) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 2.dp, top = 2.dp, end = 2.dp)
            .clickable { onClick(word.id) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = word.ru, fontWeight = FontWeight.Bold)
            Text(text = word.en, modifier = Modifier.padding(start = 10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WordListViewPreview() {
    SlovoTheme {
        val context = LocalContext.current
        WordListView(
            WordListViewModel(NullWordRepository(), AppConfig.HAVE_EDITING),
            {},
            {},
            makeBottomBar(AppScreen.WordList, NavHostController(context))
        )
    }
}
