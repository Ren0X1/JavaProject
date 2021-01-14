package OLD;

import java.util.Objects;

public class AlumnoDAW {
    private String centro;
    private int curso;
    private String nombre;
    private String primerapellido;
    private String segundoapellido;
    private char repetidor;
    private char sexo;
    private String datostutor;
    private String nifalumno;
    private int matriculados;
    //GETTER Y SETTER
    public String getCentro() {
        return centro;
    }
    public int getCurso() {
        return curso;
    }
    public void setCurso(int curso) {
        this.curso = curso;
    }
    public void setCentro(String centro) {
        this.centro = centro;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPrimerapellido() {
        return primerapellido;
    }
    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }
    public String getSegundoapellido() {
        return segundoapellido;
    }
    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
    }
    public char getRepetidor() {
        return repetidor;
    }
    public void setRepetidor(char repetidor) {
        this.repetidor = repetidor;
    }
    public char getSexo() {
        return sexo;
    }
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    public String getDatostutor() {
        return datostutor;
    }
    public void setDatostutor(String datostutor) {
        this.datostutor = datostutor;
    }
    public String getNifalumno() {
        return nifalumno;
    }
    public void setNifalumno(String nifalumno) {
        this.nifalumno = nifalumno;
    }
    public int getMatriculados() {
        return matriculados;
    }
    public void setMatriculados(int matriculados) {
        this.matriculados = matriculados;
    }
    //METODOS DE COMPROBACION
    char compruebasexo(char s) {
        String d=Character.toString(s);
        if (d.equals("M") || d.equals("m") || d.equals("V") || d.equals("v")) {
            return s;
        }
        else {
            return 'X';
        }
    }
    char compruebarepite(char s) {
        String d=Character.toString(s);
        if (d.equals("S") || d.equals("s") || d.equals("N") || d.equals("n")) {
            return s;
        }
        else {
            return 'X';
        }
    }
    String compruebanif(String nif) {
        boolean comprobar=nif.matches("[a-z]{16}[0-9]");
        if (comprobar) {
            return nif;
        }
        else {
            return "NIF INVALIDO";
        }
    }
    int compruebacurso(int curso) {
        if (curso==1) {
            return 1;
        }
        else if (curso==2) {
            return 2;
        }
        else {
            return -1;
        }
    }
    //CONSTRUCTORES
    public AlumnoDAW(String centro,int curso, String nombre, String primerapellido, String segundoapellido, char repetidor, char sexo, String datostutor, String nifalumno) {
        this.centro = centro;
        this.curso = compruebacurso(curso);
        this.nombre = nombre;
        this.primerapellido = primerapellido;
        this.segundoapellido = segundoapellido;
        this.repetidor = compruebarepite(repetidor);
        this.sexo = compruebasexo(sexo);
        this.datostutor = datostutor;
        this.nifalumno = compruebanif(nifalumno);
    }
    //TO STRING

    @Override
    public String toString() {
        return "AlumnoDAW{" +
                "centro='" + centro + '\'' +
                ", curso=" + curso +
                ", nombre='" + nombre + '\'' +
                ", primerapellido='" + primerapellido + '\'' +
                ", segundoapellido='" + segundoapellido + '\'' +
                ", repetidor=" + repetidor +
                ", sexo=" + sexo +
                ", datostutor='" + datostutor + '\'' +
                ", nifalumno='" + nifalumno + '\'' +
                '}';
    }


    //EQUALS y HASHCODE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoDAW alumnoDAW = (AlumnoDAW) o;
        return repetidor == alumnoDAW.repetidor &&
                sexo == alumnoDAW.sexo &&
                matriculados == alumnoDAW.matriculados &&
                Objects.equals(centro, alumnoDAW.centro) &&
                Objects.equals(curso, alumnoDAW.curso) &&
                Objects.equals(nombre, alumnoDAW.nombre) &&
                Objects.equals(primerapellido, alumnoDAW.primerapellido) &&
                Objects.equals(segundoapellido, alumnoDAW.segundoapellido) &&
                Objects.equals(datostutor, alumnoDAW.datostutor) &&
                Objects.equals(nifalumno, alumnoDAW.nifalumno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(centro, curso, nombre, primerapellido, segundoapellido, repetidor, sexo, datostutor, nifalumno, matriculados);
    }
}
