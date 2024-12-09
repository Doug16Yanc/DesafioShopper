package com.example.desafioshopper.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.desafioshopper.R
import com.example.desafioshopper.model.Travel

@Composable
fun ListTravels(travels : List<Travel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(720.dp)
            .padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(travels.size) {
            row -> Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                TravelsScreen(travels, row)
        }
        }
    }
}

@Composable
fun TravelsScreen(travels : List<Travel>, pos : Int) {
    Column(
        modifier = Modifier.padding(12.dp)
            .height(100.dp)
    ) {
        Text(
            text = stringResource(R.string.travel_detail),
            color = Color.DarkGray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row {
                Text(
                    text = travels[pos].dateTime.toString(),
                    color = Color.DarkGray,
                    fontSize = 16.sp
                )
                Text(
                    text = travels[pos].dateTime.toString(),
                    color = Color.DarkGray,
                    fontSize = 16.sp
                )
                travels[pos].nameDriver?.let {
                    Text(
                        text = it,
                        color = Color.DarkGray,
                        fontSize = 16.sp
                    )
                }
                travels[pos].origin?.let {
                    Text(
                        text = it,
                        color = Color.DarkGray,
                        fontSize = 16.sp
                    )
                }
                travels[pos].destination?.let {
                    Text(
                        text = it,
                        color = Color.DarkGray,
                        fontSize = 16.sp
                    )
                }
                travels[pos].distance?.let {
                    Text(
                        text = it.toString(),
                        color = Color.DarkGray,
                        fontSize = 16.sp
                    )
                }
                travels[pos].duration?.let {
                    Text(
                        text = it.toString(),
                        color = Color.DarkGray,
                        fontSize = 16.sp
                    )
                }
                travels[pos].value?.let {
                    Text(
                        text = it.toString(),
                        color = Color.DarkGray,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
