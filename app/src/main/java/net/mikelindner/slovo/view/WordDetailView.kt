package net.mikelindner.slovo.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import net.mikelindner.slovo.app.AppScreen
import net.mikelindner.slovo.db.NullWordRepository
import net.mikelindner.slovo.ui.theme.SlovoTheme

@Composable
fun WordDetailView(
    vm: WordViewModel,
    showEdit: Boolean,
    navBack: () -> Unit,
    navEdit: () -> Unit,
    bottomBar: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = bottomBar,
        topBar = {
            AppBar(title = AppScreen.WordDetail.title, showBackButton = true) {
                navBack()
            }
        },
    ) { padding ->

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(padding)
                .wrapContentSize(),
            verticalArrangement = Arrangement.Center
        ) {

            Text(text ="Russian",
                modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
            )
            Text(text = vm.russian,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 8.dp, bottom = 16.dp),
            )

            Text(text ="English",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
            )
            Text(text = vm.english,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 8.dp, bottom = 16.dp),
            )

            Text(text ="Word class",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
            )
            Text(text = vm.wordClass,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 8.dp, bottom = 16.dp),
            )

            if (showEdit) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    IconButton(onClick = {
                        navEdit()
                    }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WordDetailViewPreview() {
    SlovoTheme {
        val context = LocalContext.current
        val vm = WordViewModel(NullWordRepository(), 0L)
        WordDetailView(
            vm,
            true,
            {},
            {},
            makeBottomBar(AppScreen.WordDetail, NavHostController(context))
        )
    }
}
