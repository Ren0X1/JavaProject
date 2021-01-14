package OLD;

public class Directivos extends Empleados {
    private String grupoTrabajo;
    //-------------------
    //GETTER Y SETTER
    public String getGrupoTrabajo() { return grupoTrabajo; }
    public void setGrupoTrabajo(String grupoTrabajo) { this.grupoTrabajo = grupoTrabajo; }
    //-------------------
    //Constructores
    public Directivos(String nombre, int edad, int sueldo, String grupoTrabajo) {
        super(nombre, edad, sueldo);
        this.grupoTrabajo = grupoTrabajo;
    }
    //-------------------
    //To String
    @Override
    public String toString() {
        return "Directivos{" +
                "grupoTrabajo='" + grupoTrabajo + '\'' +
                '}';
    }
    //-------------------
}
