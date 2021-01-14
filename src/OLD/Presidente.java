package OLD;

import java.util.Objects;

public class Presidente extends Persona {
    private String nombrepresi;
    private String nombreequipo;

    public String getNombrepresi() {
        return nombrepresi;
    }

    public void setNombrepresi(String nombrepresi) {
        this.nombrepresi = nombrepresi;
    }

    public String getNombreequipo() {
        return nombreequipo;
    }

    public void setNombreequipo(String nombreequipo) {
        this.nombreequipo = nombreequipo;
    }

    public Presidente(String nombre, String apellido1, String apellido2, int edad, char sexo, String nif, String localidad, String nombrepresi, String nombreequipo) {
        super(nombre, apellido1, apellido2, edad, sexo, nif, localidad);
        this.nombrepresi = nombrepresi;
        this.nombreequipo = nombreequipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Presidente that = (Presidente) o;
        return Objects.equals(nombrepresi, that.nombrepresi) &&
                Objects.equals(nombreequipo, that.nombreequipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombrepresi, nombreequipo);
    }

    @Override
    public String toString() {
        return "OLD.Presidente{" +
                "nombrepresi='" + nombrepresi + '\'' +
                ", nombreequipo='" + nombreequipo + '\'' +
                '}';
    }
}
