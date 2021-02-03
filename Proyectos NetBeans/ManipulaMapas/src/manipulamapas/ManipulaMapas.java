package manipulamapas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ManipulaMapas {
    static TreeMap<String,Persona> personaOrdenadas=new TreeMap();
    static HashMap<String,Persona> personasNif=new HashMap();
    public static void main(String[] args) {
        //PERSONAS DE TESTEO
        Persona p1,p2,p3;
        p1=new Persona("Pepe","Lopez","Perez","11111111H");
        p2=new Persona("Carlos","Lopez","Perez","21111111D");
        p3=new Persona("Juan","Lopez","Perez","31111111T");
        personaOrdenadas.put(p1.getApellido1()+" "+p1.getApellido2()+" "+p1.getNombre(), p1);
        personaOrdenadas.put(p2.getApellido1()+" "+p2.getApellido2()+" "+p2.getNombre(), p2);
        personaOrdenadas.put(p3.getApellido1()+" "+p3.getApellido2()+" "+p3.getNombre(), p3);
        personasNif.put(p1.getNif(), p1);
        personasNif.put(p2.getNif(), p2);
        personasNif.put(p3.getNif(), p3);
        /*listaMapas(personaOrdenadas, 1);
        listaMapas(personaOrdenadas, 2)
        listaMapas(personaOrdenadas, 3)
        listaMapas(personasNif, 1);
        listaMapas(personasNif, 2);
        listaMapas(personasNif, 3);
        listarDirectorio("C:\\");*/
    }
    
    private static void listaMapas(Map<String,Persona> x, int i) {
        //POR TERMINAR
        switch(i) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                System.err.println("ERROR AL EJECUTAR");
                break;
        }
        x.forEach((key, value) -> System.out.println(key + ":" + value));
    }
    
    private static int guardarFichero(Map x) throws IOException {
        //POR TERMINAR
        Persona per;
        FileWriter fw=new FileWriter("src\\datos\\Personas.txt");
        PrintWriter pw=new PrintWriter(fw);
        x.values().forEach((object) -> {
            pw.println(object);
        });
        return x.size();
    }
    
    private static int recuperarFichero() {
        System.err.println("NO IMPLEMENTADO");
        return 0;    
    }
    
    private static File listarDirectorio(String ruta){
        //POR TERMINAR
        File micarpeta= new File(ruta);
        File[] listaFicheros=micarpeta.listFiles();
        for (int i=0,j=1;i<listaFicheros.length;i++) {
            if (listaFicheros[i].isDirectory()) {
                System.out.println(j+".- "+listaFicheros[i].getName());
                j++;
            }
        }
        return null;
    }
    
}
