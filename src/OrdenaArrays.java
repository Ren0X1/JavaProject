public class OrdenaArrays {
    public static void main (String[] args) {
        if (Args.ComprobrarTipoArgs(args)) {
            int[] numeros=Args.ConvertirInt(args);
            Args.Ordenar(numeros);
            Args.PintarArray(numeros);
        }
        else {
            System.err.println("HAS METIDO ALGUN PARAMETRO QUE NO ES UN ENTERO");
        }
    }
}
