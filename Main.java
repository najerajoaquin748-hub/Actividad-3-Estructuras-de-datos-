public class Main {

    public static void main(String[] args) {

        System.out.println(
            "=========================================="
        );

        System.out.println(
            "   ACTIVIDAD 3 - ESCAPE DEL LABORATORIO"
        );

        System.out.println(
            "=========================================="
        );

        System.out.println();


        // ==========================================
        // DESAFIO 1 - RECURSIVIDAD
        // ==========================================

        System.out.println(
            "DESAFIO 1 - RECURSIVIDAD"
        );

        int resultado1 =
            Desafio1Recursividad.reducirNumero(84729);

        int resultado2 =
            Desafio1Recursividad.reducirNumero(56318);

        int resultado3 =
            Desafio1Recursividad.reducirNumero(92746);

        String clave1 =
            "" + resultado1
            + resultado2
            + resultado3;

        System.out.println(
            "84729 -> " + resultado1
        );

        System.out.println(
            "56318 -> " + resultado2
        );

        System.out.println(
            "92746 -> " + resultado3
        );

        System.out.println();

        System.out.println(
            "CLAVE 1: " + clave1
        );

        System.out.println();


        // ==========================================
        // DESAFIO 2 - DIVIDE Y VENCERAS
        // ==========================================

        System.out.println(
            "DESAFIO 2 - DIVIDE Y VENCERAS"
        );

        int[] lecturas = {

            418, 732, 156, 894,
            327, 641, 285, 519,
            763, 204, 947, 386,
            675, 128, 856, 493
        };

        int clave2 =
            Desafio2DivideVenceras.encontrarMaximo(
                lecturas,
                0,
                lecturas.length - 1
            );

        System.out.println();

        System.out.println(
            "CLAVE 2: " + clave2
        );

        System.out.println();


        // ==========================================
        // CONTRASEÑA
        // ==========================================

        String contrasena =
            clave1 + "-" + clave2;

        System.out.println(
            "=========================================="
        );

        System.out.println(
            "CONTRASENA DEL SISTEMA:"
        );

        System.out.println(contrasena);

        System.out.println(
            "=========================================="
        );

        System.out.println();


        // ==========================================
        // DESAFIO 3 - BACKTRACKING
        // ==========================================

        System.out.println(
            "DESAFIO 3 - BACKTRACKING"
        );

        char[][] laberinto =
            LaberintoSolver.leerLaberinto();

        if (laberinto.length == 0) {

            System.out.println(
                "No fue posible cargar el laberinto."
            );

            return;
        }

        LaberintoSolver solver =
            new LaberintoSolver(laberinto);

        solver.imprimirLaberinto();

        boolean solucion =
            solver.resolver();

        System.out.println();

        if (solucion) {

            System.out.println(
                "Se encontro una ruta hacia la salida."
            );

        } else {

            System.out.println(
                "No se encontro una ruta hacia la salida."
            );
        }

        solver.imprimirResultado();
    }
}