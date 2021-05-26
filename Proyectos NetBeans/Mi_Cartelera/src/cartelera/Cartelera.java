/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartelera;

import BD.GestionaBd;
import ES.ES;
import static Miscelanea.Miscelanea.pintaMenu;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author usuario
 */
public class Cartelera  implements Comparator{

    static String ruta = "src/actores/";
    static GestionaBd connMySql;
    
    //Peliculas........................
    static TreeSet<Peliculas> peliculasTitulo   = new TreeSet();
    static TreeSet<Peliculas>  peliculasGenero  = new TreeSet(new Cartelera());  
    
    static TreeMap<String,TreeSet<Peliculas>> pelisDeUnDirector = new TreeMap();
    static TreeMap<String,TreeSet<Peliculas>> pelisDeUnGenero   = new TreeMap();
    
    //Usuarios............................................
    static TreeMap<String,Usuarios> listaDeUsuarios = new TreeMap();
    
    
    public static void main(String[] args) {
        // TODO code application logic hereint opcion, opcion2, opcion3, filas;
        int opcion,opcion1, opcion2, opcion3;
        ArrayList menu = new ArrayList();

        menu.add("Informes...");//1
        menu.add("Altas...");//2
        menu.add("Puntuaciones...");//2

        ArrayList subMenuListados = new ArrayList();

        subMenuListados.add("Listado completo de Peliculas por << Título >>.");
        subMenuListados.add("Listado completo de Péliculas por << Género >>.");

        subMenuListados.add("Listado de  Péliculas de un << Director >>.");//2.3
        subMenuListados.add("Listado de  Péliculas de un << Género >>.");//2.3
        
        ArrayList subMenuAltas = new ArrayList();
        subMenuAltas.add("Alta Péliculas.");//3
        subMenuAltas.add("Alta Usuarios.");//3  
        
        ArrayList subMenuPuntuaciones = new ArrayList();
        subMenuPuntuaciones.add("Puntuar una Pélicula...");//8
        subMenuPuntuaciones.add("Ver la puntuación media de una Pélicula...");//9

        connMySql = new GestionaBd("mysql","localhost","scott","scott","tiger1");
        
        cargaDatosBdMemoria();
        do {
            opcion = pintaMenu("Menú Principal...",menu);

            switch (opcion) {
                case 1://1.MENU INFORMES
                    opcion1 = pintaMenu("Submenú INFORMES...",subMenuListados);
                    while (opcion1 != subMenuListados.size() + 1) {

                        switch (opcion1) {
                            case 1://1.1 Pelis Titulo...
                                System.out.println("Pelis Titulo...");
                                System.out.println(peliculasTitulo);
                            break;
                            case 2://1.1 Pelis Genero...
                                System.out.println("Pelis Genero...");
                                System.out.println(peliculasGenero);
                            break;
                            case 3://1.1 Péliculas de un << Director >>
                                System.out.println("Péliculas de un << Director >>");
                                String director;
                                
                                System.out.println(pelisDeUnDirector.keySet());
                                director= ES.leeDeTeclado("Nombre del Director?");
                                
                                while(!pelisDeUnDirector.containsKey(director)){
                                    ES.leeDeTeclado("Director Erroneo, prueba de nuevo...");
                                    
                                    System.out.println(pelisDeUnDirector.keySet());
                                    director= ES.leeDeTeclado("Nombre del Director?");
                                }
                                
                                System.out.println("Lista de las peliculas del Director->"+ director);
                                
                                System.out.println(pelisDeUnDirector.get(director));
                                
                            break;
                            case 4://1.1 Péliculas de un << Genero >>
                                System.out.println("Péliculas de un << Genero >>");
                                String generos=seleccionaPGAM(pelisDeUnGenero, "Genero");
                                
                                System.out.println(pelisDeUnGenero.get(generos));
                                
                                
                            break;
                        }
                        opcion1 = pintaMenu("Submenu Listados",subMenuListados);
                    }//while    

                    break;

                case 2: //2.MENU ALTAS
                    opcion2 = pintaMenu("Submenú ALTAS...",subMenuAltas);

                    while (opcion2 != subMenuAltas.size() + 1) {

                        switch (opcion2) {
                            case 1://2.1 "Alta Péliculas."
                                //Critobal...
                               altaPelicula();
                                
                                break;

                            case 2: //2.2 "Alta Usuarios."
                                Usuarios us=altaUsuario();
                                listaDeUsuarios.put(us.getLogin(),us);
                                break;
                           
                        }
                        opcion2 = pintaMenu("Submenu Listados",subMenuAltas);
                    }//while
                    break;
                case 3: //3. PUNTUACINES....

                    opcion3 = pintaMenu(" Submenu Puntuaciones ", subMenuPuntuaciones);

                    while (opcion3 != subMenuPuntuaciones.size() + 1) {

                        switch (opcion3) {

                            case 1://3.1 Puntuar Peli
                               puntuaciones();
                                break;
                            case 2://3.2 Ver Puntuacion Peli

                                break;
                        }
                        opcion3 = pintaMenu("Submenu Notas...",subMenuPuntuaciones);
                    }//while

                    break;
               
                default:
                    if (opcion == menu.size() + 1) {
                        System.out.println("Adios....");
                    }
            }//Switch..........

        } while (opcion != menu.size() + 1);
    }// Main-----------------------------------------------------
    //-----------------------------------------------------
    static void cargaDatosBdMemoria(){
        
         ResultSet rs;
         Peliculas Peli;
        
        try {
            
            rs=connMySql.getConn().createStatement().executeQuery("Select * from peliculas");
            
            while(rs.next()){
                Peli =new Peliculas(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getBoolean(5),
                            rs.getBoolean(6),
                            rs.getDate(7),
                            rs.getString(8)
                );
                peliculasTitulo.add(Peli);
                peliculasGenero.add(Peli);  
                
                // Pelis por Director.................................
                if(pelisDeUnDirector.containsKey(Peli.getDirector())){
                    pelisDeUnDirector.get(Peli.getDirector()).add(Peli);
                }
                else{
                    pelisDeUnDirector.put(Peli.getDirector(),new TreeSet<Peliculas>());
                    pelisDeUnDirector.get(Peli.getDirector()).add(Peli);
                }
                // Pelis por Genero.................................
                if(pelisDeUnGenero.containsKey(Peli.getGenero())){
                    pelisDeUnGenero.get(Peli.getGenero()).add(Peli);
                }
                else{
                    pelisDeUnGenero.put(Peli.getGenero(),new TreeSet<Peliculas>());
                    pelisDeUnGenero.get(Peli.getGenero()).add(Peli);
                }
                
            }
            
        } catch (SQLException ex) {
            System.out.println("Error cargando pelis..."+ ex.getMessage());
        }
        
    }

