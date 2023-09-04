package uniandes.dpoo.t2.modelo;

public class Bebida {
    private String nombre;
    private int precio; // Cambiado el tipo de dato a int



    public Bebida(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() { // Cambiado el tipo de retorno a int
        return precio;
    }
    
    public int getCalorias() {
        return Calorias.getCaloriesFor(this.nombre);
    }


    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }
    
    public String generarTextoFactura() {
        return this.getNombre() + " - $" + this.getPrecio();
    }
}
