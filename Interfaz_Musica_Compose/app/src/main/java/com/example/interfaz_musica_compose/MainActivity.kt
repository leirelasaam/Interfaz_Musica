package com.example.interfaz_musica_compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interfaz_musica_compose.ui.theme.Interfaz_Musica_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Interfaz_Musica_ComposeTheme {
                AppMusica()
            }
        }
    }
}

@Composable
fun AppMusica() {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    MusicaLayout(isLandscape)
}

@Composable
fun MusicaLayout(isLandscape: Boolean) {
    val imageIcons = listOf(
        Pair(painterResource(id = R.drawable.lyrics), "Icono letra"),
        Pair(painterResource(id = R.drawable.list), "Icono cola"),
        Pair(painterResource(id = R.drawable.share), "Icono compartir")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.dark_gray))
            .padding(40.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SliderPurple(
                    default = 0.7f,
                    modifier = Modifier.weight(0.8f)
                )
                Image(
                    painter = painterResource(id = R.drawable.speaker),
                    contentDescription = "Icono de volumen",
                    modifier = Modifier.weight(0.1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cover),
                    contentDescription = "Portada del álbum Hounds Of Love",
                    modifier = Modifier.weight(1f),
                    contentScale = ContentScale.Fit
                )
            }
            Column() {
                Text(
                    "Running Up That Hill", color = colorResource(R.color.white),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp, 10.dp)
                )
                Text(
                    "Hounds Of Love",
                    color = colorResource(R.color.gray),
                    fontStyle = FontStyle.Italic
                )
                Text(
                    "Kate Bush",
                    color = colorResource(R.color.purple),
                    fontWeight = FontWeight.Bold
                )
            }
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    SliderPurple(
                        default = 0.25f,
                        modifier = Modifier.weight(0.8f)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("1:24", color = colorResource(R.color.white))
                    Text("4:58", color = colorResource(R.color.white))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_media_previous),
                    contentDescription = "Icono letra",
                    modifier = Modifier.weight(1f),
                    contentScale = ContentScale.Fit
                )

                ImageWithBg(
                    painterImg = painterResource(id = android.R.drawable.ic_media_play)
                )

                Image(
                    painter = painterResource(id = android.R.drawable.ic_media_next),
                    contentDescription = "Icono compartir",
                    modifier = Modifier.weight(1f),
                    contentScale = ContentScale.Fit
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                horizontalArrangement = Arrangement
                    .spacedBy(
                        space = 20.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Iterar sobre los iconos para crearlos
                for ((painter, descr) in imageIcons){
                    ImageIcon(painter, descr)
                }
            }
        }
    }
}

// Iconos de la parte inferior
@Composable
fun ImageIcon(painter: Painter, descr: String){
    Box(
        modifier = Modifier.size(30.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = descr,
            contentScale = ContentScale.Fit
        )
    }
}

// Para añadir el icono de Play, con borde redondeado
@Composable
fun ImageWithBg(painterImg: Painter) {
    Box(
        modifier = Modifier
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(200.dp)
            )
            .border(
                width = 3.dp,
                color = colorResource(R.color.purple),
                shape = RoundedCornerShape(200.dp)
            )
    ) {
        // Imagen encima de la de fondo
        Image(
            painter = painterImg,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(90.dp)
                .padding(10.dp),
            contentScale = ContentScale.Fit
        )
    }
}

// Slider al que se le pasa el valor por defecto y que mantiene el estado del valor
@Composable
fun SliderPurple(default: Float, modifier: Modifier) {
    var sliderPosition by remember { mutableFloatStateOf(default) }
    Slider(
        value = sliderPosition,
        onValueChange = { sliderPosition = it },
        colors = SliderDefaults.colors(
            thumbColor = colorResource(R.color.white),
            activeTrackColor = colorResource(R.color.purple),
            inactiveTrackColor = colorResource(R.color.dark_purple),
        ),
        valueRange = 0f..1f,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun AppMusicaPreview() {
    Interfaz_Musica_ComposeTheme {
        AppMusica()
    }
}