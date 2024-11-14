package com.adso.date_pickers

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adso.date_pickers.ui.theme.Date_pickersTheme
import java.time.Instant
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Date_pickersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val state = rememberDatePickerState()
                    var showDialog by remember {
                        mutableStateOf(false)
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(onClick = { showDialog = true}) {
                            Text(text = "Mostrar Fecha")
                        }
                        if(showDialog) {
                            DatePickerDialog(
                                onDismissRequest = { showDialog = false },
                                confirmButton = {
                                    Button(onClick = { showDialog = false }) {
                                        Text(text = "Confirmar!")
                                    }
                                },
                                dismissButton = {
                                    Button(onClick ={ showDialog= false}){
                                        Text(text = "Cerrar!")
                                    }
                                }
                            ) {
                                DatePicker(state = state)
                            }
                        }
                        val date =state.selectedDateMillis
                        date?.let {
                            val localDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
                            Text(text = "Fecha seleccionada:${localDate.dayOfMonth}/${localDate.monthValue}/${localDate.year}")
                        }
                    }
                }
            }
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Date_pickersTheme {
        Text(text = "vista previa sin funcion de seleccion de fecha")
    }
}*/