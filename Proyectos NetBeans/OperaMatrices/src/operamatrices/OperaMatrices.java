package operamatrices;

public class OperaMatrices {
    public static void main(String[] args) {
        if (ComprobrarTipoArgs(args)) {
            int[] Args=ConvertirInt(args);
            for(int l=0;l!=args.length;l++) {
                if (Args[l]<0) {
                    System.err.println("LOS PARAMETROS INTRODUCIDOS NO SON NUMEROS POSITIVOS");
                    break;
                }
            }
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
}
