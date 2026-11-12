import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class juegoHeroe
{
    // Constantes del Héroe y Enemigos 
    public static final int VIDA_INICIAL_HEROE = 100;
    public static final int POCIONES_INICIALES = 3;
    public static final int CURACION_POCION = 30;
    public static final int DANIO_MIN_HEROE = 10;
    public static final int DANIO_MAX_HEROE = 30;
    
    public static final int[] VIDA_ENEMIGOS = {40, 60, 80};
    public static final int DANIO_MIN_ENEMIGO = 10;
    public static final int DANIO_MAX_ENEMIGO = 35;

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
                    mostrarRanking();
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
        System.out.println("         EL DESPERTAR DEL HÉROE"           );
        System.out.println("==========================================");
        System.out.println("----------- MENÚ PRINCIPAL -----------");
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

        // Inicializar estado de partida
        int vidaHeroe = VIDA_INICIAL_HEROE;
        int pociones = POCIONES_INICIALES;
        boolean victoriaTotal = true;

        // Bucle de los 3 encuentros consecutivos
        for (int i = 0; i < VIDA_ENEMIGOS.length; i++)
        {
            int vidaEnemigo = VIDA_ENEMIGOS[i];
            System.out.println("------------------------------------------");
            System.out.println("ENEMIGO " + (i + 1) + " DE 3 contra un Enemigo (Vida: " + vidaEnemigo + ")");

            int[] estadoHeroe = {vidaHeroe, pociones};
            boolean resultadoCombate = simularCombate(scanner, estadoHeroe, vidaEnemigo);
            vidaHeroe = estadoHeroe[0];
            pociones = estadoHeroe[1];

            // Si el combate devuelve falso, el héroe murió o abandonó
            if (!resultadoCombate)
            {
                victoriaTotal = false;
                break; // Salir del bucle de encuentros
            }
        }
        
        if (victoriaTotal)
        {
            System.out.println("¡ENHORABUENA! Has derrotado a los tres enemigos y completado la aventura con éxito.");
            int puntuacion = (VIDA_ENEMIGOS.length * 100) + vidaHeroe;
            guardarEnRanking(nombreHeroe.trim(), puntuacion);
        }
        else
        {
            System.out.println("Partida terminada, has sido derrotado o has abandonado la aventura.");
        }
    }

    // Método para mostrar el ranking
    public static void mostrarRanking()
    {
        try
        {
            File archivo = new File("ranking.xml");
            if (!archivo.exists() || archivo.length() == 0)
            {
                System.out.println("El ranking está vacío.");
                return;
            }

            Document documento = crearFabricaDocumentos().newDocumentBuilder().parse(archivo);
            NodeList jugadores = documento.getElementsByTagName("jugador");
            if (jugadores.getLength() == 0)
            {
                System.out.println("El ranking está vacío.");
                return;
            }

            System.out.println("==========================================");
            System.out.println("                 RANKING                  ");
            System.out.println("==========================================");
            for (int i = 0; i < jugadores.getLength(); i++)
            {
                Element jugador = (Element) jugadores.item(i);
                String nombre = jugador.getElementsByTagName("nombre").item(0).getTextContent();
                String puntuacion = jugador.getElementsByTagName("puntuacion").item(0).getTextContent();
                System.out.println((i + 1) + ". " + nombre + " - " + puntuacion + " puntos");
            }
        }
        catch (Exception e)
        {
            System.out.println("[ERROR] No se ha podido leer el ranking: " + e.getMessage());
        }
    }

    // Método para guardar en el ranking
    public static void guardarEnRanking(String nombre, int puntuacion)
    {
        try
        {
            Document documento;
            File archivo = new File("ranking.xml");

            if (archivo.exists() && archivo.length() > 0)
            {
                documento = crearFabricaDocumentos().newDocumentBuilder().parse(archivo);
            }
            else
            {
                documento = crearFabricaDocumentos().newDocumentBuilder().newDocument();
                documento.appendChild(documento.createElement("ranking"));
            }

            Element ranking = documento.getDocumentElement();
            Element jugador = documento.createElement("jugador");
            Element nombreElemento = documento.createElement("nombre");
            nombreElemento.setTextContent(nombre);
            Element puntuacionElemento = documento.createElement("puntuacion");
            puntuacionElemento.setTextContent(String.valueOf(puntuacion));
            jugador.appendChild(nombreElemento);
            jugador.appendChild(puntuacionElemento);
            ranking.appendChild(jugador);

            List<Element> jugadores = new ArrayList<>();
            NodeList nodos = ranking.getElementsByTagName("jugador");
            for (int i = 0; i < nodos.getLength(); i++)
            {
                jugadores.add((Element) nodos.item(i));
            }
            jugadores.sort(Comparator.comparingInt((Element j) -> Integer.parseInt(
                    j.getElementsByTagName("puntuacion").item(0).getTextContent())).reversed());

            while (jugadores.size() > 10)
            {
                Element ultimo = jugadores.remove(jugadores.size() - 1);
                ranking.removeChild(ultimo);
            }
            for (Element entrada : jugadores)
            {
                ranking.appendChild(entrada);
            }

            Transformer transformador = TransformerFactory.newInstance().newTransformer();
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformador.transform(new DOMSource(documento), new StreamResult(archivo));
            System.out.println("Tu puntuación se ha guardado en el ranking.");
        }
        catch (Exception e)
        {
            System.out.println("[ERROR] No se ha podido guardar el ranking: " + e.getMessage());
        }
    }

    // Método auxiliar para la fábrica de documentos DOM
    private static DocumentBuilderFactory crearFabricaDocumentos()
    {
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        fabrica.setIgnoringElementContentWhitespace(true);
        return fabrica;
    }

    // Método para simular el combate entre el héroe y un enemigo
    public static boolean simularCombate(Scanner scanner, int[] estadoHeroe, int vidaE) 
    {
        int vidaH = estadoHeroe[0];
        int poc = estadoHeroe[1];

        while (vidaH > 0 && vidaE > 0)
        {
            System.out.println("\nEstado actual -> Tu Vida: " + vidaH + " | Pociones: " + poc + " | Vida Enemigo: " + vidaE);
            System.out.println("1. Atacar");
            System.out.println("2. Usar poción");
            System.out.println("3. Consultar inventario");
            System.out.println("4. Abandonar partida");
            System.out.print("Elige una acción de combate: ");
            int opcion = elegirOpcion(scanner);
            scanner.nextLine();

            switch (opcion)
            {
                case 1: // Atacar
                    int danioHeroe = (int) (Math.random() * (DANIO_MAX_HEROE - DANIO_MIN_HEROE + 1)) + DANIO_MIN_HEROE;
                    vidaE -= danioHeroe;
                    System.out.println("Has atacado al enemigo y le has causado " + danioHeroe + " de daño.");

                    if (vidaE <= 0)
                    {
                        System.out.println("¡Has derrotado al enemigo!");
                        estadoHeroe[0] = vidaH;
                        estadoHeroe[1] = poc;
                        return true; // El héroe gana el combate
                    }

                    // Contraataque del enemigo
                    int danioEnemigo = (int) (Math.random() * (DANIO_MAX_ENEMIGO - DANIO_MIN_ENEMIGO + 1)) + DANIO_MIN_ENEMIGO;
                    vidaH -= danioEnemigo;
                    System.out.println("El enemigo contraataca y te quita " + danioEnemigo + " puntos de daño.");
                    break;

                case 2: // Usar poción
                    if (poc > 0 && vidaH < VIDA_INICIAL_HEROE) 
                    {
                        vidaH += CURACION_POCION;
                        if (vidaH > VIDA_INICIAL_HEROE) {
                            vidaH = VIDA_INICIAL_HEROE;
                        }
                        poc--;
                       
                        System.out.println("Has usado una poción. Tu vida actual es: " + vidaH);
                    } 
                    else if (poc <= 0) 
                    {
                        System.out.println("No quedan pociones disponibles.");
                    } 
                    else 
                    {
                        System.out.println("Ya tienes la vida al máximo.");
                    }
                    break;

                case 3: // Consultar inventario
                    System.out.println("Inventario: Tienes " + poc + " pociones restantes.");
                    break;
                
                case 4: // Abandonar partida
                    System.out.println("Has decidido abandonar la partida.");
                    estadoHeroe[0] = vidaH;
                    estadoHeroe[1] = poc;
                    return false; // El héroe abandona el combate
                    
                default: 
                    System.out.println("[ERROR] Opción inválida. Introduce un número del 1 al 4.");
            }

            if (vidaH <= 0)
            {
                System.out.println("Te has quedado sin vida. Has sido derrotado.");
                estadoHeroe[0] = vidaH;
                estadoHeroe[1] = poc;
                return false; // El héroe pierde el combate
            }
        }

        estadoHeroe[0] = vidaH;
        estadoHeroe[1] = poc;
        return vidaH > 0;
    }
}