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
    public void setApellido2(String apellido) {
        this.apellido2=apellido.toLowerCase();
    }
    //----------------------------------------
    //EDAD
    private int edad;
    public int getEdad() {return edad;}
    public void setEdad(int edad1) {
        if (edad1>0) {
            this.edad=edad1;
        }
        else {
            System.err.println("EDAD INVALIDA");
        }
    }
    //----------------------------------------
    //SEXO
    private char sexo;//[H/M]
    public char getSexo() {return sexo;}
    public void setSexo(char s) {
        actualizarSexo(s);
        /*switch (s) {
            case 'H':
                actualizarSexo(s);
            case 'h':
                actualizarSexo(s);
            case 'M':
                actualizarSexo(s);
            case 'm':
                actualizarSexo(s);
            default:
                System.err.println("SEXO INVALIDO");
        }*/
    }
    void actualizarSexo(char s) {this.sexo=s;}
    //----------------------------------------
    //NIF
    private String nif;
    public String getNIF() {return nif.toUpperCase();}
    public void setNIF(String nif) {
        this.nif=nif.toLowerCase();
    }
    //----------------------------------------
    //LOCALIDAD
    private String localidad;
    public String getLoc() {return localidad.toUpperCase();}
    public void setLoc(String loc) {
        this.localidad=loc.toLowerCase();
    }
    //----------------------------------------
    //CENSO
    static int censo=0;
    public int getCenso() {return censo;}
    //----------------------------------------
    //CONSTRUCTORES
    public Persona(String nombre, String apellido1, String apellido2, int edad, char sexo, String nif, String localidad) {
        censo++;
        setNombre(nombre);
        setApellido1(apellido1);
        setApellido2(apellido2);
        setEdad(edad);
        setSexo(sexo);
        setNIF(nif);
        setLoc(localidad);
    }
    //----------------------------------------


}
