public class Personaje {
   protected String nombre;
   protected int vida;
   protected int vidaMaxima;

   public Personaje(String var1, int var2) {
      this.nombre = var1;
      this.vidaMaxima = var2;
      this.vida = var2;
   }

   public void recibirDanio(int var1) {
      this.vida -= var1;
      if (this.vida < 0) {
         this.vida = 0;
      }

   }

   public boolean estaDerrotado() {
      return this.vida <= 0;
   }

   public String getNombre() {
      return this.nombre;
   }

   public int getVida() {
      return this.vida;
   }

   public int getVidaMaxima() {
      return this.vidaMaxima;
   }
}

