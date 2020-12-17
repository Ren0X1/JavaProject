import java.util.Objects;
public class Fracciones {
    private int numerador;
    private int denominador;
    //GETTER Y SETTER
    public int getNumerador() {return numerador;}
    public void setNumerador(int numerador) {this.numerador = numerador;}
    public int getDenominador() {return denominador;}
    public void setDenominador(int denominador) {this.denominador = denominador;}
    //METODOS
    int asignar(int n) {
        if (n<0) {
            n=n*-1;
        }
        return n;
    }
    Fracciones Multiplicar(Fracciones f1, Fracciones f2) {
        return new Fracciones(f1.numerador*f2.numerador,f1.denominador*f2.denominador);
    }
    Fracciones Sumar(Fracciones f1, Fracciones f2) {
        return new Fracciones((f1.numerador*f2.denominador)+(f1.denominador*f2.numerador),f1.denominador*f2.denominador);
    }
    Fracciones Restar(Fracciones f1, Fracciones f2) {
        return new Fracciones((f1.numerador*f2.denominador)-(f1.denominador*f2.numerador),f1.denominador*f2.denominador);
    }
    Fracciones Dividir(Fracciones f1, Fracciones f2) {
        return new Fracciones(f1.numerador*f2.denominador,f1.denominador*f2.numerador);
    }
    Fracciones Simplificar(Fracciones f){
        int divisor=(int)mcd(f.numerador,f.denominador);
        return new Fracciones(f.numerador/divisor,f.denominador/divisor);
    }

    static double mcd(double n1, double n2) {
        while (n1!=n2) {
            if (n1>n2) {
                n1=n1-n2;
            }
            else {
                n2=n2-n1;
            }
        }
        return n1;
    }

    //CONSTRUCTORES
    public Fracciones(int numerador, int denominador) {
        this.numerador = asignar(numerador);
        this.denominador = asignar(denominador);
    }
    //TO STRING
    @Override
    public String toString() {
        return "Fracciones{" +
                "numerador=" + numerador +
                ", denominador=" + denominador +
                '}';
    }
    public String imprimir() {
        return numerador+"/"+denominador;
    }
    //EQUALS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fracciones that = (Fracciones) o;
        return numerador == that.numerador && denominador == that.denominador;
    }
    public boolean FraccionesEquivalentes(Fracciones f1, Fracciones f2) {
        return (f1.numerador*f2.denominador)==(f1.denominador*f2.numerador);
    }
    //HASHCODE
    @Override
    public int hashCode() {
        return Objects.hash(numerador, denominador);
    }
}