package fivem.connect.launcher;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class Launcher extends javax.swing.JFrame {
    String rutaFivem;
    String buildFivem;
    String ipFivem;
    String[] direccionServer = new String[10];
    public Launcher() {
        initComponents();
        cargarOpciones();
        cargarTablaFichero();
        cargarListaIP();
                        
        listaServers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cambiarIPserver();
            }
        });
        
        botonGuardarBuild.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                guardarBuild();
            }
        });
        
        botonGuardarIP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                guardarIP();
            }
        });
        
        botonBorrarCache1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                borrarCacheServer();
            }
        });
                
        botonRutaFivem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarRutaFivem();
            }
        });
                        
        botonBorrarGameCache.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                borrarAllCache();
            }
        });
                                
        botonBorrarLogs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                borrarLogs();
            }
        });
                                        
        botonBorrarCrashes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                borrarCrashes();
            }
        });
                                                
        botonAbrirAppdata.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirAppData();
            }
        }); 
        
        botonConectar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    ConectarServer();
                    CargarBarra();
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                    Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        checkDevMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                devMode();
            }
        });
          
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bG_AND_OR = new javax.swing.ButtonGroup();
        BusquedaAlumnos = new javax.swing.JTabbedPane();
        panelAlum = new javax.swing.JPanel();
        botonConectar = new javax.swing.JButton();
        panelBA = new javax.swing.JPanel();
        textoBuild = new javax.swing.JTextField();
        botonGuardarBuild = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        botonBorrarGameCache = new javax.swing.JButton();
        botonRutaFivem = new javax.swing.JButton();
        botonBorrarCache1 = new javax.swing.JButton();
        botonBorrarLogs = new javax.swing.JButton();
        botonBorrarCrashes = new javax.swing.JButton();
        botonAbrirAppdata = new javax.swing.JButton();
        textIP = new javax.swing.JTextField();
        botonGuardarIP = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaServers = new javax.swing.JList<>();
        checkDevMode = new javax.swing.JCheckBox();
        progresoFivem = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ren0X FiveM Launcher b1.0");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(loadImageIcon("./cache/logo.png").getImage());

        botonConectar.setText("JUGAR");

        javax.swing.GroupLayout panelAlumLayout = new javax.swing.GroupLayout(panelAlum);
        panelAlum.setLayout(panelAlumLayout);
        panelAlumLayout.setHorizontalGroup(
            panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlumLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(botonConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        panelAlumLayout.setVerticalGroup(
            panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlumLayout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(botonConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
        );

        BusquedaAlumnos.addTab("FiveM", panelAlum);

        textoBuild.setText("2060");

        botonGuardarBuild.setText("GUARDAR BUILD");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));

        botonBorrarGameCache.setText("ALL CACHE");
        botonBorrarGameCache.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarGameCacheActionPerformed(evt);
            }
        });

        botonRutaFivem.setText("RUTA FIVEM");
        botonRutaFivem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRutaFivemActionPerformed(evt);
            }
        });

        botonBorrarCache1.setText("CACHE");
        botonBorrarCache1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarCache1ActionPerformed(evt);
            }
        });

        botonBorrarLogs.setText("LOGS");
        botonBorrarLogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarLogsActionPerformed(evt);
            }
        });

        botonBorrarCrashes.setText("CRASHES");
        botonBorrarCrashes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarCrashesActionPerformed(evt);
            }
        });

        botonAbrirAppdata.setText("APPDATA");
        botonAbrirAppdata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrirAppdataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonRutaFivem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonBorrarGameCache, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonBorrarCache1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonBorrarLogs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonBorrarCrashes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonAbrirAppdata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonBorrarCache1)
                .addGap(18, 18, 18)
                .addComponent(botonRutaFivem)
                .addGap(18, 18, 18)
                .addComponent(botonBorrarGameCache)
                .addGap(18, 18, 18)
                .addComponent(botonBorrarLogs)
                .addGap(18, 18, 18)
                .addComponent(botonBorrarCrashes)
                .addGap(18, 18, 18)
                .addComponent(botonAbrirAppdata)
                .addGap(32, 32, 32))
        );

        textIP.setText("server.play.es");

        botonGuardarIP.setText("GUARDAR IP");

        listaServers.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listaServers);

        checkDevMode.setText("DEV MODE");
        checkDevMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDevModeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBALayout = new javax.swing.GroupLayout(panelBA);
        panelBA.setLayout(panelBALayout);
        panelBALayout.setHorizontalGroup(
            panelBALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBALayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(checkDevMode))
                    .addGroup(panelBALayout.createSequentialGroup()
                        .addGroup(panelBALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(panelBALayout.createSequentialGroup()
                                .addGroup(panelBALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textIP, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                    .addComponent(textoBuild))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelBALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(botonGuardarIP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(botonGuardarBuild, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(6, 6, 6)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBALayout.setVerticalGroup(
            panelBALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkDevMode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBALayout.createSequentialGroup()
                        .addGroup(panelBALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoBuild, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonGuardarBuild))
                        .addGap(18, 18, 18)
                        .addGroup(panelBALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textIP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonGuardarIP))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        BusquedaAlumnos.addTab("Opciones", panelBA);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BusquedaAlumnos)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(progresoFivem, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progresoFivem, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BusquedaAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonBorrarGameCacheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarGameCacheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonBorrarGameCacheActionPerformed

    private void botonRutaFivemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRutaFivemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonRutaFivemActionPerformed

    private void botonBorrarCache1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarCache1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonBorrarCache1ActionPerformed

    private void botonBorrarLogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarLogsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonBorrarLogsActionPerformed

    private void botonBorrarCrashesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarCrashesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonBorrarCrashesActionPerformed

    private void botonAbrirAppdataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrirAppdataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonAbrirAppdataActionPerformed

    private void checkDevModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDevModeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkDevModeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane BusquedaAlumnos;
    private javax.swing.ButtonGroup bG_AND_OR;
    private javax.swing.JButton botonAbrirAppdata;
    private javax.swing.JButton botonBorrarCache1;
    private javax.swing.JButton botonBorrarCrashes;
    private javax.swing.JButton botonBorrarGameCache;
    private javax.swing.JButton botonBorrarLogs;
    private javax.swing.JButton botonConectar;
    private javax.swing.JButton botonGuardarBuild;
    private javax.swing.JButton botonGuardarIP;
    private javax.swing.JButton botonRutaFivem;
    private javax.swing.JCheckBox checkDevMode;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaServers;
    private javax.swing.JPanel panelAlum;
    private javax.swing.JPanel panelBA;
    private javax.swing.JProgressBar progresoFivem;
    private javax.swing.JTextField textIP;
    private javax.swing.JTextField textoBuild;
    // End of variables declaration//GEN-END:variables
    
    private void cargarOpciones() {
        try {
            String linea;
            FileReader file = new FileReader("./cache/ruta.txt");
            BufferedReader bf = new BufferedReader(file);
            while((linea = bf.readLine()) != null){
                rutaFivem=linea;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("No se encuentra el fichero."); 
        } catch (IOException ex) {
            System.err.println("error-> "+ ex.getMessage());
        }
        
        try {
            String linea;
            FileReader file = new FileReader("./cache/build.txt");
            BufferedReader bf = new BufferedReader(file);
            while((linea = bf.readLine()) != null){
                buildFivem=linea;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("No se encuentra el fichero."); 
        } catch (IOException ex) {
            System.err.println("error-> "+ ex.getMessage());
        }  
                
        try {
            String linea;
            FileReader file = new FileReader("./cache/ip.txt");
            BufferedReader bf = new BufferedReader(file);
            while((linea = bf.readLine()) != null){
                ipFivem=linea;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("No se encuentra el fichero."); 
        } catch (IOException ex) {
            System.err.println("error-> "+ ex.getMessage());
        }  
    }
    
    private void guardarBuild() {
        PrintWriter pw;
        try {
            pw = new PrintWriter("./cache/build.txt");
            pw.println(textoBuild.getText());
            pw.close();
        } catch (IOException  ex) {
            System.err.println("ERROR ARCHIVO NO ENCONTRADO: "+ex.getMessage());
        }
        cargarOpciones();
    }
    
    private void guardarIP() {
        PrintWriter pw;
        try {
            pw = new PrintWriter("./cache/ip.txt");
            pw.println(textIP.getText());
            pw.close();
        } catch (IOException ex) {
            System.err.println("ERROR ARCHIVO NO ENCONTRADO: "+ex.getMessage());
        }
        cargarOpciones();
    }
        
    private void cargarRutaFivem() {
        JFileChooser selector = new JFileChooser("C:/");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(".exe", "exe");
        selector.setFileFilter(filtro);
        int estado = selector.showOpenDialog(null);
        File archivoelegido = selector.getSelectedFile();
        if (estado == JFileChooser.APPROVE_OPTION); {
            PrintWriter pw;
            try {
                pw = new PrintWriter("./cache/ruta.txt");
                String cadena=archivoelegido.getAbsolutePath();
                String[] cadenaCortada=cadena.split("FiveM.exe");
                pw.println(cadenaCortada[0]);
                pw.close();
            } catch (FileNotFoundException ex) {
                System.err.println("ERROR ARCHIVO NO ENCONTRADO: "+ex.getMessage());
            }
        }
        cargarOpciones();
    }
    
    private void borrarCacheServer() {
        String filePath = rutaFivem+"FiveM.app/data/server-cache/";
        File file = new File(filePath);
        deleteFolder(file);
        
        filePath = rutaFivem+"FiveM.app/data/server-cache-priv/";
        file = new File(filePath);
        deleteFolder(file);
    }
    
    private void borrarAllCache() {
        String filePath = rutaFivem+"FiveM.app/data/cache/";
        File file = new File(filePath);
        deleteFolder(file);
    }
    
    private void borrarLogs() {
        String filePath = rutaFivem+"FiveM.app/logs/";
        File file = new File(filePath);
        deleteFolder(file);
    }
    
    private void borrarCrashes() {
        String filePath = rutaFivem+"FiveM.app/crashes/";
        File file = new File(filePath);
        deleteFolder(file);
    }
    
    private void abrirAppData() {
        try {
            String user=System.getProperty("user.name");
            Desktop.getDesktop().open(new File("C:/Users/"+user+"/AppData/Roaming/CitizenFX"));
        } catch (IOException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void ConectarServer() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Ejecutar(rutaFivem+"/"+"FiveM.exe", "fivem://connect/"+ipFivem);
        if (listaServers.getSelectedIndex()==0) {
            playSound("oasis.wav");
        } else if (listaServers.getSelectedIndex()==1) {
            playSound("overlife.wav");
        }
    }
    
    private static void Ejecutar(String proceso, String parametro) throws IOException {
        ProcessBuilder p = new ProcessBuilder();
        p.command(proceso, parametro);
        p.start();
    }
    
    private static void deleteFolder(File file){
        for (File subFile : file.listFiles()) {
            if(subFile.isDirectory()) {
                deleteFolder(subFile);
            } else {
                subFile.delete();
            }
        }
        file.delete();
    }
    
    private void cargarTablaFichero() {
        try {
            String linea;
            DefaultListModel listaServer= new DefaultListModel();
            FileReader file = new FileReader("./cache/servidores.txt");
            BufferedReader bf = new BufferedReader(file);
            while((linea = bf.readLine()) != null){
                listaServer.addElement(linea);
            }
            listaServers.setModel(listaServer);
        } catch (FileNotFoundException ex) {
            System.err.println("No se encuentra el fichero.");
        } catch (IOException ex) {
            System.out.println("error-> "+ ex.getMessage());
        }  
    }
    
    private void cargarListaIP() {
        try {
            String linea;
            FileReader file = new FileReader("./cache/direccionesip.txt");
            BufferedReader bf = new BufferedReader(file);
            int i=0;
            while((linea = bf.readLine()) != null){
                direccionServer[i]=linea;
                i++;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("No se encuentra el fichero.");
        } catch (IOException ex) {
            System.out.println("error-> "+ ex.getMessage());
        }  
    }
    
    private void cambiarIPserver() {
        progresoFivem.setValue(0);
        int index=listaServers.getSelectedIndex();
        textIP.setText(direccionServer[index]);
    }
    
    private void devMode() {
        if (checkDevMode.isSelected()) {
            textIP.setText("localhost");
            textoBuild.setText("2189");
            cargarOpciones();
        }
    }
    
    private void CargarBarra() {
        for (int i=0;i!=progresoFivem.getMaximum();i++) {
            progresoFivem.setValue(i);
        }
    }
    
    private static ImageIcon loadImageIcon(String path) {
        File direccion = new File(path).getAbsoluteFile();
        return new ImageIcon(direccion.toString());
    }
    
    private void playSound(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioPlayer audioPlayer = new AudioPlayer(file);
    }
    
    private static class AudioPlayer {
        public AudioPlayer(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
            String audio = "./cache/audios/"+file;
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audio).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
    }
}
