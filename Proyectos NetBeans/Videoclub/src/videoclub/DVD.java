package videoclub;

import java.io.Serializable;
import java.util.ArrayList;

public class DVD implements Comparable, Serializable{
    private String codigo;
    private String titulo;
    private String director;
    private ArrayList <String> reparto;
    private ArrayList <Copia>  copias;
    private static int  codigoActual=0;
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String Codigo) {
        this.codigo = Codigo;
    }

    public DVD(String Codigo, String Titulo, String Director, ArrayList<String> reparto) {
        this.codigo = Codigo;
        this.titulo = Titulo;
        this.director = Director;
        this.reparto=reparto;
        this.copias = new ArrayList();
        codigoActual++;
    }
    public DVD(String Codigo, String Titulo, String Director, ArrayList<String> reparto,int numCopias) {
        this(Codigo, Titulo, Director, reparto);
        this.copias=creaCopias(numCopias);
    }

    private ArrayList creaCopias(int numCopias) {
        ArrayList<Copia> ccopy= new ArrayList(); 
        for (int i = 0; i < numCopias; i++) {
            ccopy.add(new Copia(this.codigo,i+1));
        }
        return ccopy;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String Titulo) {
        this.titulo = Titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String Director) {
        this.director = Director;
    }

    public ArrayList<String> getReparto() {
        return reparto;
    }

    public void setReparto(ArrayList<String> reparto) {
        this.reparto = reparto;
    }

    @Override
    public String toString() {
        return codigo+" "+titulo+" Dir->"+director+
               "\n reparto:"+reparto+"\n"  ;
    }

    @Override
    public int compareTo(Object o) {
        return codigo.compareToIgnoreCase(((DVD)o).codigo);
    }
    
    public static DVD altaDvd(){
        DVD dvdnuevo;
        ArrayList <String> reparto = new ArrayList();
        String Titulo,Codigo,Director;
        Titulo=ES.leeDeTeclado("Titulo de la pelicula: ");
        Director=ES.leeDeTeclado("Director de la pelicula: ");
        String nombre;
        System.out.println("Actores de reparto");
        do{
            nombre=ES.leeDeTeclado("Nombre del actor de reparto('c' para finallizar): ");
            reparto.add(nombre);                
        } while(!nombre.equalsIgnoreCase("c"));
        int nc=ES.leeN("Cuantas copias se necesitan de la pelicula: ");
        dvdnuevo=new DVD(generarCodigo(),Titulo,Director,reparto,nc);
        System.out.println("DVD "+Titulo+" dado de alta con exito!");
        return dvdnuevo;
    }
    
    private static String generarCodigo(){
        codigoActual++;
        String codigo= Integer.toString(codigoActual);
        do{
            codigo="0"+codigo;
        } while(codigo.length()<4);
        return codigo;            
    }
}
