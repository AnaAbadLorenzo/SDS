# Sustanaible Development System
Esta herramienta ha sido presentada como Proyecto Fin de Carrera. A continuación se describe la misma así como la metodología y tecnologías usadas para su elaboración.
## Descripción
Hoy en día, existe una fuerte tendencia en la sociedad por cumplir con los objetivos del desarrollo sostenible, tanto a nivel de instituciones, empresas y ciudadanos. 
Y por eso es necesario empezar a establecer herramientas que permitan cuantificar esos cumplimientos a través de medidas que puedan ser estandarizadas y cuantificadas 
en unidades de medida objetivas y exportables a otros mecanismos de medición y análisis del éxito. 
El objetivo fundamental del poyecto es la creación de una herramienta que permita a los usuarios y organizaciones la posibilidad de seguir conjuntos de procedimientos 
que maximicen la consecución de los 17 objetivos de desarrollo sostenible 2030 establecidos por la Organización de las Naciones Unidas. La herramienta debe permitir, 
para cada objetivo o en conjunto, la definición de listados de acciones en diferentes niveles de ejecución para que el proceso de cumplimiento se realice por etapas y 
dentro de las posibilidades de cada organización o individuo. El logro de cada nivel puede ser verificado y validado de tal forma que se obtenga el reconocimiento por 
establecer y ejecutar aquellos procedimientos que contribuyan a alguno de los objetivos. 
La herramienta también tiene en cuenta la creación de unidades de medida que permitan medir el cumplimiento de cada procedimiento establecido y ejecutado. Por tanto la 
herramienta debe: 
  - Gestionar noticias 
  - Gestionar objetivos 
  - Gestionar trámites 
  - Gestionar niveles (por objetivos y por conjuntos de objetivos) 
  - Gestionar planes (conjunto de procedimientos a nivel) 
  - Gestionar validaciones 
  - Gestionar unidades 
  - Gestionar usuarios
  
## Metodología
Para llevar a cabo este proyecto de software se seguirá un paradigma de desarrollo de software iterativo incremental dentro de una metodología Rational Unified Process. Se definirá un conjunto de iteraciones donde al final de cada una habrá un aumento de funcionalidad, consiguiendo la funcionalidad total o finalizando todas las iteraciones

## Tecnologías y Productos de terceros utilizados
En cuanto a las tecnologías y a los productos de terceros utilizados, podemos destacar:
  - JAVA, usado como lenguaje base de programación del back-end de la aplicación
  - SpringBoot, framework del que se han aprovechado los servicios de inyección de dependencias, las librerías de seguridad y de acceso a datos.
  - Maven, usado para la gestión de las dependencias del proyecto.
  - MySQL, utilizado como SGBD
  - JPA, utilizado para la gestión de la persistencia de datos de la aplicación
  - CSS, utilizado para proporcionar los estilos visuales a todas las pantallas
  - HTML y Javascript, para definir el significado y estructura de la página web y proporcionar el comportamiento de la misma
  - JQuery y Ajax, para permitir realizar peticiones asíncronas desde el Front-end al Back-end de la aplicación

## Pruebas de la aplicación
Para probar el correcto funcionamiento de la aplicación, se han desarrollado cuatro tipos de testing distintos:
  - Pruebas de caja blanca: prueban las funcionalidad de un programa conociendo la estructura interna del mismo. Están disponibles desde el apartado Test de la aplicación
  - Pruebas de caja negra: prueban la funcionalidad de un programa sin necesidad de conocer la lógica o estructura interna del mismo. Están disponibles desde el      apartado Test de la aplicación
  - Test de regresión: son aquellas que se realizan cuando el software ha sufrido algún cambio para detectar que éste no ha provocado ningún error adicional en el código. Se han realizado mediante la herramienta Postman.
  - Test unitarios: prueban el comportamiento correcto de cada una de las clases de la aplicación de manera aislada. Se han realizado mediante Junit

Adicionalmente, todo el código de la aplicación ha sido analizdo por SonarQube, una plataforma para evaluar la calidad del código fuente mediante un análisis estático sobre el mismo que permite detectar bugs o código duplicado.

## Diagrama de capas de la herramienta
![Capas_SDS](https://user-images.githubusercontent.com/88203308/187065354-e8d402e4-c44c-4f47-92db-a79dae4d2a0b.png)

## Usuarios de conexión
- Conexión como usuario administrador:
     1. Usuario: aicuna
     2. Password: aicuna
- Conexión como usuario gestor:
     1. Usuario: UsuarioGestor
     2. Password: UsuarioGestor
- Conexión como usuario:
     1. Usuario: UsuarioGenerico
     2. Password: UsuarioGenerico
