import java.util.Random;
import java.util.Scanner;

public class Main {

    static String palabra;
    static Character dificultad;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bienvenido al ahorcado, selecciona si quieres seleccionar dificultad o una dificultad aleatoria:");
        System.out.println();
        System.out.println("1. Seleccionar dificultad.");
        System.out.println("2. Dificultad aleatoria.");
        System.out.println();

        int seleccion = sc.nextInt();
        if (seleccion == 1) {
            System.out.println("Selecciona dificultad:");
            System.out.println();
            System.out.println("1. Fácil.");
            System.out.println("2. Medio.");
            System.out.println("3. Difícil.");
            System.out.println();

            seleccion = sc.nextInt();

            switch (seleccion) {
                case 1 -> dificultad = 'F';
                case 2 -> dificultad = 'M';
                case 3 -> dificultad = 'D';
            }
        } else {
            dificultad = getRandomDiff();
        }

        System.out.println("Dificultad seleccionada: " + getCompleteDiff(dificultad));
    }

    private static Character getRandomDiff() {
        Character[] dificultadArray = {'F', 'M', 'D'};
        return dificultadArray[(new Random()).nextInt(dificultadArray.length)]; // devolver un elemento aleatorio del array
    }

    private static String getCompleteDiff(Character diff) {
        return switch (diff) {
            case 'F' -> "Fácil";
            case 'M' -> "Medio";
            case 'D' -> "Difícil";
            default -> diff + "";
        };
    }
}