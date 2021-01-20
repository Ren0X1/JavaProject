package Empresa;

import java.util.Collection;
import java.util.TreeSet;

public class Empresa {
    public static void main (String[] args) {
        int op,x=0,x1=0,x2=0,i,i1,i2;
        i= ES.leeN("Introduce el numero de Directivos de la empresa: ");
        i1= ES.leeN("Introduce el numero de Empleados de la empresa: ");
        i2= ES.leeN("Introduce el numero de Clientes de la empresa: ");
        Directivos[] directivos=new Directivos[i];
        Empleados[] empleados=new Empleados[i1];
        Clientes[] clientes=new Clientes[i2];
        do {
            System.out.println("-------------------------------");
            System.out.println(
                    "1.- Añadir un Directivo\n" +
                            "2.- Añadir un Empleado\n" +
                            "3.- Añadir un Cliente\n" +
                            "4.- Generar listado de Directivos/Empleados/Clientes\n" +
                            "5.- Convertir un empleado en directivo\n" +
                            "0.- Salir\n"
            );
            System.out.println("-------------------------------");
            op = ES.leeN("Introduce una opcion: ");
            System.out.println("-------------------------------");
            switch (op) {
                case 1:
                    crearDirectivo(directivos,x);
                    x++;
                    ES.continuar();
                    continue;
                case 2:
                    crearEmpleado(empleados,x1);
                    x1++;
                    ES.continuar();
                    continue;
                case 3:
                    crearCliente(clientes,x2);
                    x2++;
                    ES.continuar();
                    continue;
                case 4:
                    crearListado(directivos,empleados,clientes,x,x1,x2);
                    ES.continuar();
                    continue;
                case 5:
                    int j= ES.leeN("Introduce el Nº de empleado a convertir: ");
                    String grupoTrabajo= ES.leeDeTeclado("Introduce el grupo de trabajo para el nuevo directivo: ");
                    String nif= ES.leeDeTeclado("Introduce el nif del nuevo Directivo: ");
                    directivos[x]=new Directivos(empleados[j].getNombre(),empleados[j].getEdad(),empleados[j].getSueldo(),grupoTrabajo, nif);
                    x++;
                    ES.continuar();
                    continue;
                case 0:
                    op = 0;
                    System.err.println("FIN DEL PROGRAMA!");
                    continue;
                default:
                    System.err.println("La intruccion elegida no existe en el programa!");
                    ES.continuar();
            }
        } while (op != 0);
    }
    static void crearDirectivo (Object[] directivos,int x) {
        String nombre= ES.leeDeTeclado("Introduce el nombre del Directivo "+x+" : ");
        int edad= ES.leeN("Introduce la edad del Directivo "+x+" : ");
        int sueldo= ES.leeN("Introduce el sueldo del Directivo "+x+" : ");
        String nif= ES.leeDeTeclado("Introduce el nif del Directivo "+x+" : ");
        String grupo= ES.leeDeTeclado("Introduce el grupo de trabajo del Directivo "+x+" : ");
        directivos[x]=new Directivos(nombre,edad,sueldo,grupo,nif);
    }
    static void crearEmpleado (Object[] empleados,int x) {
        String nombre= ES.leeDeTeclado("Introduce el nombre del Empleado "+x+" : ");
        int edad= ES.leeN("Introduce la edad del Empleado "+x+" : ");
        String nif= ES.leeDeTeclado("Introduce el nif del Empleado "+x+" : ");
        int sueldo= ES.leeN("Introduce el sueldo del Empleado "+x+" : ");
        empleados[x]=new Empleados(nombre,nif,edad,sueldo);
    }
    static void crearCliente (Object[] clientes,int x) {
        String nombre= ES.leeDeTeclado("Introduce el nombre del Cliente "+x+" : ");
        String nif= ES.leeDeTeclado("Introduce el nif del Cliente "+x+" : ");
        int edad= ES.leeN("Introduce la edad del Cliente "+x+" : ");
        String tlf= ES.leeDeTeclado("Introduce el telefono del Cliente "+x+" : ");
        clientes[x]=new Clientes(nombre,edad,tlf,nif);
    }
    static Object listaEmpresas(Collection<Empresa> lista) {
        System.out.println("Listado de empresas...");
        int i=0;
        Empresa emp=null;
        TreeSet listaOrd=new TreeSet(lista);
        while (i<1 || i>listaOrd.size()) {
            i=1;
            for (Empresa empresa : lista) {
                System.out.println(i+".-"+empresa.getClass());
                i++;
            }
            i=ES.leeN("Escribe el numero de la empresa: ");
            if (i<1 || i>listaOrd.size()) {
                System.out.println("No existe la empresa con ese numero.");
                return null;
            }

        }
        return null;
    }
    static void crearListado (Object[] directivos,Object[] empleados,Object[] clientes,int x,int x1,int x2) {
        System.out.println("DIRECTIVOS: ");
        System.out.println("------------------------------");
        for (int i=0;i!=x;i++) {
            System.out.print(directivos[i].toString());
            System.out.println();
        }
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("EMPLEADOS: ");
        System.out.println("------------------------------");
        for (int i=0;i!=x1;i++) {
            System.out.print(empleados[i].toString());
            System.out.println();
        }
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("CLIENTES: ");
        System.out.println("------------------------------");
        for (int i=0;i!=x2;i++) {
            System.out.print(clientes[i].toString());
            System.out.println();
        }
        System.out.println("------------------------------");
    }
}
