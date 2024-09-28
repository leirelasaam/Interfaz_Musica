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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
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

    MusicLayout(isLandscape)
}

@Composable
fun MusicLayout(isLandscape: Boolean) {
    val imageIcons = listOf(
        Pair(painterResource(id = R.drawable.lyrics), "Icono letra"),
        Pair(painterResource(id = R.drawable.list), "Icono cola"),
        Pair(painterResource(id = R.drawable.share), "Icono compartir")
    )

    val multimediaIcons = listOf(
        Triple(painterResource(id = R.drawable.atras), "Icono anterior", 30),
        Triple(painterResource(id = R.drawable.play), "Icono play", 80),
        Triple(painterResource(id = R.drawable.siguiente), "Icono siguiente", 30)
    )

    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.dark_gray)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PortadaAlbum(isLandscape)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(60.dp, 20.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                SliderVolume()
                SongInfo()
                IconRow(imageIcons, true)
                ProgressBar()
                MultimediaIconsRow(multimediaIcons)
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.dark_gray))
                .padding(40.dp, 60.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                SliderVolume()
                PortadaAlbum(false)
                SongInfo()
                ProgressBar()
                MultimediaIconsRow(multimediaIcons)
                IconRow(imageIcons, false)
            }
        }
    }
}

@Composable
fun PortadaAlbum(isLandscape: Boolean) {
    if (isLandscape) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.4f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.cover),
                contentDescription = "Portada del álbum Hounds Of Love",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    } else {
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
    }
}

@Composable
fun SliderVolume() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
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
}

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

@Composable
fun SongInfo() {
    Column {
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
}

@Composable
fun ProgressBar() {
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            SliderPurple(default = 0.25f, modifier = Modifier.weight(0.8f))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("1:24", color = colorResource(R.color.white))
            Text("4:58", color = colorResource(R.color.white))
        }
    }
}

@Composable
fun MultimediaIconsRow(icons: List<Triple<Painter, String, Int>>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        icons.forEach { (painter, descr, size) ->
            ImageIcon(painter, descr, size)
        }
    }
}

@Composable
fun IconRow(icons: List<Pair<Painter, String>>, isLandscape: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp),
        horizontalArrangement = if (isLandscape) {
            Arrangement.spacedBy(20.dp, Alignment.Start)
        } else {
            Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        icons.forEach { (painter, descr) ->
            ImageIcon(painter, descr, 30)
        }
    }
}

@Composable
fun ImageIcon(painter: Painter, descr: String, size: Int) {
    Box(modifier = Modifier.size(size.dp)) {
        Image(
            painter = painter,
            contentDescription = descr,
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppMusicaPreview() {
    Interfaz_Musica_ComposeTheme {
        AppMusica()
    }
}