public class Enemigo 
{
private String nombre;
private int vida;
private int vidaMaxima;
private int danioMinimo;
private int danioMaximo;

//Constructor
public Enemigo(String nombre, int vidaMaxim, int danioMinimo, int danioMaximo)
    {
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxim;
        this.vida = vidaMaxim;
        this.danioMinimo = danioMinimo;
        this.danioMaximo = danioMaximo;
    }

//Método para calcular el ataque aleatorio del enemigo
public int atacar()
    {
        return (int) (Math.random() * (danioMaximo - danioMinimo + 1)) +danioMinimo;
    }
    
//Metodo pra recibir daño
public void recibirDanio(int cantidad)
    {
        this.vida -= cantidad;
        if (this.vida < 0)
        {
            this.vida = 0;
        }
    }

    public boolean estaDerrotado()
    {
        return this.vida <= 0;
    }


    //Getters y Setters
    public String getNombre()
        {
            return nombre;
        }

    public int getVida()
        {
            return vida;
        }
    
    public void mostrarEstadoEnemigo()
        {
            System.out.println("Enemigo: " + nombre + "| Vida: " + vida + "/" + vidaMaxima);
        }


}

