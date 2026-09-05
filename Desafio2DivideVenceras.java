public class Desafio2DivideVenceras {

    // Encuentra el máximo mediante Divide y Vencerás
    public static int encontrarMaximo(
            int[] arreglo,
            int inicio,
            int fin) {

        // CASO BASE
        if (inicio == fin) {
            return arreglo[inicio];
        }

        // DIVIDIR
        int medio = (inicio + fin) / 2;

        // Resolver mitad izquierda
        int maxIzquierda =
                encontrarMaximo(arreglo, inicio, medio);

        // Resolver mitad derecha
        int maxDerecha =
                encontrarMaximo(arreglo, medio + 1, fin);

        // COMBINAR
        return Math.max(maxIzquierda, maxDerecha);
    }

    public static void main(String[] args) {

        int[] lecturas = {
            418, 732, 156, 894,
            327, 641, 285, 519,
            763, 204, 947, 386,
            675, 128, 856, 493
        };

        int maximo =
                encontrarMaximo(
                    lecturas,
                    0,
                    lecturas.length - 1
                );

        System.out.println("================================");
        System.out.println("DESAFIO 2 - DIVIDE Y VENCERAS");
        System.out.println("================================");

        System.out.println("Lecturas:");

        for (int lectura : lecturas) {
            System.out.print(lectura + " ");
        }

        System.out.println();
        System.out.println();

        System.out.println("CLAVE 2: " + maximo);
    }
}