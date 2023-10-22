# Java Back End Developer challenge by Accenture & Nuwe

## Idioma / Language
1. [Español](#español)
2. [English](#english)

## Español

## Tabla de contenidos
- [Descripcion](#descripcion)
- [Modificaciones](#modificaciones)
- [Docker](#docker)

## Descripcion
En este proyecto se ha afrontado el reto propuesto por [Accenture](https://www.accenture.com/es-es) a través de 
[Nuwe](https://nuwe.io/). Se plantea un supuesto en el que se debe renovar una aplicación de gestión de citas para un 
hospital. Mi tarea ha sido implementar, arreglar y desarrollar las diferentes necesidades de la aplicación a través
de un API REST con Spring Boot. 

## Modificaciones

Se han modificado las siguientes clases:

- **AppointmentController**: Se ha añadido un nuevo método para obtener las citas de un usuario y se ha refactorizado 
    el código existente.
- **EntityControllerUnitTest**: Se han añadido tests unitarios para el método añadido en AppointmentController.
- **EntityUnitTest**: Se han añadido tests unitarios para el método añadido en AppointmentController.

## Docker

Además se han creado dos ficheros Dockerfile para crear las imágenes de la aplicación y de la base de datos.

- **Dockerfile.maven**: Se utiliza para crear la imagen de la aplicación a partir de un fichero WAR generado con Maven.
- **Dockerfile.mysql**: Se utiliza para crear la imagen de la base de datos a partir de un fichero SQL.

------------------------------------------------------------------------------------------------------------------------

## English

## Table of contents
- [Description](#description)
- [Modifications](#modifications)
- [Docker](#docker)

## Description
In this project I have faced the challenge proposed by [Accenture](https://www.accenture.com/es-es) through 
[Nuwe](https://nuwe.io/). It is proposed a case in which an appointment management application for a hospital must be
renewed. My task has been to implement, fix and develop the different needs of the application through a REST API with
Spring Boot.

## Modifications
The following classes have been modified:

- **AppointmentController**: A new method has been added to get the appointments of a user and the existing code has 
    been refactored.
- **EntityControllerUnitTest**: Unit tests have been added for the method added in AppointmentController.
- **EntityUnitTest**: Unit tests have been added for the method added in AppointmentController.

## Docker
In addition, two Dockerfile files have been created to create the images of the application and the database.

- **Dockerfile.maven**: It is used to create the application image from a WAR file generated with Maven.
- **Dockerfile.mysql**: It is used to create the database image from a SQL file.

