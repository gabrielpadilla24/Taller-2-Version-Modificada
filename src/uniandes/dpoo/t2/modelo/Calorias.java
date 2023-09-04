package uniandes.dpoo.t2.modelo;

import java.util.HashMap;
import java.util.Map;

public class Calorias {
    
	 private static Map<String, Integer> calorieMap = new HashMap<>();

	    static {
	        // Hamburguesas
	        calorieMap.put("corral", 650); // calorías
	        calorieMap.put("corral queso", 700); 
	        calorieMap.put("corral pollo", 550);
	        calorieMap.put("corralita", 550);
	        calorieMap.put("todoterreno", 900);
	        calorieMap.put("1/2 libra", 850);
	        calorieMap.put("especial", 800);
	        calorieMap.put("casera", 750);
	        calorieMap.put("mexicana", 780);
	        calorieMap.put("criolla", 730);
	        calorieMap.put("costeña", 720);
	        calorieMap.put("hawaiana", 710);
	        calorieMap.put("wrap de pollo", 500);
	        calorieMap.put("wrap de lomo", 700);
	        calorieMap.put("ensalada mexicana", 350); 
	        
	        // Acompañamientos
	        calorieMap.put("papas medianas", 365);
	        calorieMap.put("papas grandes", 475);
	        calorieMap.put("papas en casco medianas", 365);
	        calorieMap.put("papas en casco grandes", 475);
	        
	        // Bebidas 
	        calorieMap.put("agua cristal sin gas", 0);
	        calorieMap.put("agua cristal con gas", 0);
	        calorieMap.put("gaseosa", 120);

	        // Combos
	        calorieMap.put("combo corral",1135); 
	        calorieMap.put("combo corral queso",1185);
	        calorieMap.put("combo todoterreno", 1495);
	        calorieMap.put("combo especial",1285);
	        
	        // Ingredientes adicionales
	        calorieMap.put("lechuga", 5);       // Por hoja
	        calorieMap.put("tomate", 22);       // Por rodaja
	        calorieMap.put("cebolla", 10);      // Por rodaja
	        calorieMap.put("queso mozzarella", 85);  // Por porción (aproximadamente 28 gramos)
	        calorieMap.put("huevo", 70);        // Por huevo (tamaño mediano)
	        calorieMap.put("queso americano", 105); // Por rebanada
	        calorieMap.put("tocineta express", 42); // Por rebanada
	        calorieMap.put("papa callejera", 160);  // Por porción (aproximadamente 100 gramos)
	        calorieMap.put("pepinillos", 5);    // Por rodaja
	        calorieMap.put("cebolla grille", 7);    // Por porción (aproximadamente 15 gramos)
	        calorieMap.put("suero costeño", 35);    // Por cucharada
	        calorieMap.put("frijol refrito", 90);   // Por cucharada
	        calorieMap.put("queso fundido", 80);    // Por cucharada
	        calorieMap.put("tocineta picada", 200); // Por porción (aproximadamente 28 gramos)
	        calorieMap.put("piña", 28);        // Por rodaja
	    }

	    public static int getCaloriesFor(String ingredientName) {
	        return calorieMap.getOrDefault(ingredientName, 0);  // default to 0 if not found
	    }
	}