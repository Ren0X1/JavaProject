import java.util.*;//Importamos todo el java util ya que es una clase de utilidad
public class Args {
    //Metodo para comprobar los numeros de parametros, el maximo s lo pasamos por parametro
    public static boolean ComprobrarLongitud(String[] argumentos,int n) {
        return argumentos.length == n;//Comprobamos la longitud
    }
    //Con esto comprobamos si los argumentos son de tipo entero o otro tipo
    public static boolean ComprobrarTipoArgs(String[] argumentos) {
        int c=0;
        for (int j=0;j!=argumentos.length;j++) {
            try {
                Integer.parseInt(argumentos[j]);
                c++;
            } catch(NumberFormatException e){
                //No es un entero :(
            }
        }
        return c == argumentos.length;//Comprobamos si todos son enteros o no
    }
    //Con esto comprobamos que tipo de entero es, si negativo o positivo
    public static int ComprobrarTipoEntero(String[] argumentos,int n) {
        int i=Integer.parseInt(argumentos[n]);
        switch (RangoNumerico.from(i)) {
            case ENTERO:
                return 1;//Si es positivo devolvemos 1
            case NEGATIVO:// Si es negativo devolvemos 2
                return 2;
            default:
                return 0;// Si por algun caso el metodo falla devolvemos 0
        }
    }
    //Metodo para convertir un array de caracteres a array de enteros
    public static int[] ConvertirInt(String[] argumentos) {
        int[] conv=new int[argumentos.length];
        for (int i=0;i!=conv.length;i++) {
            conv[i]=Integer.parseInt(argumentos[i]);
        }
        return conv;
    }
    //Metodo para pintar una array
    public static void PintarArray(int[] numeros) {
        for (int a=0;a!=numeros.length;a++) {
            System.out.print(numeros[a]);
            if(a!=numeros.length-1){System.out.print(", ");}
        }
    }
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
    public static int[] GenerarApuesta(int longitud, int numeros) {
        int[] apuesta=new int[longitud];
        for (int i=0;i!= apuesta.length;i++) {
           apuesta[i]=(int)aleatorio(numeros);
        }
        return apuesta;
    }
    //Metodo para ordenar una array
    public static void Ordenar(int[] array) {
        Arrays.sort(array);
    }
    //Metodo para crear los rangos numericos para comprobarlos con el metodo de @ComprobarTipoEntero
    public enum RangoNumerico {
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
