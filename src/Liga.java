public class Liga {
public static void main (String[] args) {
        int op,x=0,x1=0,x2=0,i,i1;
        i=ES.leeN("Introduce el numero de Equipos de la liga: ");
        Equipo[] Equipo=new Equipo[i];
        Jugador[] Jugador=new Jugador[i*11];
        Entrenador[] Entrenador=new Entrenador[i*5];
        Persona[] Persona=new Persona[i*11*5];
        do {
            System.out.println("-------------------------------");
            System.out.println(
	    		"1.- Crear Equipo\n" +
	            "2.- Fichar Jugador\n" +
	            "3.- Fichar entrenador\n" +
	            "4.- Listar Plantilla equipos\n" +
	            "5.- Traspasar jugador\n" +
	            "0.- Salir\n"
            );
            System.out.println("-------------------------------");
            op = ES.leeN("Introduce una opcion: ");
            System.out.println("-------------------------------");
            switch (op) {
                case 1:
                    crearEquipo(Equipo,x);
                    x++;
                    ES.continuar();
                    continue;
                case 2:
                    crearJugador(Jugador,x1);
                    x1++;
                    ES.continuar();
                    continue;
                case 3:
                    crearEntrenador(Entrenador,x2);
                    x2++;
                    ES.continuar();
                    continue;
                case 4:
                    int n=ES.leeN("Introduce el numero del equipo que quieres mostrar: ");
                    System.out.println("Jugadores del equipo "+Equipo[n].getNombreequipo()+" : ");
                    for (int k=0;k!=x1;k++) {
                        if (Equipo[n].getNombreequipo().equals(Jugador[k].getEquipo())) {
                            System.out.println(Jugador[k].toString());
                        }
                    }
                    System.out.println("Entrenador/es del equipo "+Equipo[n].getNombreequipo()+" : ");
                    for (int k=0;k!=x2;k++) {
                        if (Equipo[n].getNombreequipo().equals(Entrenador[k].getNombreequipo())) {
                            System.out.println(Entrenador[k].toString());
                        }
                    }
                    System.out.println("Presidente del equipo "+Equipo[n].getNombreequipo()+" : ");
                    System.out.println("Entrenador: "+Equipo[n].getNombreentrenador());
                    ES.continuar();
                    continue;
                case 5:
                    int j=ES.leeN("Introduce el Nº de Jugador que quieres traspasar: ");
                    String nuevoequipo=ES.leeDeTeclado("Introduce el nuevo equipo para el jugador: ");
                    Jugador[j]=new Jugador(Jugador[j].getNombre(),Jugador[j].getApellido1(),Jugador[j].getApellido2(),Jugador[j].getEdad(),Jugador[j].getSexo(),Jugador[j].getNIF(),Jugador[j].getLoc(),Jugador[j].getCalzado(),nuevoequipo,Jugador[j].getPosicion(),Jugador[j].getDorsal(),Jugador[j].getTarjetas());
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
    static void crearEquipo (Object[] equipo,int x) {
        String nombre=ES.leeDeTeclado("Introduce el nombre del equipo "+x+" : ");
        String nombreentrenador=ES.leeDeTeclado("Introduce el nombre del entrenador del equipo "+x+" : ");
        String nombrepresidente=ES.leeDeTeclado("Introduce el nombre del presidente del equipo "+x+" : ");
        String localidad=ES.leeDeTeclado("Introduce la localidad del equipo "+x+" : ");
        String estadio=ES.leeDeTeclado("Introduce el estadio del equipo "+x+" : ");
        String captain=ES.leeDeTeclado("Introduce el capitan del equipo "+x+" : ");
        equipo[x]=new Equipo(nombre,nombreentrenador,nombrepresidente,localidad,estadio,captain);
    }
    static void crearJugador (Object[] jugador,int x) {
        String nom=ES.leeDeTeclado("Introduce el nombre del jugador "+x+" : ");
        String ape1=ES.leeDeTeclado("Introduce el apellido 1 del jugador "+x+" : ");
        String ape2=ES.leeDeTeclado("Introduce el apellido 2 del jugador "+x+" : ");
        int e=ES.leeN("Introduce la edad del jugador "+x+" : ");
        char sex=ES.leecaracter("Introduce el sexo [H/M] del jugador "+x+" : ");
        String nif=ES.leeDeTeclado("Introduce el NIF del jugador "+x+" : ");
        String localidad=ES.leeDeTeclado("Introduce la localidad del jugador "+x+" : ");
        int calzado=ES.leeN("Introduce el calzado del jugador "+x+" : ");
        String equipo=ES.leeDeTeclado("Introduce el equipo del jugador "+x+" : ");
        String posicion=ES.leeDeTeclado("Introduce la posicion del jugador "+x+" : ");
        int dorsal=ES.leeN("Introduce el dorsal del jugador "+x+" : ");
        int tarjetas=ES.leeN("Introduce numero de tarjetas del jugador "+x+" : ");
        jugador[x]=new Jugador(nom,ape1,ape2,e,sex,nif,localidad,calzado,equipo,posicion,dorsal,tarjetas);
    }
    static void crearEntrenador (Object[] entrenador,int x) {
        String nom=ES.leeDeTeclado("Introduce el nombre del entrenador "+x+" : ");
        String ape1=ES.leeDeTeclado("Introduce el apellido 1 del entrenador "+x+" : ");
        String ape2=ES.leeDeTeclado("Introduce el apellido 2 del entrenador "+x+" : ");
        int e=ES.leeN("Introduce la edad del entrenador "+x+" : ");
        char sex=ES.leecaracter("Introduce el sexo [H/M] del entrenador "+x+" : ");
        String nif=ES.leeDeTeclado("Introduce el NIF del entrenador "+x+" : ");
        String localidad=ES.leeDeTeclado("Introduce la localidad del entrenador "+x+" : ");
        String nombreequipo=ES.leeDeTeclado("Introduce el equipo del entrenador "+x+" : ");
        int victorias=ES.leeN("Introduce las victorias del entrenador "+x+" : ");
        int empates=ES.leeN("Introduce los empates del entrenador "+x+" : ");
        int derrotas=ES.leeN("Introduce las derrotas del entrenador "+x+" : ");
        entrenador[x]=new Entrenador(nom,ape1,ape2,e,sex,nif,localidad,nombreequipo,victorias,empates,derrotas);
    }
}
