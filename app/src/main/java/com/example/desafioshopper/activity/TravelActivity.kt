package com.example.desafioshopper.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.desafioshopper.viewModel.TravelViewModel

class TravelActivity : ComponentActivity() {
    private val viewModel = TravelViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListTravelScreen(onBackClick = {finish()},
                viewModel = viewModel)
        }
    }
}

@Composable
fun ListTravelScreen(onBackClick : () -> Unit, viewModel: TravelViewModel) {
    val items by viewModel.travels.observeAsState(emptyList())
    val isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.requestTravel()
    }
/*   if (items.isNotEmpty()) {
     /azyColumn(modifier = Modifier.fillMaxSize()) {
          items(travels) {
              travel -> ListTravels(items)
          }
      }
    }
    */
}