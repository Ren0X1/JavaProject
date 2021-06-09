package operamatrices;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OperaMatrices {
    public static void main(String[] args) {
        try {
            if (ComprobrarTipoArgs(args)) {
                int[] Args=ConvertirInt(args);
                for(int l=0;l!=Args.length;l++) {
                    if (Args[l]<0) {
                        System.err.println("LOS PARAMETROS INTRODUCIDOS NO SON NUMEROS POSITIVOS");
                        break;
                    }
                }
                if ((Math.max(Args[2],Args[3])-Math.min(Args[2],Args[3]))<(Args[0]*Args[1])) {
                    System.err.println("LA DIFERENCIA DE NUMEROS MINIMO Y MAXIMO NO ES SUFICIENTE PARA GENRAR LA MATRIZ, DEBE SER MAYOR QUE LA MULTIPLICACION DE LAS FILAS Y COLUMNAS.");
                } else {
                    pintaM(generaM(Args[0],Args[1],Args[2],Args[3]));
                }
            } else {
                System.err.println("LOS PARAMETROS INTRODUCIDOS NO SON ENTEROS");
            }
        } catch(Exception ex) {
            System.err.println("ARGUMENTOS NO ENCONTRADOS");
        }
    }

    public static int[][] generaM(int filas, int columnas, int minimo, int maximo) {
        int[][] matriz = new int[filas][columnas];
        for (int i=0;i!=filas;i++) {
            for (int k=0;k!=columnas;k++) {
                int n;
                do {
                    n=aleatorio(minimo,maximo);
                } while(CompruebaRepetido(matriz,n));
                matriz[i][k]=n;
            }
        }
        return matriz;
    }

    private static boolean CompruebaRepetido(int[][] x, int n) {
        for (int i=0;i!=x.length;i++) {
            for (int k=0;k!=x[i].length;k++) {
                if (n==x[i][k]) {
                    return true;
                }
            }
        }
        return false;
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
