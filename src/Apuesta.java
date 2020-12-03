public class Apuesta {
    public static void main (String[] args) {
        int n=ES.leeN("Introduce el numero de apuestas que deseas realizar: ");
        int[] numeros=new int[10];
        int[] reintegro=new int[3];
        char[] letras=new char[3];
        apuesta(n,numeros,reintegro,letras);
    }
    static double aleatorio(int min,int max) {
        return min+Math.floor(Math.random()*max+1);
    }
    static void PintarArray(int[] numeros) {
        for (int a=0;a!=numeros.length;a++) {
            System.out.print(numeros[a]);
            if(a!=numeros.length-1){System.out.print(", ");}
        }
    }
    static void PintarCaracteres(char[] numeros) {
        for (int a=0;a!=numeros.length;a++) {
            System.out.print(numeros[a]);
        }
    }
    static boolean compruebaRepetidos(int[] numeros,int x,int generado) {
        for (int i=0;i!=x;i++) {
            if (numeros[i]==generado) {
                return true;
            }
        }
        return false;
    }
    static void numeros(int[] numeros){
        int x=0,j;
        for (int i=0;i!=numeros.length;i++) {
            do {
                j=(int)aleatorio(10,89);
            } while (compruebaRepetidos(numeros,x,j));
            numeros[i]=j;
            x++;
        }
    }
    static void reintegro(int[] reintegro){
        int j;
        for (int i=0;i!=reintegro.length;i++) {
            j=(int)aleatorio(1,9);
            reintegro[i]=j;
        }
    }
    static void letras(char[] letras){
        char j;
        for (int i=0;i!=letras.length;i++) {
            j=(char)aleatorio(65,25);
            letras[i]=j;
        }
    }
    static void apuesta(int n,int[] numeros,int[] reintegro,char[] letras) {
        for (int k=0;k!=n;k++) {
            numeros(numeros);
            reintegro(reintegro);
            letras(letras);
            System.out.println("Apuesta N "+(k+1)+" : ");
            PintarArray(numeros);
            System.out.println();
            System.out.println("Reintegro N "+(k+1)+" : ");
            PintarArray(reintegro);
            System.out.println();
            System.out.println("Codigo N "+(k+1)+" : ");
            System.out.print(Math.round(aleatorio(100,899)));
            System.out.print("-");
            PintarCaracteres(letras);
            System.out.print("-");
            System.out.print(Math.round(aleatorio(10000,89999)));
            System.out.println();
            System.out.println();
        }
    }
}
