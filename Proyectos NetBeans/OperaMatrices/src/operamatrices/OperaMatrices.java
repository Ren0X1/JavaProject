package operamatrices;

import java.util.Arrays;

public class OperaMatrices {
    public static void main(String[] args) {
        if (ComprobrarTipoArgs(args)) {
            for(int l=0;l!=args.length;l++) {
                int estado=ComprobrarTipoEntero(args,l);
                if (estado!=1) {
                    System.err.println("LOS PARAMETROS INTRODUCIDOS NO SON NUMEROS POSITIVOS");
                    break;
                }
            }
            int[] Args=ConvertirInt(args);

            pintaM(generaM(Args[0],Args[1],Args[2],Args[3]));
        } else {
            System.err.println("LOS PARAMETROS INTRODUCIDOS NO SON ENTEROS");
        }
    }
    
    private static int[][] generaM(int filas, int columnas, int minimo, int maximo) {
        int[][] matriz = new int[filas][columnas];
        for (int i=0;i!=filas;i++) {
            for (int k=0;k!=columnas;k++) {
                matriz[i][k]=aleatorio(minimo,maximo);
            }
        }
        return matriz;
    }

    private static void pintaM(int[][] generaM) {
        for (int i=0;i!=generaM.length;i++) {   
            for (int k=0;k!=generaM[i].length;k++) {
                System.out.print(" "+generaM[i][k]);
            }
            System.out.println();
        }
    }
    
    private static int aleatorio(int min, int max) {
        return (int)(min+Math.floor(Math.random()*(max-min)+1));
    }
    
    private static boolean ComprobrarTipoArgs(String[] argumentos) {
        int c=0;
        for (int j=0;j!=argumentos.length;j++) {
            try {
                Integer.parseInt(argumentos[j]);
                c++;
            } catch(NumberFormatException e){
                //No es un entero
            }
        }
        return c == argumentos.length;//Comprobamos si todos son enteros o no
    }
    
    private static int[] ConvertirInt(String[] argumentos) {
        int[] conv=new int[argumentos.length];
        for (int i=0;i!=conv.length;i++) {
            conv[i]=Integer.parseInt(argumentos[i]);
        }
        return conv;
    }
    
    private static int ComprobrarTipoEntero(String[] argumentos,int n) {
        int i=Integer.parseInt(argumentos[n]);
        switch (Args.RangoNumerico.from(i)) {
            case ENTERO:
                return 1;//Si es positivo devolvemos 1
            case NEGATIVO:// Si es negativo devolvemos 2
                return 2;
            default:
                return 0;// Si por algun caso el metodo falla devolvemos 0
        }
    }
    
    private enum RangoNumerico {
        ENTERO(0, 32000),
        NEGATIVO(-32000, -1),
        OTRO(0, -1);
        private final int minimo;
        private final int maximo;
        RangoNumerico(int min, int max) {
            this.minimo = min;
            this.maximo = max;
        }
        public static RangoNumerico from(int numero) {return Arrays.stream(RangoNumerico.values()).filter(range -> numero >= range.minimo && numero <= range.maximo).findAny().orElse(OTRO);}
    }
}
