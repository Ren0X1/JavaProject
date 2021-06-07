package instituto_alejandromendoza;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "alumnos")
@NamedQueries({
    @NamedQuery(name = "Alumnos.findAll", query = "SELECT a FROM Alumnos a")})
public class Alumnos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "ALUMNO")
    private String alumno;
    @Id
    @Basic(optional = false)
    @Column(name = "NIE")
    private Integer nie;
    @Column(name = "CP")
    private String cp;
    @Column(name = "LOCALIDAD")
    private String localidad;
    @Basic(optional = false)
    @Column(name = "PROVINCIA")
    private String provincia;
    @Column(name = "GRUPO")
    private String grupo;

    public Alumnos() {
    }

    public Alumnos(Integer nie) {
        this.nie = nie;
    }

    public Alumnos(Integer nie, String provincia, int partidas, int ganadas) {
        this.nie = nie;
        this.provincia = provincia;
    }

    public Alumnos(String alumno, Integer nie, String cp, String localidad, String provincia, String grupo, int partidas, int ganadas) {
        this.alumno = alumno;
        this.nie = nie;
        this.cp = cp;
        this.localidad = localidad;
        this.provincia = provincia;
        this.grupo = grupo;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public Integer getNie() {
        return nie;
    }

    public void setNie(Integer nie) {
        this.nie = nie;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nie != null ? nie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumnos)) {
            return false;
        }
        Alumnos other = (Alumnos) object;
        if ((this.nie == null && other.nie != null) || (this.nie != null && !this.nie.equals(other.nie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "institutointerfaz.Alumnos[ nie=" + nie + " ]";
    }
    
}
