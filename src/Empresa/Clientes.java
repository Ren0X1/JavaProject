package Empresa;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Clientes {
    private String nombre;
    private int edad;
    private String tlf;
    private String nif;
    //-------------------
    //GETTER Y SETTER

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

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

    public Clientes(String nombre, int edad, String tlf, String nif) {
        this.nombre = nombre;
        this.edad = compruebaedad(edad);
        this.tlf = compruebatelefono(tlf);
        this.nif = compruebanif(nif);
    }

    //-------------------
    //EQUALS Y HASH CODE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clientes clientes = (Clientes) o;
        return Objects.equals(nif, clientes.nif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif);
    }

    //-------------------
    //To String

    @Override
    public String toString() {
        return "Clientes{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", tlf='" + tlf + '\'' +
                ", nif='" + nif + '\'' +
                '}';
    }

    //-------------------
}

