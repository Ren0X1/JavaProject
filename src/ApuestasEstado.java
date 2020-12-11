public class ApuestasEstado {
    public static void main (String[] args) {
        String[] apuestas={"Euromillon","Primitiva","Bonoloto","El Gordo","Loteria Nacional"};
        int[] euromillon={5,1,50};
        int[] estrellas={2,1,50};
        char[] millon=new char[3];
        int[] primitiva={6,1,49};
        int[] bonoloto={6,1,49};
        int[] gordo={5,1,54};
        double[] loteria={1,10000,99999};
        int[] serie={3,1,9};
        int op;
        do {
            GenerarMenu(apuestas);
            op=ES.leeN("Introduce la opcion elegida: ");
            switch (op) {
                case 1:
                    int[] resultadoEuro=Combinacion.GenerarApuesta(euromillon[0],euromillon[1],euromillon[2]);
                    System.out.println("Resultado: ");
                    PintarArray(resultadoEuro);
                    Espacio();
                    System.out.println("Estrellas: ");
                    int[] resultadoEstrellas=Combinacion.GenerarApuesta(estrellas[0],estrellas[1],estrellas[2]);
                    PintarArray(resultadoEstrellas);
                    Espacio();
                    System.out.println("ELMILLON: ");
                    GenerarLetras(millon);
                    PintarCaracteres(millon);
                    System.out.print(Math.round(Combinacion.aleatorio(10000,99999)));
                    Espacio();
                    ES.continuar();
                    continue;
                case 2:
                    int[] resultadoPrimi=Combinacion.GenerarApuesta(primitiva[0],primitiva[1],primitiva[2]);
                    System.out.println("Resultado: ");
                    PintarArray(resultadoPrimi);
                    Espacio();
                    System.out.println("C: ");
                    System.out.println(Math.round(Combinacion.aleatorio(1,49)));
                    Espacio();
                    System.out.println("R: ");
                    System.out.print(Math.round(Combinacion.aleatorio(1,9)));
                    Espacio();
                    ES.continuar();
                    continue;
                case 3:
                    int[] resultadoBono=Combinacion.GenerarApuesta(bonoloto[0],bonoloto[1],bonoloto[2]);
                    System.out.println("Resultado: ");
                    PintarArray(resultadoBono);
                    Espacio();
                    System.out.println("C: ");
                    System.out.println(Math.round(Combinacion.aleatorio(1,49)));
                    Espacio();
                    System.out.println("R: ");
                    System.out.print(Math.round(Combinacion.aleatorio(1,9)));
                    Espacio();
                    ES.continuar();
                    continue;
                case 4:
                    int[] resultadoGordo=Combinacion.GenerarApuesta(gordo[0],gordo[1],gordo[2]);
                    System.out.println("Resultado: ");
                    PintarArray(resultadoGordo);
                    Espacio();
                    System.out.println("Numero Clave: ");
                    System.out.println(Math.round(Combinacion.aleatorio(1,9)));
                    Espacio();
                    ES.continuar();
                    continue;
                case 5:
                    double[] resultadoLoteria=Combinacion.GenerarApuesta(1,loteria[1],loteria[2]);
                    System.out.println("Resultado: ");
                    PintarArray(resultadoLoteria);
                    Espacio();
                    System.out.println("Serie: ");
                    int[] resultadoSerie=Combinacion.GenerarApuesta(serie[0],serie[1],serie[2]);
                    PintarArray(resultadoSerie);
                    Espacio();
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
        } while (op!=0);

    }
    static void Espacio(){
        System.out.println();
        System.out.println();
    }
    static void GenerarMenu(String[] x) {
        for (int i=0;i!=x.length;i++) {
            System.out.println((i+1)+".- "+x[i]);
            if (i==x.length-1) {
                System.out.println("0.- Salir");
            }
        }
        System.out.println("-------------------------------");
    }
    static void GenerarLetras(char[] letras){
        char j;
        for (int i=0;i!=letras.length;i++) {
            j=(char)Combinacion.aleatorio(65,90);
            letras[i]=j;
        }
    }
    static void PintarArray(int[] numeros) {
        for (int a=0;a!=numeros.length;a++) {
            System.out.print(numeros[a]);
            if(a!=numeros.length-1){System.out.print(", ");}
        }
    }
    static void PintarArray(double[] numeros) {
        for (int a=0;a!=numeros.length;a++) {
            System.out.print(Math.round(numeros[a]));
            if(a!=numeros.length-1){System.out.print(", ");}
        }
    }
    static void PintarCaracteres(char[] caracteres) {
        for (int a=0;a!=caracteres.length;a++) {
            System.out.print(caracteres[a]);
        }
    }
}
