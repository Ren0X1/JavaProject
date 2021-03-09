/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenfinal.antonio.anet;

import static examenfinal.antonio.anet.ExamenFinalAntonioAnet.listaIngredientes;
import static examenfinal.antonio.anet.ExamenFinalAntonioAnet.listaPlatos;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import utils78.ES;

/**
 *
 * @author usuario
 */
public class Restaurante {
    
    static String nombreRestaurante;
    static String nombreCocineroJefe;
    static TreeMap listaPlatos;
    static HashMap ingredientes;

    public Restaurante(String nombreRestaurante, String nombreCocineroJefe) {
        
        this.nombreRestaurante = nombreRestaurante;
        this.nombreCocineroJefe = nombreCocineroJefe;
        
    }

    
    public Restaurante(String nombreRestaurante, String nombreCocineroJefe, TreeMap listaMenu, HashMap ingredientes) {
        this.nombreRestaurante = nombreRestaurante;
        this.nombreCocineroJefe = nombreCocineroJefe;
        this.listaPlatos = listaPlatos;
        this.ingredientes = ingredientes;
    }

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public String getNombreCocineroJefe() {
        return nombreCocineroJefe;
    }

    public void setNombreCocineroJefe(String nombreCocineroJefe) {
        this.nombreCocineroJefe = nombreCocineroJefe;
    }

    public TreeMap getListaMenu() {
        return listaPlatos;
    }

    public void setListaMenu(TreeMap listaMenu) {
        this.listaPlatos = listaMenu;
    }

    public static TreeMap getListaPlatos() {
        return listaPlatos;
    }

    public static void setListaPlatos(TreeMap listaPlatos) {
        Restaurante.listaPlatos = listaPlatos;
    }

    public static HashMap getIngredientes() {
        return ingredientes;
    }

