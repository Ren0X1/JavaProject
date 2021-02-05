package manipulamapas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ManipulaMapas {
    static TreeMap<String,Persona> personasOrdenadas= new TreeMap();
    static HashMap<String,Persona> personasNif      = new HashMap();
    public static void main(String[] args) {
        ArrayList col=null;
        Persona p1,p2,p3,per;
        File fichPerObj = new File("src\\datos\\ColMapPersonas.dat");
        if(!fichPerObj.exists()) {
            p1= new Persona("pepe", "Lopez", "Perez", "11111111H");
            p2= new Persona("Ana", "Lopez", "Pedraza", "21111111H");
            p3= new Persona("Luisa", "Alba", "Perez", "31111111H");
            personasOrdenadas.put(p1.getApellido1()+" "+p1.getApellido2()+", "+p1.getNombre(), p1);
            personasOrdenadas.put(p2.getApellido1()+" "+p2.getApellido2()+", "+p2.getNombre(), p2);
            personasOrdenadas.put(p3.getApellido1()+" "+p3.getApellido2()+", "+p3.getNombre(), p3);
            personasNif.put(p1.getNif(), p1);
            personasNif.put(p2.getNif(), p2);
            personasNif.put(p3.getNif(), p3);
        } else {
            col=new ArrayList();
            personasOrdenadas =(TreeMap) leePersonaFichObjetos();
            personasNif       = new HashMap(personasOrdenadas);
        }
        System.out.println("\nListando Mapa -> personasOrdenadas");
        listaMapas(personasOrdenadas,1);
        System.out.println("\nListando Mapa -> personasNif");
        listaMapas(personasNif,1);
        System.out.println(guardaPersonaFichObjetos(personasOrdenadas)+"personas guardadas");
    }
    
    private static void listaMapas(Map<String,Persona> mapa,int modo){
        switch (modo) {
            case 1:
                System.out.println("\nListando Mapa com Entry");
                mapa.entrySet().forEach((entry) -> {
                    String key = entry.getKey();
                    Persona value = entry.getValue();

                    System.out.println(key+"=>"+value);
                });
                break;
            case 2:
                System.out.println("\nListando Mapa con Keys");
                mapa.keySet().forEach((clave) -> {
                    System.out.println(clave+"=>"+mapa.get(clave));
                });
                break;
            case 3:
                System.out.println("\nListando Mapa con Values");
                mapa.values().forEach((value) -> {
                    System.out.println("clave?=>"+value);
                });
                break;
            default:
                break;
        }
    }
    
    private static int guardaPersonaMapaFich(Map mapa){
        Persona per;
        try {
            FileWriter fw   = new FileWriter("src\\datos\\Personas.txt",true);
            try (PrintWriter pw = new PrintWriter(fw)) {
                for (Object object : mapa.values()) {
                    per=(Persona)object;
                    pw.println(per.getNombre()+";"+per.getApellido1()+";"+per.getApellido2()+";"+per.getNif());
                    pw.print(object);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error de E/S-> "+ex.getMessage());
        }
        return mapa.size();
    }
    
    private static int recuperaPersonaMapaFich(Map mapa) throws Exception{
        throw new Exception("METODO NO IMPLEMENTADO");
        /*int numPer=0;
        return numPer;*/
    }

    private static File listaDirectorio(String ruta){
        File file = new File(ruta);
        boolean salida;
        int numFich;
        if(!file.exists())
            System.err.println("No existe el directorio en la ruta-> "+ruta);
        else if(!file.isDirectory())
            System.err.println("No se ha proporcionado un directorio-> "+ruta);
        else{
            File[] listaFicheros;
            do{
                listaFicheros=file.listFiles();
                int i=1;
                for (File fichero : listaFicheros) {
                    System.out.println(i+".-"+fichero.getName());
                    i++;
                }
                numFich=ES.leeN("elige el número del fichero elegido? ");
                salida=numFich>0 && numFich<listaFicheros.length;
                if(!salida) {
                    System.out.println("Elije un número válido..."); 
                }
            } while(!salida);
            return (listaFicheros[numFich-1]);
        }
        return null;
    }
    
    private static int guardaPersonaFichObjetos(Collection col){
        Persona per;
        try {
            FileOutputStream   fos   = new FileOutputStream("src\\datos\\Personas.dat");
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                for (Object object : col) {
                    oos.writeObject(object); 
                }
            }
        } catch(NotSerializableException ex){
            System.out.println("Error de Serialización E/S-> "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error de E/S-> "+ex.getMessage());
        }
        return col.size();
    }
    
    private static int guardaPersonaFichObjetos(Map mapa){
        try {
            FileOutputStream   fos   = new FileOutputStream("src\\datos\\ColMapPersonas.dat");
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(mapa);
            }
        } catch(NotSerializableException ex){
            System.out.println("Error de Serialización Mapa E/S-> "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error de E/S-> "+ex.getMessage());
        }
        return mapa.size();
    }
    
    private static void leePersonaFichObjetos(Collection col){
        Persona per;
        ObjectInputStream ois=null;
        FileInputStream   fis=null;
        try {
            fis   = new FileInputStream("src\\datos\\Personas.dat");
            ois   = new ObjectInputStream(fis);
            while(true){
                per=(Persona)ois.readObject();
                col.add(per);
            }
        } catch(NullPointerException ex){
            System.out.println("Null Pointer E/S-> "+ex.getMessage());
        } catch(NotSerializableException ex){
            System.out.println("Error de Serialización E/S-> "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Termina la carga del fichero Binario -> "+ex.getMessage());
            try {
                ois.close();
                fis.close();
            } catch (IOException ex1) {
                System.out.println("Error cerrando los flujos-> "+ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no Encontrada-> "+ex.getMessage());
        }
    }
    
    private static Map leePersonaFichObjetos(){
        Map mapa=null;
        ObjectInputStream ois=null;
        FileInputStream   fis=null;
        try {
            fis   = new FileInputStream("src\\datos\\ColMapPersonas.dat");
            ois   = new ObjectInputStream(fis);
            mapa=(Map<String,Persona>)ois.readObject();
        } catch(NullPointerException ex){
            System.out.println("Null Pointer E/S-> "+ex.getMessage());
        } catch(NotSerializableException ex){
            System.out.println("Error de Serialización E/S-> "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Termina la carga del fichero Binario -> "+ex.getMessage());
            try {
                ois.close();
                fis.close();
            } catch (IOException ex1) {
                System.out.println("Error cerrando los flujos-> "+ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no Encontrada-> "+ex.getMessage());
        }
        return mapa;
    }
}
