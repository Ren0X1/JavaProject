import java.util.*;
public class PintarCollention {
    static void ListarLista(Collection lista) {
        System.out.println("Listado un: "+lista.getClass()+"....");
        lista.forEach(System.out::println);
    }
    static void ListarLista(List lista, int x) {
        System.out.println("Listado: "+lista.getClass().getSimpleName()+".");
        if (x == 1) {
            ListIterator li=lista.listIterator(lista.size());
            while(li.hasPrevious()) {
                Persona p=(Persona)li.previous();
                System.out.println(p);
            }
        }
        else {
            lista.forEach(System.out::println);
        }
    }
}
