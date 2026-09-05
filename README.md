# Actividad 3 — Escape del laboratorio 🧪🔐

## Descripción

Este proyecto implementa los tres desafíos de la Actividad 3 "Escape del laboratorio", utilizando técnicas de recursividad, divide y vencerás y backtracking.

El objetivo principal es resolver problemas mediante estrategias algorítmicas que permitan descomponer problemas, explorar diferentes posibilidades y obtener soluciones correctas y eficientes.

---

# Desafío 1 — El código fragmentado

## Técnica utilizada

Recursividad.

El problema consiste en reducir un número hasta obtener un solo dígito mediante la suma recursiva de sus dígitos.

Los números utilizados fueron:

* 84729
* 56318
* 92746

Los resultados obtenidos fueron:

```text
84729 → 3
56318 → 5
92746 → 1
```

Por lo tanto:

```text
CLAVE 1 = 351
```

## Caso base

El caso base ocurre cuando el número tiene un solo dígito:

```java
if (numero < 10) {
    return numero;
}
```

En ese momento no es necesario realizar otra llamada recursiva.

## Caso recursivo

Cuando el número contiene más de un dígito, se obtiene la suma de sus dígitos y posteriormente se vuelve a llamar al método de reducción:

```java
return reducirNumero(suma);
```

De esta forma el proceso continúa hasta obtener un solo dígito.

---

# Desafío 2 — El radar de emergencia

## Técnica utilizada

Divide y vencerás.

El arreglo proporcionado fue:

```text
[418, 732, 156, 894, 327, 641, 285, 519,
 763, 204, 947, 386, 675, 128, 856, 493]
```

El algoritmo divide el arreglo en dos partes y continúa dividiendo cada parte hasta llegar a subproblemas de un solo elemento.

Posteriormente se obtiene el máximo de cada mitad y finalmente se comparan ambos resultados.

El valor máximo obtenido fue:

```text
CLAVE 2 = 947
```

## División

El arreglo se divide utilizando un punto medio:

```java
int medio = (inicio + fin) / 2;
```

Después se realizan llamadas recursivas para resolver la parte izquierda y la parte derecha.

## Resolución

Cada subproblema continúa dividiéndose hasta llegar a una posición individual del arreglo.

## Combinación

Después de obtener el máximo de ambas partes, se comparan los resultados:

```java
return Math.max(maxIzquierda, maxDerecha);
```

No se utiliza `Arrays.sort()` ni `Collections.max()`.

---

# Contraseña

Las dos claves se combinan utilizando el formato indicado por la actividad:

```text
CLAVE1-CLAVE2
```

Con los resultados obtenidos:

```text
351-947
```

Esta contraseña se utiliza para acceder al Sistema de Escape.

---

# Desafío 3 — La ruta de escape

## Técnica utilizada

Backtracking.

El algoritmo recibe un laberinto que puede cambiar de un escenario a otro.

El laberinto utiliza:

```text
S = Inicio
E = Salida
# = Obstáculo
. = Camino disponible
```

El algoritmo localiza automáticamente las posiciones `S` y `E`, por lo que no depende de coordenadas específicas.

---

# Funcionamiento del backtracking

El algoritmo comienza en la posición inicial y analiza las cuatro direcciones posibles:

* Arriba
* Derecha
* Abajo
* Izquierda

Antes de realizar un movimiento se verifica:

1. Que la posición esté dentro del laberinto.
2. Que no sea un obstáculo.
3. Que no haya sido visitada en la ruta actual.

Cuando se encuentra una posición válida, se avanza recursivamente.

Cuando se llega a un callejón sin salida, el algoritmo regresa al estado anterior y prueba otra alternativa.

Este proceso permite explorar diferentes rutas.

---

# Evitar ciclos

Para evitar ciclos se utiliza una matriz booleana:

```java
boolean[][] visitado;
```

Cuando una posición forma parte de la ruta actual se marca como visitada.

Al realizar el backtracking, la posición se desmarca:

```java
visitado[fila][columna] = false;
```

Esto permite que una posición pueda utilizarse posteriormente en otra ruta diferente, pero evita ciclos dentro de la misma ruta.

---

# Obtención de la ruta más corta

El algoritmo no termina después de encontrar la primera ruta.

