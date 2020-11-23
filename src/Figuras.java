public class Figuras {
    public static void main(String[] args) {
        char c;
        int n, j;
        int opcionElegida = pintaMenu();
        switch(opcionElegida) {
            case 1:
                n=ES.leeN("Introduce las filas del cuadrado: ");
                c=ES.leecaracter("Introduce el caracter: ");
                cuadrado(n,c);
                break;
            case 2:
                n=ES.leeN("Introduce las filas del rectangulo: ");
                j=ES.leeN("Introduce las columnas del rectangulo: ");
                c=ES.leecaracter("Introduce el caracter: ");
                rectangulo(n, j, c);
                break;
            case 3:
                n=ES.leeN("Introduce el numero de filas del triangulo: ");
                c=ES.leecaracter("Introduce el caracter: ");
                triangulo(n,c);
                break;
            case 4:
                n=ES.leeN("Introduce el numero de filas del rombo: ");
                c=ES.leecaracter("Introduce el caracter: ");
                rombo(n,c);
                break;
            default:
                System.err.println("La intruccion elegida no existe en el programa!");
                ES.debug(4);
        }
    }
    static int pintaMenu() {
        System.err.println("--" + "Figuras" + "--");
        System.out.println("1.-" + "Cuadrado");
        System.out.println("2.-" + "Rectangulo");
        System.out.println("3.-" + "Triangulo");
        System.out.println("4.-" + "Rombo");
        System.out.println("---------------");
        return ES.leeN("Introduce la opcion: ");
    }
    static void cuadrado(int n, char c) {
        for (int i=0;i!=n;i++) {
            System.out.println();
            for (int j=0;j!=n;j++) {
                System.out.print(c);
            }
        }
    }
    static void rectangulo(int n, int j, char c) {
        for (int i=0;i!=n;i++) {
            System.out.println();
            for (int k=0;k!=j;k++) {
                System.out.print(c);
            }
        }
    }
    static void triangulo(int n, char c) {
        int e=n-1,f=1;
        for (int i=0;i!=n;i++) {
            System.out.println();
            for (int k=0;k!=e;k++) {
                System.out.print(" ");
            }
            for (int j=0;j!=f;j++) {
                System.out.print(c);
            }
            e--;
            f=f+2;
        }
    }
    static void rombo(int n, char c) {
        int e=n-1,f=1;
        for (int i=0;i!=n;i++) {
            System.out.println();
            for (int k=0;k!=e;k++) {
                System.out.print(" ");
            }
            for (int j=0;j!=f;j++) {
                System.out.print(c);
            }
            e--;
            f=f+2;
        }
        System.out.println();
        for(int numBlancos=0,numCarac=(n*2)-1;numCarac>0;numBlancos++,numCarac-=2){
            for(int i=0; i<numBlancos;i++) {
                System.out.print(" ");
            }
            for(int j=numCarac;j>0;j--) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
