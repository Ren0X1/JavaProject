import java.util.regex.*;
public class Persona {
    //----------------------------------------
    //NOMBRE
    private String nombre;
    public String getNombre() {return nombre.toUpperCase();}
    public void setNombre(String nombre) {
        this.nombre=nombre.toLowerCase();
    }
    //----------------------------------------
    //APELLIDO1
    private String apellido1;
    public String getApellido1() {return apellido1.toUpperCase();}
    public void setApellido1(String apellido) {
        this.apellido1=apellido.toLowerCase();
    }
    //----------------------------------------
    //APELLIDO2
    private String apellido2;
    public String getApellido2() {return apellido2.toUpperCase();}
    public void setApellido2(String apellido) {this.apellido2=apellido.toLowerCase();}
    //----------------------------------------
    //EDAD
    private int edad;
    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}
    int compruebaedad(int edad1) {
        if (edad1>0) {
            return edad1;
        }
        else {
            return -1;
        }
    }
    //----------------------------------------
    //SEXO
    private char sexo;//[H/M]
    public char getSexo() {return sexo;}
    public void setSexo(char sexo) {this.sexo = sexo;}
    char compruebasexo(char s) {
        String d=Character.toString(s);
        if (d.equals("M") || d.equals("m") || d.equals("H") || d.equals("h")) {
            return s;
        }
        else {
            return 'X';
        }
    }
    //----------------------------------------
    //NIF
    private String nif;
    public String getNIF() {return nif.toUpperCase();}
    public void setNif(String nif) { this.nif = nif.toLowerCase(); }
    String compruebanif(String x) {
        if (validarNIF(x)) {
            return x;
        }
        else {
            return "NIF INVALIDDO INTRODUCIDO";
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
    //----------------------------------------
    //LOCALIDAD
    private String localidad;
    public String getLoc() {return localidad.toUpperCase();}
    public void setLoc(String loc) {this.localidad=loc.toLowerCase();}
    //----------------------------------------
    //CENSO
    static int censo=0;
    //----------------------------------------
    //CONSTRUCTORES
    public Persona(String nombre, String apellido1, String apellido2, int edad, char sexo, String nif, String localidad) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = compruebaedad(edad);
        this.sexo = compruebasexo(sexo);
        this.nif = compruebanif(nif);
        this.localidad = localidad;
    }
    //----------------------------------------
    //TO STRING
    @Override
    public String toString() {
        return "Persona {" +
                "Nombre='" + nombre + '\'' +
                ", PrimerApellido='" + apellido1 + '\'' +
                ", SegundoApellido='" + apellido2 + '\'' +
                ", Edad=" + edad +
                ", Sexo=" + sexo +
                ", NIF='" + nif + '\'' +
                ", Localidad='" + localidad + '\'' +
                '}';
    }
    //----------------------------------------

}
