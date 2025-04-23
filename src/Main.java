import java.util.Random;
import java.util.Scanner;

public class Main {

    static String palabra;
    static String dificultad;
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
                case 1:
                    dificultad = "F";
                    break;
                case 2:
                    dificultad = "M";
                    break;
                case 3:
                    dificultad = "D";
                    break;
            }
        } else {
            dificultad = getRandomDiff();
        }

        System.out.println("Dificultad seleccionada: " + getCompleteDiff(dificultad));
    }

    private static String getRandomDiff() {
        String[] dificultadArray = {"F", "M", "D"};
        return dificultadArray[(new Random()).nextInt(dificultadArray.length)]; // devolver un elemento aleatorio del array
    }

    private static String getCompleteDiff(String diff) {
        switch (diff) {
            case "F":
                return "Fácil";
            case "M":
                return "Medio";
            case "D":
                return "Difícil";
        }
        return diff;
    }
}