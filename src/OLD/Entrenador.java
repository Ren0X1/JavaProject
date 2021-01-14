package OLD;

import java.util.Objects;
public class Entrenador extends Persona {
    private String nombreequipo;
    private int victorias;
    private int empates;
    private int derrotas;
    //GETTER Y SETTER
    public String getNombreequipo() {
        return nombreequipo;
    }
    public void setNombreequipo(String nombreequipo) {
        this.nombreequipo = nombreequipo;
    }
    public int getVictorias() {
        return victorias;
    }
    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }
    public int getEmpates() {
        return empates;
    }
    public void setEmpates(int empates) {
        this.empates = empates;
    }
    public int getDerrotas() {
        return derrotas;
    }
    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }
    //CONSTRUCTORES
    public Entrenador(String nombre, String apellido1, String apellido2, int edad, char sexo, String nif, String localidad, String nombreequipo, int victorias, int empates, int derrotas) {
        super(nombre, apellido1, apellido2, edad, sexo, nif, localidad);
        this.nombreequipo = nombreequipo;
        this.victorias = victorias;
        this.empates = empates;
        this.derrotas = derrotas;
    }
    //EQUALS, HASHCODES Y TOSTRING
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrenador that = (Entrenador) o;
        return victorias == that.victorias &&
                empates == that.empates &&
                derrotas == that.derrotas &&
                Objects.equals(nombreequipo, that.nombreequipo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(nombreequipo, victorias, empates, derrotas);
    }
    @Override
    public String toString() {
        return "Entrenador{" +
                "nombreequipo='" + nombreequipo + '\'' +
                ", victorias=" + victorias +
                ", empates=" + empates +
                ", derrotas=" + derrotas +
                '}';
    }
}

