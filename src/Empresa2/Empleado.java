package Empresa2;
public class Empleado extends Persona{
    private double sueldoBruto;
    private String telefono;
     

    
    public Empleado(double sueldoBruto, String nombre, String nif,String telefono ) {
        super(nombre, nif);
        this.sueldoBruto = sueldoBruto;
        this.telefono = telefono;
        
    }

    public Empleado(double sueldoBruto, String telefono, String nombre, String apellido1, String apellido2, String nif) {
        super(nombre, apellido1, apellido2, nif);
        this.sueldoBruto = sueldoBruto;
        this.telefono = telefono;
    }
    
    
    public double getSueldoBruto() {
        return sueldoBruto;
    }

    public void setSueldoBruto(double sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //-------------------------------
    double calcular_salario_neto(){
        return sueldoBruto-=(sueldoBruto* (double) 15)/100;
    }

    @Override
    public String toString() {
        return "Empleado{" + "sueldoBruto=" + sueldoBruto + ", telefono=" + telefono + "}\n"+
         super.toString();
    }
   //---------------------------------------------
   public static Empleado altaEmpleado(){
      System.out.println("Alta de Empleado...");
      Persona per   =Persona.altaPersona();


       return new Empleado(ES.leeNR("sueldo? "),
                                 ES.leeDeTeclado("telefono? "),
                                 per.getNombre(),
                                 per.getApellido1(),
                                 per.getApellido2(),
                                 per.getNif());
    }
  //------------------------------------------------------------  
//------------------------------------------------------------  
   public String listadoCorto (){
       return "("+calcular_salario_neto()+")"+super.getApellido1()+" "+super.getApellido2()+", "+super.getNombre();
   }
    

    
}
