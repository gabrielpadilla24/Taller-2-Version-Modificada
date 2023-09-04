package uniandes.dpoo.t2.modelo;

public class Ingrediente {
    private String nombre;
    private int costoAdicional; // Cambiado a int y renombrado



    public Ingrediente(String nombre, int costoAdicional) {
        this.nombre = nombre;
        this.costoAdicional = costoAdicional;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() { // Cambiado a int y renombrado
        return costoAdicional;
    }
    
    public int getCalorias() {
        return Calorias.getCaloriesFor(this.nombre);
    }


    @Override
    public String toString() {
        return nombre + " - $" + costoAdicional; // Cambiado a precioBase
    }
}
