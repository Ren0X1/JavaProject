package OLD;

import java.util.Arrays;
public class Cadenas {
    public static void main (String[] args) {
        String[] pares=new String[args.length];
        String[] impares=new String[args.length];
        PintarArray(args);
        ArrayPares(args,pares);
        ArrayImpares(args,impares);
        Arrays.sort(pares);
        Arrays.sort(impares);
        System.out.println();
        System.out.println("PARES: ");
        PintarArray(pares);
        System.out.println();
        System.out.println("IMPARES: ");
        PintarArray(impares);
    }
    static void PintarArray(String[] array) {
        for (int a=0;a!=array.length;a++) {
            if (!array[a].equals("null")) {
                System.out.print(array[a]);
                if(a!=array.length-1){System.out.print(", ");}
            }
        }
    }
    static void ArrayPares(String[] array,String[] pares) {
        for (int a=0;a!=array.length;a++) {
            if (array[a].length()%2==0) {
                pares[a]=array[a];
            }
            else {
                pares[a]="null";
            }
        }
    }
    static void ArrayImpares(String[] array,String[] impares) {
        for (int a=0,i=0;a!=array.length;a++,i++) {
            if (array[a].length()%2!=0) {
                impares[i]=array[a];
            }
            else {
                impares[a]="null";
            }
        }
    }
}
