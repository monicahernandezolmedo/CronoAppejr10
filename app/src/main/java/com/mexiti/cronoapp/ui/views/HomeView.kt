package com.mexiti.cronoapp.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mexiti.cronoapp.R
import com.mexiti.cronoapp.ui.components.CronCard
import com.mexiti.cronoapp.ui.components.FloatButton
import com.mexiti.cronoapp.ui.components.MainTitle
import com.mexiti.cronoapp.ui.components.formatTiempo
import com.mexiti.cronoapp.viewmodel.DataViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, dataVM:DataViewModel){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { MainTitle(title = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )

        },
        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")

            }
        }
    ) {
        ContentHomeView( it = it, navController, dataVM )
    }
}


@Composable
fun ContentHomeView(it: PaddingValues, navController: NavController, dataVM: DataViewModel) {
    Column(modifier = Modifier.padding(it)) {
        val dataList by dataVM.cronoList.collectAsState()

        LazyColumn {
            items(dataList) { item ->
                val delete = SwipeAction(
                    icon = rememberVectorPainter(image = Icons.Default.Delete),
                    background = Color.Red,
                    onSwipe = {
                        dataVM.deleteCrono(item)
                    }
                )

                SwipeableActionsBox(
                    startActions = listOf(delete),
                    swipeThreshold = 150.dp
                ) {
                    CronCard(title = item.title,
                        crono = formatTiempo(time = item.crono)) {
                        navController.navigate("EditView/${item.id}")
                    }
                }
            }
        }
    }
}



@Preview
@Composable
fun HomeViewPreview(){
   // HomeView()
}