package OLD;

public class Alumno extends Persona{
    String curso;
    String centro;
    String nie;

    public Alumno(String nombre, String apellido1, String apellido2, int edad, char sexo, String nif, String localidad,String curso, String centro, String nie) {
        super(nombre, apellido1, apellido2, edad, sexo, nif, localidad);
        this.curso = curso;
        this.centro = centro;
        this.nie = nie;
    }
}
