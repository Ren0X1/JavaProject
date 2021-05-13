/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw_componentes_21;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.*;


/**
 *
 * @author Usuario
 */
public class Sw_Componentes_21 extends MiVentana21{

    static JTabbedPane pestañas;
    
    public Sw_Componentes_21(String title) throws HeadlessException {
        super(title);
        contenidoVentana(this);
    }
    
    public Sw_Componentes_21(String title,int ancho, int alto) throws HeadlessException {
        super(title,ancho,alto);
        contenidoVentana(this);
    }
    
    static void contenidoVentana(MiVentana21 ventana){   
        
        pestañas= new JTabbedPane();
        
        // Panel de los RadioButtons.............................
        pestañas.addTab("RadioButtons", creaPanelRadios());
                
        // Panel de los Botones.............................
        pestañas.addTab("Botones",new ImageIcon("src/images/code.gif") ,creaPanelBotones());
        
          // Panel de los Combos.............................
        pestañas.addTab("Listas...",new ImageIcon("src/images/fix.gif") ,creaPanelListas());
        
        // Panel de los Combos.............................
        pestañas.addTab("Etiqueta Imegen y Texto...",new ImageIcon("src/images/fix.gif") ,creaPanelEtiqueta());
        
        
        ventana.add(pestañas,BorderLayout.CENTER);
        ventana.add(creaBarraH(),BorderLayout.NORTH);
        
     }
 
    public static void main(String[] args) {
        new Sw_Componentes_21("probando componentes....");
        new Sw_Componentes_21("probando Mi VEntana....",400,300);
    }
    
    static JPanel creaPanelRadios(){
        JPanel radios=  new JPanel();
        radios.add(new JLabel("Panels de RadioButtons..."));
        //pestañas.addTab("RadioButons", radios);
        return radios;
    }
    
