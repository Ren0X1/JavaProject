package Personas;


import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Persona implements Comparable,Serializable{//<Persona>{
     protected 	String nombre;
     protected 	String apellido1;
     protected 	String apellido2;
     private 	String nif;
     protected 	char   sexo;
                String localidad="Ronda";
//-------------Constructores---------------------------- 
     public Persona(String nombre, String nif) {
        this.nombre = nombre;
        this.nif = compruebaNif(nif);
       
    }          
    public Persona(String nombre, String apellido1, String apellido2, String nif) {
        this(nombre, nif);
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
     }
  
    public Persona(String nombre, String apellido1, String apellido2, 
                    String nif, char sexo, String pueblo) {
        this(nombre,apellido1,apellido2,nif);
        this.sexo = sexo;
        this.localidad=pueblo;
    }
  //-----------Getter & Setter-------------------------------------- 
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
        this.nif = compruebaNif(nif);
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
  //--------------------------------------------------- 
    @Override
    public String toString() {
        return "\tPersona{" + "nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", nif=" + nif + ", sexo=" + sexo + ", localidad=" + localidad + "}\n";
    }
  //--------------------------------------------------- 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nif);
        return hash;
    }
  //--------------------------------------------------- 
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
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.nif, other.nif)) {
            return false;
        }
        return true;
    }
    //--------------------------------------------------- 
    protected String compruebaNif(String nif) {
        
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        
        while( !(nif.matches("[0-9]{8}[a-zA-Z]?")) ) {
            return "";//nif = ES.leeDeTeclado("Formato de Nif incorrecto, repita el nif: "); 
           }
        if ( nif.matches("[0-9]{8}") ) {
            int  numero= Integer.parseInt(nif);
            return nif + letras.charAt(numero%23);
        }
        else  {
                int  numero= Integer.parseInt(nif.substring(0,8));
                if ( nif.charAt(8) != letras.charAt(numero%23) ){
                      System.out.println("Letra del nif incorrecta...se recalculara ");
                      return nif.substring(0,8) + letras.charAt(numero%23);
                  }
               else return nif; 
            }
        }
  //-----------------------------------------------------------  
 @Override
    public int compareTo(Object o) {
        Persona persona2= (Persona)o;
        
        int comparacion= apellido1.compareToIgnoreCase(persona2.apellido1);
        
        if(comparacion==0){
            comparacion= apellido2.compareToIgnoreCase(persona2.apellido2);
            if(comparacion==0)
                comparacion= nombre.compareToIgnoreCase(persona2.nombre);
        }
        return comparacion;
      
    }


    
    
}
