package videoclub;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public  class Persona implements Comparable<Persona> ,Serializable{
    protected char sexo;
    protected String nombre;
    protected String apellido1;
    protected String apellido2;
    protected String nif;
    private int edad;
    protected String localidad;
    private static int censo;
    public Persona(String nombre, String nif) {
        super();
        this.nombre = nombre;
        this.nif  = compruebaNif(nif);
        censo++;  
    }
    public Persona(String nombre, String apellido1, String apellido2, String nif) {
        this(nombre,nif);
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;   
    }
    public Persona(char sexo, String nombre, String apellido1, String apellido2, String nif, int edad, String localidad) {
        this(nombre, nif);
        this.sexo = sexo;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = edad;
        this.localidad = localidad;
    }
     public final void cantar(String letra) {
        System.out.println("Soy una Persona cantnanmdo..."+letra);
    }    
    public static int getCenso() {
        return censo;
    }
    public static void setCenso(int aCenso) {
        censo = aCenso;
    }   
    public char getSexo() {
        return sexo;
    }
     public void setSexo(char sexo) {
        if (Character.toUpperCase(sexo) == 'M' || Character.toUpperCase(sexo) == 'H') {
            this.sexo = Character.toUpperCase(sexo);
        } else {
            System.out.println(sexo + " no es válido");
        }
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido1() {
        return apellido1;
    }
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
    public String getApellido2() {
        return apellido2;
    }
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    public String getNif() {
        return nif;
    }
    public void setNif(String nif) {     
        String nuevoNif=compruebaNif(nif); 
        if(nuevoNif!="noValido")
            this.nif = nuevoNif; 
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getLocalidad() {
        return localidad;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.nif);
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
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (this.nif.equalsIgnoreCase(other.nif)) {
            return true;
        }
        return true;
    }
    public String toString2() {
        return "Nombre: " + nombre + ", Apellidos: " + apellido1 + " " + apellido2
            + ", NIF: " + nif + ", Edad: " + edad + ", Sexo: "
            + sexo + ", Localidad: " + localidad;
    }
    @Override
    public String toString() {
        return  "nombre=" + nombre + ", nif=" + nif+"\n";
    }
    public String toString1() {
        return "\nPersona{" + "sexo=" + sexo + 
                          ",\n nombre=" + nombre + 
                          ",\n apellido1=" + apellido1 + 
                          ",\n apellido2=" + apellido2 + 
                          ",\n nif=" + nif + 
                          ",\n edad=" + edad + 
                          ",\n localidad=" + localidad + 
                      '}';
    }
    public static String compruebaNif(String nuevoNif){
        char sigueProbando='N';
        do{
            if(nuevoNif.matches("[0-9]{8}((\\s[A-Z])|([A-Z])|(\\-[A-Z]))?")){
                char letra=convierteNumeros(nuevoNif.substring(0,8));
                if(nuevoNif.length()>8){
                    if(!(letra == nuevoNif.charAt(nuevoNif.length()-1))){
                        System.out.print("Letra incorrecta, la correcta es -> "+ letra);
                        return nuevoNif.replace(nuevoNif.charAt(nuevoNif.length()-1),letra);
                    }else {
                        return nuevoNif;	
                    } 
                }
                else return nuevoNif+letra;
            }
            sigueProbando=ES.leecaracter("NiF no Válido, reintentar?(Si ->'S')");
            if(Character.toUpperCase(sigueProbando)=='S')
                nuevoNif=ES.leeDeTeclado("Escribe le nif? ");
        } while(Character.toUpperCase(sigueProbando)=='S');
        return "noValido";
    }
    public static char convierteNumeros(String respuesta){
        String secuenciaLetrasNIF = "TRWAGMYFPDXBNJZSQVHLCKE"; 
        int nifEntero = Integer.parseInt(respuesta);
        int indice =nifEntero%23;
        char letra = secuenciaLetrasNIF.charAt(indice);
        return letra;
    }
    @Override
    public int compareTo(Persona o) {
        String nom =((Persona)o).nombre;
        return this.nombre.compareTo(nom);
    }
    public static class ComparaPersonasAlfa implements Comparator{
        @Override
        public int compare(Object o1, Object o2) {
            Persona p1  =(Persona)o1;
            Persona p2  = (Persona)o2;
            int comparacion;
            if (p1.nif.equalsIgnoreCase(p2.nif))
                return 0;
            comparacion = p1.getApellido1().compareToIgnoreCase(p2.getApellido1());
            if(comparacion==0)
                comparacion = p1.getApellido2().compareToIgnoreCase(p2.getApellido2());
            if(comparacion==0)
                comparacion = p1.getNombre().compareToIgnoreCase(p2.getNombre());
            return comparacion;
        }
    }
    public static Persona altaPersona(){
        String nom =ES.leeDeTeclado("nombre? ");
        String nif =            ES.leeDeTeclado("nif? ");
        String ape1=            ES.leeDeTeclado("primer apellido ? ");
        String ape2=            ES.leeDeTeclado("Segundo apellido ? ");
        return new Persona(nom, ape1, ape2, nif);             
    }
    public String describePerNueva(){
        return this.nombre+","+this.apellido1+","+this.apellido2+","+this.nif;
    } 
}
