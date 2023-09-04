package uniandes.dpoo.t2.modelo;

import java.util.ArrayList;
import java.util.List;

public class Combo {
    private String nombreCombo;
    private int precio;
    private List<ProductoMenu> productos;
    private Bebida bebida; 


    public Combo(String nombreCombo, double descuento, int precio) {
        this.nombreCombo = nombreCombo;
        this.precio = precio;
        this.productos = new ArrayList<>();  // Inicializa productos aquí

    }

    
    public void setProductos(List<ProductoMenu> productos) {
        this.productos = productos;
    }

    public int getPrecio() {
    	return precio;
    }

    public String getNombre() {
        return nombreCombo;
    }
    
    public int getCalorias() {
        return Calorias.getCaloriesFor(this.nombreCombo);
    }
    
    public String toString() {
        return nombreCombo + " - $" + getPrecio();
    }
    public String generarTextoFactura() {
        return "Combo: " + this.getNombre() + " - $" + this.getPrecio();
    }
}
