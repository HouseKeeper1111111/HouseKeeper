package com.example.housekeeper.presentation.reminder.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.housekeeper.core.domain.constants.DrawableConstants
import com.example.housekeeper.core.domain.constants.rememberScreenSize
import com.example.housekeeper.ui.presentation.router.Router
import com.example.housekeeper.ui.theme.HouseKeeperTextStyles
import com.example.housekeeper.ui.theme.PinkChampagne

@Composable
fun ReminderView(
    navController: NavHostController,
    onNavigateToSettingsScreen: () -> Unit
) {
    val screenSize = rememberScreenSize()
    val categoriesList = listOf(
        Pair(DrawableConstants.KITCHEN, "Kitchen"),
        Pair(DrawableConstants.BEDROOM, "Bedroom"),
        Pair(DrawableConstants.LIVING_ROOM, "Living room"),
        Pair(DrawableConstants.PETS, "Pets"),
        Pair(null, "+")
    )
    val withoutCategoryList = listOf(
        Triple(DrawableConstants.RAG_ICON, "Clean mirrors", "2d 17h"),
        Triple(DrawableConstants.BROOM_ICON, "Clean floors", "3d 12h")
    )

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = PinkChampagne)
                    .padding(paddingValues)
                    .padding(
                        top = (screenSize.height.value * 0.04).dp,
                        start = (screenSize.width.value * 0.04).dp,
                        end = (screenSize.width.value * 0.04).dp,
                        bottom = (screenSize.height.value * 0.04).dp
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .height((screenSize.height.value * 0.36).dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        items(categoriesList) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(
                                    modifier = Modifier
                                        .size(
                                            width = (screenSize.width.value * 0.44).dp,
                                            height = (screenSize.height.value * 0.1).dp
                                        )
                                        .clip(shape = RoundedCornerShape(10))
                                        .background(color = Color.White.copy(alpha = 0.5f))
                                        .clickable {
                                            if (it.first != null) {
//
                                            } else {
//
                                            }
                                        },
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
                    Spacer(modifier = Modifier.height((screenSize.height.value * 0.04).dp))
                    Text(
                        text = "Without category",
                        style = HouseKeeperTextStyles.CursedBlack35Black
                    )
                    Spacer(modifier = Modifier.height((screenSize.height.value * 0.03).dp))
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp),) {
                        items(withoutCategoryList) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height((screenSize.height.value * 0.1).dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(color = Color.White.copy(alpha = 0.5f))
                                    .padding(20.dp)
                                    .clickable {
//
                                    },
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(id = it.first),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(38.dp)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = it.second,
                                        style = HouseKeeperTextStyles.EliteTeal22Medium
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = it.third,
                                        style = HouseKeeperTextStyles.EliteTeal22Medium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        bottomBar = { Router(navController) }
    )
}


@Composable
@Preview(device = Devices.PIXEL_7_PRO)
private fun ReminderViewPreview() {
    ReminderView(onNavigateToSettingsScreen = {}, navController = rememberNavController())
}