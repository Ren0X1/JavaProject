package Empresa2;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;
public class Empresa implements Comparable{
    private String nombre;
    private TreeMap listaEmpelados;
    private ArrayList listaClientes;
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
        return Objects.equals(this.nombre, other.nombre);
    }

    @Override
    public String toString() {
        return "Empresa{" 
                + "\n nombre=" + nombre 
                + "\n listaEmpelados=" + listaEmpelados 
                + "\n listaClientes="  + listaClientes + '}';
    }

    public TreeMap getListaEmpelados() {
        return listaEmpelados;
    }

    public void setListaEmpelados(TreeMap<String,Empleado> listaEmpelados) {
        this.listaEmpelados = listaEmpelados;
    }

    public ArrayList getListaClientes() {
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
