public class Desafio1Recursividad {

    // Reduce un número hasta obtener un solo dígito
    public static int reducirNumero(int numero) {

        numero = Math.abs(numero);

        // CASO BASE
        if (numero < 10) {
            return numero;
        }

        // CASO RECURSIVO
        int suma = sumarDigitos(numero);

        return reducirNumero(suma);
    }

    // Suma los dígitos utilizando recursividad
    private static int sumarDigitos(int numero) {

        // CASO BASE
        if (numero == 0) {
            return 0;
        }

        // CASO RECURSIVO
        return (numero % 10) + sumarDigitos(numero / 10);
    }

    public static void main(String[] args) {

        int numero1 = 84729;
        int numero2 = 56318;
        int numero3 = 92746;

        int resultado1 = reducirNumero(numero1);
        int resultado2 = reducirNumero(numero2);
        int resultado3 = reducirNumero(numero3);

        System.out.println("================================");
        System.out.println("DESAFIO 1 - RECURSIVIDAD");
        System.out.println("================================");

        System.out.println(numero1 + " -> " + resultado1);
        System.out.println(numero2 + " -> " + resultado2);
        System.out.println(numero3 + " -> " + resultado3);

        String clave1 =
                "" + resultado1 + resultado2 + resultado3;

        System.out.println();
        System.out.println("CLAVE 1: " + clave1);
    }
}