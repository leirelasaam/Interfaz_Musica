# Interfaz Música XML
Esta rama contiene la interfaz de la aplicación de música desarrollada mediante la edición de los archivos XML tradicionales. 

## Funcionalidad
### Botones
La única funcionalidad añadida a esta interfaz es el botón de Play/Pause. El icono cambia al hacer clic en el botón y se guarda el estado para mantenerlo independientemente de la orientación del dispositivo.
### Sliders
Los sliders se inicializan con valores fijos. Concretamente, el slider del volumen tiene el valor del 70% y el slider de la reproducción de la canción 25%. En este último, los valores indicados del tiempo de reproducción y el tiempo restante no se actualizan, ya que son valores fijos que no están enlazados con el slider.

## Layout
### Portrait Layout
Se ha implementado una distribución vertical de los elementos, centrando los mismos en el espacio disponible.

<img src="https://github.com/user-attachments/assets/7aa264fd-2c64-4627-9d93-96abbccd062e" alt="Portrait" width="300">

### Landscape Layout
Se ha implementado una distribución horizontal donde la portada del álbum ocupa un 50% del espacio disponible y los controles el espacio restante. Los controles están organizados verticalmente.

<img src="https://github.com/user-attachments/assets/7a9b32c6-3323-464a-85b8-d45425a2d896" alt="Landscape" height="300">
