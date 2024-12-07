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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
          //  startActivity(Intent(this, ))
        })}
    }
}

@Composable
@Preview
fun IntroScreen(onClick : () -> Unit = {}) {
    Column(modifier = Modifier.fillMaxSize()
        .padding(top = 20.dp)
        .verticalScroll(rememberScrollState())
        .background(Color.White),
        verticalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier.padding(top = 20.dp)
                    .fillMaxSize(),
                contentScale = ContentScale.Fit)
        Spacer(modifier = Modifier.height(3.dp))
        Text(modifier = Modifier.padding(start = 15.dp),
            text = stringResource(id = R.string.intro_title),
            color = Color.DarkGray,
            fontFamily = FontFamily(Font(R.font.comfortaa)),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(14.dp))
        Text(text = stringResource(id = R.string.sub_title),
            color = Color.DarkGray,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(2.dp))

        Button(onClick = {onClick()},
            modifier = Modifier.padding(horizontal = 30.dp,
                vertical = 10.dp)
                .fillMaxSize()
                .width(160.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.verde)
            ),
            shape = RoundedCornerShape(10.dp)) {
            Text(text = stringResource(id = R.string.vamos),
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.comfortaa)),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}