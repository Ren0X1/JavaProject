package OLD;

public class Instituto {
    public static void main (String[] args) {
        int op,x=0,x1=0,x2=0,i,i1;
        i= ES.leeN("Introduce el numero de Alumnos del instituto: ");
        i1= ES.leeN("Introduce el numero de Profesores del instituto: ");
        AlumnoDAW[] Alumnos=new AlumnoDAW[i];
        ProfesoresDAW[] Profesores=new ProfesoresDAW[i1];
        GruposDAW[] Grupos=new GruposDAW[i1];
        do {
            System.out.println("-------------------------------");
            System.out.println(
	    		"1.- Matricular Alumno\n" +
	            "2.- Alta de profesores\n" +
	            "3.- Añadir profesor a un grupo\n" +
	            "4.- Alta de grupos\n" +
	            "5.- Listado de clase\n" +
	            "0.- Salir\n"
            );
            System.out.println("-------------------------------");
            op = ES.leeN("Introduce una opcion: ");
            System.out.println("-------------------------------");
            switch (op) {
                case 1:
                    crearAlumno(Alumnos,x);
                    x++;
                    ES.continuar();
                    continue;
                case 2:
                    crearProfesor(Profesores,x1);
                    x1++;
                    ES.continuar();
                    continue;
                case 3:
                    String grupo= ES.leeDeTeclado("Introduce el nombre del Grupo "+x2+" (Si el grupo no existe se creara): ");
                    int n= ES.leeN("Introduce el numero identificador del profesor que deseas meter en el grupo: ");
                    Grupos[x2]=new GruposDAW(Profesores[n].getNombre(),Profesores[n].getAsignatura(),Profesores[n].getEdad(),Profesores[n].getAula(),grupo);
                    x2++;
                    ES.continuar();
                    continue;
                case 4:
                    crearGrupo(Grupos,x2);
                    x2++;
                    ES.continuar();
                    continue;
                case 5:
                    crearListado(Alumnos,Profesores,Grupos,x,x1,x2);
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
    static void crearAlumno (Object[] alumno,int x) {
        if (x<20) { //Con esto comprobamos que no se pase el limite de alumnos
            String centro= ES.leeDeTeclado("Introduce el centro del Alumno "+x+" : ");
            int curso= ES.leeN("Introduce el curso del Alumno "+x+" [1/2]: ");
            String nombre= ES.leeDeTeclado("Introduce el nombre del Alumno "+x+" : ");
            String ape1= ES.leeDeTeclado("Introduce el primer apellido del Alumno "+x+" : ");
            String ape2= ES.leeDeTeclado("Introduce el segundo apellido del Alumno "+x+" : ");
            char rep= ES.leecaracter("Es repetidor el Alumno "+x+" [S/N]: ");
            char sex= ES.leecaracter("Sexo del Alumno "+x+" [V/M]: ");
            String datost= ES.leeDeTeclado("Introduce los datos del tutor del Alumno "+x+" (NIF,nombre): ");
            String nifalu= ES.leeDeTeclado("Introduce el NIF del Alumno "+x+" con el formato (A-Z*16 0-9*1): ");
            alumno[x]=new AlumnoDAW(centro,curso,nombre,ape1,ape2,rep,sex,datost,nifalu);
        }
        else {
            System.err.println("Se ha superado el limite permitido de alumnos en los dos cursos!");
        }
    }
    static void crearProfesor (Object[] profesor,int x) {
        String nombre= ES.leeDeTeclado("Introduce el nombre del Profesor "+x+" : ");
        String asignatura= ES.leeDeTeclado("Introduce la asignaturas que imparte el Profesor "+x+" : ");
        int edad= ES.leeN("Introduce la edad del del Profesor "+x+" : ");
        int aula= ES.leeN("Introduce el aula del del Profesor "+x+" : ");
        profesor[x]=new ProfesoresDAW(nombre,asignatura,edad,aula);
    }
    static void crearGrupo (Object[] grupo,int x) {
        String nombre= ES.leeDeTeclado("Introduce el nombre del Grupo "+x+" : ");
        grupo[x]=new GruposDAW(nombre);
    }
    static void crearListado (Object[] alumnos,Object[] profesores,Object[] grupos,int x,int x1,int x2) {
        System.out.println("ALUMNOS: ");
        System.out.println("------------------------------");
        for (int i=0;i!=x;i++) {
            System.out.print(alumnos[i].toString());
            System.out.println();
        }
        System.out.println("TENEMOS "+x+" ALUMNOS EN EL INSTITUTO");
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("PROFESORES: ");
        System.out.println("------------------------------");
        for (int i=0;i!=x1;i++) {
            System.out.print(profesores[i].toString());
            System.out.println();
        }
        System.out.println("TENEMOS "+x1+" PROFESORES EN EL INSTITUTO");
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("GRUPOS: ");
        System.out.println("------------------------------");
        for (int i=0;i!=x2;i++) {
            System.out.print(grupos[i].toString());
            System.out.println();
        }
        System.out.println("TENEMOS "+x2+" GRUPOS EN EL INSTITUTO");
        System.out.println("------------------------------");
    }
}
