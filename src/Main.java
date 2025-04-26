import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static String palabra;
    static Character dificultad;
    static Scanner sc = new Scanner(System.in);
    static int intentos;
    static boolean seguir = true;
    static String[] ahorcadoDibujo = {
            """
|---------
|
|
|
|
|""",
            """
|---------
|       O
|
|
|
|""",
            """
|---------
|       O
|       |
|
|
|""",
            """
|---------
|       O
|      /|
|
|
|""",
            """
|---------
|       O
|      /|\\
|
|
|""",
            """
|---------
|       O
|      /|\\
|      /
|
|""",
            """
|---------
|       O
|      /|\\
|      / \\
|
|"""
    };

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
        palabra = getRandomWord();
        System.out.println("Palabra seleccionada:");
        for (int i = 0; i < palabra.length(); i++) {
            System.out.print("_");
        }

        System.out.println("\n");
        System.out.println(ahorcadoDibujo[0]);

        List<Character> letrasUsadas = new ArrayList<>();
        intentos = 0;

        while (seguir) {
            System.out.println("\nPalabra: " + mostrarPalabra(palabra, letrasUsadas));
            System.out.println("Letras usadas: " + letrasUsadas);
            System.out.print("Introduce una letra: ");
            char letra = sc.next().toLowerCase().charAt(0);

            if (letrasUsadas.contains(letra)) {
                System.out.println("Ya usaste esa letra.");
                continue;
            }

            letrasUsadas.add(letra);

            if (!palabra.toLowerCase().contains(String.valueOf(letra))) {
                intentos++;
                System.out.println("Letra incorrecta.");
            } else {
                System.out.println("¡Letra correcta!");
            }

            System.out.println("\n" + ahorcadoDibujo[intentos]);

            if (mostrarPalabra(palabra, letrasUsadas).equalsIgnoreCase(palabra)) {
                System.out.println("\n¡Ganaste! La palabra era: " + palabra);
                seguir = false;
            } else if (intentos == ahorcadoDibujo.length - 1) {
                System.out.println("\n¡Perdiste! La palabra era: " + palabra);
                seguir = false;
            }
        }
    }

    private static Character getRandomDiff() {
        Character[] dificultadArray = {'F', 'M', 'D'};
        return dificultadArray[(new Random()).nextInt(dificultadArray.length)];
    }

    private static String getCompleteDiff(Character diff) {
        return switch (diff) {
            case 'F' -> "Fácil";
            case 'M' -> "Medio";
            case 'D' -> "Difícil";
            default -> diff + "";
        };
    }

    public static String getRandomWord() {
        List<String> palabras = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("./src/palabras.txt"));
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith(dificultad + " ")) {
                    palabras.add(linea.substring(2));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (palabras.isEmpty()) return null;

        return palabras.get(new Random().nextInt(palabras.size()));
    }

    private static String mostrarPalabra(String palabra, List<Character> letrasUsadas) {
        String resultado = "";
        for (char c : palabra.toLowerCase().toCharArray()) {
            if (letrasUsadas.contains(c)) {
                resultado += c;
            } else {
                resultado += "_";
            }
        }
        return resultado;
    }


}
