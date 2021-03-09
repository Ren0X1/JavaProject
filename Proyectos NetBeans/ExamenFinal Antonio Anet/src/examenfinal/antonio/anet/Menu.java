/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenfinal.antonio.anet;

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author usuario
 */
public class Menu {
    
    String nombreMenu;
    Float precio;
    
    Plato primerPlato;
    Plato segundoPlato;
    Plato postre;


    public Menu(String nombreMenu, Plato primerPlato, Plato segundoPlato, Plato postre) {
        this.nombreMenu = nombreMenu;
        precio = primerPlato.getPrecio() + segundoPlato.getPrecio() + postre.getPrecio();
        this.primerPlato = primerPlato;
        this.segundoPlato = segundoPlato;
        this.postre = postre;

    }

    
    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Plato getPrimerPlato() {
        return primerPlato;
    }

    public void setPrimerPlato(Plato primerPlato) {
        this.primerPlato = primerPlato;
    }

    public Plato getSegundoPlato() {
        return segundoPlato;
    }

    public void setSegundoPlato(Plato segundoPlato) {
        this.segundoPlato = segundoPlato;
    }

    public Plato getPostre() {
        return postre;
    }

    public void setPostre(Plato postre) {
        this.postre = postre;
    }


    @Override
    public String toString() {
        return "Menu{" + "nombreMenu=" + nombreMenu + ", precio=" + precio + ", primerPlato=" + primerPlato + ", segundoPlato=" + segundoPlato + ", postre=" + postre + '}';
    }
    
    
    void listaMenu(){
    
        System.out.println("Menu: " + nombreMenu);
        System.out.println("\tPrimer plato: " + primerPlato.getNombrePlato());
        System.out.println("\tSegundo plato: " + segundoPlato.getNombrePlato());
        System.out.println("\tTercer plato: " + postre.getNombrePlato());
        System.out.println("\tPrecio: " + precio);
        System.out.println("");
        
    }
    
    
    void listaMenuDetalle(){
    
        primerPlato.listaPlatoDetalle();
        segundoPlato.listaPlatoDetalle();
        postre.listaPlatoDetalle();
        System.out.println("Precio Total = " + precio + "€");
        
    }
   
    
}
