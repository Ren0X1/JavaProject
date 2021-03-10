package pueblos21_eva2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
public class Pueblos21_Eva2_A {
    static TreeMap <String, Comunidad> listaComunidades = new TreeMap<>();
    static TreeMap <String, Provincia> listaProvincias = new TreeMap<>();
    public static void main(String[] args) throws Exception {
        int opcion;
        ArrayList menu=new ArrayList();
        menu.add("Lanzar script 'tablas.sql' en SqlPlus \n"+ "\t-> ( 0 Puntos )");     
        menu.add("Cargar datos desde fichero a las tablas\n"+ "(comunidades.txt / provincias.txt)\n"+ "\t->( 2 Puntos ) ó \n"+ "lanzar script 'creaTablasDatos_ComuProv_Oracle.sql'\n"+ "\t -> ( 0 puntos )");
        menu.add("Cargar datos en Memoria Accesibles por\n"+ " Nombre de Comunidad y Nombre Provincia\n"+ "( debéis crear las clases Comunidad y provincia )\n"+ "\t->( 2 Puntos )");
        menu.add("Listado Ordenado alfabeticamente: Comunidades->Provincias\n"+ "(se listaran para cada comunidad sus provincias ordenadas\n"+ "\t->( 2 Puntos )");
        menu.add("Dada una provincia buscar su comunidad\n"+ "\t->( 1,5 Puntos )");
        menu.add("[ Elegir entre 6 y 7 ]\nCrear Fichero con las provincias de una Comunidad dada.\n"+ "\t->( 1,5 Puntos )");
        menu.add("[ Elegir entre 6 y 7 ]\nInsertar una nueva Provincia en una Comunidad concreta ( en Memoria )\n"+ "\t->( 1,5 Puntos )");
        do{
            opcion=pintaMenu("<< Menú Comunidades -> Provincias (Oracle) >>",menu);
            switch(opcion){
                case 1: break;
                case 2:
                    cargarSQL("InsertComunidades.sql");
                    cargarSQL("InsertProvincias.sql");
                    break;
                case 3:
                    cargarMemoria();
                    break;
                case 4:
                    generarListadoComunidades(listaComunidades);
                    generarListadoProvincias(listaProvincias);
                    listadoProvinciaPorComunidades(listaComunidades,listaProvincias);
                    break;
                case 5:
                    String prov=ES.leeDeTeclado("Introduce el nombre de una provincia: ");
                    buscarComunidad(listaComunidades,listaProvincias,prov);
                    break;
                case 6:
                    throw new Exception("METODO NO IMPLEMENTADO");
                case 7:
                    String nuevaprov=ES.leeDeTeclado("Introduce el nombre de la nueva provincia: ");
                    String comunidad=ES.leeDeTeclado("Introduce el codigo de la comunidad: ");
                    insertarProv(nuevaprov, comunidad);
                    break;
            }
        } while(opcion!=menu.size()+1);
    }
    private static void insertarProv(String prov, String comu) {
        String numero=Integer.toString(listaProvincias.size()+1);
        Provincia provi = new Provincia(numero,prov,comu);
        listaProvincias.put(prov,provi);
    }
    private static void buscarComunidad(Map<String,Comunidad> x, Map<String,Provincia> y, String prov) {
        y.forEach((key, value) -> {
            if (value.getProvincia().equals(prov)) {
                x.forEach((key1, value1) -> {
                    if(value.getComunidad().equals(value1.getCodigo())) {
                        System.err.println("LA COMUNIDAD DE LA PROVINCIA "+prov+" ES "+value1.getNombre());
                    }
                });
            }
        });
    }
    private static void listadoProvinciaPorComunidades(Map<String,Comunidad> x, Map<String,Provincia> y) {
        System.out.println("LISTADO DE COMUNIDADES Y PROVINCIAS: ");
        System.out.println("------------------------");
        x.forEach((key, value) -> {
            System.out.println(".- "+key +value.toString2());
            value.listadoFinal(y, value.getCodigo());
        });
        System.out.println("------------------------");
        System.out.println();
    }
    private static void generarListadoComunidades(Map<String,Comunidad> x) {
        System.out.println("LISTADO DE COMUNIDADES: ");
        System.out.println("------------------------");
        x.forEach((key, value) -> System.out.println(".- "+key +value.toString2()));
        System.out.println("------------------------");
        System.out.println();
    }
    private static void generarListadoProvincias(Map<String,Provincia> x) {
        System.out.println("LISTADO DE PROVINCIAS:  ");
        System.out.println("------------------------");
        x.forEach((key, value) -> System.out.println(".- "+key +value.toString2()));
        System.out.println("------------------------");
        System.out.println();
    }
    private static void cargarMemoria() {
        Connection db=obtieneConexion();
        cargarMemoriaComunidades("SELECT * FROM COMUNIDADES",db);
        cargarMemoriaProvincias("SELECT * FROM PROVINCIAS",db);
    }
    private static void cargarSQL(String nombreArchivo) {
        Connection db=obtieneConexion();
        try {
            String linea;
            FileReader file = new FileReader("src/datos/"+nombreArchivo);
            BufferedReader bf = new BufferedReader(file);
            while((linea = bf.readLine()) != null){
                insertarDatos(linea,db);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error al encontrar archivo: "+ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
    }
    private static boolean insertarDatos(String sentencia, Connection conn) {
        Statement stm=null;
        try {
            stm=conn.createStatement();
            stm.executeUpdate(sentencia);
            return true;
        } catch(SQLException ex) {
            System.err.println("Error al ejecutar: "+ex.getMessage()+sentencia);
            return false;
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                System.err.println("Error cerrando la conexion: "+ex.getMessage());
                return false;
            }
        }
    }
    private static int cargarMemoriaComunidades(String sentencia, Connection conn) {
        int numFilas=-1;
        Statement stm=null;
        ResultSet rset=null;
        int cnt=0;
        try {
            stm=conn.createStatement(); 
            rset= stm.executeQuery(sentencia);
            numFilas=rset.getMetaData().getColumnCount();
            while(rset.next()) {
                Comunidad comu = new Comunidad(rset.getString(1),rset.getString(2));
                listaComunidades.put(rset.getString(1),comu);
                cnt++;
            }
            System.out.println(cnt+ " COMUNIDADES CARGARDAS CON EXISTO.");
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
    private static int cargarMemoriaProvincias(String sentencia, Connection conn) {
        int numFilas=-1;
        Statement stm=null;
        ResultSet rset=null;
        int cnt=0;
        try {
            stm=conn.createStatement(); 
            rset= stm.executeQuery(sentencia);
            numFilas=rset.getMetaData().getColumnCount();
            while(rset.next()) {
                Provincia provi = new Provincia(rset.getString(1),rset.getString(2),rset.getString(3));
                listaProvincias.put(rset.getString(2),provi);
                cnt++;
            }
            System.out.println(cnt+ " PROVINCIAS CARGARDAS CON EXISTO.");
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
    private static Connection obtieneConexion() {
        Connection conn=null;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
        } catch(SQLException ex) {
            System.err.println("Error al conectar: "+ex.getMessage());
        }
        return conn;
    }
    private static int pintaMenu(String titulo,ArrayList<String> menu){
        int opcion=0,i;
        do{
            System.out.println("_______________________________________________");
            System.out.println("\t"+titulo);
            System.out.println("_______________________________________________");
            i=1;
            for (String opcionMenu : menu) {
                System.out.println(i+".- "+opcionMenu);
                i++;
            }
            System.out.println(i+".- Salir");
            opcion=ES.leeN("Introduzca opcion: ");
            if(opcion > menu.size()+1) {
                System.out.println("Escribe una opción válida!");   
            }
        } while(opcion > menu.size()+1);
        return opcion;   
    }
}
