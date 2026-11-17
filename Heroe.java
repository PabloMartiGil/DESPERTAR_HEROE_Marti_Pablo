public class Heroe extends Personaje {
   private inventario inventario = new inventario(5);

   public Heroe(String var1, int var2) {
      super(var1, var2);
   }

   public inventario getInventario() {
      return this.inventario;
   }

   public void mostrarEstado() {
      System.out.println("Héroe: " + this.nombre + " | Vida: " + this.vida + "/" + this.vidaMaxima);
      this.inventario.mostrarInventario();
   }
}