    static JPanel creaPanelBotones(){
        JPanel botones=  new JPanel();
        //botones.add(new JLabel("Panels de Botones..."));
        botones.setLayout(new BorderLayout());
            //Paneles intermedios......
            JPanel norte = new JPanel();
            JPanel centro = new JPanel();
            
            norte.setBorder(BorderFactory.createLineBorder(Color.red));
            JButton boton1,boton2,boton3;
            
            boton1=new JButton("botón 1");
            boton2=new JButton("botón 2");
            boton3=new JButton("botón 3");
            norte.add(boton1);norte.add(boton2);norte.add(boton3);
            
            botones.add(norte,BorderLayout.NORTH);
              //----Panel SUR ----------------------------------------
              //------------------------------------------------------
            JPanel sur = new JPanel();
            sur.setBorder(BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.BLUE),"Diseño Botón"));
            botones.add(sur,BorderLayout.SOUTH);
            JButton icono, colorFondo, colorTexto;
            JTextField fichIcono;
            sur.add(icono = new JButton("icono..."));
            sur.add(fichIcono= new JTextField("     "));
            sur.add(colorFondo = new JButton("colorFondo..."));
            sur.add(colorTexto = new JButton("colorTexto..."));
            
            JFileChooser ficheroIcono= new JFileChooser(new File("src/images"));
            
            icono.addActionListener((e) -> {
                 int returnVal = ficheroIcono.showOpenDialog(null);
                 
                 if(returnVal == JFileChooser.APPROVE_OPTION){
                    File fich= ficheroIcono.getSelectedFile();
                    fichIcono.setText(fich.getName());
                     System.out.println("file="+fich.getAbsolutePath());
                 }
                 
            });
            
          //----Panel Este----------------------------------------              
            JPanel este = new JPanel();
            
            JCheckBox check1,check2,check3;
            
            este.setLayout(new BoxLayout(este, BoxLayout.Y_AXIS));
            este.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            botones.add(este,BorderLayout.EAST);
            
            este.add(Box.createVerticalGlue());
            check1 =new JCheckBox("botón 1");
            check2 =new JCheckBox("botón 2");
            check3 =new JCheckBox("botón 3");
            
           /*check1.addItemListener(new EventoCheck());
            check2.addItemListener(new EventoCheck());
            check3.addItemListener(new EventoCheck());*/
            check1.addItemListener((e) -> {
                if(e.getStateChange()==ItemEvent.SELECTED )
                       centro.add(boton1);
                   else   
                       norte.add(boton1);
                centro.repaint();norte.repaint();
                centro.validate();norte.validate();
            });
            
            este.add(check1);este.add(check2);este.add(check3);
            
            este.add(Box.createVerticalGlue());
           //----Panel Este---------------------------------------- 
            JPanel oeste = new JPanel();
             oeste.setBorder(BorderFactory.createLineBorder(Color.PINK));
            botones.add(oeste,BorderLayout.WEST);
            
            oeste.setLayout(new BoxLayout(oeste,BoxLayout.X_AXIS));
            
            JPanel slidetAncho= new JPanel();
            slidetAncho.setLayout(new BoxLayout(slidetAncho,BoxLayout.Y_AXIS));
            slidetAncho.add(new JLabel("Ancho"));
            JSlider slaiderIz= new JSlider(JSlider.VERTICAL,0,400,200);
            
            slaiderIz.setMajorTickSpacing(50);
            slaiderIz.setMinorTickSpacing(25);
            slaiderIz.setPaintLabels(true);
            slaiderIz.setPaintTicks(true);
            
            slidetAncho.add(slaiderIz);
            
            JTextField textoAncho= new JTextField("Valor:    ");
            textoAncho.setMaximumSize(textoAncho.getPreferredSize());
            slidetAncho.add(textoAncho);
            
            slaiderIz.addChangeListener((e) -> {
                boton1.setSize(slaiderIz.getValue(), boton1.getHeight());
                textoAncho.setText("-- "+slaiderIz.getValue()+" --");
            });
            
            
            oeste.add(slidetAncho);
            
            JPanel slidetAlto= new JPanel();
            slidetAlto.setLayout(new BoxLayout(slidetAlto,BoxLayout.Y_AXIS));
            
            slidetAlto.add(new JLabel("Alto"));
            JSlider slaiderD= new JSlider(JSlider.VERTICAL,0,400,200);
            
            slaiderD.setMajorTickSpacing(50);
            slaiderD.setMinorTickSpacing(25);
            slaiderD.setPaintLabels(true);
            slaiderD.setPaintTicks(true);
            
            slidetAlto.add(slaiderD);
            
            JTextField textoAlto= new JTextField("Valor:    ");
            textoAlto.setMaximumSize(textoAncho.getPreferredSize());
            slidetAlto.add(textoAlto);
            
             slaiderD.addChangeListener((e) -> {
                boton1.setSize( boton1.getWidth(),slaiderD.getValue());
                textoAlto.setText("-- "+slaiderD.getValue()+" --");
            });
            
            oeste.add(slidetAlto);
            
           
             centro.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            botones.add(centro,BorderLayout.CENTER);
            return botones;
    } 
    
    static JPanel creaPanelListas(){
        JPanel combos= new JPanel();
        
        String[] contenidos ={"Manzana","Naranja","Platano","Tiburon","Tomate","Trucha"};
        
        combos.add(new JLabel("JComboBox-> "));
        JComboBox jComboBox1 = new JComboBox(contenidos);
        combos.add(jComboBox1);
        
        combos.add(new JLabel("JList-> "));
        JList jList1 = new JList(contenidos);
        combos.add(jList1);
        
        JTextField estoEs= new JTextField("esto es ");
        combos.add(estoEs);
        JLabel fruta = new JLabel(new ImageIcon("src/images/Fruta.jpg"));
        combos.add(fruta);
        return combos;
    }   
    
    static JPanel creaPanelEtiqueta(){
        JPanel pEtiqueta = new JPanel();
        
        JLabel etiqueta = new JLabel("etiqueta con texto e imagen",
                                       new ImageIcon("src/images/Fruta.jpg"),
                                       JLabel.LEFT);
        pEtiqueta.add(etiqueta);
        
        
        JPanel botones = new JPanel();
        
        botones.setBorder(BorderFactory.createLineBorder(Color.yellow));
        botones.setBackground(new Color(100,100,100));
        JRadioButton izda = new JRadioButton("Izquierda");
        JRadioButton centro = new JRadioButton("centro");
        JRadioButton derecha = new JRadioButton("derecha");
        
        ButtonGroup bg= new ButtonGroup();
        bg.add(izda);bg.add(centro);bg.add(derecha);
                
        botones.add(izda);botones.add(centro);botones.add(derecha);
        
        pEtiqueta.add(botones);
        
        return pEtiqueta; 
    }
   
    
    static JToolBar creaBarraH(){
        JToolBar Bh = new JToolBar(JToolBar.HORIZONTAL);
        
        JButton BNuevo=new JButton("A",new ImageIcon("src/images/salvar.gif"));
        JButton BAbrir=new JButton("B");
        JButton BCopiar=new JButton("C");
        
        Bh.add(BNuevo); Bh.add(BAbrir); Bh.add(BCopiar);
        
        return Bh;
    }

    private static class EventoCheck implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
           String texto=((JCheckBox)e.getSource()).getText();
           
           switch(texto){
               
               case "botón 1":
                   //JOptionPane.showMessageDialog(null, "check en boton 1");
                   System.out.println("check en boton 1");
                   if(e.getStateChange()==ItemEvent.SELECTED )
                       centro.add(boton1);
                   else   
                       norte.add(boton1);
                   break;
               case "botón 2":
                  // JOptionPane.showMessageDialog(null, "check en boton 2");
                    System.out.println("check en boton 2");
                   break;
               case "botón 3":
                  // JOptionPane.showMessageDialog(null, "check en boton 3");
                    System.out.println("check en boton 3");
                   break;    
               
           }
           
           
        }

       
    }
}
