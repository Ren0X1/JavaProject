package pueblos21_eva2;

public class Provincia {
    private String idprovincia;
    private String provincia;
    private String comunidad;

    public String getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(String idprovincia) {
        this.idprovincia = idprovincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    @Override
    public String toString() {
        return "Provincia{" + "idprovincia=" + idprovincia + ", provincia=" + provincia + ", comunidad=" + comunidad + '}';
    }
    public String toString2() {
        return "";
    }
    public Provincia(String idprovincia, String provincia, String comunidad) {
        this.idprovincia = idprovincia;
        this.provincia = provincia;
        this.comunidad = comunidad;
    }
    
}
