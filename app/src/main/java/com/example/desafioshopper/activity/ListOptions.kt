package com.example.desafioshopper.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.desafioshopper.R
import com.example.desafioshopper.model.Driver
import com.example.desafioshopper.response.Confirm
import com.example.desafioshopper.viewModel.DriverViewModel
import kotlinx.coroutines.launch

@Composable
fun ListOptions(context : Context, options: List<Driver>, viewModel : DriverViewModel, customer_id : String, origin : String, destination : String, distance : Double, duration : String, value : Double) {
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(10.dp)
    ) {
        items(options.size) { index ->
            OptionsScreen(context, options, index, viewModel, customer_id, origin, destination, distance, duration, value)
        }
    }
}

@Composable
fun OptionsScreen(context : Context, options: List<Driver>, pos: Int, viewModel : DriverViewModel, customer_id : String, origin : String, destination : String, distance : Double, duration : String, value : Double) {
    val driver = options[pos]
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .background(Color.Gray, shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.driver_detail),
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.comfortaa)),
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(8.dp))

        driver.name?.let {
            Text(
                text = "Nome: $it",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.comfortaa))
            )
        }

        driver.description?.let {
            Text(
                text = "Descrição: $it",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.comfortaa))
            )
        }

        driver.vehicle?.let {
            Text(
                text = "Veículo: $it",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.comfortaa))
            )
        }

        driver.review?.rating?.let {
            Text(
                text = "Avaliação: $it",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.comfortaa))
            )
        }

        driver.review?.comment?.let {
            Text(
                text = "Comentário: $it",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.comfortaa))
            )
        }

        driver.value?.let {
            Text(
                text = "Valor R$ : $it",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.comfortaa))
            )
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    chooseDriver(
                        context = context,
                        viewModel = viewModel,
                        customer_id = customer_id,
                        origin = origin,
                        destination = destination,
                        distance = distance,
                        duration = duration,
                        driver = driver,
                        value = value
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.verde)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = stringResource(R.string.choose_driver),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.comfortaa))
            )
        }

    }
}


private suspend fun chooseDriver(
    context: Context,
    viewModel: DriverViewModel,
    customer_id: String,
    origin: String,
    destination: String,
    distance: Double,
    duration: String,
    driver: Driver,
    value: Double
) {

    if (!isDistanceValid(driver.id ?: 0, distance)) {
        Toast.makeText(null, "Distância inválida para o motorista escolhido!", Toast.LENGTH_SHORT)
            .show()
    }
    val confirm = Confirm(
        customer_id = customer_id,
        origin = origin,
        destination = destination,
        distance = distance,
        duration = duration,
        id = driver.id ?: 0,
        name = driver.name ?: "",
        value = value
    )

    try {
        val response = viewModel.confirmTravel(confirm)
        if (response) {
            Toast.makeText(context, "Erro ao confirmar a viagem:", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Viagem confirmada!", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, TravelActivity::class.java).apply {
                putExtra("customer_id", customer_id)
                putExtra("id", driver.id)
            }
            context.startActivity(intent)
        }

    } catch (e: Exception) {
        Toast.makeText(context, "Erro de rede: ${e.message}", Toast.LENGTH_LONG).show()
    }
}

private fun isDistanceValid(driver_id : Int, distance: Double) : Boolean{
    val acceptableDistances = mapOf(
        1 to 1.0,
        2 to 5.0,
        3 to 10.0
    )
    val acceptable = acceptableDistances[driver_id] ?: return false
    return distance >= acceptable
}