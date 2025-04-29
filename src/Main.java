import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static String palabra;
    static Character dificultad;
    static Scanner sc = new Scanner(System.in);
    static int intentos;
    static boolean seguir = true,valido;
    static boolean inputValido= false;
    static int seleccion = 0;
    static char letra = ' ';
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
        while (!inputValido) {
            System.out.println("Bienvenido al ahorcado, selecciona si quieres seleccionar dificultad o una dificultad aleatoria:");
            System.out.println();
            System.out.println("1. Seleccionar dificultad.");
            System.out.println("2. Dificultad aleatoria.");
            System.out.println();
            if(sc.hasNextInt()) {
                seleccion = sc.nextInt();
                inputValido = true;
            }
            else{
                System.out.println("Entrada invalida.Introduce otro numero");
                sc.next();
            }
        }
        if (seleccion == 1) {
            boolean volver = false;
            do {
                System.out.println("Selecciona dificultad:");
                System.out.println();
                System.out.println("0. Volver al menú anterior.");
                System.out.println("1. Fácil.");
                System.out.println("2. Medio.");
                System.out.println("3. Difícil.");
                System.out.println();

                inputValido = false;
                while (!inputValido) {
                    if (sc.hasNextInt()) {
                        seleccion = sc.nextInt();
                        if (seleccion >= 1 && seleccion <= 3) {
                            inputValido = true;
                        } else {
                            System.out.println("Escribe un numero entre 0 y 3");
                        }
                    } else {
                        System.out.println("Entrada invalida.Introduce otro numero");
                        sc.next();
                    }
                }
                switch (seleccion) {
                    case 0->{
                        volver = true;
                        inputValido = false;
                    }
                    case 1 -> dificultad = 'F';
                    case 2 -> dificultad = 'M';
                    case 3 -> dificultad = 'D';
                }
            }while(volver);
        } else {
            dificultad = getRandomDiff();
        }

        System.out.println("Dificultad seleccionada: " + getCompleteDiff(dificultad));
        palabra = getRandomWord();
        //Podemos quitar aqui los acentos y jugar con esa palabra para el match de letras y asi que se visualice con tildes
        //pero se compare sin ellas
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

            do{
                valido=true;
                System.out.print("Introduce una letra: ");
                letra = sc.next().toLowerCase().charAt(0);
                switch (letra){
                    case 'á'-> letra = 'a';
                    case 'é'-> letra = 'e';
                    case 'í'-> letra = 'i';
                    case 'ó'-> letra = 'o';
                    case 'ú'-> letra = 'u';

                }

                if (letra<'a' || letra>'z'){
                    System.out.println("Introduce una letra valida");
                    valido = false;
                }
            }while(!valido);

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
            limpiarPantalla();
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

    private static void limpiarPantalla(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
