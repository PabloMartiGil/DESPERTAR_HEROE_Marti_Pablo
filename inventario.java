public class Inventario 

{
    private String[] items;
    private int[] cantidades;
    private int capacidadMaxima;

    // Constructor
    public Inventario(int capacidadMaxima)
    {
        this.capacidadMaxima = capacidadMaxima;
        this.items = new String[capacidadMaxima];
        this.cantidades = new int[capacidadMaxima];

        //inicializamos con una posición or defecto para el heroe
        this.items[0] = "Poción de Vida";
        this.cantidades[0] = 3; // Cantidad inicial de pociones
    }

    //Método para mostrar el inventario
    public void mostrarInventario()
    {
        System.out.println("==========================================");
        System.out.println("           INVENTARIO DEL HÉROE              ");
        System.out.println("==========================================");
        boolean vacio = true;
        for (int i = 0; i < capacidadMaxima; i++)
        {
            if (items[i] != null && cantidades[i] > 0)
            {
                System.out.println((i + 1) + ". " + items[i] + " - Cantidad: " + cantidades[i]);
                vacio = false;
            }
        }
        if (vacio)
        {
            System.out.println("El inventario está vacío.");
        }
    }
    // Método para usar un posición del inventario
    public boolean usarPocion()
    {
        for (int i =0; i < capacidadMaxima; i++)
        {
            if (items[i] != null && items[i].equalsIgnoreCase("Pocion de vida")) && cantidades[i] + "unidades" > 0)
            {
                cantidades[i]--;
                System.out.println("Has usado una poción de vida. Te quedan " + cantidades[i] + " unidades.");
                return true; // Pocion usada con éxito
            }
        }
        return false; // No quedan pociones
    }
    // Método para consultar si quedan pociones
    public int obtenerCantidadPociones()
    {
        for (int i = 0; i < capacidadMaxima; i++)
        {
            if (items[i] != null && items[i].equalsIgnoreCase("Pocion de vida"))
            {
                return cantidades[i];
            }
        }
        return 0; // No quedan pociones
    }


}
