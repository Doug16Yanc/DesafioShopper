package com.example.desafioshopper.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.desafioshopper.R
import com.example.desafioshopper.activity.ui.theme.DesafioShopperTheme

class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{ IntroScreen(onClick = {
        })}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun IntroScreen(onClick : () -> Unit = {}) {
    val scrollState = rememberScrollState()
    val userId = remember { mutableStateOf("") }
    val origin = remember { mutableStateOf("")}
    val destination = remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 2.dp)
        .background(Color.White)
        .verticalScroll(scrollState)) {
        Text(text = stringResource(R.string.app),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.comfortaa)),
            color = colorResource(id = R.color.verde),
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(text = stringResource(R.string.introduction),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.comfortaa)),
            color = Color.DarkGray,
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxSize(),
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = userId.value,
            onValueChange = { userId.value = it },
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
            value = userId.value,
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
            value = userId.value,
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

        Spacer(modifier = Modifier.padding(top = 15.dp))

        Button(onClick = {onClick()},
            modifier = Modifier.padding(horizontal = 30.dp,
                vertical = 10.dp)
                .fillMaxSize()
                .width(160.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.verde)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = stringResource(R.string.calculate),
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.comfortaa)),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

