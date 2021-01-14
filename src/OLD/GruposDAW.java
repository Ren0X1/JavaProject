package OLD;

import java.util.Objects;

public class GruposDAW extends ProfesoresDAW {
    private String nombregrupo;
    public String getNombregrupo() {
        return nombregrupo;
    }
    public void setNombregrupo(String nombregrupo) {
        this.nombregrupo = nombregrupo;
    }

    public GruposDAW(String nombre, String asignatura, int edad, int aula, String nombregrupo) {
        super(nombre, asignatura, edad, aula);
        this.nombregrupo = nombregrupo;
    }
    public GruposDAW(String nombregrupo) {
        this("null","null",-1,-1,"null");
        this.nombregrupo = nombregrupo;
    }

    @Override
    public String toString() {
        return "OLD.GruposDAW{" +
                "nombregrupo='" + nombregrupo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GruposDAW gruposDAW = (GruposDAW) o;
        return Objects.equals(nombregrupo, gruposDAW.nombregrupo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombregrupo);
    }
}
