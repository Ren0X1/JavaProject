public class NotasModulos {
    public static void main (String[] args) {
        int x=0, i, n, va=0;
        float media=0;
        String[] asignaturas= new String[]{"PROG", "L.M", "ENDES", "DB", "S.INF", "FOL"};
        i=ES.leeN("Introduce los alumnos que vas a registrar: ");
        n=ES.leeN("Introduce la nota maxima que puede tener un alumno: ");
        String[] nombre=new String[i];
        int[][][] nota=new int[i][6][3];
        int k=i;
        System.out.println("Puedes terminar antes el programa si en el nombre del alumno escribes salir");
        ES.leeDeTeclado("Pulsa \"ENTER\" para continuar...");
        do {
            do {
                System.out.println("El nombre del alumno no puede estar vacio.");
                nombre[x]=ES.leeDeTeclado("Introduce el nombre del alumno "+(x+1)+": ");
            } while (nombre[x].equals(""));
            if (!nombre[x].equals("salir")) {
                do {
                    System.out.println("La nota maxima solo puede ser de "+n);
                    if (!nombre[x].equals("")){
                        for (int cnt=0;cnt!=6;cnt++) {
                            for (int h=0;h!=3;h++) {
                                nota[x][cnt][h]=ES.leeN("Introduce la nota del alumno "+(x+1)+" en la asignatura "+asignaturas[cnt]+" en la "+(h+1)+" EVA: ");
                            }
                        }
                    }
                    va++;
                } while (nota[x][va][0]>n && nota[x][va][1]>n && nota[x][va][2]>n);
                va=0;
            }
            else {
                k=x;
                x=i-1;
            }
            x++;
        } while (x!=i);
        System.out.println("BOLETIN");
        System.out.println("--------------");
        for (int j=0;j!=k;j++) {
            if (!nombre[j].equals("")) {
                System.out.println(nombre[j]+": ");
                System.out.println("------------");
                for (int e=0;e!=6;e++) {
                    System.out.print("Asignatura "+asignaturas[e]+": ");
                    for (int a=0;a!=3;a++) {
                        System.out.print(nota[j][e][a]);
                        media+=nota[j][e][a];
                        if (a<2) {
                            System.out.print(", ");
                        }
                        else {
                            System.out.println();
                        }
                    }
                    System.out.println("MEDIA DE "+asignaturas[e]+": "+media/3);
                    media=0;
                    System.out.println("------------------------");
                }
                System.out.println();
            }
        }
    }
}
