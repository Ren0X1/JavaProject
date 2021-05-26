/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peliculas;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.EventObject;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.UIManager; 
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Usuario
 */
public class MiVentana extends JFrame implements ActionListener,WindowListener {
    //ItemListener,WindowListener {))
    

    String tipoVista;
    JRadioButtonMenuItem bMotif, bMetal, bWindows, bnimbus;
    ButtonGroup vistas;
    JPanel cpane;
    
    public MiVentana(String titulo,int ancho, int alto){
       super(titulo);
       cpane =(JPanel) this.getContentPane();
       this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       
             
     // Dimensionamos y Centramos la ventana...............................  
       
        Dimension t_pant = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension t_vent = new Dimension(ancho,alto);
        
        // Calculamos las coordenadas para que quede centrado
        int x = (t_pant.width/2) - (t_vent.width/2);
        int y = (t_pant.height/2) - (t_vent.height/2);
        setBounds(x, y, t_vent.width, t_vent.height);
        
        
     //--- Menu Look&Feel con JRadiobutonItemMenu
        
        bMotif = new JRadioButtonMenuItem("Motif (Unix)",false);
        bMetal = new JRadioButtonMenuItem("Metal (Java)",true);
        bWindows = new JRadioButtonMenuItem("Windows",false);
        bnimbus = new JRadioButtonMenuItem("Bnimbus",false);
        
        vistas = new ButtonGroup();
        vistas.add(bMotif);
        vistas.add(bMetal);
        vistas.add(bWindows);
        vistas.add(bnimbus);
        
      /*  bMotif.addItemListener(this);
        bMetal.addItemListener(this);
        bWindows.addItemListener(this);
        bnimbus.addItemListener(this);*/
        bMotif.addActionListener(this);
        bMetal.addActionListener(this);
        bWindows.addActionListener(this);
        bnimbus.addActionListener(this);
        JSeparator separador = new JSeparator();
        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener( new EscuchaMenu());
        
        JMenuBar menuP = new JMenuBar();
        JMenu menu = new JMenu("Look&Feel");
        menu.add(bMetal);
        menu.add(bMotif);
        menu.add(bWindows);
        menu.add(bnimbus);
        menu.add(separador);
        menu.add(salir);
        salir.setMnemonic('S');
        salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
        
        menuP.add(menu);
        
        JMenu menuM = new JMenu("Mantenimiento");
        JMenuItem borrar=new JMenuItem("Borrar...");
        menuM.add(borrar);
        JMenuItem guardar=new JMenuItem("Guardar...");
        menuM.add(guardar);
        borrar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               peliculas.Principal.borrarPeli();
           }
       });
        
        menuP.add(menuM);
        
        
        this.setJMenuBar(menuP);
        listaLFDisponibles();
        System.out.println(" Time = "+Calendar.getInstance().getTime());
        
       this.addWindowListener(this);
       
       this.setVisible(true);
       
      
   
    }

//----------------------------------------------------
    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
      
        salir();
       
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
       
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

   
    public void windowDeactivated(WindowEvent e) {
       
    }
//-- Implementamos ActionListener..............................................
    
    public void actionPerformed(ActionEvent e) {
        
        cambiaLF(e);
   
    }
//----------------------------------------------------------------
    class EscuchaMenu implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           salir();
           // WindowEvent we = new WindowEvent(ventana,WindowEvent.WINDOW_CLOSING);
           // ventana.dispatchEvent(we);
       
        }
    }   
 //-----------------------------------------------------------       
   // Obtener la lista de  LookAndFeelInfo...
    public void listaLFDisponibles(){
    LookAndFeelInfo[] lista = UIManager.getInstalledLookAndFeels();
	for (int i = 0; i < lista.length; i++) {
	    System.out.println(lista[i].getClassName());
	}
    }
   //--- metodo Salir------------------------------------------------------------------
    public void salir(){
        int res= JOptionPane.showConfirmDialog(null,"Quieres SAlir ??...",
                                                             "Cerrando Aplicaci�n",
                       JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE);
               if(res==JOptionPane.YES_OPTION){
                            JOptionPane.showMessageDialog(null,"Saliendo.....!!!");  
                            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                              System.exit(0);
                          }  
               else  {  JOptionPane.showMessageDialog(null,"Salida abortada...!!!");      
                       this.setVisible(true);
               }
       
    }
    //---- Cambia Look&Feel---------------------------
    public void cambiaLF(ActionEvent e){
      // ActionEvent e=(ActionEvent)evento;
       
       if( e.getSource()== bMotif ){
            tipoVista="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        }else if (e.getSource()== bMetal )
            tipoVista="javax.swing.plaf.metal.MetalLookAndFeel";
        else if (e.getSource()== bWindows )
                tipoVista="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                else
                    tipoVista="javax.swing.plaf.nimbus.NimbusLookAndFeel";
        try {
            UIManager.setLookAndFeel(tipoVista);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println("Error al cambiar de Apariencia :"+ e.toString());
        }
        
        cpane.updateUI();
        
       
        
        
    }
    //-------------------------------------------
    
    
}
