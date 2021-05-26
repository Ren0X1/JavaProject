/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas21;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

/**
 *
 * @author Usuario
 */
public class Empresa implements Comparable{
    private String nombre;
    private TreeMap<String,Empleado>    listaEmpelados;
    private ArrayList<Cliente>          listaClientes;
    static int numEmp =0;

    public Empresa(String nombre) {
        this.nombre = nombre;
        listaClientes  = new ArrayList();
        listaEmpelados = new TreeMap();
        
    }

    
    public Empresa(String nombre,ArrayList<Cliente>  listaClientes) {
        this(nombre);
        this.listaClientes = listaClientes;
    
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empresa other = (Empresa) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empresa{" 
                + "\n nombre=" + nombre 
                + "\n listaEmpelados=" + listaEmpelados 
                + "\n listaClientes="  + listaClientes + '}';
    }

    public TreeMap<String,Empleado> getListaEmpelados() {
        return listaEmpelados;
    }

    public void setListaEmpelados(TreeMap<String,Empleado> listaEmpelados) {
        this.listaEmpelados = listaEmpelados;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public int compareTo(Object o) {
       return nombre.compareToIgnoreCase( ((Empresa)o).nombre);
    }
   
    //------------------------------------------------------------  
   public String listadoCorto (){
       return nombre;
   }
    
    
}
