package OLD;

public class Pi {
    public static void main(String[] args) {
        int opcionElegida=pintaMenu();
        switch (opcionElegida) {
            case 1:
                float base, altura, area;
                String baseS, alturaS;
                baseS= ES.leeDeTeclado("Introduce la base: ");
                alturaS= ES.leeDeTeclado("Introduce la altura: ");
                base=Float.parseFloat(baseS);
                altura=Float.parseFloat(alturaS);
                area=base*altura/2;
                System.out.println("El area del triangulo es: "+area);
                ES.debug(1);
                break;
            case 2:
                ES.debug(2);
                break;
            case 3:
                ES.debug(3);
                break;
            default:
                System.err.println("La intruccion elegida no existe en el programa!");
                ES.debug(4);
                break;
        }
    }
    static int pintaMenu() {
        int opcion;
        System.err.println("--"+ "Calcular Areas" +"--");
        System.out.println("1.-"+ "Triangulo");
        System.out.println("2.-"+ "Circulo");
        System.out.println("3.-"+ "Cilindro");
        System.out.println("---------------");
        opcion= ES.leeN("Introduce la opcion: ");
        return opcion;
    }
}
