package uniandes.dpoo.t2.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurante {
    private List<ProductoMenu> menu;
    private List<Ingrediente> ingredientes;
    private List<Combo> combos;
    private Map<Integer, Pedido> pedidos; // Almacenar todos los pedidos con ID como clave
    private List<Combo> menuCombos;  // Lista de todos los combos
    private List<Bebida> bebidas;




    public Restaurante() {
        cargarMenu(new File("data/menu.txt"));
        cargarIngredientes(new File("data/ingredientes.txt"));
        cargarCombos(new File("data/combos.txt"));
        cargarBebidas(new File("data/bebidas.txt"));
        pedidos = new HashMap<>();
        

    }


    private void cargarMenu(File archivoMenu) {
        menu = new ArrayList<>(); // Inicializamos el menú aquí
        try (BufferedReader br = new BufferedReader(new FileReader(archivoMenu))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";"); 
                ProductoMenu producto = new ProductoMenu(partes[0].trim(), Integer.parseInt(partes[1].trim()));
                menu.add(producto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void cargarIngredientes(File archivoIngredientes) {
        ingredientes = new ArrayList<>(); // Inicializamos el menú aquí
        try (BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";"); 
                Ingrediente ingrediente = new Ingrediente(partes[0].trim(), Integer.parseInt(partes[1].trim()));
                ingredientes.add(ingrediente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void cargarCombos(File archivoCombo) {
        combos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCombo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");

                double descuento = Double.parseDouble(partes[1].trim().replace("%", ""));
                
                int precioTotal = 0;
                for (int i = 2; i < partes.length; i++) {
                    ProductoMenu producto = buscarProducto(partes[i].trim());
                    if (producto != null) {
                        precioTotal += producto.getPrecio();
                    }
                }
                
                int precioConDescuento = precioTotal - (int) (descuento / 100.0 * precioTotal);

                Combo combo = new Combo(partes[0].trim(), descuento, precioConDescuento);
                combos.add(combo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void cargarBebidas(File archivoBebidas) {
        bebidas = new ArrayList<>(); // Inicializamos el menú aquí
        try (BufferedReader br = new BufferedReader(new FileReader(archivoBebidas))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";"); 
                Bebida bebida = new Bebida(partes[0].trim(), Integer.parseInt(partes[1].trim()));
                bebidas.add(bebida);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    



    private ProductoMenu buscarProducto(String nombre) {
        for (ProductoMenu producto : menu) {
            if (producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        return null;
    }

    public int iniciarPedido(String nombreCliente, String direccionCliente) {
        Pedido nuevoPedido = new Pedido(nombreCliente, direccionCliente);
        pedidos.put(nuevoPedido.getIdPedido(), nuevoPedido); // Agregamos el pedido al map
        return nuevoPedido.getIdPedido();
    }
    
    public void agregarProductoAPedido(int idPedido, ProductoMenu producto) {
        Pedido pedido = pedidos.get(idPedido);
        if (pedido != null && !pedido.estaCerrado()) {
            pedido.agregarProducto(producto);
        }
    }
    
    public void agregarComboAPedido(int idPedido, Combo combo) {
        Pedido pedido = pedidos.get(idPedido);
        if (pedido != null && !pedido.estaCerrado()) {
            // Aquí puedes agregar el Combo al Pedido, similar a como se agregan productos.
            // Esto implica que tendrás que extender la clase Pedido para manejar combos.
            pedido.agregarCombo(combo);
        }
    }
    
    public void cerrarYGuardarPedido(int idPedido) {
        Pedido pedido = pedidos.get(idPedido);
        if (pedido != null) {
            pedido.cerrar();
            
            if (existePedidoIdentico(pedido)) {
                System.out.println("Hay una coincidencia en el pedido.");
            } else {
                System.out.println("No hay ningún pedido idéntico.");
            }
                
            System.out.println("Pedido con ID " + idPedido + " cerrado y guardado con éxito.");
        } else {
            System.out.println("No se encontró un pedido con el ID especificado.");
        }
    }


    
    public Pedido getPedidoPorId(int idPedido) {
        return pedidos.get(idPedido);
    }
    


    public ArrayList<ProductoMenu> getMenuBase() {
        return new ArrayList<>(this.menu);  // Convertimos la lista a un ArrayList específicamente.
    }
    
    
    public List<Combo> getMenuCombos() {
        return menuCombos;
    }
    
    public ArrayList<Ingrediente> getIngredientes() {
    	return new ArrayList<>(this.ingredientes);
    }
    
    public ArrayList<Combo> getCombos(){
    	return new ArrayList<>(this.combos);
    }
    
    public ArrayList<Bebida> getBebidas(){
    	return new ArrayList<>(this.bebidas);
    }
    


    private String generarFacturaComoString(Pedido pedido) {
        StringBuilder factura = new StringBuilder();
        factura.append("Factura Pedido: ").append(pedido.getIdPedido()).append("\n");
        factura.append("Cliente: ").append(pedido.getNombreCliente()).append("\n");
        factura.append("Dirección: ").append(pedido.getDireccionCliente()).append("\n");
        factura.append("Productos:\n");
        
        for (ProductoMenu producto : pedido.getProductos()) {
            factura.append(producto.toString()).append("\n"); 
        }
        for (Combo combo : pedido.getCombos()) {
            factura.append("Combo: ").append(combo.toString()).append("\n");
        }
        return factura.toString();
    }

    private boolean existePedidoIdentico(Pedido pedidoActual) {
        String facturaActual = generarFacturaComoString(pedidoActual);
        
        try (BufferedReader br = new BufferedReader(new FileReader("factura_1.txt"))) {
            StringBuilder facturaLeida = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Factura Pedido:")) {
                    if (facturaLeida.length() > 0) { 
                        if (facturaActual.equals(facturaLeida.toString())) {
                            return true;
                        }
                        facturaLeida.setLength(0); 
                    }
                }
                facturaLeida.append(linea).append("\n");
            }
            if (facturaActual.equals(facturaLeida.toString())) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    
}

