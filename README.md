# Leccion_03_Lab
Laboratorio 3 Android - FruitWorld 2 App

Objetivo
--------
Crear una app con un recycler view usando card view para sus items. El icono en la barra de opciones no se muestra. Cada card view, debe
estar comprendido de una image a su izquierda, ocupando el 40% del card view, y dejando un 60% para el resto. La altura 100dp.

Nuestro modelo fruta, deberá tener las siguientes propiedades: nombre, descripción, imagen para el background, imagen de icono y
cantidad. Establecer un límite de cantidad a 10, y un valor para resetear de 0. Un método para gestionar añadir cantidad y resetear
cantidad podría ser de ayuda, mejor que con get/set.

En la parte del 40%, mostramos la imagen de background de nuestro modelo, y en la del 60%, enseñaríamos, nombre, descripción y
cantidad.

Crear botón en el menú de opciones para añadir una nueva fruta a nuestro recicler view. Nuevas frutas pueden tener un contador en el
nombre para diferenciarlos entre sí, aunque los demás valores del modelo pueden repetirse entre los nuevas frutas.

Recuerda crear 3 paquetes para manejar los diferentes activities, adapters, models.

Para añadir uno a la cantidad total de cada fruta, pulsaremos en la imagen de background que ocupa la parte izquierda de nuestro item.

Crear context menu para nuestros elementos en el recycler view, con 2 principales opciones: Borrar y Resetear cantidad, donde el primero
borra el elemento en sí y el segundo, resetea a 0 la cantidad del mismo.
El context menu debe mostrar como título, el icono de nuestro modelo, y el nombre.

Ayúdate de Picasso para la carga del background de cada item.

Pista: es posible que para implementar el context menu sea necesario investigar un poco, ya que no se comporta igual que el ListView en
estos términos. Posiblemente, una solución válida sería implementarlo dentro de nuestro ViewHolder en nuestro Adapter.
