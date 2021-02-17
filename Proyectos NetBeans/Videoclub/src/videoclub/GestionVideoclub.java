package videoclub;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionVideoclub {
    public static void main(String[] args) {
        HashMap<String,VideoClub> listaVideoclubs= new HashMap();  
        TreeMap <String,DVD> listaDvd  = new TreeMap();        
        TreeSet <Cliente> ListaCliente = new TreeSet();        
        int opcion,opcionalta;
        ArrayList opciones = new ArrayList();
        ArrayList opcionesaltas = new ArrayList();
        ArrayList opcionelistados = new ArrayList();
        VideoClub vd;
        //opciones.add("Salir");
        opciones.add("Menu Altas");
        opciones.add("Menu Listados");
        //opcionesaltas.add("Salir");
        opcionesaltas.add("Alta Videoclub");
        opcionesaltas.add("Alta DVD");
        opcionesaltas.add("Alta Cliente");
        opcionesaltas.add("Asigna DVD");
        opcionesaltas.add("Asigna Cliente");
        //opcionelistados.add("Salir");
        opcionelistados.add("Listado de Videoclub");
        opcionelistados.add("Listado de DVD");
        opcionelistados.add("Listado de Cliente");        
        cargarDatosDesdeFichero(listaDvd,ListaCliente);
        do{
            opcion=pintaMenu("Principal",opciones);
            switch(opcion){
                case 1:
                    do{
                        opcionalta=pintaMenu("Menu Altas",opcionesaltas);
                        switch(opcionalta){
                            case 0: 
                                System.out.println("Saliendo de la aplicación");
                                guardaDatosEnFichero(listaDvd,ListaCliente);
                                break;
                            case 1:
                                System.out.println("Alta Videoclub");
                                VideoClub vid= VideoClub.altaVideoclub();
                                listaVideoclubs.put(vid.getNombre(), vid);
                                break;
                            case 2:
                                System.out.println("Alta DVD");
                                DVD Dvdnuevo=DVD.altaDvd();
                                listaDvd.put(Dvdnuevo.getCodigo(), Dvdnuevo);
                                break;
                            case 3:
                                System.out.println("Alta Cliente");
                                Cliente cli=Cliente.altaCliente();
                                ListaCliente.add(cli);
                                break;
                            case 4:
                                vd=(VideoClub)seleccionaDeColeccion(listaVideoclubs.values());
                                if(vd!=null){
                                    DVD dvd=(DVD)seleccionaDeColeccion(listaDvd.values());    
                                    if(dvd!=null) {
                                        if(!vd.getPeliculas().containsKey(dvd.getCodigo())){
                                            vd.getPeliculas().put(dvd.getCodigo(), dvd);
                                            System.out.println("Película asignado con extio!");
                                        } else {
                                            System.out.println("Este videoclub ya tiene la peli->"+dvd);
                                        }
                                    }
                                } else {
                                    System.out.println("No hay videoclubs para mostrar o se cancelo la asignación");
                                }
                                break;
                            case 5:
                                vd=(VideoClub)seleccionaDeColeccion(listaVideoclubs.values());
                                if(vd!=null){
                                    cli=(Cliente)seleccionaDeColeccion(ListaCliente);    
                                    if(cli!=null){
                                        if(!vd.getClientes().contains(cli)){
                                            vd.getClientes().add(cli);
                                            System.out.println("Cliente asignado con extio!");
                                        } else {
                                            System.out.println("Este videoclub ya tiene el cliente->"+cli);
                                        }
                                    }
                                } else {
                                    System.out.println("No hay videoclubs para mostrar o se cancelo la asignación");
                                }
                                break;    
                        }
                    } while(opcionalta!=0);
                    break;
                case 2:    
                    do{
                        opcionalta=pintaMenu("Listados",opcionelistados);
                        switch(opcionalta){
                            case 0: 
                                System.out.println("Saliendo de la aplicación");
                                break;
                            case 1:
                                System.out.println("Listado Videoclubs");
                                System.out.println(listaVideoclubs);
                                break;
                            case 2:
                                System.out.println("Listado DVD");
                                System.out.println(listaDvd);
                                break;
                            case 3:
                                System.out.println("Listado Cliente");
                                System.out.println(ListaCliente);
                                break;
                        }
                    } while(opcionalta!=0);
                    break;
                case 0:
                    System.out.println("Adios..");
                    break;
                default:
                    System.out.println("No valido");
                    break;
            }
        } while(opcion!=0);
    }

    private static int pintaMenu(String nombreMenu,ArrayList<String> opciones){
        int opElegida;
        do{
            int nop=1;
            System.out.println("|****Menú "+ nombreMenu+"*****|");
            for (String opcion : opciones) {
                System.out.println("|\t"+nop+" .-"+opcion);
                nop++;
            }
            System.out.println("|\t0 .- Salir");
            System.out.println("|***********************|");
            opElegida=ES.leeN("Elije opción? ");
        } while(opElegida>opciones.size()+1 || opElegida<0);
        return  opElegida;
    }

    private static Object  seleccionaDeColeccion(Collection lista){
        if(lista.size()>0) {   
            String tipo=lista.iterator().next().getClass().getSimpleName();
            System.out.println("Listado de "+ tipo);    
            int i=0;
            TreeSet listaOrd = new TreeSet(lista);
            ArrayList listaEmpre= new ArrayList(listaOrd);
            while(i<1 || i> listaEmpre.size()){
                i=1;
                for (Object ele : listaEmpre) {
                    System.out.println(i+".- "+ ele);
                    i++;
                }
                i=ES.leeN("Escribe el número de "+ tipo +"? ");
                if(i<1 || i> listaEmpre.size()){
                    System.out.println("No existe "+tipo+" con ese número");
                    char c=ES.leecaracter("Escribe 'c' para cancelar");
                    if (c == 'c') {
                        return null;
                    }
                } 
            }
            return listaEmpre.get(i-1);
        } else {
            System.out.println("No Hay nada que listar...");
            return null;
        }
    }

    private static int listaVideoclub(ArrayList <VideoClub> video){
        int nop=1,op;
        video.forEach((vid) -> {
            System.out.println(nop+".-"+vid);
        });
        op=ES.leeN("Escoge el Videoclub para asignar al cliente");
        return op-1;
    }
    
    private static void añadeopciones(){
    
    }
    
    private static void cargarDatosDesdeFichero( TreeMap <String,DVD> listaDvd, TreeSet <Cliente> ListaCliente ){
        String rutaCli="src\\datos\\clientes.txt";
        File file = new File(rutaCli);
        FileReader fr;
        BufferedReader br;
        FileInputStream fis;
        ObjectInputStream ois=null;
        String cliente, cli[];
        Cliente clii;
        if (file.exists()) {
            try {
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                while ((cliente = br.readLine()) != null  ) {
                    cli = cliente.split("[\\s\\(-]");
                    if(cli.length==4) {
                        clii=  new Cliente(cli[0], cli[1], "Sin Segundo Apellido", cli[2], cli[3]);
                    } else {
                        clii=  new Cliente(cli[0], cli[1], cli[2], cli[3], cli[4]);
                    } 
                    ListaCliente.add(clii);
                }
                br.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Error");
            } catch (IOException ex) {
                System.out.println("Error leyendo clientes. ");
            }
        } else {
            System.out.println("No existe el fichero en la ruta: " + rutaCli);
        }
        DVD dvd;
        file= new File("src\\datos\\peliculas.dat");
        if( file.exists()){
            try {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                do{
                    dvd=(DVD) ois.readObject(); 
                    listaDvd.put(dvd.getCodigo(), dvd);
                } while(true);
            } catch (FileNotFoundException | ClassNotFoundException ex) {
                Logger.getLogger(GestionVideoclub.class.getName()).log(Level.SEVERE, null, ex);
            } catch(EOFException ex){
                System.out.println("fin carga de peliculas de objetos...->"+ex.getMessage());
            } 
             catch (IOException ex) {
                System.out.println("Error inesperado de I/o ó se llegó al fin del fichero-> "+ex.getMessage());
            } finally{
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.out.println("Error cerrando flujo fichero objetos peliculas->" + ex.getMessage());
                }
            }
        } else {
            String rutaPeli="src\\datos\\peliculas.txt";
            DVD peli;
            String[] actores;
            ArrayList listaActores;    
            file = new File(rutaPeli);
            if (file.exists()) {
                try {
                    fr = new FileReader(file);
                    br = new BufferedReader(fr);
                    while ((cliente = br.readLine()) != null  ) {
                        cli = cliente.split("[\\*\\[]");
                        actores=cli[3].split(",");
                        listaActores= new ArrayList(Arrays.asList(actores));
                        peli=  new DVD(cli[0], cli[1], cli[2],listaActores);
                        listaDvd.put(peli.getCodigo(), peli);
                    }
                    br.close();
                } catch (FileNotFoundException ex) {
                    System.out.println("Error");
                } catch (IOException ex) {
                    System.out.println("Error leyendo Peliculas. ");
                }
            } else {
                System.out.println("No existe el fichero en la ruta: " + rutaCli);
            }
        }
    }
    
    private static void guardaDatosEnFichero( TreeMap <String,DVD> listaDvd, TreeSet <Cliente> ListaCliente ){
        FileWriter         fwtxt;
        PrintWriter        pw   = null;
        FileOutputStream   fwobj; 
        ObjectOutputStream oos  =null;
        int longActores;
        try {
            fwtxt= new FileWriter("src\\datos\\peliculas2.txt");
            pw= new PrintWriter(fwtxt);
            fwobj= new FileOutputStream("src\\datos\\peliculas.dat");
            oos  = new ObjectOutputStream(fwobj);
            Collection<DVD> col= listaDvd.values();
            for (DVD dvd : col) {
               longActores =dvd.getReparto().toString().length();
               pw.println( dvd.getCodigo()+"*"
                          +dvd.getTitulo()+"*"
                          +dvd.getDirector()+"*"
                          +dvd.getReparto().toString().substring(1, longActores-2));

               oos.writeObject(dvd);
            }
        } catch (IOException ex) {
            System.out.println("eRROR guardando peliculas.txt-> "+ex.getMessage());
        } finally {
            try {
                pw.close();oos.close();
            } catch (IOException ex) {
                System.out.println("eRROR cerrando los flujos -> "+ex.getMessage());
            }
        }
    }
}
    

