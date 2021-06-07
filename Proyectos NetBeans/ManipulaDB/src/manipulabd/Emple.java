/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulabd;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "EMPLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emple.findAll", query = "SELECT e FROM Emple e")
    , @NamedQuery(name = "Emple.findByEmpNo", query = "SELECT e FROM Emple e WHERE e.empNo = :empNo")
    , @NamedQuery(name = "Emple.findByApellido", query = "SELECT e FROM Emple e WHERE e.apellido = :apellido")
    , @NamedQuery(name = "Emple.findByOficio", query = "SELECT e FROM Emple e WHERE e.oficio = :oficio")
    , @NamedQuery(name = "Emple.findByDir", query = "SELECT e FROM Emple e WHERE e.dir = :dir")
    , @NamedQuery(name = "Emple.findByFechaAlt", query = "SELECT e FROM Emple e WHERE e.fechaAlt = :fechaAlt")
    , @NamedQuery(name = "Emple.findBySalario", query = "SELECT e FROM Emple e WHERE e.salario = :salario")
    , @NamedQuery(name = "Emple.findByComision", query = "SELECT e FROM Emple e WHERE e.comision = :comision")
    , @NamedQuery(name = "Emple.findByDeptNo", query = "SELECT e FROM Emple e WHERE e.deptNo = :deptNo")})
public class Emple implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EMP_NO")
    private Short empNo;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "OFICIO")
    private String oficio;
    @Column(name = "DIR")
    private Short dir;
    @Column(name = "FECHA_ALT")
    @Temporal(TemporalType.DATE)
    private Date fechaAlt;
    @Column(name = "SALARIO")
    private Integer salario;
    @Column(name = "COMISION")
    private Integer comision;
    @Basic(optional = false)
    @Column(name = "DEPT_NO")
    private short deptNo;

    public Emple() {
    }

    public Emple(Short empNo) {
        this.empNo = empNo;
    }

    public Emple(Short empNo, short deptNo) {
        this.empNo = empNo;
        this.deptNo = deptNo;
    }

    public Short getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Short empNo) {
        this.empNo = empNo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public Short getDir() {
        return dir;
    }

    public void setDir(Short dir) {
        this.dir = dir;
    }

    public Date getFechaAlt() {
        return fechaAlt;
    }

    public void setFechaAlt(Date fechaAlt) {
        this.fechaAlt = fechaAlt;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public Integer getComision() {
        return comision;
    }

    public void setComision(Integer comision) {
        this.comision = comision;
    }

    public short getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(short deptNo) {
        this.deptNo = deptNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empNo != null ? empNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emple)) {
            return false;
        }
        Emple other = (Emple) object;
        if ((this.empNo == null && other.empNo != null) || (this.empNo != null && !this.empNo.equals(other.empNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "manipulabd.Emple[ empNo=" + empNo + " ]";
    }
    
}
