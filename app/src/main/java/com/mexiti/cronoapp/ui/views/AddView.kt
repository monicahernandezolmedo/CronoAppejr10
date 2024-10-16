package com.mexiti.cronoapp.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mexiti.cronoapp.R
import com.mexiti.cronoapp.model.Cronos
import com.mexiti.cronoapp.ui.components.CircleButton
import com.mexiti.cronoapp.ui.components.MainIconButton
import com.mexiti.cronoapp.ui.components.MainTextField
import com.mexiti.cronoapp.ui.components.MainTitle
import com.mexiti.cronoapp.ui.components.formatTiempo
import com.mexiti.cronoapp.viewmodel.CronometroViewModel
import com.mexiti.cronoapp.viewmodel.DataViewModel

@Composable
fun ContentAddView(it:PaddingValues,
                   navController: NavController,
                   cronometroVM: CronometroViewModel,
                   dataVM:DataViewModel
                   ){
    val state = cronometroVM.state
    LaunchedEffect(key1 = state.cronometroActivo ){
        cronometroVM.cronos()
    }
    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = formatTiempo(time = cronometroVM.time),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)
            ) {
            CircleButton(icon = painterResource(id = R.drawable.play_arrow_24),
                    //Enable Cronom State
                enabled = !state.cronometroActivo
            ) {
                //Start cronomVM.iniciar()
                cronometroVM.iniciar()
            }
            CircleButton(icon = painterResource(id = R.drawable.pause_24),
                    //State pause
                enabled = state.cronometroActivo
            ) {
                //Start cronomVM.pausar()
                cronometroVM.pausar()
            }
            CircleButton(icon = painterResource(id = R.drawable.stop_24),
                    // State inactivo
                enabled = !state.cronometroActivo

            ) {
                //Start cronomVM.detener()
                cronometroVM.detener()
            }
            CircleButton(icon = painterResource(id = R.drawable.save_24),
                    //state Save
                    enabled = state.showSaveButton
            ) {
                //Start cronomVM.showTextField()
                cronometroVM.showTextField()
            }
        }
        /*
            Code to Save time if state.showTextField
         */
        if( state.showTextField){
            MainTextField(value = state.title,
                onValueChange = {cronometroVM.onValue(it)}  ,
                label = "Title")

            Button(onClick = {
                dataVM.addCrono(
                    Cronos(title = state.title,
                        crono = cronometroVM.time
                        )
                )
                cronometroVM.detener()
                navController.popBackStack()
            }) {
                    Text(text = "Guardar")
            }

        }

    }

}
//AddView(navController:  navegación entre vistas)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController,
            cronometroVM: CronometroViewModel,
            dataVM: DataViewModel
            ){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = stringResource(R.string.add_view) ) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )

        }
    ) {
        ContentAddView(it = it, navController = navController,cronometroVM, dataVM )
    }
}

@Preview
@Composable
fun AddViewPreview(){
    //AddView()
}