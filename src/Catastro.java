import java.sql.SQLOutput;

public class Catastro {
    public static void main (String[] args) {
        int op=1,x=0;
        Persona p[]=new Persona[100];
        do {
            System.out.println("-------------------------------");
            System.out.println(
                    "1.- Añadir una persona\n"+
                            "2.- Lista de personas\n"+
                            "3.- Buscar una persona\n"+
                            "0.- Salir\n"
                    );
            System.out.println("-------------------------------");
            op=ES.leeN("Introduce una opcion: ");
            System.out.println("-------------------------------");
            switch (op) {
                case 1:
                    String nom=ES.leeDeTeclado("INTRODUCE NOMBRE DE LA PERSONA "+x+" : ");
                    String ape1=ES.leeDeTeclado("INTRODUCE APELLIDO 1 DE LA PERSONA "+x+" : ");
                    String ape2=ES.leeDeTeclado("INTRODUCE APELLIDO 2 DE LA PERSONA "+x+" : ");
                    int e=ES.leeN("INTRODUCE EDAD DE LA PERSONA "+x+" : ");
                    char sex=ES.leecaracter("INTRODUCE SEXO[H/M] DE LA PERSONA "+x+" : ");
                    String nif=ES.leeDeTeclado("INTRODUCE NIF DE LA PERSONA "+x+" : ");
                    String localidad=ES.leeDeTeclado("INTRODUCE LOCALIDAD DE LA PERSONA "+x+" : ");
                    p[x]=new Persona(nom,ape1,ape2,e,sex,nif,localidad);
                    x++;
                    continuar();
                    continue;
                case 2:
                    for (int i=0;i!=x;i++) {
                        System.out.print(p[i].getNombre()+", "+p[i].getApellido1()+", "+p[i].getApellido2()+", "+p[i].getEdad()+", "+p[i].getSexo()+", "+p[i].getNIF()+", "+p[i].getLoc());
                        System.out.println();
                    }
                    continuar();
                    continue;
                case 3:
                    String nifbuscar=ES.leeDeTeclado("INTRODUCE NIF DE LA PERSONA A BUSCAR : ");
                    for (int i=0;i!=x;i++) {
                        if (p[i].getNIF()==nifbuscar) {
                            System.out.print(p[i].getNombre()+", "+p[i].getApellido1()+", "+p[i].getApellido2()+", "+p[i].getEdad()+", "+p[i].getSexo()+", "+p[i].getNIF()+", "+p[i].getLoc());
                        }
                    }
                    continuar();
                    continue;
                case 0:
                    op=0;
                    System.err.println("FIN DEL PROGRAMA!");
                    continuar();
                    continue;
                default:
                    System.err.println("La intruccion elegida no existe en el programa!");
                    continuar();
            }
        } while (op!=0);
    }
    static void continuar() {
        ES.leeDeTeclado("Pulsa \"ENTER\" para continuar...");
    }
}
