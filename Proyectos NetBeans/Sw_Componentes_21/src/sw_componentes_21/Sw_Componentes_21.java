package sw_componentes_21;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Sw_Componentes_21 extends JFrame{

    //JTabbedPane pestañas;
    public Sw_Componentes_21(String title) throws HeadlessException {
        super(title);
        this.setJMenuBar(creaMenu());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane pestañas= new JTabbedPane();
        
        // Panel de los RadioButtons.............................
        pestañas.addTab("RadioButtons", creaPanelRadios());
                
        // Panel de los Botones.............................
        pestañas.addTab("Botones",new ImageIcon("src/images/code.gif") ,creaPanelBotones());
        
          // Panel de los Combos.............................
        pestañas.addTab("Listas...",new ImageIcon("src/images/fix.gif") ,creaPanelListas());
        
        // Panel de los Combos.............................
        pestañas.addTab("Etiqueta Imegen y Texto...",new ImageIcon("src/images/fix.gif") ,creaPanelEtiqueta());
        
        
        add(pestañas,BorderLayout.CENTER);
        add(creaBarraH(),BorderLayout.NORTH);
        setVisible(true);
        setBounds(200, 100, 600, 500);
        
    }

    
    
    public static void main(String[] args) {
        new Sw_Componentes_21("probando componentes....");
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
            norte.setBorder(BorderFactory.createLineBorder(Color.red));
            
            for(int i=1;i<4;i++)
                norte.add(new JButton("botón "+i));
            
            botones.add(norte,BorderLayout.NORTH);
            
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
                        
            JPanel este = new JPanel();
            este.setLayout(new BoxLayout(este, BoxLayout.Y_AXIS));
            este.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            botones.add(este,BorderLayout.EAST);
            
            este.add(Box.createVerticalGlue());
            for(int i=1;i<4;i++)
                este.add(new JCheckBox("botón "+i,true));
            
            este.add(Box.createVerticalGlue());
            
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
            
            oeste.add(slidetAlto);
            
            JPanel centro = new JPanel();
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
    static JMenuBar creaMenu(){
        
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
          
         JRadioButtonMenuItem metal,motif,windows,nimbus; 
         metal = new JRadioButtonMenuItem("Metal (java)");
         motif = new JRadioButtonMenuItem("Motif (Unix)");
         windows = new JRadioButtonMenuItem("windows");
         nimbus = new JRadioButtonMenuItem("Nimbus");
         
         look_feel.add(metal);look_feel.add(motif);
         look_feel.add(windows);look_feel.add(nimbus);
         
         aspectos.add(metal); aspectos.add(motif);
          aspectos.add(windows); aspectos.add(nimbus);
        
        return menuPrincipal;
    }
    
    static JToolBar creaBarraH(){
        JToolBar Bh = new JToolBar(JToolBar.HORIZONTAL);
        
        JButton BNuevo=new JButton("A",new ImageIcon("src/images/salvar.gif"));
        JButton BAbrir=new JButton("B");
        JButton BCopiar=new JButton("C");
        
        Bh.add(BNuevo); Bh.add(BAbrir); Bh.add(BCopiar);
        
        return Bh;
    }
}
