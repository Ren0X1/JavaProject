package OLD;

public class Clientes {
    private String nombre;
    private int edad;
    private String tlf;
    //-------------------
    //GETTER Y SETTER
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getTlf() { return tlf; }
    public void setTlf(String tlf) { this.tlf = tlf; }
    //-------------------
    //Metodos de comprobacion
    int compruebaedad (int edad1) {
        if (edad1>0) {
            return edad1;
        }
        return -1;
    }
    String compruebatelefono (String x) {
        if (x.length()!=9) {
            return "TELEFONO INVALIDO";
        }
        return x;
    }
    //-------------------
    //Constructores
    public Clientes(String nombre, int edad, String tlf) {
        this.nombre = nombre;
        this.edad = compruebaedad(edad);
        this.tlf = compruebatelefono(tlf);
    }
    //-------------------
    //To String
    @Override
    public String toString() {
        return "Clientes{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", tlf='" + tlf + '\'' +
                '}';
    }
    //-------------------
}
