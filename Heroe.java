public class Heroe 

{
private String nombre;
private int vida;
private int vidaMaxima;
private int Inventario inventario;

public Heroe(String nombre, int vidaMaxima)
{
    this.nombre = nombre;
    this.vidaMaxima = vidaMaxima;
    this.vida = vidaMaxima;
    this.Inventario = new inventario(5); //capacidad para 5 huecos
}


}
