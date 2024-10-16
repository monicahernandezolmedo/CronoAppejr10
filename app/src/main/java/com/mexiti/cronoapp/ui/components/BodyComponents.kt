package com.mexiti.cronoapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mexiti.cronoapp.R

/* This file contain components of App that show important information. */
@Composable
fun formatTiempo(time:Long):String{
    val segundos = time % 60
    val minutos = (time/60)%60
    val horas = time/3600
    return String.format("%02d:%02d:%02d",horas,minutos, segundos)
}

@Composable
fun MainTitle(title: String){
    Text(text= title, color = Color.White, fontWeight = FontWeight.Bold)

}

@Composable
fun MainTextField(value: String,
                  onValueChange: (String) -> Unit,
                  label: String){

    OutlinedTextField(value = value,
        onValueChange =onValueChange,
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(bottom = 15.dp)
        )
}

@Composable
fun CronCard(title: String,
             crono:String,
             onClick: () -> Unit ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clickable {  //The card can used using a click on
                onClick()
            }
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
                )
            Row {
                Icon(painter = painterResource(id = R.drawable.icon_timer_24),
                    contentDescription = "",
                    tint = Color.Gray
                )
                Text(text = crono, fontSize = 20.sp)
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                color = MaterialTheme.colorScheme.primary

            )

        }


    }
}


/** Add into this section String Format Time   **/






@Preview(showBackground = true, backgroundColor = 2345 )
@Composable
fun MainTitlePreview(){
    MainTitle(title = "CronoApp")
}



    @Preview(showBackground = true)
    @Composable
    fun MainTextfieldPreview(){

        MainTextField(value = "00:05:00", onValueChange ={} , label = "Score" )

    }


@Preview(showBackground = true)
@Composable
fun CronCardPreview(){
    CronCard(title = "Cronos", crono = "01:24:00") {
        
    }
}
