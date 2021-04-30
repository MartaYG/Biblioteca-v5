# Tarea: Biblioteca IES Al-Ándalus
## Profesor: José Ramón Jiménez Reyes
## Alumno: Marta Yebra González

Desde el IES Al-Ándalus nos acaban de dar unos nuevos requisitos a aplicar sobre la última versión que le mostramos y que les gustó bastante. Lo que nos piden es lo siguiente:

- Quieren conservar la interfaz de texto de la aplicación.
- Quieren también tener una nueva interfaz de usuario gráfica para ejecutar la aplicación.

Tu tarea consiste en dotar a la aplicación de la tarea anterior de una interfaz gráfica de usuario, utilizando JavaFX. La interfaz se puede diseñar al gusto de cada un@, pero deberá utilizar los componentes más adecuados en cada caso. Cuanto más elaborada esté mayor será la calificación. Para ello debes emplear los diferentes tipos de controles, menús y contenedores que nos proporciona la API de JavaFX. Se pide al menos:

- Un menú que nos permita salir de la aplicación o mostrar un cuadro de diálogo "Acerca de..." con información de la autoría de la aplicación y una imagen.
- La gestión de alumnos permitirá añadir uno nuevo, borrar uno ya existente, realizar búsquedas y mostrar todos los alumnos.
- La gestión de libros permitirá añadir uno nuevo, borrar uno ya existente, realizar búsquedas y mostrar todos los libros.
- La gestión de préstamos permitirá realizar un nuevo préstamo, devolver un libro, borrar un préstamo ya existente, realizar búsquedas y - mostrar todas los préstamos, los préstamos realizados por un alumno dado, los préstamos realizados sobre un libro en cuestión, los préstamos realizados en un mes y las estadísticas por cursos para un mes.

Por tanto, tu tarea va a consistir en completar los siguientes apartados:

1. Debes realizar un fork del repositorio de tu tarea anterior en otro nuevo llamado Biblioteca-v4. Dicho repositorio lo clonarás localmente y realizarás las diferentes modificaciones que se piden en esta tarea.
2. Modifica el fichero de configuración de gradle para que incluya las librerías necesarias para poder trabajar correctamente con JavaFX. Crea un nuevo paquete para la vista gráfica. En principio la ventana principal sólo incluirá el menú adecuado. Cada fichero debe estar en la carpeta adecuada (bien sea un recurso -imagen o .fxml- o un fichero .java). Realiza el commit correspondiente.
3. Realiza la gestión de alumnos tal y como se indica anteriormente. Realiza el commit correspondiente.
4. Realiza la gestión de libros tal y como se indica anteriormente. Realiza el commit correspondiente.
5. Realiza la gestión de préstamos tal y como se indica anteriormente. Realiza el commit correspondiente.
6. La aplicación principal aceptará como parámetro "-vtexto" o "-vgrafica" y lanzará nuestra aplicación con una interfaz u otra. Si el parámetro pasado a la aplicación no es correcto o no se le pasa parámetro, se lanzará por defecto la aplicación con la interfaz gráfica. Realiza el commit correspondiente y sube los cambios a tu repositorio remoto.

###### Se valorará:
- La nomenclatura del repositorio de GitHub y del archivo entregado sigue las indicaciones de entrega.
- La indentación debe ser correcta en cada uno de los apartados.
- El nombre de los identificadores debe ser adecuado.
- Se deben utilizar los comentarios adecuados.
- Se valorará la corrección ortográfica tanto en los comentarios como en los mensajes que se muestren al usuario.

