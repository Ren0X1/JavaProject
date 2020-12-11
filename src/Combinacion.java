public class Combinacion {
    public static double aleatorio(double min, double max) {
        return min+Math.floor(Math.random()*(max-min)+1);
    }
    public static int[] GenerarApuesta(int longitud, int min,int max) {
        int[] apuesta=new int[longitud];
        for (int i=0;i!= apuesta.length;i++) {
            apuesta[i]=(int)aleatorio(min,max);
        }
        return apuesta;
    }
    public static double[] GenerarApuesta(int longitud, double min,double max) {
        double[] apuesta=new double[longitud];
        for (int i=0;i!= apuesta.length;i++) {
            apuesta[i]=aleatorio(min,max);
        }
        return apuesta;
    }
}
