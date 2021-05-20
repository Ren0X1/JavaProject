/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pueblos21;

import java.sql.Connection;

/**
 *
 * @author Usuario
 */
public class DatosConn {
    private Connection con;
    private String codigoError;
    private String mensajeError;

    public DatosConn() {
        con =null;
        codigoError="";
        mensajeError="";
    }

    public DatosConn(Connection con, String codigoError, String mensajeError) {
        this.con = con;
        this.codigoError = codigoError;
        this.mensajeError = mensajeError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    @Override
    public String toString() {
        return codigoError +" : "+  mensajeError ;
    }
    
    
    
}