    public static void setIngredientes(HashMap ingredientes) {
        Restaurante.ingredientes = ingredientes;
    }

   

    
    @Override
    public String toString() {
        return "Restaurante{" + "nombreRestaurante=" + nombreRestaurante + ", nombreCocineroJefe=" + nombreCocineroJefe + ", listaMenu=" + listaPlatos + ", ingredientes=" + ingredientes + '}';
    }

    
    /*static void CreaPlatos(){
    
        HashSet lIngredientes = new HashSet();
        
        String nombrePlato = ES.leeDeTeclado("Introduzca el nombre del plato: ");
        String descripcion = ES.leeDeTeclado("Introduzca una breve descripcion de la preparacion del plato: ");
        
        Set<String> set1 = listaIngredientes.keySet();
 
        for(String key : set1) {
            
            System.out.print( key + "\t\t");
                    
        }
        
        String ingrediente = ES.leeDeTeclado("Introduzca el ingrediente principal del plato ");
        
        while (listaIngredientes.containsKey(ingrediente)) {            
        
            lIngredientes.add(listaIngredientes.get(ingrediente));
            
        }
        
        String respuesta = null;
        
        while(respuesta.equals("")){
        
            respuesta = ES.leeDeTeclado("Introduzca otro ingrediente que conforme el plato. Pulse enter para terminar");
            
             lIngredientes.add(listaIngredientes.get(ingrediente));
        }
        
        String precioPlatoS = ES.leeDeTeclado("Introduzca el precio del plato: ");
        
        float precioPlato = Float.parseFloat(precioPlatoS);
    
        Plato plato = new Plato(nombrePlato, descripcion, ingrediente, precioPlato, lIngredientes);
        
        guardaPlatostxt(nombrePlato, descripcion, ingrediente, precioPlatoS, lIngredientes);
    
    }
    
    
    static void guardaPlatostxt(String nombrePlato, String descripcion, String nombreIngredienteP, String precioPlato, HashSet<Ingrediente> lIngredientes){
    
            FileWriter fw = null;
                
                try {
        
                    fw = new FileWriter("src/Datos/Plato.txt",true);
                    PrintWriter pw = new PrintWriter(fw);
            
                    String lplato = nombrePlato + "," + descripcion + "," + nombreIngredienteP + "," + precioPlato + "/";

                    
                    for (Ingrediente ingr : lIngredientes) {
            
                        lplato = lplato + ingr.getNombre() + "/";
            
                    }
                    
                    pw.print(lplato + "");
            
                    fw.close();
                    
            
                } catch (FileNotFoundException ex) {
            
                    System.out.println("Archivo no encontrado... " + ex);
            
                } catch (IOException ex) {
            
                    System.out.println("Fallo al escribir... " + ex);
            
                }
                
        }
    
    
    
    static void CreaIngredientes(){
    
        HashMap<String, Integer> listaNutricional = new HashMap<>();
        
        String nombre = ES.leeDeTeclado("Introduzca el nombre del ingrediente: ");
        String origen = ES.leeDeTeclado("Introduzca el pueblo de origen: ");
        String precioKiloS = ES.leeDeTeclado("Introduzca el precio por kilo del ingrediente: ");
        
        float precioKilo = Float.parseFloat(precioKiloS);
        
        int caloria = ES.leeN("Introduzca la cantidad de calorías que posee por cada 100gr de " + nombre + ": ");
        int grasa = ES.leeN("Introduzca la cantidad de grasa que posee por cada 100gr de " + nombre + ": ");
        
        listaNutricional.put("Caloria", caloria);
        listaNutricional.put("Grasa", grasa);
        
        String respuesta = ES.leeDeTeclado("Quiere añadir alguna caracteristica nutricional mas? (S = Si; N = No): ");
        
        if(respuesta.equalsIgnoreCase(respuesta)){
        
            while (respuesta.equalsIgnoreCase("s")) {                
            
                String caract = ES.leeDeTeclado("Introduzca el nombre de la caracteristica nutricional: ");
                int valor = ES.leeN("Inserte el valor nutricional por cada 100 gr de " + nombre + ": ");
                
                listaNutricional.put(caract, valor);
                
                respuesta = ES.leeDeTeclado("Quiere añadir alguna caracteristica nutricional mas? (S = Si; N = No): ");
                
                
            }
            
            Ingrediente ingr = new Ingrediente(nombre, origen, precioKilo, listaNutricional);
            
            listaIngredientes.put(nombre, ingr);
            ingredientes.put(nombre, ingr);
            
            guardaIngredientestxt(nombre, origen, precioKiloS, listaNutricional);
            
        }
        
    }
        
        
    static void guardaIngredientestxt(String nombre, String origen, String precioKilo, HashMap listaNutricional){
    
            FileWriter fw = null;
                
                try {
        
                    fw = new FileWriter("src/Datos/Ingrediente.txt",true);
                    PrintWriter pw = new PrintWriter(fw);
            
                    String lingrediente = nombre + "," + origen + "," + precioKilo + "/";
                    
                    Set<String> set1 = listaNutricional.keySet();
 
                    
                    for(String key : set1) {
            
                        lingrediente = lingrediente + key + ":";
                        lingrediente = lingrediente + listaNutricional.get(key) + "/"; 
        
                    }
                    
                    pw.print(lingrediente + "");
            
                    fw.close();
            
                } catch (FileNotFoundException ex) {
            
                    System.out.println("Archivo no encontrado... " + ex);
            
                } catch (IOException ex) {
            
                    System.out.println("Fallo al escribir... " + ex);
            
                }

        }
        
        
        
    static void CrearMenu(){
    
        String nombre = ES.leeDeTeclado("Inserte el nombre del menu: ");
        
        Set<String> set1 = listaPlatos.keySet();
 
        
        for(String key : set1) {
            
            System.out.println("Platos: "  + key + "\t\t");

        
        }
    
        String Splato1 = ES.leeDeTeclado("Inserte el primer plato del menu ");
        
        Plato plato1 = (Plato) listaPlatos.get(Splato1);
        
        String Splato2 = ES.leeDeTeclado("Inserte el primer plato del menu ");
        
        Plato plato2 = (Plato) listaPlatos.get(Splato2);
        
        String Splato3 = ES.leeDeTeclado("Inserte el primer plato del menu ");
    
        Plato plato3 = (Plato) listaPlatos.get(Splato3);
        
        Menu menu = new Menu(nombre, plato3, plato3, plato3);
        
    }
    
    
    static void listaIngredientes(){
    
        Set<String> set1 = ingredientes.keySet();
 
        // Y se saca los valores con un for each
        for(String key : set1) {
            
            System.out.print("Clave: "  + key + "\t\t");
            System.out.println("Valores: "  + ingredientes.get(key));
        
        }
    
    }*/
        
}
