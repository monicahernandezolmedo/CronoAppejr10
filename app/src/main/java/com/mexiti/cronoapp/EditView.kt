package com.mexiti.cronoapp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mexiti.cronoapp.model.Cronos
import com.mexiti.cronoapp.ui.components.CircleButton
import com.mexiti.cronoapp.ui.components.MainIconButton
import com.mexiti.cronoapp.ui.components.MainTextField
import com.mexiti.cronoapp.ui.components.MainTitle
import com.mexiti.cronoapp.ui.components.formatTiempo
import com.mexiti.cronoapp.viewmodel.CronometroViewModel
import com.mexiti.cronoapp.viewmodel.DataViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditView(navController: NavController,
             cronometroVM:CronometroViewModel,
             dataVM:DataViewModel,
             id:Long
){

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "editar cronometro") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(
                        icon = Icons.Default.ArrowBack
                    ) {
                        navController.popBackStack()
                    }
                }//
            )
        }//ejercicio 10
    ) {
//ejer10

        ContentEditView(it = it, navController, cronometroVM,dataVM,id)
    }

}

@Composable
fun ContentEditView(it: PaddingValues,
                    navController: NavController,
                    cronometroVM: CronometroViewModel,
                    dataVM: DataViewModel,
                    id: Long) {
    val state = cronometroVM.state
    LaunchedEffect(key1 = state.cronometroActivo ){
        cronometroVM.cronos()
    }

    LaunchedEffect(key1 = Unit){
        cronometroVM.getCronoById(id)
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
            fontWeight = FontWeight.Bold
        )
        Row( horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        {
            CircleButton(icon = painterResource(id = R.drawable.play_arrow_24),
                enabled = !state.cronometroActivo
            ) {
                cronometroVM.iniciar()
            }
            CircleButton(icon = painterResource(id = R.drawable.pause_24),
                enabled = state.cronometroActivo
            ) {
                cronometroVM.pausar()
            }
        }
        MainTextField(value = state.title,
            onValueChange = {cronometroVM.onValue(it)},
            label = "Title")
        Button(onClick = {
            dataVM.updateCrono(
                Cronos(id = id,
                    title = state.title,
                    crono = cronometroVM.time
                )
            )
            navController.popBackStack()
        }) {
            Text(text = "Guardar Cambios")
        }
        Button(onClick = {
            dataVM.deleteCrono(
                Cronos(id = id,
                    title = state.title,
                    crono = cronometroVM.time
                )
            )
            //
            navController.popBackStack()
        }) {
            Text(text = "Eliminar Registro")
        }


        DisposableEffect(Unit){
            onDispose {
                cronometroVM.detener()
            }
        }

    }

}