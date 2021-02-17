package videoclub;

import java.util.TreeMap;
import java.util.TreeSet;

public class VideoClub {
    private String       nombre;
    private TreeSet <Cliente>    clientes;
    private TreeMap <String,DVD> peliculas;

    public VideoClub(String nombre) {
        this.nombre = nombre;
        clientes = new  TreeSet();
        peliculas = new TreeMap();
    }

    public VideoClub(String nombre, TreeSet<Cliente> clientes, TreeMap<String, DVD> peliculas) {
        this.nombre = nombre;
        this.clientes = clientes;
        this.peliculas = peliculas;
    }


    @Override
    public String toString() {
        return  nombre+" "+
                "\n Clientes:"+clientes+
                "\n DVDs    :"+peliculas;
    }
    
  
    public static VideoClub altaVideoclub(){
        VideoClub vid;
        String nombre;
        nombre=ES.leeDeTeclado("Nombre del videoclub");
        vid=new VideoClub(nombre);
        System.out.println("Videoclub "+nombre+" dado de alta con exito!");
        return vid;
    }

    public TreeSet <Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(TreeSet <Cliente> clientes) {
        this.clientes = clientes;
    }

    public TreeMap <String,DVD> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(TreeMap <String,DVD> peliculas) {
        this.peliculas = peliculas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
}
