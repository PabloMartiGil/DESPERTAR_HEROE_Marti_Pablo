import java.util.Scanner;

public class juegoHeroe
{
    // Constantes del Héroe y Enemigos (Apartado 4.3)
    public static final int VIDA_INICIAL_HEROE = 100;
    public static final int POCIONES_INICIALES = 3;
    public static final int CURACION_POCION = 25;
    public static final int DANIO_MIN_HEROE = 10;
    public static final int DANIO_MAX_HEROE = 20;
    
    public static final int[] VIDA_ENEMIGOS = {40, 60, 80};
    public static final int DANIO_MIN_ENEMIGO = 5;
    public static final int DANIO_MAX_ENEMIGO = 15;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do 
        {
            mostrarMenu();
            opcion = elegirOpcion(scanner);
            
            // Limpiar el búfer después de leer un entero para evitar problemas con futuros nextLine()
            scanner.nextLine(); 

            switch (opcion)
            {
                case 1:
                    System.out.println("\n[INFO] Iniciando Nueva Partida...");
                    iniciarPartida(scanner);
                    break;
                case 2:
                    System.out.println("\n[INFO] Consultando Ranking...");
                    // Aquí consultaremos el ranking
                    break;
                case 3:
                    mostrarAyuda();
                    break;
                case 4:
                    System.out.println("\n¡Gracias por jugar! Saliendo del juego...");
                    break;
                default:
                    System.out.println("\n[ERROR] Opción no válida. Introduce un número entre 1 y 4.");
            }
            System.out.println();
        } while (opcion != 4);

        scanner.close();
    }

    // Método estático para mostrar el menú principal
    public static void mostrarMenu()
    {
        System.out.println("==========================================");
        System.out.println("     EL DESPERTAR DEL HÉROE - MENÚ        ");
        System.out.println("==========================================");
        System.out.println("1. Nueva partida");
        System.out.println("2. Consultar ranking");
        System.out.println("3. Ayuda");
        System.out.println("4. Salir");
        System.out.print("Elige una opción: ");
    }

    // Método para comprobar que el número sea entero.
    public static int elegirOpcion(Scanner scanner)
    {
        while (!scanner.hasNextInt())
        {
            System.out.println("[ERROR] Entrada no válida, introduce un número: ");
            scanner.next(); // Limpiar la entrada errónea
        }
        return scanner.nextInt();
    }

    // Método para mostrar la ayuda
    public static void mostrarAyuda()
    {
        System.out.println("==========================================");
        System.out.println("                   AYUDA                  ");
        System.out.println("==========================================");
        System.out.println("Supera los 3 encuentros consecutivos con enemigos.");
        System.out.println("Gestiona tu vida y tus 3 pociones inteligentemente.");
        System.out.println("¡Buena suerte, compañero!");
    }

    // Método para iniciar partida
    public static void iniciarPartida(Scanner scanner)
    {
        String nombreHeroe = "";

        // Control de nombre vacío o con espacios
        while (nombreHeroe.trim().isEmpty())
        {
            System.out.print("Introduce el nombre del héroe: ");
            nombreHeroe = scanner.nextLine();
            
            if (nombreHeroe.trim().isEmpty())
            {
                System.out.println("[ERROR] El nombre no puede estar vacío ni contener solo espacios.");
            }
        }
        
        System.out.println("¡Bienvenido, " + nombreHeroe + "! Comienza la aventura.");
    }
}