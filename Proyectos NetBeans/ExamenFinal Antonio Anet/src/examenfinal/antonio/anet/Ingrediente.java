/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenfinal.antonio.anet;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author usuario
 */
public class Ingrediente {
    
    String nombre;
    String origen;
    float precioKilo;
    HashMap<String, Integer> listaNutricional;

    public Ingrediente(String nombre, String origen, float precioKilo, HashMap<String, Integer> listaNutricional) {
        this.nombre = nombre;
        this.origen = origen;
        this.precioKilo = precioKilo;
        this.listaNutricional = listaNutricional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public float getPrecioKilo() {
        return precioKilo;
    }

    public void setPrecioKilo(float precioKilo) {
        this.precioKilo = precioKilo;
    }

    public HashMap<String, Integer> getListaNutricional() {
        return listaNutricional;
    }

    public void setListaNutricional(HashMap<String, Integer> listaNutricional) {
        this.listaNutricional = listaNutricional;
    }

    @Override
    public String toString() {
        return "Ingrediente{" + "nombre=" + nombre + ", origen=" + origen + ", precioKilo=" + precioKilo + ", listaNutricional=" + listaNutricional + '}';
    }
    
    
    void listaIngrediente(){
    
        System.out.println(nombre + ":");
        System.out.println("\tOrigen: " + origen);
        System.out.println("\tPrecio: " + precioKilo);
        listarPropiedades();
    
    }
    
    void listaIngrediente2(){
    
        System.out.println(nombre + ":");
        System.out.println("Origen: " + origen);
        System.out.println("Precio: " + precioKilo);
        listarPropiedades();
    
    }
    
    
    void listarPropiedades(){
    
        Set<String> set1 = listaNutricional.keySet();
        
        for(String key : set1) {
            
          
            
            System.out.print("\t\t1.- "  + key  );
            System.out.println("("  + listaNutricional.get(key) + ")");
        
        }
    
    }
    
}
