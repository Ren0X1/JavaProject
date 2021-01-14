package Empresa;

public class Directivos extends Empleados {
    private String grupoTrabajo;
    //-------------------
    //GETTER Y SETTER
    public String getGrupoTrabajo() { return grupoTrabajo; }
    public void setGrupoTrabajo(String grupoTrabajo) { this.grupoTrabajo = grupoTrabajo; }
    //-------------------
    //Constructores
    public Directivos(String nombre, int edad, int sueldo, String grupoTrabajo, String nif) {
        super(nombre, nif, edad, sueldo);
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

