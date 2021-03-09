/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenfinal.antonio.anet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author usuario
 */
public class Plato {
    
    String nombrePlato;
    String descripcion;
    String ingrediente;
    Float precio;
    HashSet<Ingrediente> listaIngredientes;

    
    public Plato(String nombrePlato, String descripcion, String ingrediente, Float precio, HashSet listaIngredientes) {
        this.nombrePlato = nombrePlato;
        this.descripcion = descripcion;
        this.ingrediente = ingrediente;
        this.precio = precio;
        this.listaIngredientes = listaIngredientes;
    }

    
    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public HashSet getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(HashSet listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }
    
    
    @Override
    public String toString() {
        return "Plato{" + "nombrePlato=" + nombrePlato + ", descripcion=" + descripcion + ", ingrediente=" + ingrediente + ", precio=" + precio + ", listaIngredientes=" + listaIngredientes + '}';
    }
    
    void listaPlatos(){
    
        String listado = ("( "+ ingrediente + " ");

        for (Ingrediente ingr : listaIngredientes) {
            
            listado = listado + ingr.getNombre() + " ";
            
        }
        
        listado = listado + ")" + "\t Precio " + precio;
        System.out.println(listado);
    
    }
    
    void listarPlatos(){
    
        System.out.print("Plato: " + nombrePlato + "\t");
        System.out.println("Preparacion: " + descripcion);
        System.out.println("Precio: " + precio);
        System.out.println("Ingrediente principal: " + ingrediente);

        System.out.print("Ingredientes: ");
        
        for (Ingrediente listaIngrediente : listaIngredientes) {
            
            System.out.print(listaIngrediente.getNombre() + " ");
            
        }
        
        System.out.println("\n");
        
    }

    void listaPlatoDetalle(){
    
        System.out.println("Plato = " + nombrePlato);
        System.out.println("\tPreparacion = " + descripcion);
        System.out.println("\tIngrediente Principal = " + ingrediente);
        System.out.println("\tPropiedades Nutricionales: ");
        //System.out.println(ListaIngredientes().);
   
        
        Ingrediente ingr = ExamenFinalAntonioAnet.listaIngredientes.get(ingrediente);
        
        ingr.listarPropiedades();
        
        System.out.println("\tResto Ingredientes....: "); 
        
        HashSet hs = listaIngredientes;
        
        Iterator<Ingrediente> iterator = hs.iterator();
        
        while (iterator.hasNext()) {
            
            Ingrediente ingr2 = iterator.next();
            
            System.out.println("\t\t" + ingr2.nombre + "[" + ingr2.getOrigen() + "]");
            
        }
        
        System.out.println("----------------------------------------------------");
        System.out.println("Temporada Aconsejada: " + temporada());
        System.out.println("Precio plato : " + precio);
        System.out.println("----------------------------------------------------");
        
    }
    
    String temporada(){
    
        String estacion = null;
        
        int n = (int) Math.floor(Math.random()*(4-0+1)+0);
    
        switch(n){
            
            case 0: estacion = "Todas";
            break;
            
            case 1: estacion = "Primavera";
            break;
            
            case 2: estacion = "Verano";
            break;
            
            case 3: estacion = "Otoño";
            break;
            
            case 4: estacion = "Invierno";
            break;
        
        }
        
        return estacion;
        
    }
    
}
