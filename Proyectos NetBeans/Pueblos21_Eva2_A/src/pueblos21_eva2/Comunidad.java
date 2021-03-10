package pueblos21_eva2;

import java.util.Map;

public class Comunidad {
    private String nombre;
    private String codigo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Comunidad{" + "nombre=" + nombre + ", codigo=" + codigo + '}';
    }
    public String toString2() {
        return "";
    }
    
    void listadoFinal(Map<String,Provincia> listaProv, String codigo){
        listaProv.forEach((String key, Provincia value) -> {
            if(value.getComunidad().equals(codigo)) {
                System.out.println("      .- "+key +value.toString2());
            } 
        });
    }

    public Comunidad(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }
    
}
