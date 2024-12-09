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
import com.example.desafioshopper.model.Driver

@Composable
fun ListDrivers(drivers : List<Driver>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(720.dp)
            .padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(drivers.size) {
                row -> Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            DriversScreen(drivers, row)
                }
        }
    }
}

@Composable
private fun DriversScreen(drivers : List<Driver>, pos : Int) {
    Column(
        modifier = Modifier.padding(12.dp)
            .height(100.dp)
    ) {
        Text(
            text = stringResource(R.string.driver_detail),
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
                drivers[pos].name?.let {
                    Text(
                        text = it,
                        color = Color.DarkGray,
                        fontSize = 16.sp
                    )
                }
                drivers[pos].description?.let {
                    Text(
                        text = it,
                        color = Color.DarkGray,
                        fontSize = 16.sp
                    )
                }
                drivers[pos].vehicle?.let {
                    Text(
                        text = it,
                        color = Color.DarkGray,
                        fontSize = 16.sp
                    )
                }
                drivers[pos].rate?.let {
                    Text(
                        text = it.toString(),
                        color = Color.DarkGray,
                        fontSize = 16.sp
                    )
                }
                drivers[pos].value?.let {
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
