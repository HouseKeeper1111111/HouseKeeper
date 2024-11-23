package com.example.housekeeper.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.housekeeper.R
import com.example.housekeeper.ui.theme.HouseKeeperTextStyles
import com.example.housekeeper.ui.theme.PinkChampagne
import com.example.housekeeper.ui.theme.rememberScreenSize

@Composable
fun ReminderScreen() {
    val screenSize = rememberScreenSize()
    val categoriesList = listOf(
        Pair(R.drawable.kitchen_icon, "Kitchen"),
        Pair(R.drawable.bedroom_icon, "Bedroom"),
        Pair(R.drawable.living_room_icon, "Living room"),
        Pair(R.drawable.pets_icon, "Pets"),
        Pair(null, "+"),
    )

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = PinkChampagne)
                    .padding(paddingValues)
                    .padding(
                        top = (screenSize.height.value * 0.05).dp,
                        start = (screenSize.width.value * 0.015).dp,
                        end = (screenSize.width.value * 0.015).dp,
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Your reminders",
                        style = HouseKeeperTextStyles.CursedBlack35Black
                    )
                    Spacer(modifier = Modifier.height((screenSize.height.value * 0.03).dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        items(categoriesList) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(
                                            width = (screenSize.width.value * 0.45).dp,
                                            height = (screenSize.height.value * 0.1).dp
                                        )
                                        .clip(shape = RoundedCornerShape(10))
                                        .background(color = Color.White),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if(it.first != null)
                                        Image(
                                            modifier = Modifier.fillMaxSize(),
                                            painter = painterResource(id = it.first!!),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop
                                        )
                                    Text(
                                        text = it.second,
                                        style = HouseKeeperTextStyles.EliteTeal22Medium,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}


@Composable
@Preview(device = Devices.PIXEL_7_PRO)
private fun ReminderScreenPreview() {
    ReminderScreen()
}