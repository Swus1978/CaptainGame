package com.example.captaingame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.captaingame.ui.theme.CaptainGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaptainGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    CaptainGame()
                }
            }
        }
    }
}

@Composable
fun CaptainGame() {
    val treasuresFound = remember { mutableStateOf(0) }
    val direction = remember { mutableStateOf("North") }
    val stormOrTreasure = remember { mutableStateOf("") }

    fun sail(newDirection: String) {
        direction.value = newDirection
        if (Random.nextBoolean()) {
            treasuresFound.value += 1
            stormOrTreasure.value = "Found a Treasure!"
        } else {
            stormOrTreasure.value = "Storm Ahead!"
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Treasures found: ${treasuresFound.value}")
        Text(text = "Current Direction: ${direction.value}")
        Text(text = stormOrTreasure.value)

        Button(onClick = { sail("East") }) { Text("Sail East") }
        Button(onClick = { sail("West") }) { Text("Sail West") }
        Button(onClick = { sail("North") }) { Text("Sail North") }
        Button(onClick = { sail("South") }) { Text("Sail South") }
    }
}

@Preview(showBackground = true)
@Composable
fun CaptainGamePreview() {
    CaptainGame()
}
