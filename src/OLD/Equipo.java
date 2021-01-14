package OLD;

import java.util.Objects;
public class Equipo {
    private String nombreequipo;
    private String nombreentrenador;
    private String nombrepresidente;
    private String localidad;
    private String nombreestadio;
    private String nombrecapitan;
    //GETTER Y SETTER
    public String getNombreequipo() {
        return nombreequipo;
    }
    public void setNombreequipo(String nombreequipo) {
        this.nombreequipo = nombreequipo;
    }
    public String getNombreentrenador() {
        return nombreentrenador;
    }
    public void setNombreentrenador(String nombreentrenador) {
        this.nombreentrenador = nombreentrenador;
    }
    public String getNombrepresidente() {
        return nombrepresidente;
    }
    public void setNombrepresidente(String nombrepresidente) {
        this.nombrepresidente = nombrepresidente;
    }
    public String getLocalidad() {
        return localidad;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public String getNombreestadio() {
        return nombreestadio;
    }
    public void setNombreestadio(String nombreestadio) {
        this.nombreestadio = nombreestadio;
    }
    public String getNombrecapitan() {
        return nombrecapitan;
    }
    public void setNombrecapitan(String nombrecapitan) {
        this.nombrecapitan = nombrecapitan;
    }
    //CONTRUCTORES
    public Equipo(String nombreequipo, String nombreentrenador, String nombrepresidente, String localidad, String nombreestadio, String nombrecapitan) {
        this.nombreequipo = nombreequipo;
        this.nombreentrenador = nombreentrenador;
        this.nombrepresidente = nombrepresidente;
        this.localidad = localidad;
        this.nombreestadio = nombreestadio;
        this.nombrecapitan = nombrecapitan;
    }
    //EQUALS, HASHCODE y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(nombreequipo, equipo.nombreequipo) &&
                Objects.equals(nombreentrenador, equipo.nombreentrenador) &&
                Objects.equals(nombrepresidente, equipo.nombrepresidente) &&
                Objects.equals(localidad, equipo.localidad) &&
                Objects.equals(nombreestadio, equipo.nombreestadio) &&
                Objects.equals(nombrecapitan, equipo.nombrecapitan);
    }
    @Override
    public int hashCode() {
        return Objects.hash(nombreequipo, nombreentrenador, nombrepresidente, localidad, nombreestadio, nombrecapitan);
    }
    @Override
    public String toString() {
        return "Equipo{" +
                "nombreequipo='" + nombreequipo + '\'' +
                ", nombreentrenador='" + nombreentrenador + '\'' +
                ", nombrepresidente='" + nombrepresidente + '\'' +
                ", localidad='" + localidad + '\'' +
                ", nombreestadio='" + nombreestadio + '\'' +
                ", nombrecapitan='" + nombrecapitan + '\'' +
                '}';
    }
}

