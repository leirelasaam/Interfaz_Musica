package com.example.interfaz_musica_compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interfaz_musica_compose.ui.theme.Interfaz_Musica_ComposeTheme

/**
 * Actividad principal de la aplicación de música.
 *
 */
class MainActivity : ComponentActivity() {
    /**
     * Método llamado cuando la actividad se crea por primera vez.
     *
     * @param savedInstanceState Bundle que contiene el estado guardado de la actividad, si está disponible.
     */
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

/**
 * Comprueba la orientación del dispositivo.
 *
 */
@Composable
fun AppMusica() {
    val configuracion = LocalConfiguration.current
    val esApaisado = configuracion.orientation == Configuration.ORIENTATION_LANDSCAPE
    AppMusicaLayout(esApaisado)
}

/**
 * Representa el diseño de la interfaz que se adapta a la orientación del dispositivo.
 *
 * @param esApaisado Un booleano que indica si la orientación de la pantalla es horizontal (true) o vertical (false).
 */
@Composable
fun AppMusicaLayout(esApaisado: Boolean) {
    // Variable para controlar el botón de play/pause y guardar su estado
    var estaReproduciendo by rememberSaveable { mutableStateOf(false) }

    // Listado de iconos (letra, lista, compartir)
    val iconosOpciones = listOf(
        Pair(painterResource(R.drawable.lyrics), stringResource(R.string.no_sp_letra)),
        Pair(painterResource(R.drawable.list), stringResource(R.string.no_sp_lista)),
        Pair(painterResource(R.drawable.share), stringResource(R.string.no_sp_compartir))
    )

    // Listado de iconos para el control de la reproducción (anterior, play/pause, siguiente)
    val iconosReproduccion = listOf(
        Triple(painterResource(R.drawable.atras), stringResource(R.string.no_sp_anterior), 30),
        Triple(
            // Control del icono a mostrar según el valor de isPlaying
            painterResource(if (estaReproduciendo) R.drawable.pausa else R.drawable.play),
            stringResource(R.string.no_sp_play_pause),
            80
        ),
        Triple(painterResource(R.drawable.siguiente), stringResource(R.string.no_sp_siguiente), 30)
    )

    /**
     * Cambia el estado de la variable isPlaying.
     *
     */
    fun clicBoton() {
        estaReproduciendo = !estaReproduciendo
    }

    if (esApaisado) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.dark_gray)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PortadaAlbum(true)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(60.dp, 20.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                SliderVolumen()
                InformacionCancion()
                FilaIconosOpciones(iconosOpciones, true)
                SliderReproduccion()
                FilaIconosMultimedia(iconosReproduccion, ::clicBoton)
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.dark_gray))
                .padding(40.dp, 60.dp), verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
            ) {
                SliderVolumen()
                PortadaAlbum(false)
                InformacionCancion()
                SliderReproduccion()
                FilaIconosMultimedia(iconosReproduccion, ::clicBoton)
                FilaIconosOpciones(iconosOpciones, false)
            }
        }
    }
}

/**
 * Componente para la portada del disco, cuya disposición depende de la orientación del dispositivo.
 *
 * @param esApaisado Un booleano que indica si la orientación de la pantalla es horizontal (true) o vertical (false).
 */
@Composable
fun PortadaAlbum(esApaisado: Boolean) {
    if (esApaisado) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.4f)
        ) {
            Image(
                painter = painterResource(R.drawable.cover),
                contentDescription = stringResource(R.string.no_sp_portada),
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
                painter = painterResource(R.drawable.cover),
                contentDescription = stringResource(R.string.no_sp_portada),
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.Fit
            )
        }
    }
}

/**
 * Componente para crear un slider con modificaciones de estilo.
 *
 * @param predeterminado Un Float que indica el valor por defecto del thumb.
 * @param modifier Un Modifier que se aplica en el slider.
 */
