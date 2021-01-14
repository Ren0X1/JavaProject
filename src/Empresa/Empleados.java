package Empresa;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Empleados {
    private String nombre;
    private String nif;
    private int edad;
    private int sueldo;
    //-------------------
    //GETTER Y SETTER

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    //-------------------
    //Metodos de comprobacion
    int compruebaedad (int edad1) {
        if (edad1>0) {
            return edad1;
        }
        return -1;
    }
    String compruebanif(String x) {
        if (validarNIF(x)) {
            return x;
        }
        else {
            return "INVALIDO: "+x;
        }
    }
    static boolean validarNIF(String nif) {
        boolean correcto;
        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher matcher = pattern.matcher(nif);
        if (matcher.matches()) {
            String letra = matcher.group(2);
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int index = Integer.parseInt(matcher.group(1));
            index = index % 23;
            String reference = letras.substring(index, index + 1);
            correcto = reference.equalsIgnoreCase(letra);
        }
        else {
            correcto = false;
        }
        return correcto;
    }

    //-------------------
    //Constructores

    public Empleados(String nombre, String nif, int edad, int sueldo) {
        this.nombre = nombre;
        this.nif = compruebanif(nif);
        this.edad = compruebaedad(edad);
        this.sueldo = sueldo;
    }

    //-------------------
    //EQUALS Y HASH CODE


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleados empleados = (Empleados) o;
        return Objects.equals(nif, empleados.nif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif);
    }

    //-------------------
    //To String

    @Override
    public String toString() {
        return "Empleados{" +
                "nombre='" + nombre + '\'' +
                ", nif='" + nif + '\'' +
                ", edad=" + edad +
                ", sueldo=" + sueldo +
                '}';
    }

    //-------------------
}
