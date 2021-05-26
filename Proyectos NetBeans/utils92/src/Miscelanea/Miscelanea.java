/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Miscelanea;

import ES.ES;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Eloy
 */
public class Miscelanea {
    
    
    
     public static <T> T containsColecciones(T emp, Collection<T> c) {
          T siguiente;
          
        if (c.contains(emp)) {
           
           Iterator it= c.iterator();
           while( it.hasNext() ){
               siguiente=(T) it.next();
               if(siguiente.equals(emp))
                   return siguiente;
           }
         }
        return null;
    }
  //------------------------------------------------------------------    
 //------------------------------------------------------------------    
 public static int pintaMenu(String titulo,ArrayList<String> menu){
   int opcion=0,i=1;
    System.out.println("___________________________________");
    System.out.println("\t"+titulo);
    System.out.println("___________________________________");
     
        do{
            for (String opcionMenu : menu) {
                System.out.println(i+".- "+opcionMenu);
                i++;
            }
            System.out.println(i+".- Salir");
          
        opcion=ES.leeN("Introduzca opcion: ");
        
        if(opcion > menu.size()+1)
               System.out.println("Escribe una opción válida!");
      }while (opcion > menu.size()+1);
     return opcion;   
} // pintaMenu        */
 //---------------------------------------------------
   public static String seleccionaFichero(String ruta){
      File file = new File("src/datos");
      String fich="";
      for(int i=0; i<file.list().length;i++ )
            System.out.println(file.list()[i]);
      
      do{
         fich=ES.leeDeTeclado("Nombre fichero? ");
         
      }while( !Arrays.asList(file.list()).contains(fich) );
      return fich;
    }    
 
}
