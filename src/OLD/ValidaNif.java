package OLD;

public class ValidaNif {
    public static void main (String[] args) {
        String letraNIF2 = "TRWAGMYFPDXBNJZSQVHLCKE";
        String respuesta;
        boolean valido;
        do {
            respuesta = ES.leeDeTeclado("Introduce NIF: ");
            valido = respuesta.matches("[0-9]{8}[A-Z]");

            if (!valido) {
                System.err.println("Falta la LETRA");
                valido = respuesta.matches("[0-9]{8}");
                if (!valido) {
                    System.err.println("Faltan algun numero.");

                } else {
                    int n = Integer.parseInt(respuesta);
                    respuesta = respuesta + letraNIF(n,letraNIF2);
                    System.out.println(respuesta);
                }
            }
        } while (!valido);
        System.out.println("NIF correcto. ");
    }
    static char letraNIF (int n, String x) {
        int resul = Matematicas.resto(n,23);
        char letra = ' ';
        for (int i=0;i!=21; i++) {
            letra = x.charAt(resul);
        }
        return letra;
    }
}
