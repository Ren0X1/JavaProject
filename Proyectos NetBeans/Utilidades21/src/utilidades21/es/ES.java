package utilidades21.es;


/**
 * Clase creada para facilitar la lectura de datos desde el teclado

 */
public class ES {

    /**
     * Este mtodo captura todo lo que se introduce por teclado hasta pulsar enter
     * y lo devuelve como una cadena de caracteres (String)
     * @return String
     */
    public static String leeDeTeclado() {
      int tecla;
      String cadena = "";
      boolean fin = false;
      while (!fin) {
         try {
            tecla = System.in.read();
            if (tecla < 0 ||
                (char)tecla == '\n')
               fin = true;
            else
                if (!Character.isISOControl((char)tecla))
                    cadena += (char)tecla;
         } catch(java.io.IOException e) {
               fin = true;
         }
      }
      return cadena;
   }

   /**
    * Escribe un mensaje en pantalla, y queda a la espera de recoger toda la cadena
    * de caracteres que se introduzca desde teclado hasta pulsar enter.
    * @param  mensaje mensaje que se escribe
    * @return la cadena leida
    */

   public static String leeDeTeclado(String mensaje) {
      System.out.println(mensaje);
	  String cad=leeDeTeclado();
      return cad;
   }
   /**
       * Lee un nmero real (double) desde teclado, tras la escritura en pantalla
       * de un mensaje solicitndolo. La introduccin del nmero finaliza al pulsar enter
       * @param mensaje Mensaje que se imprime
       * @return el valor numrico leido (como double)
       * @exception NumberFormatException si se
       * introduce un valor incorrecto
    */
public static double leeNR()
{ while(true) {
          
           try {
              return Double.parseDouble(leeDeTeclado().trim());
           } catch(NumberFormatException e) {
              System.err.println("NO ES UN NMERO REAL VLIDO: Vuelve a intentarlo.");
           }
        }
}
   public static double leeNR(String mensaje) {
        while(true) {
           
           try {
              return Double.parseDouble(leeDeTeclado(mensaje).trim());
           } catch(NumberFormatException e) {
              System.err.println("NO ES UN NMERO REAL VLIDO: Vuelve a intentarlo.");
           }
        }
   }
   /**
    * Muestra un mensaje en pantalla para solicitar un nmero, y
    * espera a que se introduzca un valor desde teclado. La secuencia de caracteres que se
    * introduzcan hasta pulsar enter se intenta convertir en un nmero entero(int)
    * Si no es posible, se mostrar un mensaje de error. Su es posible, devuelve el
    * valor numrico correspondiente al nmero tecleado.
    * @param mensaje Mensaje que se muestra en pantalla para pedir el nmero
    * @return el nmero leido (como int)
    * @exception NumberFormatException si se
    * introduce un valor incorrecto para el nmero entero
    */

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
            System.err.println("NO ES UN NMERO ENTERO VLIDO: Vuelve a intentarlo.");
         }
      }
      return numero;
   }

   /**
    * Lee un entero mayor o igual al mnimo indicado como parmentro. Si se introducen
    * valores menores, indica mediante un mensaje de error que debe introducirse un
    * valor mayor.
    * @param mensaje El mensaje que solicita el nmero entero.
    * @param minimo Es el menor nmero entero admitido
    * @return el entero leido
    */
   public static int leeN(String mensaje,int minimo) {
        int entero;
        do {
                entero = leeN(mensaje);
                if (entero < minimo)
                       System.err.println(
                       " EL MNIMO VALOR PERMITIDO ES: " + minimo);
        } while (entero < minimo);

        return entero;
   }

   /**
    * Lee un entero mayor o igual al mnimo y menor o igual que el mximo indicados
    *  como parmentros. Si se introducen valores menores que el mnimo o mayores que
    * el mximo, indica mediante un mensaje de error que debe introducirse un
    * valor dentro del rango (mayor o menor, segn corresponda).
    * @param mensaje El mensaje que solicita el nmero entero.
    * @param minimo Es el menor nmero entero admitido
    * @param maximo Es el mayor nmero entero admitido
    * @return el entero leido
    */

   public static int leeN(String mensaje,int minimo, int maximo) {
   
        int entero;
        do {
                entero = leeN(mensaje);
                if (entero < minimo)
                       System.err.println(
                      " EL MNIMO VALOR PERMITIDO ES: " + minimo);
                else if (entero > maximo)
                       System.err.println(
                      " EL MXIMO VALOR PERMITIDO ES: " + maximo);
        } while (entero < minimo || entero > maximo);

        return entero;
   }

	public static char leecaracter(String mensaje)
	{
	String cadena;
	char op;
	cadena= leeDeTeclado (mensaje);
	op= cadena.charAt(0);
	return op;
	}




}
