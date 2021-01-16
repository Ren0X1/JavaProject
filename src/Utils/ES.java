package Utils;
public class ES {
    public static String leeDeTeclado() {
        int tecla;
        StringBuilder cadena = new StringBuilder();
        boolean fin = false;
        while (!fin) {
            try {
                tecla = System.in.read();
                if (tecla < 0 || (char)tecla == '\n') {
                    fin = true;
                }
                else if (!Character.isISOControl((char)tecla)) {
                    cadena.append((char) tecla);
                }
            } catch(java.io.IOException e) {
                fin = true;
            }
        }
        return cadena.toString();
    }
    public static String leeDeTeclado(String mensaje) {
        System.out.println(mensaje);
        return leeDeTeclado();
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void debug(String x) {
        try {
            int n=Integer.parseInt(x);
            System.out.println(ANSI_YELLOW+"Debug Nº: "+ANSI_RED+n+ANSI_RESET);
        } catch (NumberFormatException e) {
            System.err.println("PARAR QUE EL DEBUG FUNCIONE TIENES QUE METER UN NUMERO ENTERO DE 0-32K");
        }
    }

    public static double leeNR() {
        while(true) {
            try {
              return Double.parseDouble(leeDeTeclado().trim());
            } catch(NumberFormatException e) {
              System.err.println("NO ES UN NÚMERO REAL VÁLIDO: Vuelve a intentarlo.");
            }
        }
    }
    public static double leeNR(String mensaje) {
        while(true) {
            try {
              return Double.parseDouble(leeDeTeclado(mensaje).trim());
            } catch(NumberFormatException e) {
              System.err.println("NO ES UN NÚMERO REAL VÁLIDO: Vuelve a intentarlo.");
            }
        }
    }
    public static int leeN(String mensaje) {
        int numero=0;
        boolean incorrecto=true;
            while(incorrecto) {
                System.out.print(mensaje);
                try {
                    numero=Integer.parseInt(leeDeTeclado().trim());
                    incorrecto=false;
                } catch(NumberFormatException e) {
                    incorrecto=true;
                    System.err.println("NO ES UN NÚMERO ENTERO VÁLIDO: Vuelve a intentarlo.");
                }
            }
        return numero;
    }
    public static int leeN(String mensaje,int minimo) {
        int entero;
        do {
            entero = leeN(mensaje);
            if (entero < minimo) {
                System.err.println(" EL MÍNIMO VALOR PERMITIDO ES: " + minimo);
            }
        } while (entero < minimo);
        return entero;
    }
    public static int leeN(String mensaje,int minimo, int maximo) {
        int entero;
        do {
            entero = leeN(mensaje);
            if (entero < minimo) {
                System.err.println(" EL MÍNIMO VALOR PERMITIDO ES: " + minimo);
            }
            else if (entero > maximo) {
                System.err.println(" EL MÁXIMO VALOR PERMITIDO ES: " + maximo);
            }
        } while (entero < minimo || entero > maximo);
        return entero;
    }
    public static char leecaracter(String mensaje) {
        String cadena;
        char op;
        cadena= leeDeTeclado (mensaje);
        op= cadena.charAt(0);
        return op;
    }
    public static void continuar() {
        ES.leeDeTeclado("Pulsa \"ENTER\" para continuar...");
    }
}
