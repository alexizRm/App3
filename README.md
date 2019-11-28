# Aplicación 3

Aplicación que registra n numeros de usuarios para la materia de computo movil

#Descripción

Esta aplicación permite al usuario registrarse en una base de datos tomando como elementos de registro el nombre, numero de cuenta, usuario (correo), contraseña y género, para posteriormente localizarle en un listado general de los usuarios registrados.
Este sistema es util para almacenar en una base de datos local (sqlite) información especifica sobre un grupo de personas, por ejemplo un registro academico.

#Instrucciones

1.Para ingresar es necesario registrarse previamente para lo cual la aplicacion muestra una pantalla de inicio denominada con las opciones de "Entrar" o "Registrar"

2.Seleccionar la opción registrar para acceder a la segunda pantalla denominada "Crear cuenta nueva" , la cual muestra los campos siguientes

-Nombre completo del usuario (No acepta caracteres numéricos, ni especiales)

-Numero de cuenta (Acepta 9 digitos)

-Usuario (Correo electrónico) (Solo acpta correos electronicos registrados ante una plataforma comercial)

-Contraseña (Valido cualquier caracter alfa numerico o especial)

-Género (Elegible entre dos rubros)

3.Seleccionar la opción registrar. El sistema devolvera a la pagina inicial en la cual habra que iniciar sesión con el usuario y contraseña previamente registrados

4.El sistema mostrara la paginia denominada "Hola Usuario" en la cual se muestran las opciones de "Eliminar cuenta"(No Implementada),"Mostrar alumnos" y "Cerrar sesión"

5.Seleccionar la opción mostrar alumnos para que el sistema despliegue el listado de usuarios registrados haciendo visibles sus datos de identificación (Género-Mediante un simbolo distintivo-, Nombre y numero de cuenta.

6.El usuario puede también seleccionar la opcion cerrar sesión para volver al menu pricipal
