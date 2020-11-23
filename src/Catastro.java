public class Catastro {
    public static void main (String[] args) {
        int op,x=0;
        Persona[] p=new Persona[100];
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
                    ES.continuar();
                    continue;
                case 2:
                    for (int i=0;i!=x;i++) {
                        System.out.print(p[i].toString());
                        System.out.println();
                    }
                    ES.continuar();
                    continue;
                case 3:
                    String nifbuscar=ES.leeDeTeclado("INTRODUCE NIF DE LA PERSONA A BUSCAR : ");
                    for (int i=0;i!=x;i++) {
                        if (p[i].getNIF().equals(nifbuscar.toUpperCase())) {
                            System.out.print(p[i].toString());
                            System.out.println();
                        }
                    }
                    ES.continuar();
                    continue;
                case 0:
                    op=0;
                    System.err.println("FIN DEL PROGRAMA!");
                    ES.continuar();
                    continue;
                default:
                    System.err.println("La intruccion elegida no existe en el programa!");
                    ES.continuar();
            }
        } while (op!=0);
    }
}
