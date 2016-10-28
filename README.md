# KunderWebservices

Documento de requerimientos - Postulación Kunder Web Service


Arquitectura/Tecnologías
Se utiliza Librería jersey versión 1.12 para la generación de webservice con estándar JAX-RS
Se utiliza Java EE version 6 como aplicación Web desplegado en servidor Weblogic 12c (requerimiento).


Consideraciones:


Requerimiento 1

Se asume que largo de palabra debe ser siempre 4. De lo contrario se arrojará error BAD Request.

Se asume que palabra contendrá sólo letras. De lo contrario se arrojará error BAD Request.


Requerimiento 2

Se asume como fecha de salida la fecha de ejecución. Esto dado que parámetro de entrada sólo considera hora (ej: HH:mm:ss).




Fuentes/Documentación


https://github.com/jlvarela/KunderWebservices.git
