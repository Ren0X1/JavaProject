/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import ES.ES;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Pedro
 */
public class GestionaBd {
    
    private Connection conn;
    private String tipoBd;
    private String servidor;
    private String bd;
    private String usuario;
    private String pw;

    public GestionaBd(String tipoBd, String servidor, String bd, String usuario, String pw) {
        this.tipoBd = tipoBd;
        this.servidor = servidor;
        this.bd = bd;
        this.usuario = usuario;
        this.pw = pw;
        conn= conectaBD( tipoBd, servidor, bd,usuario,  pw);
    }
    //----------------------------------------------------------------------------
  
     public Connection conectaBD(String tipoBd,String servidor,String bd, 
                                    String usuario, String pw){
         Connection conn=null;
         String url="";
        try {
            // 2)  Cargar el driver en memoria.............
            if(tipoBd.equalsIgnoreCase("MySql")){
              DriverManager.registerDriver( new com.mysql.jdbc.Driver());
              url="jdbc:mysql://"+servidor+"/"+bd;  
              
               conn =DriverManager.getConnection(url, usuario, pw);
                
            }
            else if(tipoBd.equalsIgnoreCase("oracle")){
              DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver());
              url="jdbc:oracle:thin:@"+servidor+":1521:"+bd;  
              //url="jdbc:oracle:thin:@localhost:1521:xe";  
               conn =DriverManager.getConnection(url, usuario, pw);
            } 
            else System.out.println("Bd no Soportada....");
       
            
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la BD -> "+ ex);
            //ex.printStackTrace();
        } catch (Exception ex) {
           System.out.println("Error cargan el driver de la BD -> "+ ex.getMessage());
        }finally{
           return conn; 
        }
         
     }
    //-----------------------------------------------------------------
     public void listarTablaBD(String tabla){
      
      Statement stm =null;
      ResultSet rst =null;
      
        if (conn==null)
             System.out.println("No Hay conexión con la BD....");
         
         else{
            
            try {
                stm=conn.createStatement();
                
                //rst=stm.executeQuery("select * from "+tabla);
                rst=stm.executeQuery(tabla);
                
                ResultSetMetaData rsetMD=rst.getMetaData();
                
                //imprimir las cabeceras..............................
                for(int i=1; i<=rsetMD.getColumnCount(); i++)
                    System.out.print(rsetMD.getColumnName(i)+"\t\t");
                System.out.println("\n------------------------------------------------------------");
                
                 while(rst.next()){
                    for(int i=1; i<=rsetMD.getColumnCount(); i++)
                         System.out.print(rst.getObject(i)+"\t\t\t");
                     System.out.println("");
                 }
                
            } catch (SQLException ex) {
                System.out.println("Error al crear la Sentencia ->" + ex.getMessage());
            }finally{
                try {
                    stm.close();
                    if(rst!=null)
                        rst.close();
                } catch (SQLException ex) {
                    System.out.println("Cerrando stm y rst..->"+ ex.getMessage());
                }
            }
            
        }
         
     }
    //------------------------------------------------------ 
     public void lanzaScript(String fichero){
         File file = new File(fichero);
         FileReader fr;
         BufferedReader br; 
         String sentencia,script="",sentencias[];
         Statement stm=null;
         
         if(file.exists()){
             
             try {
                  fr = new FileReader(file);
                  //br = new BufferedReader(fr);
                  Scanner sc= new Scanner(fr);
                  sc.useDelimiter(";");
                  
                  stm=conn.createStatement();
                  while(sc.hasNext()){
                      sentencia=sc.next();
                      stm.executeUpdate(sentencia);
                  }
                  
                /*  
                  while( (linea=br.readLine())!=null)
                      script+=linea; 
                  
                  sentencias=script.split(";");
                  
                  stm=conn.createStatement();
                  for(int i=0;i<sentencias.length;i++)
                      stm.executeUpdate(sentencias[i]);*/
                  
                 
             } catch (FileNotFoundException ex) {
                 System.out.println("Error: no se encuentra el fichero dcon los scripts -> "
                                        +ex);
             } catch (SQLException ex) {
                 System.out.println("Error: Sql -> " +ex);
             }finally{
                 try {
                     stm.close();
                 } catch (SQLException ex) {
                    System.out.println("Error: cerrando stm -> " +ex);
                 }
             }
             
             
         }
         else System.out.println("No se encronto el fichero-> "+fichero);
         
     }
     //-------------------------------------------------------------------
     public int cargaTabla(String tabla, String fichero, String separador){
         File file = new File(fichero);
         FileReader fr;
         BufferedReader br; 
         String linea, campos[],insert;
         Statement stm;
         int filas=0;
     
         if(file.exists()){
            
             try {
                 fr = new FileReader(file);
                 br = new BufferedReader(fr);
                 
                 stm = conn.createStatement();
                 
                  while( (linea=br.readLine())!=null){
                      campos=linea.split(separador);
                      insert="insert into "+tabla+" values("
                              +"'"+campos[0]+"',"
                              +"'"+campos[1]+"')";
                      stm.executeUpdate(insert);
                      filas++;
                  }
                 
                 
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(GestionaBd.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(GestionaBd.class.getName()).log(Level.SEVERE, null, ex);
             } catch (SQLException ex) {
                 System.out.println("Error SQL inesperado-> ");
                 ex.printStackTrace();
             }
         
         
         }
         else{
             System.out.println("El fichero de datos no se encuetra...");
         }
         return filas;
     }
     
      //-------------------------------------------------------------------
     public int cargaTablaPrepare(String tabla, String fichero, String separador){
         File file = new File(fichero);
         FileReader fr;
         BufferedReader br; 
         String linea, campos[],insert;
         PreparedStatement stm;
         int filas=0;
     
         if(file.exists()){
            
             try {
                 fr = new FileReader(file);
                 br = new BufferedReader(fr);
                 
                 stm = conn.prepareStatement("insert into "+tabla+" values(?,?)");
                 
                  while( (linea=br.readLine())!=null){
                      campos=linea.split(separador);
                              stm.setString(1,campos[0]);
                              stm.setString(2,campos[1]);
                    
                      stm.executeUpdate();
                      filas++;
                  }
                 
                 
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(GestionaBd.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(GestionaBd.class.getName()).log(Level.SEVERE, null, ex);
             } catch (SQLException ex) {
                 System.out.println("Error SQL inesperado-> ");
                 ex.printStackTrace();
             }
         
         
         }
         else{
             System.out.println("El fichero de datos no se encuetra...");
         }
         return filas;
     }
     //-------------------------------------------------------------------
     public String seleccionTablaBd(){
         ArrayList listaTablas= new ArrayList();
         String tabla="",tablasBd;
        try {
            
            if (tipoBd.equalsIgnoreCase("mysql"))
                 tablasBd="select table_name FROM information_schema.tables WHERE table_schema = '"+bd+"'";
            else tablasBd="select table_name tablas_disponibles from user_tables";
            
            ResultSet rst= conn.createStatement().executeQuery(tablasBd);
            
            while(rst.next()){
                System.out.println(rst.getString(1));
                listaTablas.add(rst.getString(1));
            }
            do{
               tabla=ES.leeDeTeclado("Selecciona Tabla ?  )");
            }while(! listaTablas.contains(tabla) );
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionaBd.class.getName()).log(Level.SEVERE, null, ex);
        }
                   
     return tabla;
         
     }
     //----------------------------------
    public int cargaTabla2(String tabla, String fichero, String separador){
        
        FileReader fr   =null;
      //  Connection conn =null;
        Scanner scl   =null, sc=null;
        String linea="",codigo,nombre,insert="";
        String campo = "";
        ArrayList<String> types=new ArrayList();
        ResultSet rset;
        int filas=0; 
        try {
           // conn = conectaOracle();
            Statement sentencia= conn.createStatement();
            // Procesamos el fichero..............
            //fr  = new FileReader(ruta+fichero);
            fr  = new FileReader(fichero);
            sc  = new Scanner(fr);
            
            // Obtener el tipo de las columns......................................
             rset=sentencia.executeQuery("select data_type from user_tab_columns " +
                                        "where table_name='"+tabla+"'");
                 while (rset.next()){ 
                  types.add(rset.getString("data_type") );
                  
                 }
            
            // Opcion 2..............................................................     
            
            rset=sentencia.executeQuery("select * from "+tabla+" where ROWNUM < 1 ");
            ResultSetMetaData rstMd=rset.getMetaData();
            
            while(sc.hasNextLine()){
                linea =sc.nextLine();
                scl= new Scanner(linea );
                scl.useDelimiter(separador); 
                 
                insert="insert into "+tabla+" values (";
                 
                int i=0;       
                while(scl.hasNext()){
                    campo=scl.next();
                  //  if( types.get(i).indexOf("VARCHAR")>=0)
                    if( types.get(i).equalsIgnoreCase("VARCHAR2"))
                        insert+="'"+campo+"',";
                    else insert+=campo+",";
                    
                    System.out.println("rstMd.getColumnTypeName-> "+ rstMd.getColumnTypeName(i+1));
                    
                    i++;
                }
                insert= insert.substring(0, insert.length()-1);
                insert+=")";
                System.out.println("insert= "+insert);
                sentencia.executeUpdate(insert);
                filas++;
            }
                      
        } catch (SQLException ex) {
            System.out.println("Error SQL->" +ex.getMessage() );
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionaBd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
                conn.close();
            } catch (IOException ex) {
                System.out.println("Error cerrando el fichero->"+fichero+": "+ex);
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexio BD ->"+ex);
            }
        }
        return filas;
        
    }//carga TAbla
    //--------------------------------------------
      //----------------------------------
     public int cargaTablaGenerico(String tabla, String fichero, String separador){
        
        FileReader fr   =null;
        Scanner scl   =null, sc=null;
        String linea="",insert="",consulta="";
        String campo = "";
        ResultSet rset =null;
        Statement sentencia=null;
        int filas=0; 
        
        ArrayList<String> tipos=new ArrayList();
        
        try {
           
            sentencia= conn.createStatement();
            // Procesamos el fichero..............
           
            fr  = new FileReader(fichero);
            sc  = new Scanner(fr);
           
            
            // Obtener los metadados de la tabla a partir del resultSet.....
            // más fácil pero menos eficiente sería -> select *.....
            switch(this.tipoBd.toUpperCase()){
                case "MYSQL": consulta="select * from "+tabla+" LIMIT 1";
                    break;
                case "ORACLE":consulta="select * from "+tabla+" where ROWNUM < 1 ";
                    break;
            }
            rset=sentencia.executeQuery(consulta);
            ResultSetMetaData rstMd=rset.getMetaData();
            //Cargamos los tipos........................
            for(int i=1;i<= rstMd.getColumnCount(); i++) 
                  tipos.add(rstMd.getColumnTypeName(i));
            
            while(sc.hasNextLine()){
                linea =sc.nextLine();
                scl= new Scanner(linea );
                scl.useDelimiter(separador); 
                 
                insert="insert into "+tabla+" values (";
                 
                int i=0;       
                while(scl.hasNext()){
                    campo=scl.next();
                    // opcion ( 1 )...................................
                    /*if( rstMd.getColumnTypeName(i).indexOf("CHAR")>=0)
                         insert+="'"+campo+"',";
                    else insert+=campo+",";**************/
                    
                    // opcion ( 2 )...................................
                    switch(tipos.get(i)){
                        
                        case "CHAR":
                        case "VARCHAR":
                        case "VARCHAR2":insert+="'"+campo+"',";
                             break;
                        default        :insert+=campo+","; 
                    }
                    i++;
                }
                insert= insert.substring(0, insert.length()-1);
                insert+=")";
                System.out.println("insert= "+insert);
                sentencia.executeUpdate(insert);
                filas++;
            }
                      
        } catch (SQLException ex) {
            System.out.println("Error SQL->" +ex.getMessage() );
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el fichero de datos-> "+ fichero);
        } finally {
            try {
                fr.close();
                rset.close();sentencia.close();
                //conn.close();
            } catch (IOException ex) {
                System.out.println("Error cerrando el fichero->"+fichero+": "+ex);
            } catch (SQLException ex) {
                System.out.println("Error cerrando ResultSet -> "+ ex.getMessage());
            } 
        }
        return filas;
        
    }//carga TAbla

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getTipoBd() {
        return tipoBd;
    }

    public void setTipoBd(String tipoBd) {
        this.tipoBd = tipoBd;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
    
    
}//class






