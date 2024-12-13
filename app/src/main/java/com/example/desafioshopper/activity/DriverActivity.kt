package com.example.desafioshopper.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.desafioshopper.R
import com.example.desafioshopper.model.Driver
import com.example.desafioshopper.viewModel.DriverViewModel


class DriverActivity : ComponentActivity() {

    private val viewModel: DriverViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val driverOptions = intent.getSerializableExtra("driver_options") as? ArrayList<Driver> ?: arrayListOf()
        val customer_id = intent.getStringExtra("customer_id") ?: ""
        val origin = intent.getStringExtra("origin") ?: ""
        val destination = intent.getStringExtra("destination") ?: ""
        val distance = intent.getDoubleExtra("distance", 0.0)
        val duration = intent.getStringExtra("duration") ?: ""
        val value = intent.getDoubleExtra("value", 0.0)

        setContent {
            DriverScreen(
                options = driverOptions, viewModel, customer_id, origin, destination, distance, duration, value
            )
        }
    }
}

@Composable
fun DriverScreen(options: List<Driver>, viemModel : DriverViewModel, customer_id : String, origin : String, destination : String, distance : Double, duration : String, value : Double) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = stringResource(R.string.details),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.comfortaa)),
            color = colorResource(id = R.color.verde),
            modifier = Modifier
                .padding(top = 80.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        ListOptions(context, options, viemModel, customer_id, origin, destination, distance, duration, value)
    }
}
