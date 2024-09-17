package com.example.greetingsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*  // For layout components
import androidx.compose.material3.*  // For Button, Text, TextField, etc.
import androidx.compose.runtime.*  // For remember, mutableStateOf
import androidx.compose.ui.Alignment  // For Alignment.CenterHorizontally
import androidx.compose.ui.graphics.Color  // For Color.Blue
import androidx.compose.ui.text.font.FontWeight  // For FontWeight.Bold
import androidx.compose.ui.text.style.TextAlign  // For TextAlign.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp  // For padding and spacing values
import androidx.compose.ui.unit.sp  // For font size
import androidx.compose.ui.Modifier
import com.example.greetingsapp.ui.theme.GreetingsAppTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(modifier: Modifier = Modifier) {
    // State to hold the user's name and the greeting message
    var name by remember { mutableStateOf("") }
    var greetingMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Input field for the user's name
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to generate the greeting message
        Button(onClick = {
            greetingMessage = generateGreeting(name)
        }) {
            Text("Generate Greeting")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the greeting message
        if (greetingMessage.isNotEmpty()) {
            Text(
                text = greetingMessage,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

fun generateGreeting(name: String): String {
    // Get the current time using Calendar (works on all API levels)
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)

    // Determine the time of day
    val timeOfDay = when (hour) {
        in 5..11 -> "Good morning"
        in 12..16 -> "Good afternoon"
        in 17..20 -> "Good evening"
        else -> "Hello in the middle of the night"
    }

    // Return the personalized greeting
    return if (name.isNotEmpty()) {
        "$timeOfDay, $name!"
    } else {
        "$timeOfDay!"
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingScreenPreview() {
    GreetingsAppTheme {
        GreetingScreen()
    }
}