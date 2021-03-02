package conectadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectaDB {

    public static void main(String[] args) {
        Connection db=obtieneConexion();
        listaSelect("SELECT * FROM EMPLE",db);
    }
    
    private static Connection obtieneConexion() {
        Connection conn=null;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn=DriverManager.getConnection("jdbc:oracle:system@localhost:1521:xe","system","system");
        } catch(SQLException ex) {
            System.err.println("Error al conectar: "+ex.getMessage());
        }
        return conn;
    }
    
    private static int listaSelect(String sentencia, Connection conn) {
        int numFilas=-1;
        Statement stm=null;
        ResultSet rset=null;
        try {
            stm=conn.createStatement(); 
            rset= stm.executeQuery(sentencia);
            numFilas=rset.getMetaData().getColumnCount();
            while(rset.next()) {
                System.out.println(rset.getString(2)+" : ");
                System.out.println(rset.getString("salario")+" : ");
            }
        } catch(SQLException ex) {
            System.err.println("Error al conectar: "+ex.getMessage());
        } finally {
            try {
                rset.close();
                stm.close();
            } catch (SQLException ex) {
                System.err.println("Error cerrando la conexion: "+ex.getMessage());
            }
        }
        return numFilas;
    }
    
}
