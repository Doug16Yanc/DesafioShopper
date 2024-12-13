package com.example.desafioshopper.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.desafioshopper.R
import com.example.desafioshopper.model.Travel
import com.example.desafioshopper.viewModel.TravelViewModel

class TravelActivity : ComponentActivity() {
    val viewModel : TravelViewModel by viewModels()

    private val customer_id = intent.getStringExtra("customer_id") ?: ""
    private val id = intent.getStringExtra("id") ?: ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelScreen(viewModel, customer_id, id)
        }
    }
}

@Composable
fun TravelScreen(viewModel: TravelViewModel, customer_id : String, id : String) {

    val travels = remember { mutableStateOf<List<Travel>>(emptyList()) }

    LaunchedEffect(Unit) {
        val list = viewModel.getTravels(customer_id, id)
        travels.value
    }

    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = stringResource(R.string.travels),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.comfortaa)),
            color = colorResource(id = R.color.verde),
            modifier = Modifier
                .padding(top = 80.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        ListTravels(travels.value)
    }
}
