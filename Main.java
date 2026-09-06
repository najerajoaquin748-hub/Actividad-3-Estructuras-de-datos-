import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {

    // MOVIMIENTOS
    // Arriba, abajo, izquierda, derecha

    static final int[] DF = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};

    // VARIABLES PARA EL DESAFÍO 3

    static char[][] laberinto;
    static boolean[][] visitado;

    static List<Posicion> rutaActual = new ArrayList<>();
    static List<Posicion> mejorRuta = new ArrayList<>();
    static List<Posicion> historialVisitas = new ArrayList<>();

    static long retrocesos = 0;
    static long estados = 0;

    // CLASE POSICIÓN

    static class Posicion {

        int fila;
        int columna;

        Posicion(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        @Override
        public String toString() {
            return fila + "," + columna;
        }
    }

    // MÉTODO MAIN

    public static void main(String[] args) {
        // DESAFÍO 1

        System.out.println("\nDESAFIO 1 RECURSIVIDAD");

        int numero1 = 84729;
        int numero2 = 56318;
        int numero3 = 92746;

        int resultado1 = reducirAUnDigito(numero1);
        int resultado2 = reducirAUnDigito(numero2);
        int resultado3 = reducirAUnDigito(numero3);

        String clave1 =
                "" + resultado1 + resultado2 + resultado3;

        System.out.println(numero1 + " -> " + resultado1);
        System.out.println(numero2 + " -> " + resultado2);
        System.out.println(numero3 + " -> " + resultado3);

        System.out.println("CLAVE 1: " + clave1);

        // DESAFÍO 2

        System.out.println("\nDESAFIO 2 DIVIDE Y VENCERAS");

        int[] lecturas = {
                418, 732, 156, 894,
                327, 641, 285, 519,
                763, 204, 947, 386,
                675, 128, 856, 493
        };

        int clave2 =
                maximoDivideYVenceras(
                        lecturas,
                        0,
                        lecturas.length - 1
                );

        System.out.println("CLAVE 2: " + clave2);

        System.out.println(
                "CONTRASENA: "
                        + clave1
                        + "-"
                        + clave2
        );

        // DESAFÍO 3

        System.out.println("\nDESAFIO 3 BACKTRACKING");

        try {

            leerLaberintoDesdeArchivo("maze.txt");

            resolverLaberinto();

        } catch (IOException e) {

            System.out.println(
                    "ERROR: No se pudo leer maze.txt."
            );

            System.out.println(
                    "Coloca maze.txt en la misma carpeta que Main.java."
            );

            System.out.println(
                    "Detalle: " + e.getMessage()
            );
        }
    }

    // DESAFÍO 1 - RECURSIVIDAD

    public static int reducirAUnDigito(int numero) {

        numero = Math.abs(numero);

        // Caso base
        if (numero < 10) {
            return numero;
        }

        // Caso recursivo
        int suma = sumarDigitos(numero);

        return reducirAUnDigito(suma);
    }

    public static int sumarDigitos(int numero) {

        // Caso base
        if (numero < 10) {
            return numero;
        }

        // Caso recursivo
        return (numero % 10)
                + sumarDigitos(numero / 10);
    }

    // DESAFÍO 2 - DIVIDE Y VENCERÁS

    public static int maximoDivideYVenceras(
            int[] arreglo,
            int inicio,
            int fin) {

        // Caso base
        if (inicio == fin) {
            return arreglo[inicio];
        }

        // Dividir
        int mitad =
                inicio + (fin - inicio) / 2;

        // Resolver parte izquierda
        int maxIzquierda =
                maximoDivideYVenceras(
                        arreglo,
                        inicio,
                        mitad
                );

        // Resolver parte derecha
        int maxDerecha =
                maximoDivideYVenceras(
                        arreglo,
                        mitad + 1,
                        fin
                );

        // Combinar
        return Math.max(
                maxIzquierda,
                maxDerecha
        );
    }

    // LEER LABERINTO DESDE maze.txt

    public static void leerLaberintoDesdeArchivo(
            String nombreArchivo)
            throws IOException {

        Path ruta =
                Paths.get(nombreArchivo);

        List<String> lineas =
                Files.readAllLines(ruta);

        List<String> filasValidas =
                new ArrayList<>();

        for (String linea : lineas) {

            linea = linea.trim();

            if (!linea.isEmpty()) {

                linea =
                        linea.replace(" ", "");

                filasValidas.add(linea);
            }
        }

        if (filasValidas.isEmpty()) {

            throw new IOException(
                    "El archivo maze.txt esta vacio."
            );
        }

        int filas =
                filasValidas.size();

        int columnas =
                filasValidas.get(0).length();

        laberinto =
                new char[filas][columnas];

        for (int i = 0;
             i < filas;
             i++) {

            String linea =
                    filasValidas.get(i);

            if (linea.length()
                    != columnas) {

                throw new IOException(
                        "Todas las filas deben tener el mismo tamano."
                );
            }

            laberinto[i] =
                    linea.toCharArray();
        }

        System.out.println(
                "\nLaberinto cargado correctamente desde maze.txt"
        );

        System.out.println(
                "Tamano: "
                        + filas
                        + "x"
                        + columnas
        );

        System.out.println("\nLABERINTO:");

        mostrarLaberinto();
    }

    // MOSTRAR LABERINTO

    public static void mostrarLaberinto() {

        for (char[] fila : laberinto) {

            System.out.println(
                    new String(fila)
            );
        }
    }

    // RESOLVER LABERINTO

    public static void resolverLaberinto() {

        Posicion inicio =
                buscarCaracter('S');

        /*
         * La página utiliza T como meta.
         * También se acepta E por compatibilidad
         * con las instrucciones generales.
         */

        Posicion meta =
                buscarCaracter('T');

        if (meta == null) {
            meta =
                    buscarCaracter('E');
        }

        if (inicio == null) {

            System.out.println(
                    "ERROR: No se encontro S."
            );

            return;
        }

        if (meta == null) {

            System.out.println(
                    "ERROR: No se encontro T ni E."
            );

            return;
        }

        visitado =
                new boolean[
                        laberinto.length]
                        [laberinto[0].length];

        rutaActual.clear();
        mejorRuta.clear();
        historialVisitas.clear();

        retrocesos = 0;
        estados = 0;

        buscar(
                inicio.fila,
                inicio.columna,
                meta
        );

        mostrarResultado();
    }

    // BACKTRACKING

    public static void buscar(
            int fila,
            int columna,
            Posicion meta) {

        // Posición fuera del laberinto

        if (fila < 0
                || fila >= laberinto.length
                || columna < 0
                || columna >= laberinto[0].length) {

            return;
        }

        // Pared

        if (laberinto[fila][columna] == '#') {
            return;
        }

        // Evitar ciclos

        if (visitado[fila][columna]) {
            return;
        }

        // Poda

        /*
         * Si ya existe una mejor ruta y
         * la ruta actual ya tiene igual o
         * mayor longitud, esta rama no puede
         * mejorar la solución.
         */

        if (!mejorRuta.isEmpty()
                && rutaActual.size() + 1
                >= mejorRuta.size()) {

            return;
        }

        estados++;

        Posicion actual =
                new Posicion(
                        fila,
                        columna
                );

        historialVisitas.add(actual);

        visitado[fila][columna] = true;

        rutaActual.add(actual);

        // Caso base: se encontró la meta

        if (fila == meta.fila
                && columna == meta.columna) {

            if (mejorRuta.isEmpty()
                    || rutaActual.size()
                    < mejorRuta.size()) {

                mejorRuta =
                        copiarRuta(
                                rutaActual
                        );
            }

            rutaActual.remove(
                    rutaActual.size() - 1
            );

            visitado[fila][columna] =
                    false;

            return;
        }

        // Explorar las cuatro direcciones

        for (int i = 0;
             i < 4;
             i++) {

            int nuevaFila =
                    fila + DF[i];

            int nuevaColumna =
                    columna + DC[i];

            buscar(
                    nuevaFila,
                    nuevaColumna,
                    meta
            );
        }

        // Backtracking

        rutaActual.remove(
                rutaActual.size() - 1
        );

        visitado[fila][columna] =
                false;

        retrocesos++;
    }

    // COPIAR RUTA

    public static List<Posicion> copiarRuta(
            List<Posicion> ruta) {

        List<Posicion> copia =
                new ArrayList<>();

        for (Posicion p : ruta) {

            copia.add(
                    new Posicion(
                            p.fila,
                            p.columna
                    )
            );
        }

        return copia;
    }

    // BUSCAR S, T O E

    public static Posicion buscarCaracter(
            char buscado) {

        for (int fila = 0;
             fila < laberinto.length;
             fila++) {

            for (int columna = 0;
                 columna < laberinto[fila].length;
                 columna++) {

                if (laberinto[fila][columna]
                        == buscado) {

                    return new Posicion(
                            fila,
                            columna
                    );
                }
            }
        }

        return null;
    }

    // MOSTRAR RESULTADO

    public static void mostrarResultado() {

        System.out.println(
                "\n=============================="
        );

        System.out.println(
                "POSICIONES VISITADAS:"
        );

        System.out.println(
                "=============================="
        );

        for (Posicion p :
                historialVisitas) {

            System.out.println(p);
        }

        System.out.println();

        System.out.println(
                "RUTA FINAL MAS CORTA:"
        );

        if (mejorRuta.isEmpty()) {

            System.out.println(
                    "SIN SOLUCION"
            );

            System.out.println(
                    "RUTA:"
            );

        } else {

            StringJoiner ruta =
                    new StringJoiner(";");

            for (Posicion p :
                    mejorRuta) {

                ruta.add(
                        p.toString()
                );
            }

            System.out.println(
                    "RUTA:" + ruta
            );

            System.out.println(
                    "Movimientos: "
                            + (mejorRuta.size() - 1)
            );
        }

        System.out.println();

        System.out.println(
                "METRICAS:retrocesos="
                        + retrocesos
                        + ";estados="
                        + estados
        );
    }
}