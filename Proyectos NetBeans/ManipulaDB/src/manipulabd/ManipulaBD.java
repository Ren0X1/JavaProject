package manipulabd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ManipulaBD {

    public static void main(String[] args) {
        Connection conn=obtieneConexion("Oracle","localhost","xe","system","system");
        listaSelect("SELECT * FROM EMPLE",conn);
    }
    
    static Connection  obtieneConexion(String tipoBd,String servidor,String nombreBd,String usuario,String contraseña){
        Connection conn=null;
        String url="";
        try {
            if(tipoBd.equalsIgnoreCase("Oracle") ){
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                url="jdbc:oracle:thin:@"+servidor+":1521:"+nombreBd;
            } else {  
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                url="jdbc:mysql://"+servidor+"/"+nombreBd;
            }
            conn=DriverManager.getConnection(url,usuario,contraseña);
        } catch (SQLException ex) {
            System.out.println("Error cargando el Driver -> "+ex.getMessage());
        }
        return conn;
    }

    private static void cabezera(ResultSetMetaData x) throws SQLException {
        int numColum = x.getColumnCount();
        
    }
    static int listaSelect(String sentencia, Connection conn){
        int numFilas=0, numCols=-1;
        Statement stm =null;
        int salario = 0;
        ResultSet rset=null;
        try {
            stm= conn.createStatement();
            rset = stm.executeQuery(sentencia);
            ResultSetMetaData rsMd = rset.getMetaData();
            numCols= rsMd.getColumnCount();
            rsMd.getColumnName(1);   
            for ( int i=1; i<=numCols;i++) {
                String x = rsMd.getColumnName(i)+" ("+rsMd.getColumnTypeName(i)+")";
                System.out.print(x+"\t\t");
            }
            System.out.println("");
            for ( int j=0; j<=numCols;j++) {
                System.out.print("----------------"); 
            }
            System.out.println("");
            while(rset.next()){
                for( int i=1; i<=numCols;i++){
                    System.out.print(rset.getObject(i)+"\t\t");
                }
                numFilas++;
                salario+=(int)rset.getObject("SALARIO");
                System.out.println("");           
            }
            System.out.println("LA EMPRESA TIENE "+numFilas+" EMPLEADOS.");
            System.out.println("LA MEDIA DE SALARIO ES "+salario/numFilas+"€ .");
            
        } catch (SQLException ex) {
            System.out.println("Error creando la sentencia"+ ex.getMessage());
        } finally{
            try {
                  rset.close();stm.close();
            } catch (SQLException ex) {
                  System.out.println("Error cerrando el rset o stm ->"+ex.getMessage());
            }
        }
        return numFilas;
    }
}