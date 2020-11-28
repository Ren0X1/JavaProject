public class Empleados {
    private String nombre;
    private int edad;
    private int sueldo;
    //-------------------
    //GETTER Y SETTER
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public int getSueldo() { return sueldo; }
    public void setSueldo(int sueldo) { this.sueldo = sueldo; }
    //-------------------
    //Metodos de comprobacion
    int compruebaedad (int edad1) {
        if (edad1>0) {
            return edad1;
        }
        return -1;
    }
    //-------------------
    //Constructores
    public Empleados(String nombre, int edad, int sueldo) {
        this.nombre = nombre;
        this.edad = compruebaedad(edad);
        this.sueldo = sueldo;
    }
    //-------------------
    //To String
    @Override
    public String toString() {
        return "Empleados{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", sueldo=" + sueldo +
                '}';
    }
    //-------------------
}
