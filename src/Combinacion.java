public class Combinacion {
    public static double aleatorio(int n) {
        int min,max;
        switch (n) {
            case 1:
                min=1;
                max=9;
                break;
            case 2:
                min=10;
                max=99;
                break;
            case 3:
                min=100;
                max=999;
                break;
            case 4:
                min=1000;
                max=9999;
                break;
            default:
                min=1;
                max=32000;
        }
        return min+Math.floor(Math.random()*(max-min)+1);
    }
    public static double aleatorio(int min, int max) {
        return min+Math.floor(Math.random()*(max-min)+1);
    }
    public static int[] GenerarApuesta(int longitud, int numeros) {
        int[] apuesta=new int[longitud];
        for (int i=0;i!= apuesta.length;i++) {
            apuesta[i]=(int)aleatorio(numeros);
        }
        return apuesta;
    }
    public static int[] GenerarApuesta(int longitud, int min,int max) {
        int[] apuesta=new int[longitud];
        for (int i=0;i!= apuesta.length;i++) {
            apuesta[i]=(int)aleatorio(min,max);
        }
        return apuesta;
    }
}
