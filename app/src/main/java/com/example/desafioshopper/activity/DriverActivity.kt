package com.example.desafioshopper.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.desafioshopper.activity.ui.theme.DesafioShopperTheme
import com.example.desafioshopper.viewModel.DriverViewModel
import com.example.desafioshopper.viewModel.TravelViewModel

class DriverActivity : ComponentActivity() {

    private val viewModel = DriverViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent(){
            ListDriverScreen(onBackClick = {finish()},
            viewModel = viewModel)
    }
}

@Composable
fun ListDriverScreen(onBackClick : () -> Unit, viewModel: DriverViewModel) {
    val items by viewModel.drivers.observeAsState(emptyList())
    val isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.getDrivers()
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
}