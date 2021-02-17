package videoclub;

import java.io.Serializable;

public class Copia implements Serializable{
    private String codigo;
    private int numcopia;
    public Copia(String codigo, int numcopia) {
        this.codigo = codigo;
        this.numcopia = numcopia;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public int getNumcopia() {
        return numcopia;
    }
    public void setNumcopia(int numcopia) {
        this.numcopia = numcopia;
    }
}
