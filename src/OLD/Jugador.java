package OLD;

import java.util.Objects;
public class Jugador extends Persona {
    private int calzado;
    private String equipo;
    private String posicion;
    private int dorsal;
    private int tarjetas;
    //GETTER Y SETTER
    public int getCalzado() {
        return calzado;
    }
    public void setCalzado(int calzado) {
        this.calzado = calzado;
    }
    public String getEquipo() {
        return equipo;
    }
    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
    public String getPosicion() {
        return posicion;
    }
    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    public int getDorsal() {
        return dorsal;
    }
    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }
    public int getTarjetas() {
        return tarjetas;
    }
    public void setTarjetas(int tarjetas) {
        this.tarjetas = tarjetas;
    }
    //CONSTRUCTORES
    public Jugador(String nombre, String apellido1, String apellido2, int edad, char sexo, String nif, String localidad, int calzado, String equipo, String posicion, int dorsal, int tarjetas) {
        super(nombre, apellido1, apellido2, edad, sexo, nif, localidad);
        this.calzado = calzado;
        this.equipo = equipo;
        this.posicion = posicion;
        this.dorsal = dorsal;
        this.tarjetas = tarjetas;
    }
    //EQUALS, HASH CODE Y TO STRING
    @Override
    public String toString() {
        return "OLD.Jugador{" +
                "calzado=" + calzado +
                ", equipo='" + equipo + '\'' +
                ", posicion='" + posicion + '\'' +
                ", dorsal=" + dorsal +
                ", tarjetas=" + tarjetas +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return calzado == jugador.calzado &&
                dorsal == jugador.dorsal &&
                tarjetas == jugador.tarjetas &&
                Objects.equals(equipo, jugador.equipo) &&
                Objects.equals(posicion, jugador.posicion);
    }
    @Override
    public int hashCode() {
        return Objects.hash(calzado, equipo, posicion, dorsal, tarjetas);
    }
}

