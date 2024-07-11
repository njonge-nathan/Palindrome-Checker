package com.example.palindrome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.palindrome.ui.theme.PalindromeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PalindromeTheme {
                PalindromeApp()
            }
        }
    }
}

@Composable
fun PalindromeApp() {

    var text by remember {
        mutableStateOf("")
    }

    val check = palindromeChecker(text)

    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .padding(horizontal = 30.dp)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(text = "Palindrome Checker App")

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier
                .size(width = 280.dp, height = 200.dp)
        ) {
            Text(text = stringResource(R.string.card_text),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(all = 30.dp),
                textAlign = TextAlign.Center
                )
        }

        Spacer(modifier = Modifier.height(30.dp))

        EditTextField(
            label = R.string.word_label,
            leadingIcon = R.drawable.equal,
            value = text,
            onValueChanged = { text = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )

        TextValue(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 80.dp)
                .align(Alignment.CenterHorizontally),
            text = check
        )
    }
}

@Composable
fun EditTextField(
    @StringRes label: Int,
    @DrawableRes leadingIcon: Int,
    value: String,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), contentDescription = null) },
        colors = colors
    )
}

@Composable
private fun palindromeChecker(inputText: String): String {
    return if (inputText.isEmpty()) {
        ""
    } else {
        val isPalindrome = inputText == inputText.reversed()
        if (isPalindrome) {
            stringResource(R.string.result_text_palindrome)
        } else {
            stringResource(R.string.result_text_not_palindrome)
        }
    }
}

@Composable
fun TextValue(
    text: String,
    modifier: Modifier
) {
    Text(
        modifier = modifier,
        text = text
    )
}

@Preview(showSystemUi = true)
@Composable
fun PalindromeAppPreview() {
    PalindromeApp()
}