  //--------------------------------------------------------------------
   @Override
    public int compare(Object o1, Object o2) {
     Peliculas peli1=(Peliculas)o1;
     Peliculas peli2=(Peliculas)o2;
     
     int compara;
        compara=peli1.getGenero().compareToIgnoreCase(peli2.getGenero());
     if(compara==0)
             return peli1.getNombre().compareToIgnoreCase(peli2.getNombre());
      
     return compara;
     
    }
//--------------------------------------------------------------
   static String seleccionaPGAM(Object lista, String mensaje) {
       
       boolean noCancelar=true;
       TreeSet listado;
       String elto;
       boolean treeset=false;
       try{
        TreeMap mapa=(TreeMap)lista;
        listado = new TreeSet(mapa.keySet());
        }catch(ClassCastException ex){
         listado= (TreeSet)lista;
         treeset=true;
        }
        
           do{
            
            System.out.println("Lista de "+mensaje);
            System.out.println(listado);
            elto = ES.leeDeTeclado("Elige el nombre del " + mensaje + " que quiere elejir (escribe salir para finalizar)? ");
           
            if(elto.equalsIgnoreCase("salir")){
               return "cancelado";}
            
            if(!listado.contains(elto)){
                   System.out.println(mensaje+ " erroneo");
                   ES.leeDeTeclado("pulse una tecla para continuar ");
           }
            
           
        }  while(!listado.contains(elto)&& elto!="salir");
           
           
         //  else
            return elto;
         
    }
   //-------------------------------------------------------------------
   static Object altaPelicula(){
       
            String nombre, director, genero,imagen,actores = null,nomActores = "";
            int estreno,publico = 0,direMensaje, nActores;
            boolean estreno1,publico1;
            Date date = new Date();
            FileWriter fw       =null;
            BufferedWriter bw   =null;
            
            int codPeli=peliculasTitulo.size()+2;
            nombre= ES.leeDeTeclado("inserta el nombre de la pelicula");
            while(nombre==""){
                nombre= ES.leeDeTeclado("No pueded insertar un nombre vacio, inserte un nombre de nuevo:");
            }
            direMensaje= ES.leeN("Escribe 1 si quieres insertar un director nuevo o escribe 2 si quieres elegir uno de la lista de directores.",1,2);
            if(direMensaje==1){
                director=ES.leeDeTeclado("Elige el Nombre del nuevo director");
                while(director==""){
                    director= ES.leeDeTeclado("No pueded insertar un nombre vacio, inserte un nombre de nuevo:");
                }
            }
            else{
                director= seleccionaPGAM(pelisDeUnDirector, "director");
                if (director.equalsIgnoreCase("cancelado") ) {
                    System.out.println("el usuario ha cancelado el proceso");
                    return null;
                }
            }
            genero= seleccionaPGAM(pelisDeUnGenero, "Genero");
            if (genero.equalsIgnoreCase("cancelado") ) {
                System.out.println("el usuario ha cancelado el proceso");
                return null;
            }
            
            estreno=ES.leeN("Escribe 1 si ya se ha estrenado o 0 si no se ha estrenado", 0, 1);
            if (estreno==1){
                estreno1=true;
                
                publico=ES.leeN("Escribe 1 si se estreno con publico o 0 si fue sin publico", 0, 1);
                if(publico==1)
                    publico1=true;
                else
                    publico1=false;
            }
            else{
                estreno1=false;
                publico1=false;
            }
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            imagen=seleccionaFichero("src/caratulas");
            
            nActores= ES.leeN("Cuantos actores tiene la pelicula");
            for (int i = 1; i <= nActores; i++) {
                
                actores=ES. leeDeTeclado("Nombre del actor numero "+i);
                nomActores+= actores+", ";
            }
            System.out.println(nomActores.substring(0, nomActores.length()-2));
            try {
                fw = new FileWriter(ruta+"actores.txt", true);
                bw = new BufferedWriter(fw);
                bw.write("\n"+codPeli+";"+nomActores.substring(0, nomActores.length()-2));
                bw.close();
            } catch (IOException ex) {
                System.out.println("Error Sql ->"+ ex.getMessage());
            }finally{
                try {
                    fw.close();
                    bw.close();
                } catch (IOException ex) {
                    System.out.println("Error Sql ->"+ ex.getMessage());
                }
            }
            try{
            //insertamos la pelicula en la bd...........
            connMySql.getConn().createStatement().executeUpdate(
                    "INSERT INTO Peliculas (codigo_pelicula,nombre,director,genero,estreno,publico,fecha_insercion,imagen) "
                            + "VALUES("+codPeli+",'"+nombre+"','"+director+"','"+genero+"','"+estreno+"','"+publico+"','"+dateFormat.format(date)+"','"+imagen+"')");
           
            }catch(SQLException ex){
                System.out.println("Error insertando la Peli ->"+ ex.getMessage());
            }
            
            System.out.println("Pelicula insertada en la BD");
            // Cargar peli en Memoria...............
           
            Peliculas peli=new Peliculas(codPeli, nombre, director, genero, estreno1, publico1,date,imagen);
             peliculasTitulo.add(peli);
                                peliculasGenero.add(peli);
                                
                                if(!pelisDeUnGenero.containsKey(peli.getGenero()))
                                    pelisDeUnGenero.put(peli.getGenero(), new TreeSet<Peliculas>());
                                    
                                pelisDeUnGenero.get(peli.getGenero()).add(peli);
                                
                                if(!pelisDeUnDirector.containsKey(peli.getDirector()))
                                    pelisDeUnDirector.put(peli.getDirector(), new TreeSet<Peliculas>());
                                    
                                pelisDeUnDirector.get(peli.getDirector()).add(peli);   
              
            //return new Peliculas(codPeli, nombre, director, genero, estreno1, publico1,date,imagen);
              return null;                 
   }
   //---------------------------------------------------------
    
