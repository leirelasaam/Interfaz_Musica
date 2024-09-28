# Interfaz Música Compose
Esta rama contiene la interfaz de la aplicación de música desarrollada mediante Jetpack Compose.

## Funcionalidad
### Botones
La única funcionalidad añadida a esta interfaz es el botón de Play/Pause. El icono cambia al hacer clic en el botón y se guarda el estado para mantenerlo independientemente de la orientación del dispositivo.
### Sliders
Los sliders se inicializan con valores fijos. Concretamente, el slider del volumen tiene el valor del 70% y el slider de la reproducción de la canción 25%. En este último, los valores indicados del tiempo de reproducción y el tiempo restante no se actualizan, ya que son valores fijos que no están enlazados con el slider.

## Layout
### Portrait Layout
Se ha implementado una distribución vertical de los elementos, centrando los mismos en el espacio disponible.

<img src="https://github.com/user-attachments/assets/766fe981-bd5f-48d3-8730-4b19a235be46" alt="Portrait" width="300">

### Landscape Layout
Se ha implementado una distribución horizontal donde la portada del álbum ocupa un 40% del espacio disponible y los controles el espacio restante. Los controles están organizados en una columna.

<img src="https://github.com/user-attachments/assets/42081436-00cc-452c-8072-74660265c8b0" alt="Landscape" height="300">
