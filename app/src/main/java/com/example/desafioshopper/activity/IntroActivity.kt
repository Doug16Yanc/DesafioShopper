package com.example.desafioshopper.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getDisplayOrDefault
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.example.desafioshopper.R
import com.example.desafioshopper.viewModel.TravelViewModel

class IntroActivity : BaseActivity() {
    private val viewModel: TravelViewModel by viewModels()

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntroScreen(viewModel = viewModel)
        }
    }
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntroScreen(viewModel: TravelViewModel) {
    val scrollState = rememberScrollState()
    val customer_id = remember { mutableStateOf("") }
    var origin = remember { mutableStateOf("") }
    val destination = remember { mutableStateOf("") }
    val travelResponse by viewModel.travelResponse
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(2.dp)
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = stringResource(R.string.app),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.comfortaa)),
            color = colorResource(id = R.color.verde),
            modifier = Modifier
                .padding(top = 80.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.introduction),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.comfortaa)),
            color = Color.DarkGray,
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        OutlinedTextField(
            value = customer_id.value,
            onValueChange = { customer_id.value = it },
            label = {
                Text(
                    text = stringResource(R.string.user_id),
                    fontFamily = FontFamily(Font(R.font.comfortaa)),
                    color = colorResource(R.color.verde)
                )
            },
            modifier = Modifier
                .padding(horizontal = 13.dp, vertical = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White
            ),
            leadingIcon = {
                Icon(painter = painterResource(R.drawable.user),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.height(32.dp))
            }
        )

        OutlinedTextField(
            value = origin.value,
            onValueChange = { origin.value = it },
            label = {
                Text(
                    text = stringResource(R.string.origin),
                    fontFamily = FontFamily(Font(R.font.comfortaa)),
                    color = colorResource(R.color.verde)
                )
            },
            modifier = Modifier
                .padding(horizontal = 13.dp, vertical = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White
            ),
            leadingIcon = {
                Icon(painter = painterResource(R.drawable.origin),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.height(32.dp))
            }
        )
        OutlinedTextField(
            value = destination.value,
            onValueChange = { destination.value = it },
            label = {
                Text(
                    text = stringResource(R.string.destination),
                    fontFamily = FontFamily(Font(R.font.comfortaa)),
                    color = colorResource(R.color.verde)
                )
            },
            modifier = Modifier
                .padding(horizontal = 13.dp, vertical = 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White
            ),
            leadingIcon = {
                Icon(painter = painterResource(R.drawable.destination),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.height(32.dp))
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                if (customer_id.value.isBlank() || origin.value.isBlank() || destination.value.isBlank()) {
                    Toast.makeText(context, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
                }
                else if (origin.value == destination.value) {
                    Toast.makeText(context, "A origem não pode ser igual ao destino.", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.getRideEstimate(customer_id.value, origin.value, destination.value)
                }
            },
            modifier = Modifier
                .padding(start = 32.dp)
                .width(320.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.verde)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = stringResource(R.string.calculate),
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.comfortaa)),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        travelResponse?.let { response ->
            val driverOptions = response.options


            if (driverOptions.isNotEmpty()) {

                val mapUrl = "https://maps.googleapis.com/maps/api/staticmap" +
                    "?size=600x300" +
                    "&markers=color:green|label:A|${origin.value}" +
                    "&markers=color:red|label:B|${destination.value}" +
                    "&path=color:blue|weight:5|${origin.value}|${destination.value}" +
                    "&key="

                AsyncImage(
                    model = mapUrl,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                val intent = Intent(context, DriverActivity::class.java).apply {
                    putExtra("customer_id", customer_id.value)
                    putExtra("origin", origin.value)
                    putExtra("destination", destination.value)
                    putExtra("distance", response.distance)
                    putExtra("duration", response.duration)
                    putExtra("driver_options", ArrayList(driverOptions))
                    putExtra("value", response.value)
                    }
                context.startActivity(intent)
            } else {
                Toast.makeText(LocalContext.current, "Nenhum motorista disponível", Toast.LENGTH_SHORT).show()
            }
        }






    }


}
