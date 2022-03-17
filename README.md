# LuloBankEmployeesService-Project
Cómo entidad Bancaria, la empresa LuloBank requiere automatizar la API "http://dummy.restapiexample.com/api/v1/", usada para la gestión de sus empleados vinculados a la Vicepresidencia de Tecnología. Dado que los procesos son repetitivos, se ha decidido que se va a guardar la información en un archivo de Excel y al final de la semana se ejecuta la automatización para que este proceso se realice de manera rápida y confiable sin generar indisponibilidad de ambientes.

### Herramientas y softwares usados:

ava 1.8.Maven para gestión de librerías.
Patron de diseño Serenity BDD Screenplay.Cucumber 6  con Serenity BDD.
Serenity Rest , Serenity BDD Screenplay
Hamcrest
ExtentReports, dependencias y clases externas para manejo de DataEntries, etc.PostMan para las pruebas preliminares.

### Entrada de datos

Los casos de pruebas se ingresar en el dataEntry que se encuentra en la ruta: src/main/resources/com/lulobank/automation/DataEntrys/DataEntry_Api_Operations.xlsx, el equipo de automatización creó un DataEntry(DDT) con el fin de que se ingresen los datos de manera ordenada.

###Casos de pruebas:

Consultar Salario de los empleados
Consultar si es empleado de LuloBank
Eliminar empleado
Crear nuevo empleado

###Observaciones u recomenaciones:
Incluir ejecución en herramientas de integración continua como Jenkins.