@Composable
fun SliderPersonalizado(predeterminado: Float, modifier: Modifier) {
    var posicion = predeterminado
    Slider(
        value = posicion,
        onValueChange = { posicion = it },
        colors = SliderDefaults.colors(
            thumbColor = colorResource(R.color.white),
            activeTrackColor = colorResource(R.color.purple),
            inactiveTrackColor = colorResource(R.color.dark_purple),
        ),
        valueRange = 0f..1f,
        modifier = modifier
    )
}

/**
 * Componente que utiliza el otro componente SliderPersonalizado para añadir el control del volumen.
 *
 */
@Composable
fun SliderVolumen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SliderPersonalizado(
            predeterminado = 0.7f, modifier = Modifier.weight(0.8f)
        )
        Image(
            painter = painterResource(R.drawable.speaker),
            contentDescription = stringResource(R.string.no_sp_volumen),
            modifier = Modifier.weight(0.1f)
        )
    }
}
/**
 * Componente que utiliza el otro componente SliderPersonalizado para crear el slider del tiempo de reproducción de la canción.
 * Se muestra el tiempo de reproducción y el tiempo restante.
 *
 */
@Composable
fun SliderReproduccion() {
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            SliderPersonalizado(predeterminado = 0.25f, modifier = Modifier.weight(0.8f))
        }
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(stringResource(R.string.min_inicio), color = colorResource(R.color.white))
            Text(stringResource(R.string.min_fin), color = colorResource(R.color.white))
        }
    }
}

/**
 * Componente que agrupa los textos referentes a la canción en reproducción.
 * Se muestran el nombre, álbum y artista, en este orden.
 *
 */
@Composable
fun InformacionCancion() {
    Column {
        Text(
            stringResource(R.string.cancion),
            color = colorResource(R.color.white),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 10.dp)
        )
        Text(
            stringResource(R.string.album),
            color = colorResource(R.color.gray),
            fontStyle = FontStyle.Italic
        )
        Text(
            stringResource(R.string.artista),
            color = colorResource(R.color.purple),
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Componente para generar la fila de iconos en el control de la reproducción.
 *
 * @param iconos Lista que contiene los iconos a añadir.
 * @param clicBoton Función onClick para el botón de play/pause.
 */
@Composable
fun FilaIconosMultimedia(iconos: List<Triple<Painter, String, Int>>, clicBoton: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        iconos.forEach { (painter, descr, size) ->
            val modifier = if (descr == stringResource(R.string.no_sp_play_pause)) {
                Modifier.clickable { clicBoton() }
            } else {
                Modifier
            }

            Box(modifier = modifier.size(size.dp)) {
                Image(
                    painter = painter, contentDescription = descr, contentScale = ContentScale.Fit
                )
            }
        }
    }
}

/**
 * Componente para generar una fila de iconos.
 *
 * @param icons Lista de iconos a añadir.
 * @param isLandscape Un booleano que indica si la orientación de la pantalla es horizontal (true) o vertical (false).
 */
@Composable
fun FilaIconosOpciones(icons: List<Pair<Painter, String>>, isLandscape: Boolean) {
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
            IconoOpcion(painter, descr, 30)
        }
    }
}

/**
 * Componente para añadir una imagen.
 *
 * @param painter Painter que se utiliza para dibujar la imagen.
 * @param descr Descripción de la imagen para la accesibilidad.
 * @param size Tamaño del contenedor de la imagen.
 */
@Composable
fun IconoOpcion(painter: Painter, descr: String, size: Int) {
    Box(modifier = Modifier.size(size.dp)) {
        Image(
            painter = painter, contentDescription = descr, contentScale = ContentScale.Fit
        )
    }
}

/**
 * Componente de vista previa para la aplicación de música.
 *
 */
// Apaisado
@Preview(
    showBackground = true,
    heightDp = 412,
    widthDp = 873
)
// Vertical
@Preview(
    showBackground = true,
    widthDp = 412,
    heightDp = 873
)
@Composable
fun AppMusicaPreview() {
    Interfaz_Musica_ComposeTheme {
        AppMusica()
    }
}