public class inventario {
   private String[] items;
   private int[] cantidades;
   private int capacidadMaxima;

   public inventario(int var1) {
      this.capacidadMaxima = var1;
      this.items = new String[var1];
      this.cantidades = new int[var1];
      this.items[0] = "Pocion de vida";
      this.cantidades[0] = 3;
   }

   public void mostrarInventario() {
      System.out.println("==========================================");
      System.out.println("           INVENTARIO DEL HÉROE              ");
      System.out.println("==========================================");
      boolean var1 = true;

      for(int var2 = 0; var2 < this.capacidadMaxima; ++var2) {
         if (this.items[var2] != null && this.cantidades[var2] > 0) {
            System.out.println(var2 + 1 + ". " + this.items[var2] + " - Cantidad: " + this.cantidades[var2]);
            var1 = false;
         }
      }

      if (var1) {
         System.out.println("El inventario está vacío.");
      }

   }

   public boolean usarPocion() {
      for(int var1 = 0; var1 < this.capacidadMaxima; ++var1) {
         if (this.items[var1] != null && this.items[var1].equalsIgnoreCase("Pocion de vida") && this.cantidades[var1] > 0) {
            int var10002 = this.cantidades[var1]--;
            int var10001 = this.cantidades[var1];
            System.out.println("Has usado una poción de vida. Te quedan " + var10001 + " unidades.");
            return true;
         }
      }

      return false;
   }

   public int obtenerCantidadPociones() {
      for(int var1 = 0; var1 < this.capacidadMaxima; ++var1) {
         if (this.items[var1] != null && this.items[var1].equalsIgnoreCase("Pocion de vida")) {
            return this.cantidades[var1];
         }
      }

      return 0;
   }
}

