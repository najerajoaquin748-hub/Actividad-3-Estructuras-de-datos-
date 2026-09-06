# 🧪🔐 Actividad 3 — Escape del Laboratorio

## Estructuras de Datos

**Alumno:** Joaquin Raul Najera Saldaña  
**Materia:** Estructuras de Datos  
**Actividad:** Actividad 3 — Escape del Laboratorio  
**Lenguaje:** Java  

---

## 📌 Descripción

Este proyecto corresponde a la Actividad 3 de la materia de Estructuras de Datos.

El objetivo consiste en resolver tres desafíos de programación utilizando diferentes técnicas:

1. Recursividad.
2. Divide y vencerás.
3. Backtracking.

Los primeros dos desafíos permiten obtener una contraseña para ingresar al Sistema de Escape. Después, el tercer desafío consiste en resolver un laberinto generado por el sistema y encontrar la ruta más corta desde el punto de inicio hasta la salida.

---

# 🎯 Objetivo

Aplicar recursividad, divide y vencerás y backtracking para resolver diferentes problemas mediante Java.

En particular, el programa permite:

- Reducir números mediante recursividad.
- Encontrar el máximo de un arreglo mediante divide y vencerás.
- Leer un laberinto desde un archivo `maze.txt`.
- Encontrar automáticamente el inicio y la meta.
- Explorar diferentes caminos.
- Evitar obstáculos y posiciones inválidas.
- Evitar ciclos.
- Realizar retrocesos mediante backtracking.
- Comparar diferentes soluciones.
- Encontrar la ruta más corta.
- Detectar cuando un laberinto no tiene solución.
- Mostrar las posiciones visitadas.
- Mostrar métricas de la búsqueda.

---

# 🔢 Desafío 1 — Recursividad

El primer desafío recibe tres bloques numéricos:

```text
84729
56318
92746
```

Cada número debe reducirse hasta obtener un solo dígito.

La suma de los dígitos se realiza utilizando recursividad y sin utilizar ciclos para efectuar la reducción.

## Resultados

### Primer número

```text
84729
8 + 4 + 7 + 2 + 9 = 30
3 + 0 = 3
```

Resultado:

```text
84729 → 3
```

### Segundo número

```text
56318
5 + 6 + 3 + 1 + 8 = 23
2 + 3 = 5
```

Resultado:

```text
56318 → 5
```

### Tercer número

```text
92746
9 + 2 + 7 + 4 + 6 = 28
2 + 8 = 10
1 + 0 = 1
```

Resultado:

```text
92746 → 1
```

Por lo tanto:

```text
CLAVE 1: 351
```

## Caso base

El caso base ocurre cuando el número contiene solamente un dígito.

```java
if (numero < 10) {
    return numero;
}
```

Cuando se alcanza esta condición, la recursividad termina.

## Caso recursivo

Cuando el número contiene más de un dígito, se obtiene el último dígito utilizando:

```java
numero % 10
```

y el resto del número mediante:

```java
numero / 10
```

El método vuelve a llamarse con el resto del número hasta alcanzar el caso base.

---

# 📡 Desafío 2 — Divide y vencerás

El segundo desafío utiliza las siguientes lecturas:

```text
[418, 732, 156, 894, 327, 641, 285, 519,
 763, 204, 947, 386, 675, 128, 856, 493]
```

El objetivo consiste en encontrar el valor máximo utilizando la estrategia de divide y vencerás.

No se utiliza:

```java
Arrays.sort()
Collections.max()
```

ni se ordena previamente el arreglo.

## División

El arreglo se divide recursivamente en dos partes:

```text
              Arreglo
             /       \
       Izquierda     Derecha
```

Cada parte continúa dividiéndose hasta llegar a un solo elemento.

## Resolución

Cuando el subproblema contiene un solo elemento, ese elemento representa el máximo de esa parte.

## Combinación

Los resultados de la parte izquierda y derecha se comparan.

El mayor de ambos se devuelve hacia la llamada anterior.

Después de completar las comparaciones se obtiene:

```text
CLAVE 2: 947
```

---

# 🔐 Contraseña

Los resultados de los primeros dos desafíos son:

```text
CLAVE 1: 351
CLAVE 2: 947
```

