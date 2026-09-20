package com.example.pocketbrief

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    private var model by mutableStateOf(PocketBriefModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (model.signedIn) {
                Text("Good morning, ${model.displayName}")
                Text(model.statusText)
                Text("${model.alertCount} alert(s)")
                Button(onClick = { model = model.copy(signedIn = false) }) { Text("Sign out") }
            } else {
                Button(onClick = { model = PocketBriefModel() }) { Text("Sign in") }
            }
        }
    }
}
