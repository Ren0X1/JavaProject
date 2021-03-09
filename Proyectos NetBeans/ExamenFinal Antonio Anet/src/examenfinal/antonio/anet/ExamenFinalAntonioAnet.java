/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenfinal.antonio.anet;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import java.util.logging.Level;
import java.util.logging.Logger;
import utils78.ES;


public class ExamenFinalAntonioAnet {

    /**
     * @param args the command line arguments
     */
    
    static TreeMap <String, Ingrediente> listaIngredientes = new TreeMap<>();
    static TreeMap <String, Plato> listaPlatos = new TreeMap<>();
    static TreeMap <String, Menu> listaMenu = new TreeMap<>();
    
            
    public static void main(String[] args) {
        
        Restaurante restaurante = new Restaurante("AntonioRestaurant", "Antonio Anet");
        
        CargaIngrediente();
        CargaPlato();
        CargaMenu();
        
        int opc;
        
        do{
            
            System.out.println("");
            System.out.println("1-. Altas Platos / Ingredientes"); 
            System.out.println("2-. Crear Menu ");
            System.out.println("3-. Listados ");
            System.out.println("0-. Salir");
            
            opc = ES.leeN("Introduzca una opcion ");
            
            
            
            switch(opc){
            
                case 1: 
                    
                    int opc2;
                    
                    do{
                    
                        System.out.println("");
                        System.out.println("1-. Crear Platos");
                        System.out.println("2-. Crear Ingredientes");
                        System.out.println("0-. Salir");
                        System.out.println("");
                        
                        opc2 = ES.leeN("Introduzca una opcion ");
                        
                        System.out.println("");
                        
                        switch(opc2){
                        
                            case 1: CreaPlatos();
                            break;
                            
                            case 2: CreaIngredientes();
                            break;
                        
                        }
                    
                    } while(opc2!=0);
                    
                break;

                case 2: CrearMenu();
                break;
                
                case 3: 
                    
                    int opc3;
                    
                    do{
                        
                    
                        System.out.println("");
                        System.out.println("1-. Listar todos los menús ");
                        System.out.println("2-. Recitar un menú ");
                        System.out.println("3-. Listar todos los platos ");
                        System.out.println("4-. Listar todos los ingredientes ");
                        System.out.println("5-. Listar restaurante ");
                        System.out.println("0-. Salir");
                        System.out.println("");
                        
                        opc2 = ES.leeN("Introduzca una opcion ");
                        
                        System.out.println("");
                        
                        switch(opc2){
                        
                            case 1: listarMenuSimple();
                            break;
                            
                            case 2: ListarMenuDetalle();
                            break;
                        
                            case 3: listarPlatos();
                            break;
                            
                            case 4: listarIngredientes();
                            break;
                            
                            case 5: ListarRestaurante();
                            break;
                            
                        }
                    
                    } while(opc2!=0);
                    
                break;
                      
            }
            
        } while(opc!=0);
        
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
            
            
            guardaIngredientestxt(nombre, origen, precioKiloS, listaNutricional);
            
        }
        
    }
    
        
    static void CreaPlatos(){
    
        HashSet lIngredientes = new HashSet();
        
        String nombrePlato = ES.leeDeTeclado("Introduzca el nombre del plato: ");
        String descripcion = ES.leeDeTeclado("Introduzca una breve descripcion de la preparacion del plato: ");
        
        System.out.println("");
        
        Set<String> set1 = listaIngredientes.keySet();
 
        for(String key : set1) {
            
            System.out.println( key + "\t\t");
                    
        }
        
        System.out.println("");
        
        String ingrediente = ES.leeDeTeclado("Introduzca el ingrediente principal del plato ");
        
        while (!listaIngredientes.containsKey(ingrediente)) {            
            
            ingrediente = ES.leeDeTeclado("Error. Introduzca el ingrediente principal del plato. Asegurese de escribirlo bien: ");
            
        }

        
        String respuesta = ES.leeDeTeclado("Introduzca otro ingrediente que conforme el plato. Pulse enter para terminar");
        
        while(!respuesta.equals("")){
        
            while (!listaIngredientes.containsKey(respuesta) && !respuesta.equals("")) {            
            
                respuesta = ES.leeDeTeclado("Error. Introduzca el ingrediente del plato. Asegurese de escribirlo bien. Puede cancelarlo si lo desea ");
            
            }
        
            lIngredientes.add(listaIngredientes.get(respuesta));
            
            respuesta = ES.leeDeTeclado("Introduzca otro ingrediente que conforme el plato. Pulse enter para terminar");
        
        }

        
        String precioPlatoS = ES.leeDeTeclado("Introduzca el precio del plato: ");
        
        float precioPlato = Float.parseFloat(precioPlatoS);
    
        Plato plato = new Plato(nombrePlato, descripcion, ingrediente, precioPlato, lIngredientes);
        
        listaPlatos.put(nombrePlato, plato);
        
        guardaPlatostxt(nombrePlato, descripcion, ingrediente, precioPlatoS, lIngredientes);
    
        
    }
    
    
    static void CrearMenu(){
    
        String nombre = ES.leeDeTeclado("Inserte el nombre del menu: ");
        
        Set<String> set1 = listaPlatos.keySet();
 
        
        for(String key : set1) {
            
            System.out.println("Platos: "  + key + "\t\t");

        
        }
    
        String Splato1 = ES.leeDeTeclado("Inserte el primer plato del menu ");
        
        while (!listaPlatos.containsKey(Splato1)) {            
            
            Splato1 = ES.leeDeTeclado("Error. Inserte el primer plato del menu. Asegurese de escribirlo bien: ");
            
        }
        
        Plato plato1 = (Plato) listaPlatos.get(Splato1);
        
        
        String Splato2 = ES.leeDeTeclado("Inserte el segundo plato del menu ");
        
        while (!listaPlatos.containsKey(Splato2)) {            
            
            Splato2 = ES.leeDeTeclado("Error. Inserte el segundo plato del menu. Asegurese de escribirlo bien: ");
            
        }
        
        Plato plato2 = (Plato) listaPlatos.get(Splato2);
        
        
        String Splato3 = ES.leeDeTeclado("Inserte el postre del menu ");
        
        while (!listaPlatos.containsKey(Splato3)) {            
            
            Splato3 = ES.leeDeTeclado("Error. Introduzca el postre del menu. Asegurese de escribirlo bien: ");
            
        }
    
        Plato plato3 = (Plato) listaPlatos.get(Splato3);
        
        Menu menu = new Menu(nombre, plato1, plato2, plato3);
        
        listaMenu.put(nombre, menu);
        
        guardaMenutxt(menu);
        
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
                    
            pw.println(lingrediente);
            
            fw.close();
            
        } catch (FileNotFoundException ex) {
            
            System.out.println("Archivo no encontrado... " + ex);
            
        } catch (IOException ex) {
            
            System.out.println("Fallo al escribir... " + ex);
            
        }

    }
    
    
    static void guardaPlatostxt(String nombrePlato, String descripcion, String nombreIngredienteP, String precioPlato, HashSet<Ingrediente> lIngredientes){
    
        FileWriter fw = null;
                
            try {
        
                fw = new FileWriter("src/Datos/Plato.txt",true);
                PrintWriter pw = new PrintWriter(fw);
            
                String lplato = nombrePlato + "," + descripcion + "," + nombreIngredienteP + "," + precioPlato + "/";

                    
                for (Ingrediente ingr : lIngredientes) {
            
                    lplato = lplato + ingr.getNombre() + ";";
            
                }
                    
                pw.println(lplato);
            
                fw.close();
                    
            
            } catch (FileNotFoundException ex) {
            
                System.out.println("Archivo no encontrado... " + ex);
            
            } catch (IOException ex) {
            
                System.out.println("Fallo al escribir... " + ex);
            
            }   
                
    }
        
    
    static void guardaMenutxt(Menu menu){
    
        FileWriter fw = null;
                
        try {
        
            fw = new FileWriter("src/Datos/Menu.txt",true);
            PrintWriter pw = new PrintWriter(fw);
            
            String lmenu = menu.nombreMenu + "," + menu.primerPlato.getNombrePlato() + "," 
                                                  + menu.segundoPlato.getNombrePlato() + ","
                                                  + menu.postre.getNombrePlato();
                    
            pw.println(lmenu);
            
            fw.close();
            
            } catch (FileNotFoundException ex) {
            
                System.out.println("Archivo no encontrado... " + ex);
            
            } catch (IOException ex) {
            
                System.out.println("Fallo al escribir... " + ex);
            
            }
    
    }
    
    
    static void CargaIngrediente(){
    
        
        HashMap lnutricional = new HashMap<String, Integer>();
        
        try {
            
            String linea;
            
            FileReader file = new FileReader("src/Datos/Ingrediente.txt");
            BufferedReader bf = new BufferedReader(file);
            
            while((linea = bf.readLine()) != null){
            
                String [] palabras = linea.split(",");
                
                String nombre = palabras[0];
                String origen = palabras[1];
                
                String [] palabras2 = palabras[2].split("/");
                
                String precioS = palabras2[0]; 
                
                float precio = Float.parseFloat(precioS);
                
                for (int i = 1; i < palabras2.length; i++) {
                    
                    String [] palabras3 = palabras2[i].split(":");
                    
                    lnutricional.put(palabras3[0], palabras3[1]);
                    
                }
                
                Ingrediente ingr = new Ingrediente(nombre, origen, precio, lnutricional);
            
                listaIngredientes.put(nombre, ingr);
                
            }
            
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(ExamenFinalAntonioAnet.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (IOException ex) {
            
            Logger.getLogger(ExamenFinalAntonioAnet.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    
    }
    
    
    static void CargaMenu(){
    
        try {
            
            String linea;
            
            FileReader file = new FileReader("src/Datos/Menu.txt");
            BufferedReader bf = new BufferedReader(file);
            
            while((linea = bf.readLine()) != null){
            
                String [] palabras = linea.split(",");
                
                String nombreMenu = palabras[0];
                String plato1 = palabras[1];
                String plato2 = palabras[2];
                String plato3 = palabras[3];
                
                Plato primerPlato = listaPlatos.get(plato1);
                Plato segundoPlato = listaPlatos.get(plato2);
                Plato postre = listaPlatos.get(plato3);
                
                Menu menu = new Menu(nombreMenu, primerPlato, segundoPlato, postre);
                
                listaMenu.put(nombreMenu, menu);
                
                
            }
            
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(ExamenFinalAntonioAnet.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (IOException ex) {
            
            Logger.getLogger(ExamenFinalAntonioAnet.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    
    }
    
    
    static void CargaPlato(){
        
        try {
            
            String linea;
            FileReader file = new FileReader("src/Datos/Plato.txt");
            BufferedReader bf = new BufferedReader(file);
            
            while((linea = bf.readLine()) != null){
                    
                HashSet lIngredientes = new HashSet<Ingrediente>();
                
                String [] palabras = linea.split(",");
                
                String nombreplato = palabras[0];
                String descripcion = palabras[1];
                String ingredienteP = palabras[2];
                
                String [] palabras2 = palabras[3].split("/");
                
                String precioS = palabras2[0];
                
                float precio = Float.parseFloat(precioS);
                
                if(palabras[3].contains(";")){
                
                    String [] palabras3 = palabras2[1].split(";");
                
                    for (int i = 0; i < palabras3.length; i++) {
                    
                        lIngredientes.add(listaIngredientes.get(palabras3[i]));
                    
                    }
                
                }
                
                Plato plato = new Plato(nombreplato, descripcion, ingredienteP, precio, lIngredientes);
                
                listaPlatos.put(nombreplato, plato);
                
            }
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(ExamenFinalAntonioAnet.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (IOException ex) {
            
            Logger.getLogger(ExamenFinalAntonioAnet.class.getName()).log(Level.SEVERE, null, ex);

        }
    
    }
    
    
    /*static void creaRestaurante(){
     
            HashMap lmap = cargaIgrediente();
     
            Set<String> set1 = lmap.keySet();
 
        // Y se saca los valores con un for each
        for(String key : set1) {
            
            System.out.print("Clave: "  + key + "\t\t");
            System.out.println("Valores: "  + lmap.get(key));
        
        }
            
        }*/
        
    
    static void listarMenuSimple(){
       
        Set<String> set1 = listaMenu.keySet();

        for(String key : set1) {
            
            listaMenu.get(key).listaMenu();
        
        }
       
    }
        

    static void ListarMenuDetalle(){
    
        System.out.println("Lista de menús");
        
        Set<String> set1 = listaMenu.keySet();
 
        for(String key : set1) {
            
            System.out.println( "\t" + key);
        
        }
        
        String menuS = ES.leeDeTeclado("Elija un menú para su recitación: ");

        while (!listaMenu.containsKey(menuS)) {            
            
            menuS = ES.leeDeTeclado("Error. Elija un menú para su recitación. Asegurese de escribirlo bien: ");
            
        }
        
        Menu menu = listaMenu.get(menuS);
        
        menu.listaMenuDetalle();
    
    }
    
    
    static void listarPlatos(){
        
            Set<String> set1 = listaPlatos.keySet();
 
            for(String key : set1) {
            
                listaPlatos.get(key).listarPlatos();
        
            }
        
        }
    
    
    static void listarIngredientes(){
    
        Set<String> set1 = listaIngredientes.keySet();
 
        for(String key : set1) {
            
            listaIngredientes.get(key).listaIngrediente2();
        
            System.out.println("");
        }
    
    }
    
    
    static void ListarRestaurante(){
    
        System.out.println("Restaurante Antonio \n");
        
        Set<String> set1 = listaMenu.keySet();
 
        System.out.println("Menus: ");
        
        for(String key : set1) {
            
            System.out.print(key + "/");

        }
        
        System.out.println("");
        System.out.println("");
        
        Set<String> set2 = listaPlatos.keySet();
 
        System.out.println("Platos: ");
        
        for(String key : set2) {
            
            System.out.print(key + "/");

        }
        
        System.out.println("");
        System.out.println("");
        
        Set<String> set3 = listaIngredientes.keySet();
 
        System.out.println("Ingredientes: ");
        
        for(String key : set3) {
            
            System.out.print(key + " /");

        }
    
        System.out.println("");
        System.out.println("");
        
    }

}