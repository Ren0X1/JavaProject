package Empresa2;
import java.util.Objects;
public class Cliente extends Persona {
    private String telefono;
    

    public Cliente(String telefono, String nombre, String nif) {
        super(nombre, nif);
        this.telefono = telefono;
        
    }

    public Cliente(String telefono, String nombre, String apellido1, String apellido2, String nif) {
        super(nombre, apellido1, apellido2, nif);
        this.telefono = telefono;
    }
    
    

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.telefono);
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
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.telefono, other.telefono);
    }

    @Override
    public String toString() {
        return "Cliente{" + "telefono=" + telefono + "}/n"+
                super.toString();
    }
    //---------------------------------------------
   public static Cliente altaCliente(){
      System.out.println("Alta de Cliente...");
      Persona per   =Persona.altaPersona();


       return new Cliente(
                                 ES.leeDeTeclado("telefono? "),
                                 per.getNombre(),
                                 per.getApellido1(),
                                 per.getApellido2(),
                                 per.getNif());
    }
  //------------------------------------------------------------  
   public String listadoCorto (){
       return super.getApellido1()+" "+super.getApellido1()+", "+super.getNombre();
   }
    
    
}