Cada vez que llega a la salida compara la ruta encontrada con la mejor ruta registrada.

Si la nueva ruta tiene menos posiciones, se conserva como la mejor:

```java
if (mejorRuta.isEmpty()
        || solucion.size() < mejorRuta.size()) {

    mejorRuta = solucion;
}
```

De esta manera se pueden explorar diferentes soluciones y conservar la que tenga menor cantidad de movimientos.

---

# Optimización mediante poda

Se implementó una poda de búsqueda.

Si ya existe una solución y la ruta actual tiene una longitud igual o mayor que la mejor solución encontrada, esa rama no puede producir una solución mejor.

Por ello se detiene la exploración:

```java
if (!mejorRuta.isEmpty()
        && rutaActual.size() >= mejorRuta.size()) {

    return;
}
```

Esta optimización evita explorar algunas ramas que ya no pueden mejorar la solución encontrada.

---

# Caso sin solución

El programa también contempla el escenario en el que no existe ningún camino entre `S` y `E`.

Cuando termina la búsqueda sin encontrar una solución, muestra:

```text
No existe una ruta válida hacia la salida.
```

---

# Pruebas realizadas

Se realizaron cinco pruebas principales:

## Caso 1 — Ruta directa

Se utilizó un laberinto con un camino sencillo entre el inicio y la salida.

Resultado esperado:

```text
Se encontró una ruta.
```

## Caso 2 — Callejón sin salida

Se utilizó un laberinto que contiene una alternativa que termina en un callejón sin salida.

El algoritmo debe regresar mediante backtracking y continuar con otra alternativa.

## Caso 3 — Varias rutas

Se utilizó un laberinto con diferentes caminos posibles.

El algoritmo explora las alternativas y conserva la ruta más corta.

## Caso 4 — Primera ruta no es la más corta

Se utilizó un escenario en el que una primera ruta encontrada no representa la solución óptima.

El algoritmo continúa explorando y posteriormente conserva una ruta con menor número de movimientos.

## Caso 5 — Sin solución

Se utilizó un laberinto en el que no existe ningún camino entre `S` y `E`.

El programa identifica correctamente que no existe solución.

---

# Resultado

El programa muestra:

```text
POSICIONES VISITADAS:

fila,columna
fila,columna
...

RUTA FINAL MAS CORTA:

RUTA:r0,c0;r1,c1;r2,c2;...

METRICAS:retrocesos=X;estados=Y
```

Las posiciones y métricas dependen del laberinto utilizado.

---

# Tecnologías utilizadas

* Java
* Recursividad
* Divide y vencerás
* Backtracking
* Arreglos
* Matrices
* Listas
* Estructuras de control
* Git y GitHub

---

# Cómo ejecutar

1. Descargar o clonar el repositorio.
2. Abrir el proyecto en un IDE compatible con Java.
3. Ejecutar `Main.java`.
4. Introducir el número de filas.
5. Introducir el número de columnas.
6. Introducir el laberinto.
7. El programa localizará automáticamente `S` y `E`.
8. El algoritmo realizará la búsqueda mediante backtracking.
9. Finalmente se mostrará la ruta más corta y las métricas.

---

# Conclusión

La actividad permitió aplicar tres estrategias algorítmicas diferentes a problemas concretos. La recursividad permitió reducir números mediante la descomposición del problema en llamadas más pequeñas. Divide y vencerás permitió encontrar el valor máximo de un arreglo dividiéndolo en subproblemas y combinando sus resultados. Finalmente, el backtracking permitió explorar diferentes caminos dentro de un laberinto y regresar cuando una alternativa no era válida.

Una parte importante del desarrollo fue comprender que encontrar una solución no significa necesariamente encontrar la solución óptima. Por esta razón, el desafío del laberinto continúa explorando alternativas y compara las soluciones encontradas para conservar la ruta más corta.

Además, el uso de posiciones visitadas permite evitar ciclos y la poda reduce exploraciones innecesarias cuando una ruta ya no puede superar la mejor solución encontrada.

En conjunto, la actividad permitió comprender la relación entre problema, estrategia, algoritmo, implementación y pruebas, demostrando que las técnicas algorítmicas pueden utilizarse para resolver problemas de manera estructurada y general.
