import java.util.Random;
import java.util.Scanner;
public class Penjat {


   

    // Scanner estàtic per poder fer-lo servir a tot arreu
    static Scanner scanner = new Scanner(System.in);

    // Dades proporcionades a l'enunciat
    static final String[] PARAULES = {
        "hola", "cadira", "tisores", "riu", "fotografia", "escala",
        "llibre", "ordinador", "ratoli", "armari", "pati", "programa", "columna"
    };

    static final String[] DIBUIXOS = {
        """
           +---+
               |
               |
               |
              ===
        """,
        """
           +---+
           O   |
               |
               |
              ===
        """,
        """
           +---+
           O   |
           |   |
               |
              ===
        """,
        """
           +---+
           O   |
          /|   |
               |
              ===
        """,
        """
           +---+
           O   |
          /|\\  |
               |
              ===
        """,
        """
           +---+
           O   |
          /|\\  |
          /    |
              ===
        """,
        """
           +---+
           O   |
          /|\\  |
          / \\  |
              ===
        """
    };

    // --- Mètode MAIN ---
    public static void main(String[] args) {
        // 1. Escollir paraula
        String paraulaSecreta = obtenirParaula();
        
        // 2. Inicialitzar estat
        char[] estat = inicialitzarEstat(paraulaSecreta);
        
        int errors = 0;
        final int MAX_ERRORS = 6;
        boolean jocAcabat = false;

        System.out.println("BENVINGUT AL JOC DEL PENJAT!");

        // Bucle principal del joc
        while (!jocAcabat) {
            // Mostrar informació
            mostrarNinot(errors);
            mostrarEstat(estat);
            System.out.println("Errors: " + errors + "/" + MAX_ERRORS);

            // Comprovar si hem perdut abans de demanar lletra
            if (errors >= MAX_ERRORS) {
                System.out.println("\nHas perdut! La paraula era: " + paraulaSecreta);
                jocAcabat = true;
            } else if (paraulaCompletada(estat)) {
                System.out.println("\nEnhorabona! Has guanyat!");
                jocAcabat = true;
            } else {
                // Demanar lletra i actualitzar
                char lletra = demanarLletra();
                boolean encert = actualitzarEstat(estat, paraulaSecreta, lletra);

                if (encert) {
                    System.out.println(">> Molt bé! La lletra '" + lletra + "' hi és!");
                } else {
                    System.out.println(">> Fallada! La lletra '" + lletra + "' no hi és.");
                    errors++;
                }
            }
            System.out.println("--------------------------------------------------");
        }
        
        scanner.close(); // Tanquem el scanner al final
    }

    // --- MÈTODES OBLIGATORIS ---

    public static String obtenirParaula() {
        Random rand = new Random();
        int index = rand.nextInt(PARAULES.length);
        return PARAULES[index];
    }

    public static void mostrarNinot(int errors) {
        if (errors >= 0 && errors < DIBUIXOS.length) {
            System.out.println(DIBUIXOS[errors]);
        }
    }

    public static char demanarLletra() {
        char lletra = ' ';
        boolean valida = false;

        while (!valida) {
            System.out.print("Entra una lletra: ");
            String input = scanner.nextLine().toLowerCase();

            // Comprovem que sigui 1 sol caràcter i que sigui una lletra
            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                lletra = input.charAt(0);
                valida = true;
            } else {
                System.out.println("Error: Si us plau, introdueix una única lletra vàlida (a-z).");
            }
        }
        return lletra;
    }

    public static char[] inicialitzarEstat(String paraula) {
        char[] estat = new char[paraula.length()];
        for (int i = 0; i < estat.length; i++) {
            estat[i] = '_';
        }
        return estat;
    }

    public static boolean actualitzarEstat(char[] estat, String paraula, char lletra) {
        boolean trobada = false;
        for (int i = 0; i < paraula.length(); i++) {
            if (paraula.charAt(i) == lletra) {
                estat[i] = lletra; // Destapem la lletra
                trobada = true;
            }
        }
        return trobada;
    }

    public static void mostrarEstat(char[] estat) {
        System.out.print("PARAULA: ");
        for (int i = 0; i < estat.length; i++) {
            System.out.print(estat[i] + " ");
        }
        System.out.println(); // Salt de línia
    }

    public static boolean paraulaCompletada(char[] estat) {
        for (char c : estat) {
            if (c == '_') {
                return false; // Si trobem un guió, no està completa
            }
        }
        return true;
    }
}

    

