/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw_componentes_21;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.UIManager;


/**
 *
 * @author Usuario
 */
public class MiVentana21 extends JFrame implements ItemListener {

    public MiVentana21(String title) throws HeadlessException {
        super(title);
        this.setJMenuBar(creaMenu());
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salir();
            }
            
        });
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    
    public MiVentana21(String title,int ancho, int alto){
        this(title);
        Dimension t_pant = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension t_vent = new Dimension(ancho,alto);
        
        int x = (t_pant.width/2) - (t_vent.width/2); 
        int y = (t_pant.height/2) - (t_vent.height/2);
        
       // setBounds(x, y, ancho, alto);
        setPreferredSize(t_vent);
        setMinimumSize(t_vent);
        
        setLocationRelativeTo(null);
        
    }
    //---------------------------
     JMenuBar creaMenu(){
        
        ButtonGroup aspectos =new  ButtonGroup();
        
        JMenuBar menuPrincipal= new JMenuBar();
        JMenu look_feel = new JMenu("Look&feel");
        JMenu archivo   = new JMenu("Archivo...");
         menuPrincipal.add(archivo); menuPrincipal.add(look_feel);
        
        JMenuItem nuevo = new JMenuItem("Nuevo...");
         JMenuItem abrir = new JMenuItem("Abrir...");
         JMenuItem guardar = new JMenuItem("Guardar...");
         
         archivo.add(nuevo); archivo.add(abrir); archivo.add(guardar);
         archivo.add(new JSeparator());
         
         JCheckBoxMenuItem flotar= new JCheckBoxMenuItem("Flotar BH");
         archivo.add(flotar);
         archivo.add(new JSeparator());
        
         JMenuItem salir = new JMenuItem("Salir");
          archivo.add(salir);
          
          salir.setMnemonic(KeyEvent.VK_S);
          salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
          
          salir.addActionListener((e) -> salir());
          /*
          salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saliendo sin funcion Arrow...");
                salir();
            }
            });***/
       // salir.addActionListener(this);
          
         JRadioButtonMenuItem metal,motif,windows,nimbus; 
         metal = new JRadioButtonMenuItem("Metal (java)");
         motif = new JRadioButtonMenuItem("Motif (Unix)");
         windows = new JRadioButtonMenuItem("windows");
         nimbus = new JRadioButtonMenuItem("Nimbus");
         
         metal.addItemListener(this);
          motif.addItemListener(this);
           windows.addItemListener(this);
            nimbus.addItemListener(this);
         
         look_feel.add(metal);look_feel.add(motif);
         look_feel.add(windows);look_feel.add(nimbus);
         
         aspectos.add(metal); aspectos.add(motif);
          aspectos.add(windows); aspectos.add(nimbus);
        
        return menuPrincipal;
    }
    //---------------------------------------
     void salir(){
     int respuesta=JOptionPane.showConfirmDialog(rootPane, "Quieres Salir...?", 
                                      "Cerrando Ventana...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
     if(respuesta ==JOptionPane.YES_OPTION){
         JOptionPane.showMessageDialog(rootPane,"Hasta Luego");
         System.exit(0);
     }   
     else{
     JOptionPane.showMessageDialog(rootPane,"Salida Abortada");
     }
     
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
       /* System.out.println("Se disparo un -> "+ e.getClass().getSimpleName()+
            "en el objeto  -> "+e.getSource().getClass().getSimpleName()+"\n"+
                ((JRadioButtonMenuItem)e.getSource()).getActionCommand());*/
       if(e.getStateChange()==ItemEvent.SELECTED)
            cambiarApariencia(e,this);
    }
    
    //----------------------------------
    static void cambiarApariencia(ItemEvent e,MiVentana21 ventana){
        String piel=((JRadioButtonMenuItem)e.getSource()).getText();
        String apariencia="";
        
        switch (piel){
            case "Metal (java)":
                apariencia="javax.swing.plaf.metal.MetalLookAndFeel";
                break;
            case "Motif (Unix)":
                apariencia ="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
                break;
            case "windows":
                apariencia ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                break;
            case "Mac":
                apariencia ="javax.swing.plaf.mac.MacLookAndFeel";
                break; 
            case "Nimbus":
                apariencia ="javax.swing.plaf.nimbus.NimbusLookAndFeel";
                break;    
        }
        try {
            UIManager.setLookAndFeel(apariencia);
            
        } catch (Exception ex) {
            System.out.println("Error Campiando la piel... "+ ex.getMessage());
        }
      
        ventana.getContentPane().repaint();
        JOptionPane.showMessageDialog(ventana, "Look&Field -> "+piel);
    
    }

    
}
