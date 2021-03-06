/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas21;

import java.util.Collection;
import java.util.HashSet;
import utilidades21.es.ES;

/**
 *
 * @author Usuario
 */
public class Directivo extends Empleado{
   private String                 categoria;
   private HashSet<Empleado>      subordinados;

    public Directivo(String categoria, float sueldoBruto, String nombre, String nif,String telefono) {
        super(sueldoBruto, nombre, nif,telefono);
        this.categoria = categoria;
        
        subordinados = new HashSet();
    }

    public Directivo(String categoria, HashSet subordinados, float sueldoBruto, String nombre, String nif,String telefono) {
        this(categoria,sueldoBruto, nombre, nif,telefono);
        this.subordinados = subordinados;
    }
    
    public Directivo(String categoria,  double sueldoBruto, String telefono, String nombre, String apellido1, String apellido2, String nif) {
        super(sueldoBruto, telefono, nombre, apellido1, apellido2, nif);
        this.categoria = categoria;
        subordinados = new HashSet();
        
    }
    
    public Directivo(String categoria, HashSet<Empleado> subordinados, double sueldoBruto, String telefono, String nombre, String apellido1, String apellido2, String nif) {
        super(sueldoBruto, telefono, nombre, apellido1, apellido2, nif);
        this.subordinados = subordinados;
    }
    
    

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
 public HashSet<Empleado> getSubordinados() {
        return subordinados;
    }

    public void setSubordinados(HashSet<Empleado> subordinados) {
        this.subordinados = subordinados;
    }
   

    @Override
    public String toString() {
        
        return "Directivo{" + "categoria=" + categoria + 
                "\n, subordinados=\n" + subordinados+
                "\n"+ super.toString(); 
                
    }
//------------------------------------------------------------  
   public String listadoCorto (){
       return super.listadoCorto()+"categoría-> "+categoria;
   }
   
   
}
