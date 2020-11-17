public class Persona {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int edad;
    private char sexo;//[H/M]
    private String nif;
    private String localidad;
    static int censo;
    public String getNombre() {
        return nombre.toUpperCase();
    }
    public void setNombre(String nom) {
        this.nombre=nom.toLowerCase();
    }
}