Por lo tanto, la contraseña utilizada para acceder al Sistema de Escape es:

```text
351-947
```

---

# 🧩 Desafío 3 — Backtracking

Después de ingresar al Sistema de Escape se obtiene un laberinto generado por el sistema.

El laberinto se descarga mediante un archivo llamado:

```text
maze.txt
```

El programa Java lee este archivo automáticamente.

Esto permite utilizar diferentes laberintos sin escribir las posiciones directamente dentro del código.

---

# 🗺️ Representación del laberinto

Los caracteres utilizados son:

```text
S = Inicio
T = Meta
E = Meta compatible
# = Obstáculo
. = Camino disponible
```

El programa localiza automáticamente el inicio y la salida.

---

# 🧠 Funcionamiento del Backtracking

El algoritmo comienza en la posición inicial `S`.

Desde cada posición intenta explorar las siguientes direcciones:

```text
Arriba
Abajo
Izquierda
Derecha
```

Antes de realizar un movimiento comprueba que:

- La posición se encuentre dentro del laberinto.
- No sea una pared.
- No haya sido visitada dentro de la ruta actual.

Si el movimiento es válido, el algoritmo continúa recursivamente.

Cuando encuentra un callejón sin salida, regresa a la posición anterior y prueba otra alternativa.

Este proceso se conoce como **backtracking**.

---

# 🔄 Control de ciclos

Para evitar ciclos se utiliza una estructura de posiciones visitadas.

Cuando una posición forma parte de la ruta actual se marca como visitada.

El algoritmo no puede volver a utilizar esa posición dentro de la misma ruta.

Cuando se realiza backtracking, la posición se desmarca para permitir que pueda utilizarse posteriormente desde otra ruta diferente.

Esto permite explorar distintas soluciones sin quedar atrapado en ciclos.

---

# 🏆 Búsqueda de la ruta más corta

Encontrar la primera ruta hacia la salida no es suficiente.

Cada vez que el algoritmo encuentra la meta, compara la nueva ruta con la mejor solución encontrada anteriormente.

Si la nueva ruta tiene menos posiciones, se convierte en la nueva mejor ruta.

De esta manera el programa conserva la ruta más corta encontrada.

La cantidad de movimientos se calcula como:

```text
Movimientos = número de posiciones de la ruta - 1
```

---

# ✂️ Poda de búsqueda

Se implementó una optimización para evitar exploraciones innecesarias.

Si ya existe una mejor ruta y la ruta que actualmente se está explorando tiene una longitud igual o mayor, se detiene esa rama.

Por ejemplo:

```text
Mejor ruta encontrada: 15 movimientos
Ruta actual: 16 movimientos
```

La ruta actual ya no puede mejorar la solución, por lo que no es necesario continuar explorándola.

Esta técnica reduce búsquedas innecesarias.

---

# 🚫 Caso sin solución

El programa también contempla laberintos donde no existe una ruta válida desde el inicio hasta la meta.

En ese caso se muestra:

```text
SIN SOLUCION
```

Esto permite manejar tanto escenarios normales como casos especiales.

---

# 📊 Salida del programa

Durante la ejecución se muestran las posiciones exploradas utilizando el formato:

```text
fila,columna
```

Por ejemplo:

```text
0,0
1,0
2,0
2,1
3,1
```

Al finalizar se muestra la ruta más corta:

```text
RUTA:r0,c0;r1,c1;r2,c2;...
```

También se muestra la cantidad de movimientos:

```text
Movimientos: X
```

y las métricas:

```text
METRICAS:retrocesos=X;estados=Y
```

---

# 📈 Métricas

El programa registra dos métricas principales:

### Retrocesos

Cantidad de veces que el algoritmo tuvo que regresar durante la exploración mediante backtracking.

### Estados

Cantidad de posiciones o estados procesados durante la búsqueda.

Estas métricas dependen del laberinto utilizado.

---

# 🧪 Pruebas consideradas

Para comprobar el funcionamiento general de la solución se consideran diferentes escenarios.

### Caso 1 — Ruta directa

Existe un camino sencillo desde el inicio hasta la salida.

El programa debe encontrar correctamente la ruta.

### Caso 2 — Callejón sin salida

Existe un camino que termina bloqueado.

