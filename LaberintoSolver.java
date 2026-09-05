import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LaberintoSolver {

    private char[][] laberinto;
    private boolean[][] visitado;

    private int filas;
    private int columnas;

    private int inicioFila = -1;
    private int inicioColumna = -1;

    private int salidaFila = -1;
    private int salidaColumna = -1;

    private List<String> rutaActual;
    private List<String> mejorRuta;
    private List<String> posicionesVisitadas;

    private int retrocesos = 0;
    private int estados = 0;

    // Arriba, derecha, abajo, izquierda
    private final int[] movimientosFila = {-1, 0, 1, 0};
    private final int[] movimientosColumna = {0, 1, 0, -1};

    public LaberintoSolver(char[][] laberinto) {

        this.laberinto = laberinto;

        this.filas = laberinto.length;
        this.columnas = laberinto[0].length;

        this.visitado = new boolean[filas][columnas];

        this.rutaActual = new ArrayList<>();
        this.mejorRuta = new ArrayList<>();
        this.posicionesVisitadas = new ArrayList<>();

        buscarInicioYSalida();
    }

    // =====================================================
    // BUSCAR INICIO Y SALIDA
    // =====================================================

    private void buscarInicioYSalida() {

        for (int fila = 0; fila < filas; fila++) {

            for (int columna = 0; columna < columnas; columna++) {

                if (laberinto[fila][columna] == 'S') {

                    inicioFila = fila;
                    inicioColumna = columna;
                }

                // La página utiliza T como meta.
                // También aceptamos E por compatibilidad.
                if (laberinto[fila][columna] == 'T'
                        || laberinto[fila][columna] == 'E') {

                    salidaFila = fila;
                    salidaColumna = columna;
                }
            }
        }
    }

    // =====================================================
    // RESOLVER LABERINTO
    // =====================================================

    public boolean resolver() {

        if (inicioFila == -1 || inicioColumna == -1) {

            System.out.println(
                "ERROR: No se encontro S en el laberinto."
            );

            return false;
        }

        if (salidaFila == -1 || salidaColumna == -1) {

            System.out.println(
                "ERROR: No se encontro T o E en el laberinto."
            );

            return false;
        }

        backtracking(inicioFila, inicioColumna);

        return !mejorRuta.isEmpty();
    }

    // =====================================================
    // BACKTRACKING
    // =====================================================

    private void backtracking(int fila, int columna) {

        estados++;

        if (!esMovimientoValido(fila, columna)) {
            return;
        }

        // Marcamos la posición
        visitado[fila][columna] = true;

        String posicionActual = posicion(fila, columna);

        rutaActual.add(posicionActual);
        posicionesVisitadas.add(posicionActual);

        // =================================================
        // CASO BASE
        // =================================================

        if (fila == salidaFila && columna == salidaColumna) {

            // Si es la primera solución
            // o es más corta que la anterior
            if (mejorRuta.isEmpty()
                    || rutaActual.size() < mejorRuta.size()) {

                mejorRuta = new ArrayList<>(rutaActual);
            }

        } else {

            // =================================================
            // PODA
            // =================================================

            /*
             * Si ya encontramos una ruta y la ruta actual
             * ya tiene una longitud igual o mayor,
             * dejamos de explorar esa rama.
             */
            if (mejorRuta.isEmpty()
                    || rutaActual.size() < mejorRuta.size()) {

                for (int direccion = 0;
                     direccion < 4;
                     direccion++) {

                    int nuevaFila =
                        fila + movimientosFila[direccion];

                    int nuevaColumna =
                        columna + movimientosColumna[direccion];

                    if (esMovimientoValido(
                            nuevaFila,
                            nuevaColumna)) {

                        backtracking(
                            nuevaFila,
                            nuevaColumna
                        );
                    }
                }
            }
        }

        // =================================================
        // RETROCESO
        // =================================================

        rutaActual.remove(rutaActual.size() - 1);

        visitado[fila][columna] = false;

        retrocesos++;
    }

    // =====================================================
    // VALIDAR MOVIMIENTO
    // =====================================================

    private boolean esMovimientoValido(
            int fila,
            int columna) {

        // Fuera del laberinto
        if (fila < 0
                || fila >= filas
                || columna < 0
                || columna >= columnas) {

            return false;
        }

        // Obstáculo
        if (laberinto[fila][columna] == '#') {

            return false;
        }

        // Evitar ciclos
        if (visitado[fila][columna]) {

            return false;
        }

        return true;
    }

    private String posicion(
            int fila,
            int columna) {

        return fila + "," + columna;
    }

    // =====================================================
    // LEER maze.txt
    // =====================================================

    public static char[][] leerLaberinto() {

        List<String> lineas = new ArrayList<>();

        File archivo = new File("maze.txt");

        try {

            Scanner scanner = new Scanner(archivo);

            while (scanner.hasNextLine()) {

                String linea = scanner.nextLine();

                linea = linea.trim();

                // Eliminar espacios
                linea = linea.replaceAll("\\s+", "");

                if (!linea.isEmpty()) {

                    lineas.add(linea);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println();
            System.out.println(
                "ERROR: No se encontro el archivo maze.txt."
            );

            System.out.println(
                "Debe estar en la misma carpeta del proyecto."
            );

            return new char[0][0];
        }

        if (lineas.isEmpty()) {

            System.out.println(
                "ERROR: maze.txt esta vacio."
            );

            return new char[0][0];
        }

        /*
         * IMPORTANTE:
         *
         * Estas son variables LOCALES.
         * Ya no utilizamos los campos filas y columnas,
         * por eso desaparece el error de static.
         */

        int cantidadFilas = lineas.size();

        int cantidadColumnas =
            lineas.get(0).length();

        // Verificar que todas las filas midan lo mismo
        for (int i = 0; i < cantidadFilas; i++) {

            if (lineas.get(i).length()
                    != cantidadColumnas) {

                System.out.println();
                System.out.println(
                    "ERROR: Las filas del laberinto "
                    + "no tienen la misma longitud."
                );

                System.out.println(
                    "Error encontrado en la fila: " + i
                );

                return new char[0][0];
            }
        }

        char[][] resultado =
            new char[cantidadFilas][cantidadColumnas];

        for (int fila = 0;
             fila < cantidadFilas;
             fila++) {

            resultado[fila] =
                lineas.get(fila).toCharArray();
        }

        return resultado;
    }

    // =====================================================
    // IMPRIMIR LABERINTO
    // =====================================================

    public void imprimirLaberinto() {

        System.out.println();
        System.out.println(
            "========== LABERINTO =========="
        );

        System.out.println(
            "Filas: " + filas
            + " | Columnas: " + columnas
        );

        System.out.println();

        for (int fila = 0;
             fila < filas;
             fila++) {

            for (int columna = 0;
                 columna < columnas;
                 columna++) {

                System.out.print(
                    laberinto[fila][columna]
                );
            }

            System.out.println();
        }
    }

    // =====================================================
    // IMPRIMIR RESULTADOS
    // =====================================================

    public void imprimirResultado() {

        System.out.println();
        System.out.println(
            "========== RESULTADO =========="
        );

        System.out.println();

        // POSICIONES VISITADAS
        System.out.println(
            "POSICIONES VISITADAS:"
        );

        for (String posicion : posicionesVisitadas) {

            System.out.println(posicion);
        }

        System.out.println();

        // SIN SOLUCIÓN
        if (mejorRuta.isEmpty()) {

            System.out.println(
                "No existe una ruta valida hacia la salida."
            );

            System.out.println();

            System.out.println(
                "METRICAS:retrocesos="
                + retrocesos
                + ";estados="
                + estados
            );

            return;
        }

        // RUTA MÁS CORTA
        System.out.println(
            "RUTA FINAL MAS CORTA:"
        );

        System.out.print("RUTA:");

        for (int i = 0;
             i < mejorRuta.size();
             i++) {

            System.out.print(mejorRuta.get(i));

            if (i < mejorRuta.size() - 1) {

                System.out.print(";");
            }
        }

        System.out.println();

        int movimientos =
            mejorRuta.size() - 1;

        System.out.println();

        System.out.println(
            "Movimientos: " + movimientos
        );

        System.out.println();

        System.out.println(
            "METRICAS:retrocesos="
            + retrocesos
            + ";estados="
            + estados
        );
    }
}