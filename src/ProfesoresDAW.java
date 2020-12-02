import java.util.Objects;

public class ProfesoresDAW {
    private String nombre;
    private String asignatura;
    private int edad;
    private int aula;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getAula() {
        return aula;
    }

    public void setAula(int aula) {
        this.aula = aula;
    }
    int compruebaedad(int edad1) {
        if (edad1>0) {
            return edad1;
        }
        else {
            return -1;
        }
    }

    public ProfesoresDAW(String nombre, String asignatura, int edad, int aula) {
        this.nombre = nombre;
        this.asignatura = asignatura;
        this.edad = compruebaedad(edad);
        this.aula = aula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfesoresDAW that = (ProfesoresDAW) o;
        return edad == that.edad &&
                aula == that.aula &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(asignatura, that.asignatura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, asignatura, edad, aula);
    }

    @Override
    public String toString() {
        return "ProfesoresDAW{" +
                "nombre='" + nombre + '\'' +
                ", asignatura='" + asignatura + '\'' +
                ", edad=" + edad +
                ", aula=" + aula +
                '}';
    }
}