El algoritmo debe regresar y probar otra alternativa.

### Caso 3 — Varias rutas

Existen diferentes caminos para llegar a la salida.

El programa debe comparar las soluciones y conservar la más corta.

### Caso 4 — La primera ruta no es la más corta

La primera solución encontrada es válida, pero existe otra con menos movimientos.

El algoritmo debe continuar buscando y reemplazar la primera solución por la mejor.

### Caso 5 — Sin solución

No existe ningún camino entre el inicio y la salida.

El programa debe detectar esta situación y mostrar:

```text
SIN SOLUCION
```

---

# 📁 Estructura del proyecto

La estructura principal del proyecto es:

```text
Actividad3/
│
├── Main.java
├── maze.txt
└── README.md
```

`Main.java` contiene la implementación de los tres desafíos.

`maze.txt` contiene el laberinto descargado desde el Sistema de Escape.

`README.md` contiene la documentación del proyecto.

---

# ▶️ Cómo ejecutar el programa

## Requisitos

Es necesario tener instalado:

- Java JDK.
- Una terminal o IDE compatible con Java.

Por ejemplo:

- Visual Studio Code.
- IntelliJ IDEA.
- Eclipse.

## Paso 1

Descargar el archivo `maze.txt` desde el Sistema de Escape.

## Paso 2

Colocar `maze.txt` en la misma carpeta que `Main.java`.

```text
Actividad3/
├── Main.java
├── maze.txt
└── README.md
```

## Paso 3

Abrir una terminal dentro de la carpeta del proyecto.

## Paso 4

Compilar el programa:

```bash
javac Main.java
```

## Paso 5

Ejecutar:

```bash
java Main
```

El programa leerá el archivo `maze.txt` y ejecutará los desafíos correspondientes.

---

# 💡 Decisiones importantes de implementación

Durante el desarrollo se tomaron las siguientes decisiones:

- Utilizar recursividad para reducir los números del desafío 1.
- No utilizar ciclos para realizar la reducción solicitada.
- Aplicar divide y vencerás para encontrar el máximo del arreglo.
- No ordenar el arreglo para encontrar el máximo.
- Leer el laberinto desde `maze.txt` para evitar una solución específica.
- Localizar automáticamente el inicio y la meta.
- Utilizar backtracking para explorar diferentes alternativas.
- Controlar posiciones visitadas para evitar ciclos.
- Desmarcar posiciones durante el retroceso para permitir nuevas rutas.
- Continuar buscando después de encontrar la primera solución.
- Comparar las rutas encontradas para conservar la más corta.
- Implementar poda para evitar exploraciones innecesarias.
- Contemplar el caso en el que no existe una solución.

---

# 📚 Técnicas utilizadas

| Desafío | Técnica | Objetivo |
|---|---|---|
| Desafío 1 | Recursividad | Reducir los números a un solo dígito |
| Desafío 2 | Divide y vencerás | Encontrar el máximo del arreglo |
| Desafío 3 | Backtracking | Encontrar la ruta más corta del laberinto |

---

# ✅ Resultados

Los resultados obtenidos en los primeros desafíos son:

```text
CLAVE 1: 351
CLAVE 2: 947
CONTRASEÑA: 351-947
```

Para el tercer desafío, la ruta, número de movimientos y métricas dependen del archivo `maze.txt` generado por el Sistema de Escape.

---

# 📝 Conclusión

Esta actividad permitió aplicar diferentes técnicas para resolver problemas mediante Java.

La recursividad fue utilizada para dividir un cálculo en versiones más pequeñas del mismo problema. Divide y vencerás permitió separar un arreglo en diferentes partes y combinar sus resultados para encontrar el valor máximo. Finalmente, el backtracking permitió explorar diferentes caminos dentro de un laberinto, regresar cuando una alternativa no funcionaba y comparar las soluciones encontradas.

La búsqueda de la ruta más corta permitió comprender que encontrar una solución válida no siempre significa haber encontrado la mejor solución. También fue necesario controlar ciclos, posiciones inválidas y caminos bloqueados.

La lectura del archivo `maze.txt` hace que la solución sea general, ya que permite ejecutar el algoritmo utilizando diferentes laberintos sin modificar manualmente las posiciones dentro del código.