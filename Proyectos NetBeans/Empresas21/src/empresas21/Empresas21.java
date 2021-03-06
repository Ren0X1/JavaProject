/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas21;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;
import utilidades21.Personas.Persona;
import utilidades21.es.ES;

/**
 *
 * @author Usuario
 */
public class Empresas21 {

    static  HashMap <String,Empresa> listaEmpresas= new HashMap();
    static  TreeSet <Cliente>        listaClientes= new TreeSet(new Persona.ComparaPersonasAlfa());
    
    
    public static void main(String[] args) {
        int opcion,opcionMa,opcionLi,opcionDir;
        Empresa emp;
        
      do{  
       System.out.println("------MENÚ PRINCIPAL-------\n"+
			"\t1.-Menú Altas.........\n"+
			"\t2.-Menú listados.......\n"+
			"\t3.-Menú Directivos.......\n"+
                       	"\t0.-Salir................\n"+
			"--------------------------\n");	
							
	 opcion=ES.leeN("Escribe la opción deseada: ");
			
		switch(opcion){
			case 1:
                        do{    
                         System.out.println("------MENÚ de ALTAS-------\n"+
			"\t1.- Alta Empresa..............\n"+
			"\t2.- Alta Empleado.............\n"+
			"\t3.- Nuevo Cliente.............\n"+
                        "\t4.- Alta Cliente Empresa......\n"+
                       	"\t0.- Volver al menú principal..\n"+
			"--------------------------\n");	
							
                         opcionMa=ES.leeN("Escribe la opción deseada: "); 
                         switch(opcionMa){
                            case 1:
                                System.out.println("Alta de Empresa...");
                                emp= new Empresa(ES.leeDeTeclado("Nombre de la Empresa? "));
                                listaEmpresas.put(emp.getNombre(), emp);
                                
                               break;
                             case 2:
                                 emp=(Empresa)seleccionaDeColeccion(listaEmpresas.values());
                                 
                                if(emp!=null){ 
                                    Empleado emple = Empleado.altaEmpleado();
                                    emp.getListaEmpelados().put(emple.getNif(), emple);
                                }
                                else
                                     System.out.println("Alta de empleado cancelada por el usuario");
                                
                               break;
                             case 3:
                                 System.out.println("Alta de Cliente...");
                                 Cliente cli = Cliente.altaCliente();
                                 listaClientes.add(cli);
                                 
                               break;
                              case 4:
                                 System.out.println("Asignar Cliente a Empresa...");
                                 
                                  System.out.println("Elige el cliente");
                                  cli=(Cliente)seleccionaDeColeccion(listaClientes);
                                  
                                  if(cli!=null){ 
                                    System.out.println("Elige la empresa"); 
                                    emp=(Empresa)seleccionaDeColeccion(listaEmpresas.values());
                                    
                                    if(emp!=null){ 
                                      emp.getListaClientes().add(cli);
                                    }
                                    else System.out.println("operacion cancelada por el usuario");
                                  }
                                  else System.out.println("operacion cancelada por el usuario");
                                 
                               break;  
                         }  
                         
                         }while(opcionMa!=0);        
                             
                        break;
                        
                        
                        case 2://Menu Listado
                        
                        do{    
                            
                        System.out.println("------MENÚ Listados -------\n"+
			"\t1.- Listar Empresas..........\n"+
			"\t2.- Listar Empleados..........\n"+
			"\t3.- Listar Clientes Empresa...\n"+
                        "\t4.- Listar Todos los Clientes..\n"+
                       	"\t0.-Volve al menú principal..\n"+
			"--------------------------\n");	
							
                         opcionLi=ES.leeN("Escribe la opción deseada: "); 
                         switch(opcionLi){
                            case 1:
                               System.out.println("Listado de Empresa...");
                                System.out.println(listaEmpresas);
                              
                            break;
                            case 2:
                               System.out.println("Listado de Empleados...");
                               
                               System.out.println("Elige la empresa"); 
                               emp=(Empresa)seleccionaDeColeccion(listaEmpresas.values());
                               if(emp!=null)
                                    System.out.println(emp.getListaEmpelados());
                              
                            break;
                            case 3:
                               System.out.println("Listado de Clientes...");
                               
                               System.out.println("Elige la empresa"); 
                               emp=(Empresa)seleccionaDeColeccion(listaEmpresas.values());
                               if(emp!=null)
                                    System.out.println(emp.getListaClientes());
                              
                            break;
                            case 4:
                                System.out.println("Listado General de Clientes...");
                                System.out.println(listaClientes);  
                                break;
                         }
                        }while(opcionLi!=0);
                        
                        
                        break;
                        case 3://Menu Directivos
                        
                        do{    
                            
                        System.out.println("------MENÚ Listados -------\n"+
			"\t1.- Listar Directivos..........\n"+
			"\t2.- Listar Subordinados.........\n"+
			"\t3.- Ascender Empleado a Directivo...\n"+
                        "\t4.- Añadir Subordinado..\n"+
                       	"\t0.-Volve al menú principal..\n"+
			"--------------------------\n");	
							
                         opcionDir=ES.leeN("Escribe la opción deseada: "); 
                         switch(opcionDir){
                            case 1:
                            break;
                         }
                        }while(opcion!=0); 
                            
                        break;
                        
                        
                        case 0:
                            System.out.println("Fin de la palicación...");
                        break;
                        default: System.out.println("Opción errónea...");
                        } 
                    
      
      }while(opcion!=0);    
       }//main
   //---------------------------------
   static Object  seleccionaDeColeccion(Collection lista){
     System.out.println("Listado de Empresa...");    
     int i=0;
    
     TreeSet listaOrd = new TreeSet(lista);
     ArrayList listaEmpre= new ArrayList(listaOrd);
     
     while(i<1 || i> listaEmpre.size()){
       i=1;
       for (Object ele : listaEmpre) {
           System.out.println(i+".- "+ele);//.listadoCorto());//.getNombre());
           i++;
       }
       i=ES.leeN("Escribe el número de la empresa? ");
       
       if(i<1 || i> listaEmpre.size()){
            System.out.println("No existe una empresa con ese número");
            char c=ES.leecaracter("Escribe 'c' para cancelar");
            if (c == 'c')
                return null;
       } 
      }
     
     return listaEmpre.get(i-1);
    }
    

}//class
