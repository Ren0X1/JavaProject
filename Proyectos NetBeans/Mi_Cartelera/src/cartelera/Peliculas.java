/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartelera;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;


public class Peliculas implements Serializable, Comparable, Comparator<Peliculas>{

    private static final long serialVersionUID = 1L;
   
    private Integer codigoPelicula;
   
    private String nombre;
   
    private String director;
   
    private String genero;
   
    private boolean estreno;
    
    private boolean publico;
   
    private Date fechaInsercion;
   
    private String imagen;
    //private ArrayList<String> actores;
    private String actores;

    public Peliculas() {
    }

    public Peliculas(String nombre) {
        this.nombre = nombre;
    }

    public Peliculas(Integer codigoPelicula) {
        this.codigoPelicula = codigoPelicula;
    }

    public Peliculas(Integer codigoPelicula, String nombre, String director, String genero, boolean estreno, boolean publico, Date fechaInsercion,String imagen) {
        this.codigoPelicula = codigoPelicula;
        this.nombre = nombre;
        this.director = director;
        this.genero = genero;
        this.estreno = estreno;
        this.publico = publico;
        this.fechaInsercion = fechaInsercion;
        this.imagen=imagen;
        this.actores = ActoresPeli(codigoPelicula);
    }

    public Integer getCodigoPelicula() {
        return codigoPelicula;
    }

    public void setCodigoPelicula(Integer codigoPelicula) {
        this.codigoPelicula = codigoPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean getEstreno() {
        return estreno;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public boolean isEstreno() {
        return estreno;
    }

    public boolean isPublico() {
        return publico;
    }

    public String getActores() {
        return actores;
    }

    public void setEstreno(boolean estreno) {
        this.estreno = estreno;
    }

    public boolean getPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public Date getFechaInsercion() {
        return fechaInsercion;
    }

    public void setFechaInsercion(Date fechaInsercion) {
        this.fechaInsercion = fechaInsercion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPelicula != null ? codigoPelicula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peliculas)) {
            return false;
        }
        Peliculas other = (Peliculas) object;
        if ((this.codigoPelicula == null && other.codigoPelicula != null) || (this.codigoPelicula != null && !this.codigoPelicula.equals(other.codigoPelicula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         
        return  "     "+codigoPelicula +"              "+nombre +"             "+director +"       " +genero + "       " +estreno + "     " +publico + "       " +fechaInsercion+"       " 
                 
                 +actores+"\n";
    }
    //-----------------------------------------------------------------
      @Override
    public int compareTo(Object o) {
        Peliculas alumno2= (Peliculas)o;
      
        return this.nombre.compareToIgnoreCase(alumno2.nombre);
      
    }

   //--------------------------------------------------------------------
    @Override
    public int compare(Peliculas o1, Peliculas o2) {
   
     int compara;
        compara=o1.getGenero().compareToIgnoreCase(o2.getGenero());
     if(compara==0)
             return o1.nombre.compareToIgnoreCase(o2.nombre);
      
     return compara;
     
    }
   
   //--------------------------------------------------------
static String ActoresPeli(int codigoPeli){

 BufferedReader br = null;
 String actores="Pelicula sin reparto...",linea;
 String[] listaActores;
        try {
            br = new BufferedReader(new FileReader("src/actores/"+"actores.txt"));
            
            while ( (linea = br.readLine())!=null ){
                listaActores=linea.split(";");
                if( codigoPeli==Integer.parseInt(listaActores[0]) )
                    return listaActores[1];
                
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("No encuentra el fichero actores.txt ");
        } catch (IOException ex) {
            System.out.println("Error IO...");
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println("Error cerrando BufferedReader");
            }
        }
        return actores;
}
}
