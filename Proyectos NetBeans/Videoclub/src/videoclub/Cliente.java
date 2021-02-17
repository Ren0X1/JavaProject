package videoclub;

import java.util.ArrayList;
import java.util.TreeSet;

public class Cliente extends Persona {
    ArrayList <Copia> listaPeliculas;
    public Cliente(String nombre,String ape1,String ape2, String nif, String localidad) {
        super(nombre, ape1, ape2, nif);
        this.localidad= localidad;
    }

    public ArrayList<Copia> getListapeliculas() {
        return listaPeliculas;
    }

    public void setListapeliculas(ArrayList<Copia> Listapeliculas) {
        this.listaPeliculas = Listapeliculas;
    }

    @Override
    public String toString() {
        return nombre + "{"+nif+"-"+localidad+"}\n";
    }
    
    public static Cliente altaCliente(){
        Persona per=altaPersona();
        Cliente cli=new Cliente(per.nombre,per.apellido1,per.apellido2,per.nif,ES.leeDeTeclado("Pueblo de residencia: "));
        System.out.println("Cliente "+ cli +" dado de alta con exito!");
        return cli;
    }
    
    public Cliente BuscaClientesNombre(String nombre, TreeSet <Cliente> listacliente){
        Cliente cliente= null;
        ArrayList<Cliente> listaCliente= new ArrayList(listacliente);
        for(int i=0;i<listaCliente.size();i++){
            if(listaCliente.get(i).getNombre().equals(nombre)){
                cliente=listaCliente.get(i);
            }
        }
        return cliente;
    }
    
    public  Cliente listaPersona(TreeSet <Cliente> listacliente){
        int nop=1;
        String nnom;
        listacliente.forEach((cli) -> {
            System.out.println(nop+".-"+cli);
        });
        nnom=ES.leeDeTeclado("Escoge el nombre del Cliente que quieras asignar");
        // Cliente clienten = null;//new Cliente();
        return BuscaClientesNombre(nnom, listacliente);   
    }
  
}
