package ligafutbol;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Random;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class Ventana extends javax.swing.JFrame {
    int equipos=0;
    int numeroEnfrentamientos=1;
    int MaxEnfre;
    public Ventana() {
        initComponents();
        cargarEquipos();
        
        botonAñadir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                añadirEquipo();
                cargarEquipos();
            }
        });
        
        botonBorrarEquipo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                borrarEquipo(listaEquipos.getSelectedValue());
                cargarEquipos();
            }
        });
        
        EnfreCont.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                ponerEnfre();
            }
        });
        
        EnfreCont.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ponerEnfre();
            }
        });
        
        generarApuestas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                limpiarArchivo("src/datos/partidos.txt");
                generarPartidos(numeroEnfrentamientos);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bG_AND_OR = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        textoEquipo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        botonAñadir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaEquipos = new javax.swing.JList<>();
        botonBorrarEquipo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        contadorEquipos = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        generarApuestas = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelApuestas = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        numEnfre = new javax.swing.JLabel();
        EnfreCont = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        textoEquipo.setText("NombreEquipo");

        jLabel1.setText("Nombre:");

        botonAñadir.setText("Añadir");

        listaEquipos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listaEquipos);

        botonBorrarEquipo.setText("Borrar");

        jLabel2.setText("Equipos:");

        contadorEquipos.setText("10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonBorrarEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contadorEquipos)
                        .addGap(34, 34, 34))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(botonAñadir)
                    .addComponent(botonBorrarEquipo)
                    .addComponent(jLabel2)
                    .addComponent(contadorEquipos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Equipo", jPanel1);

        generarApuestas.setText("Generar");

        panelApuestas.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        panelApuestas.setLayout(new java.awt.GridLayout(numeroEnfrentamientos, 6));
        jScrollPane2.setViewportView(panelApuestas);

        jLabel3.setText("Numero Enfrentamientos:");

        jLabel4.setText("Maximos Enfrentamientos Posibles: ");

        numEnfre.setForeground(new java.awt.Color(153, 0, 0));
        numEnfre.setText("10");

        EnfreCont.setText("1");

        jLabel5.setText("Local");

        jLabel6.setText("Visitante");

        jLabel7.setText("1");

        jLabel8.setText("X");

        jLabel9.setText("2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(generarApuestas)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(182, 182, 182)
                                .addComponent(jLabel6)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(EnfreCont, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numEnfre)
                                .addGap(77, 77, 77))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(jLabel8)
                                .addGap(99, 99, 99)
                                .addComponent(jLabel9)
                                .addContainerGap())))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generarApuestas)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(numEnfre)
                    .addComponent(EnfreCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Quiniela", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EnfreCont;
    private javax.swing.ButtonGroup bG_AND_OR;
    private javax.swing.JButton botonAñadir;
    private javax.swing.JButton botonBorrarEquipo;
    private javax.swing.JLabel contadorEquipos;
    private javax.swing.JButton generarApuestas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList<String> listaEquipos;
    private javax.swing.JLabel numEnfre;
    private javax.swing.JPanel panelApuestas;
    private javax.swing.JTextField textoEquipo;
    // End of variables declaration//GEN-END:variables

    private void añadirEquipo() {
        try {
            String nombre=textoEquipo.getText();
            Writer output;
            output = new BufferedWriter(new FileWriter("src/datos/equipos.txt", true));
            output.append(nombre+"\n");
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarEquipos() {
        equipos=0;
        try {
            String linea;
            DefaultListModel listaEqui= new DefaultListModel();
            FileReader file = new FileReader("src/datos/equipos.txt");
            BufferedReader bf = new BufferedReader(file);
            while((linea = bf.readLine()) != null){
                listaEqui.addElement(linea);
                equipos++;
            }
            listaEquipos.setModel(listaEqui);
        } catch (FileNotFoundException ex) {
            System.err.println("No se encuentra el fichero.");
        } catch (IOException ex) {
            System.out.println("error-> "+ ex.getMessage());
        }
        contadorEquipos.setText(""+equipos);
        calcularMaximosEnfre();
    }
    
    private void cargarEquipos(File tempFile) {
        PrintWriter pw;
        try {
            String linea;
            FileReader file = new FileReader(tempFile);
            BufferedReader bf = new BufferedReader(file);
            pw = new PrintWriter("src/datos/equipos.txt");
            while((linea = bf.readLine()) != null) {
                pw.println(linea);
            }
            pw.close();
            cargarEquipos();
        } catch (IOException  ex) {
            System.err.println("ERROR ARCHIVO NO ENCONTRADO: "+ex.getMessage());
        }
    }
    
    private void borrarEquipo(String equipo) {
        BufferedReader reader;
        try {
            File inputFile = new File("src/datos/equipos.txt");
            File tempFile = new File("src/datos/equiposTmp.txt");
            reader = new BufferedReader(new FileReader(inputFile));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                String lineToRemove = equipo;
                String currentLine;
                while((currentLine = reader.readLine()) != null) {
                    String trimmedLine = currentLine.trim();
                    
                    if(trimmedLine.equals(lineToRemove)) {
                        continue;
                    }
                    
                    if (!currentLine.isEmpty()) {
                        writer.write(currentLine+"\n");
                    }
                }
            }
            reader.close();
            cargarEquipos(tempFile);
            tempFile.delete();      
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void calcularMaximosEnfre() {
        int PartidosPorEquipo=equipos-1;
        int NumMaxEnfre=PartidosPorEquipo*equipos;
        numEnfre.setText(""+NumMaxEnfre);
        MaxEnfre=NumMaxEnfre;
    }
    
    private void ponerEnfre() {
        int x=Integer.parseInt(EnfreCont.getText());
        if (x<=MaxEnfre) {
            numeroEnfrentamientos=x;
        } else {
            numeroEnfrentamientos=MaxEnfre;
            EnfreCont.setText(""+MaxEnfre);
        } 
    }
    
    private String[] crearPartido() {
        String[] teams=new String[2];
        int valorEntero = aleatorio(listaEquipos.getModel().getSize()-(listaEquipos.getModel().getSize()-1),listaEquipos.getModel().getSize());
        int valorEntero2;
        do {
            valorEntero2 = aleatorio(listaEquipos.getModel().getSize()-(listaEquipos.getModel().getSize()-1),listaEquipos.getModel().getSize());
        } while(valorEntero==valorEntero2);
        teams[0]=listaEquipos.getModel().getElementAt(valorEntero);
        teams[1]=listaEquipos.getModel().getElementAt(valorEntero2);
        return teams;
    }
    
    private static int aleatorio(int min, int max) {
        Random r = new Random();
        int low = min-1;
        int high = max;
        return r.nextInt(high-low) + low;
    }
    
    private void generarPartidos(int Maximo) {
        panelApuestas.removeAll();
        for (int x=0;x!=Maximo;x++) {
            String[] partido=crearPartido();
            do {
                partido=crearPartido();
            } while (CompruebaPartido(partido));
            for (int i=0;i!=3;i++) {
                JLabel cb = null;
                if (i==0) {
                    cb = new JLabel(partido[0]);
                } else if (i==1) {
                    cb = new JLabel("- VS -");
                } else if (i==2) {
                    cb = new JLabel(partido[1]);
                }
                panelApuestas.add(cb);
                panelApuestas.revalidate();
                panelApuestas.repaint();  
            }
            for (int i=0;i!=3;i++) {
                JCheckBox cb = new JCheckBox();
                panelApuestas.add(cb);
                panelApuestas.revalidate();
                panelApuestas.repaint();  
            }  
        }
        panelApuestas.setLayout(new GridLayout(numeroEnfrentamientos,6));
    }
    
    private boolean CompruebaPartido(String[] partido) {
        String partidos=partido[0]+"-"+partido[1];
        try {
            String linea;
            FileReader file = new FileReader("src/datos/partidos.txt");
            BufferedReader bf = new BufferedReader(file);
            while((linea = bf.readLine()) != null){
                if (linea.equals(partidos)) {
                    return true;
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("No se encuentra el fichero.");
        } catch (IOException ex) {
            System.out.println("error-> "+ ex.getMessage());
        }
        try {
            Writer output;
            output = new BufferedWriter(new FileWriter("src/datos/partidos.txt", true));
            output.append(partidos+"\n");
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private void limpiarArchivo(String ruta) {
        try (PrintWriter writer = new PrintWriter(ruta)) {
            writer.print("");
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