   public static String seleccionaFichero(String ruta){
      File file = new File(ruta);
      String fich="";
      for(int i=0; i<file.list().length;i++ )
            System.out.println(file.list()[i]);
      
      do{
         fich=ES.leeDeTeclado("Nombre fichero? ");
         
      }while( !Arrays.asList(file.list()).contains(fich) );
      return fich;
    }   
   //-----------------------------------------
    static Usuarios altaUsuario(){
   String login, password, nombre,apellidos,email;
   int tipo;
  
            
           
           login= ES. leeDeTeclado("escribe el nombre de usuario");
           if(listaDeUsuarios.containsKey(login)){
           login= ES. leeDeTeclado("el usuario ya esta disponible, escribe otro nombre");
           }
            password= ES. leeDeTeclado("escribe tu contraseña");
            nombre= ES. leeDeTeclado("escribe tu nombre");
            apellidos= ES. leeDeTeclado("escribe tus apellidos");
            email= ES. leeDeTeclado("escribe tu email");
            tipo=ES.leeN("Escribe 1 si es aficionado, 2 si es empresario o 3 si es experto", 1, 3);
             

        try {
            connMySql.getConn().createStatement().executeUpdate("INSERT INTO Usuarios VALUES('"+login+"','"+password+"','"+nombre+"','"+apellidos+"','"+email+"',"+tipo+")");
        } catch (SQLException ex) {
            System.out.println("Error insertando Usuarios..->" + ex.getMessage());
        }
        
            System.out.println("Usuario insertada en la BD");
           return new Usuarios(login, password, nombre, apellidos, email, tipo);
            }
   //----------------------------------------------
    static void puntuaciones(){
            String usuario,titulo,comentario;
            int puntuacion,cp = 0,tipo = 0;
            usuario = seleccionaPGAM(listaDeUsuarios, "Usuario");
            if (!usuario.equalsIgnoreCase("cancelado") ) {
            Peliculas peli;      
            // titulo = seleccionaPGAM(peliculasTitulo, "titulo de la pelicula");
            do{
              System.out.println(peliculasTitulo);
              titulo=  ES.leeDeTeclado("titulo de la peli? (s para cancelar) ");
              peli= new Peliculas(titulo);
            }while(!peliculasTitulo.contains(peli) && !titulo.equalsIgnoreCase("s"));
              
              if (!titulo.equalsIgnoreCase("s") ) {
                    
                    puntuacion = ES.leeN("Escribe la puntuacion para la pelicula del 1 al 10",1,10);
                    comentario = ES.leeDeTeclado("Escribe un comentario");
                    try{
                     ResultSet rst = connMySql.getConn().createStatement().executeQuery("select codigo_pelicula from peliculas where nombre='"+titulo+"'");
       
                     if (rst.next())
                     cp=rst.getInt(1);
                    }catch(SQLException ex){
                      System.out.println("Error seleccionando el cp -->");
                   
                      } 
                    for (Usuarios usu : listaDeUsuarios.values()) {
                      if(usuario.equalsIgnoreCase(usu.getLogin())){
                       tipo= usu.getTipo();}
                  }
                    
            try {
            connMySql.getConn().createStatement().executeUpdate("INSERT INTO puntuaciones VALUES("+cp+",'"+usuario+"','"+puntuacion*tipo+"','"+comentario+"')");
           
            }catch(SQLException ex){
                 System.out.println("Error insertando Puntuacion..->" + ex.getMessage());
            }  
           System.out.println("insertado en la tabla de la base de datos puntuaciones lo siguiente. \n codigo_pelicula --> "+cp+", usuario--> "+usuario+", puntuacion--> "+puntuacion*tipo+", comentario--> "+comentario);
            }//cancelado.... 
            }
            else
             System.out.println("el usuario ha cancelado el proceso");
            
    }
}// Class----------------------------------------------------------
