package com.example.composefocussimple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import com.example.composefocussimple.ui.theme.ComposeFocusSimpleTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFocusSimpleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        val focusRequester = remember { FocusRequester() }
        val myFocusManager = LocalFocusManager.current

        var value by remember { mutableStateOf("") }

        val (a, b, c) = FocusRequester.createRefs()

        TextField(
            modifier = Modifier.focusRequester(focusRequester),
            value = value,
            onValueChange = {
                value = it
            },
            singleLine = true,
            keyboardActions = KeyboardActions { myFocusManager.moveFocus(FocusDirection.Next) }
        )

        TextField(
            value = value,
            onValueChange = {
                value = it
            },
            singleLine = true,
            keyboardActions = KeyboardActions {
                myFocusManager.clearFocus()
            })

        Button(onClick = {
            focusRequester.requestFocus()
        }) {
            Text("Gain focus")
        }



        Button(onClick = {
            myFocusManager.clearFocus()
        }) {
            Text("Clear focus")
        }

//        LaunchedEffect(Unit) {
//            while (true) {
//                delay(2000)
//                focusManager.clearFocus()
//            }
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeFocusSimpleTheme {
        Greeting("Android")
    }
}