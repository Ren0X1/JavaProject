package instituto_alejandromendoza;

import BD.Conectate;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class Insti extends javax.swing.JFrame {
    Conectate conecta;
    String grupoSeleccionado;
    public Insti() {
        conecta = new Conectate(null, true);
        initComponents();
        comboTablaGrupos = new JComboBox<>();
        cargaGrupos(comboGrupo, "select GRUPO from grupos", conecta.connBd.getConn());
        tableAlumnos = cargaTabla(scrollTabla, "select ALUMNO, GRUPO from alumnos", conecta.connBd.getConn());
        jTable_BA = cargaTabla(scrollTablaBA, "select * from alumnos", conecta.connBd.getConn());
        tableGrupos = cargaTabla(scrollTabla1, "select * from grupos", conecta.connBd.getConn());
        ponerNum();      
        
        tableAlumnos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ficha();
                cargarAsig();
            }
        });
        
        tableAlumnos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                ficha();
                cargarAsig();
            }
        });
        
        tableGrupos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                grupoSeleccionado=guardaLista();
                cargaAlumnos();
                ponerNum();
            }
        });
        
        tableGrupos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                grupoSeleccionado=guardaLista();
                cargaAlumnos();
                ponerNum();
            }

        });
        
        botonGuardarLista.addActionListener((ActionEvent e) -> {
            if (grupoSeleccionado == null) {
                JOptionPane.showMessageDialog(rootPane, "Ningun curso seleccionado");
            } else {
                listaAlumFichero(grupoSeleccionado);
                JOptionPane.showMessageDialog(rootPane, "Listado del curso guardado correctamente");
            }
        });
        
        cargaProv(comboProvincia, "provincias", conecta.connBd.getConn());
        comboProvincia.addItemListener((ItemEvent e) -> {
            String provincia = comboProvincia.getSelectedItem().toString();
            cargaPoblacion(comboLocalidad, provincia, conecta.connBd.getConn());
        });

        comboLocalidad.addItemListener((ItemEvent e) -> {
            try {
                textCP.setText("");
                Statement st = conecta.connBd.getConn().createStatement();
                ResultSet rs;
                String localidad = comboLocalidad.getSelectedItem().toString();
                rs = st.executeQuery("select postal from poblaciones where poblacion ='" + localidad + "'");
                while (rs.next()) {
                    textCP.setText(rs.getString("postal")); //CARGAMOS UN STRING
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error al crear Sentencia " + ex.getMessage());
            }
        });
        
        cargaGrupos(comboGrupo, "select grupo from grupos", conecta.connBd.getConn());
        jRB_Or.setActionCommand("OR");jRB_And.setActionCommand("AND");
        bG_AND_OR.add(jRB_Or);bG_AND_OR.add(jRB_And);
        
        texto_col1.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e); 
                RowFilter filtro;
                String filtro1 ="^"+texto_col1.getText();
                int  col1 = jCombo_Col1.getSelectedIndex();
                RowFilter filtraCombo1 = RowFilter.regexFilter(filtro1, col1);
                String filtro2 ="^"+texto_col2.getText();
                int  col2 = jCombo_Col2.getSelectedIndex();
                RowFilter filtraCombo2 = RowFilter.regexFilter(filtro2, col2);
                LinkedList<RowFilter> lista = new LinkedList<>();
                lista.add(filtraCombo1);
                lista.add(filtraCombo2);
                String opera= bG_AND_OR.getSelection().getActionCommand();
                if (opera.equalsIgnoreCase("AND")) {
                     filtro = RowFilter.andFilter((Iterable)lista);
                } else {
                     filtro = RowFilter.orFilter((Iterable)lista);
                }
                TableRowSorter ordena=(TableRowSorter) jTable_BA.getRowSorter();
                ordena.setRowFilter(filtro);
            }    
        });

        jCombo_Col1.addActionListener((ActionEvent e) -> {
           KeyEvent ke = new KeyEvent( texto_col1, KeyEvent.KEY_RELEASED, 1,1,1,'a');
           texto_col1.dispatchEvent(ke);
        });
       
        jCombo_Col2.addActionListener((ActionEvent e) -> {
            KeyEvent ke = new KeyEvent( texto_col1, KeyEvent.KEY_RELEASED, 1,1,1,'a');
            texto_col1.dispatchEvent(ke);
        });
       
        texto_col2.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                KeyEvent ke = new KeyEvent( texto_col1, KeyEvent.KEY_RELEASED, 1,1,1,'a');
                texto_col1.dispatchEvent(ke);
            }
        });

        jRB_Or.addChangeListener((ChangeEvent e) -> {
            KeyEvent ke = new KeyEvent( texto_col1, KeyEvent.KEY_RELEASED, 1,1,1,'a');
            texto_col1.dispatchEvent(ke);
        });
        
        jRB_And.addChangeListener((ChangeEvent e) -> {
            KeyEvent ke = new KeyEvent( texto_col1, KeyEvent.KEY_RELEASED, 1,1,1,'a');
            texto_col1.dispatchEvent(ke);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bG_AND_OR = new javax.swing.ButtonGroup();
        BusquedaAlumnos = new javax.swing.JTabbedPane();
        panelAlum = new javax.swing.JPanel();
        labelGrupos = new javax.swing.JLabel();
        tabbedPaneLista = new javax.swing.JTabbedPane();
        panelFicha = new javax.swing.JPanel();
        panelDatos = new javax.swing.JPanel();
        textNomb = new javax.swing.JTextField();
        labelNom = new javax.swing.JLabel();
        textNif = new javax.swing.JTextField();
        labelNif = new javax.swing.JLabel();
        labelFoto = new javax.swing.JLabel();
        foto = new javax.swing.JLabel();
        textGrup = new javax.swing.JTextField();
        labelNif1 = new javax.swing.JLabel();
        panelProcedencia = new javax.swing.JPanel();
        textLoc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textCodigoPostal = new javax.swing.JTextField();
        labelCodPostal = new javax.swing.JLabel();
        scrollTabla2 = new javax.swing.JScrollPane();
        tableAlumnos1 = new javax.swing.JTable();
        panelNuevoAlum = new javax.swing.JPanel();
        botonReiniciar = new javax.swing.JButton();
        botonCargar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        labelNombre = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textApellidos = new javax.swing.JTextField();
        labelNie = new javax.swing.JLabel();
        textNie = new javax.swing.JTextField();
        comboGrupo = new javax.swing.JComboBox<>();
        labelGrupo = new javax.swing.JLabel();
        botonCargarFoto = new javax.swing.JButton();
        textFoto = new javax.swing.JTextField();
        labelFoto1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelLocalidad = new javax.swing.JLabel();
        comboLocalidad = new javax.swing.JComboBox<>();
        labelProv = new javax.swing.JLabel();
        comboProvincia = new javax.swing.JComboBox<>();
        labelCP = new javax.swing.JLabel();
        textCP = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        comboGrupo1 = new javax.swing.JComboBox<>();
        labelGrupo1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaActoresDisp = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        reparto = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        panelLista = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listLista = new javax.swing.JList<>();
        botonExaminar = new javax.swing.JButton();
        botonGuardarLista = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tableAlumnos = new javax.swing.JTable();
        scrollTabla1 = new javax.swing.JScrollPane();
        tableGrupos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelBA = new javax.swing.JPanel();
        scrollTablaBA = new javax.swing.JScrollPane();
        jTable_BA = new javax.swing.JTable();
        jCombo_Col1 = new javax.swing.JComboBox<>();
        jCombo_Col2 = new javax.swing.JComboBox<>();
        texto_col1 = new javax.swing.JTextField();
        texto_col2 = new javax.swing.JTextField();
        jRB_And = new javax.swing.JRadioButton();
        jRB_Or = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelGrupos.setText("GRUPOS");

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelDatos.setToolTipText("");

        textNomb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelNom.setText("Nombre");

        textNif.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textNif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNifActionPerformed(evt);
            }
        });

        labelNif.setText("NIE");

        labelFoto.setText("Foto");
        labelFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 204)));

        foto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        foto.setForeground(new java.awt.Color(0, 153, 153));
        foto.setText("FOTO");

        textGrup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelNif1.setText("GRUPO:");

        panelProcedencia.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Procedencia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        textLoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setText("Localidad");

        textCodigoPostal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelCodPostal.setText("CP");

        javax.swing.GroupLayout panelProcedenciaLayout = new javax.swing.GroupLayout(panelProcedencia);
        panelProcedencia.setLayout(panelProcedenciaLayout);
        panelProcedenciaLayout.setHorizontalGroup(
            panelProcedenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProcedenciaLayout.createSequentialGroup()
                .addGroup(panelProcedenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(labelCodPostal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelProcedenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 34, Short.MAX_VALUE))
        );
        panelProcedenciaLayout.setVerticalGroup(
            panelProcedenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProcedenciaLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(panelProcedenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelProcedenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCodPostal))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelNom)
                    .addComponent(labelNif))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addComponent(textNif, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelNif1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textGrup))
                    .addComponent(textNomb, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(foto)
                .addGap(201, 201, 201))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(panelProcedencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(foto)
                    .addComponent(textNomb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textNif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNif)
                            .addComponent(labelNif1)
                            .addComponent(textGrup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelProcedencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollTabla2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrollTabla2MouseClicked(evt);
            }
        });

        tableAlumnos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nombre"
            }
        ));
        tableAlumnos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAlumnos1MouseClicked(evt);
            }
        });
        scrollTabla2.setViewportView(tableAlumnos1);

        javax.swing.GroupLayout panelFichaLayout = new javax.swing.GroupLayout(panelFicha);
        panelFicha.setLayout(panelFichaLayout);
        panelFichaLayout.setHorizontalGroup(
            panelFichaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFichaLayout.createSequentialGroup()
                .addGroup(panelFichaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollTabla2))
                .addGap(0, 29, Short.MAX_VALUE))
        );
        panelFichaLayout.setVerticalGroup(
            panelFichaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFichaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollTabla2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPaneLista.addTab("Ficha", panelFicha);

        botonReiniciar.setText("Limpiar");
        botonReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReiniciarActionPerformed(evt);
            }
        });

        botonCargar.setText("Guardar");
        botonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargarActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos Alumno", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        labelNombre.setText("Nombre: ");

        textNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNombreActionPerformed(evt);
            }
        });

        jLabel3.setText("Apellidos: ");

        labelNie.setText("Nie: ");

        comboGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboGrupoActionPerformed(evt);
            }
        });

        labelGrupo.setText("Grupo");

        botonCargarFoto.setText("Cargar Foto");
        botonCargarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargarFotoActionPerformed(evt);
            }
        });

        labelFoto1.setText("Foto");
        labelFoto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 204)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(labelNie)
                            .addComponent(labelGrupo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textNie, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(textFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonCargarFoto)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(labelFoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNombre)
                            .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(textApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textNie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNie))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelGrupo)
                            .addComponent(comboGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonCargarFoto))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(labelFoto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Procedencia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        labelLocalidad.setText("Localidad: ");

        labelProv.setText("Provincia: ");

        labelCP.setText("CP: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelProv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelLocalidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(labelCP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textCP, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelProv)
                    .addComponent(comboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLocalidad)
                    .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCP))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Matricula", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        comboGrupo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboGrupo1ActionPerformed(evt);
            }
        });

        labelGrupo1.setText("Grupo");

        listaActoresDisp.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listaActoresDisp);

        jScrollPane4.setViewportView(reparto);

        jButton1.setText("->");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("<-");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(comboGrupo1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(labelGrupo1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labelGrupo1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboGrupo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout panelNuevoAlumLayout = new javax.swing.GroupLayout(panelNuevoAlum);
        panelNuevoAlum.setLayout(panelNuevoAlumLayout);
        panelNuevoAlumLayout.setHorizontalGroup(
            panelNuevoAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoAlumLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelNuevoAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelNuevoAlumLayout.createSequentialGroup()
                        .addComponent(botonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panelNuevoAlumLayout.setVerticalGroup(
            panelNuevoAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoAlumLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNuevoAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCargar)
                    .addComponent(botonReiniciar))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        tabbedPaneLista.addTab("Nuevo Alumno", panelNuevoAlum);

        listLista.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listLista);

        botonExaminar.setText("Examinar");
        botonExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonExaminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelListaLayout = new javax.swing.GroupLayout(panelLista);
        panelLista.setLayout(panelListaLayout);
        panelListaLayout.setHorizontalGroup(
            panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(botonExaminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelListaLayout.setVerticalGroup(
            panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaLayout.createSequentialGroup()
                .addGroup(panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelListaLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelListaLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(botonExaminar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPaneLista.addTab("Listas", panelLista);

        botonGuardarLista.setText("Guardar Lista");

        scrollTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrollTablaMouseClicked(evt);
            }
        });

        tableAlumnos.setAutoCreateRowSorter(true);
        tableAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nombre"
            }
        ));
        tableAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAlumnosMouseClicked(evt);
            }
        });
        scrollTabla.setViewportView(tableAlumnos);

        scrollTabla1.setBackground(new java.awt.Color(204, 204, 204));
        scrollTabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrollTabla1MouseClicked(evt);
            }
        });

        tableGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nombre"
            }
        ));
        tableGrupos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGruposMouseClicked(evt);
            }
        });
        scrollTabla1.setViewportView(tableGrupos);

        jLabel1.setText("Nº de Alumnos: ");

        jLabel4.setText("TEST");
        jLabel4.setAutoscrolls(true);

        javax.swing.GroupLayout panelAlumLayout = new javax.swing.GroupLayout(panelAlum);
        panelAlum.setLayout(panelAlumLayout);
        panelAlumLayout.setHorizontalGroup(
            panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlumLayout.createSequentialGroup()
                .addGroup(panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAlumLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelGrupos)
                            .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                            .addComponent(scrollTabla1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(panelAlumLayout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPaneLista, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonGuardarLista))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        panelAlumLayout.setVerticalGroup(
            panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlumLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelGrupos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAlumLayout.createSequentialGroup()
                        .addComponent(scrollTabla1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)))
                    .addGroup(panelAlumLayout.createSequentialGroup()
                        .addComponent(botonGuardarLista)
                        .addGap(18, 18, 18)
                        .addComponent(tabbedPaneLista, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        BusquedaAlumnos.addTab("Alumnos", panelAlum);

        jTable_BA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollTablaBA.setViewportView(jTable_BA);

        jCombo_Col1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCombo_Col2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        texto_col1.setToolTipText("");

        texto_col2.setToolTipText("");

        jRB_And.setSelected(true);
        jRB_And.setText("AND");
        jRB_And.setToolTipText("");

        jRB_Or.setText("OR");
        jRB_Or.setToolTipText("");

        javax.swing.GroupLayout panelBALayout = new javax.swing.GroupLayout(panelBA);
        panelBA.setLayout(panelBALayout);
        panelBALayout.setHorizontalGroup(
            panelBALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTablaBA, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBALayout.createSequentialGroup()
                        .addComponent(jCombo_Col1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(texto_col1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRB_And)
                        .addGap(5, 5, 5)
                        .addComponent(jRB_Or)
                        .addGap(18, 18, 18)
                        .addComponent(jCombo_Col2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(texto_col2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(291, Short.MAX_VALUE))
        );
        panelBALayout.setVerticalGroup(
            panelBALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBALayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCombo_Col1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCombo_Col2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto_col1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texto_col2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRB_And)
                    .addComponent(jRB_Or))
                .addGap(40, 40, 40)
                .addComponent(scrollTablaBA, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BusquedaAlumnos.addTab("Buscar Alumnos...", panelBA);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BusquedaAlumnos)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(BusquedaAlumnos))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void scrollTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollTablaMouseClicked

    }//GEN-LAST:event_scrollTablaMouseClicked

    private void tableAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAlumnosMouseClicked

    }//GEN-LAST:event_tableAlumnosMouseClicked

    private void botonExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExaminarActionPerformed
        JFileChooser selector = new JFileChooser("src/datos/");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(".txt", "txt");
        selector.setFileFilter(filtro);
        int estado = selector.showOpenDialog(null);
        File archivoelegido = selector.getSelectedFile();
        if (estado == JFileChooser.APPROVE_OPTION); {
            cargarTablaFichero(archivoelegido.getName());
        }
    }//GEN-LAST:event_botonExaminarActionPerformed

    private void textNifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNifActionPerformed

    private void tableGruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGruposMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableGruposMouseClicked

    private void scrollTabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollTabla1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_scrollTabla1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboGrupo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGrupo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboGrupo1ActionPerformed

    private void botonCargarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarFotoActionPerformed
        JFileChooser selector = new JFileChooser("src/fotos/");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(".jpg & .gif", "jpg", "gif");
        selector.setFileFilter(filtro);
        int estado = selector.showOpenDialog(null);
        File archivoelegido = selector.getSelectedFile();
        if (estado == JFileChooser.APPROVE_OPTION); {
            textFoto.setText(archivoelegido.getName());
        }
        ImageIcon icon = new ImageIcon("src/fotos/" + archivoelegido.getName());
        Image imagen = icon.getImage();
        Image imagenNueva = imagen.getScaledInstance(labelFoto1.getWidth(), labelFoto1.getHeight(), Image.SCALE_SMOOTH);//se crea a partir de la imangen antigua una imagen nueva con las dimensiones que queramos
        labelFoto1.setIcon(new ImageIcon(imagenNueva)); 
    }//GEN-LAST:event_botonCargarFotoActionPerformed

    private void comboGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGrupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboGrupoActionPerformed

    private void textNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNombreActionPerformed

    private void botonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarActionPerformed
        if (textNombre.getText().isEmpty() || textApellidos.getText().isEmpty() || textNie.getText().isEmpty() || comboProvincia.getSelectedItem().toString().isEmpty() || comboLocalidad.getSelectedItem().toString().isEmpty() || comboGrupo.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Debe relllenar todos los campos");

        } else {
            cargaAlumno(conecta.connBd.getConn());
        }
    }//GEN-LAST:event_botonCargarActionPerformed

    private void botonReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReiniciarActionPerformed
        limpiarFicha();
    }//GEN-LAST:event_botonReiniciarActionPerformed

    private void tableAlumnos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAlumnos1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableAlumnos1MouseClicked

    private void scrollTabla2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollTabla2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_scrollTabla2MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane BusquedaAlumnos;
    private javax.swing.ButtonGroup bG_AND_OR;
    private javax.swing.JButton botonCargar;
    private javax.swing.JButton botonCargarFoto;
    private javax.swing.JButton botonExaminar;
    private javax.swing.JButton botonGuardarLista;
    private javax.swing.JButton botonReiniciar;
    private javax.swing.JComboBox<String> comboGrupo;
    private javax.swing.JComboBox<String> comboGrupo1;
    private javax.swing.JComboBox<String> comboLocalidad;
    private javax.swing.JComboBox<String> comboProvincia;
    private javax.swing.JLabel foto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jCombo_Col1;
    private javax.swing.JComboBox<String> jCombo_Col2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRB_And;
    private javax.swing.JRadioButton jRB_Or;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable_BA;
    private javax.swing.JLabel labelCP;
    private javax.swing.JLabel labelCodPostal;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelFoto1;
    private javax.swing.JLabel labelGrupo;
    private javax.swing.JLabel labelGrupo1;
    private javax.swing.JLabel labelGrupos;
    private javax.swing.JLabel labelLocalidad;
    private javax.swing.JLabel labelNie;
    private javax.swing.JLabel labelNif;
    private javax.swing.JLabel labelNif1;
    private javax.swing.JLabel labelNom;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelProv;
    private javax.swing.JList<String> listLista;
    private javax.swing.JList<String> listaActoresDisp;
    private javax.swing.JPanel panelAlum;
    private javax.swing.JPanel panelBA;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelFicha;
    private javax.swing.JPanel panelLista;
    private javax.swing.JPanel panelNuevoAlum;
    private javax.swing.JPanel panelProcedencia;
    private javax.swing.JList<String> reparto;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JScrollPane scrollTabla1;
    private javax.swing.JScrollPane scrollTabla2;
    private javax.swing.JScrollPane scrollTablaBA;
    private javax.swing.JTabbedPane tabbedPaneLista;
    private javax.swing.JTable tableAlumnos;
    private javax.swing.JTable tableAlumnos1;
    private javax.swing.JTable tableGrupos;
    private javax.swing.JTextField textApellidos;
    private javax.swing.JTextField textCP;
    private javax.swing.JTextField textCodigoPostal;
    private javax.swing.JTextField textFoto;
    private javax.swing.JTextField textGrup;
    private javax.swing.JTextField textLoc;
    private javax.swing.JTextField textNie;
    private javax.swing.JTextField textNif;
    private javax.swing.JTextField textNomb;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField texto_col1;
    private javax.swing.JTextField texto_col2;
    // End of variables declaration//GEN-END:variables
    JComboBox<Object> comboTablaGrupos;

    private void cargaGrupos(JComboBox lista, String select, Connection conn) {
        try {
            lista.removeAllItems();
            Statement st = conn.createStatement();
            ResultSet rs;
            rs = st.executeQuery(select);
            ResultSetMetaData rsMd= rs.getMetaData();
            
            lista.addItem("Selecciona un "+ rsMd.getColumnLabel(1));
            while (rs.next()) {
                lista.addItem(rs.getObject(1));
            }
            lista.setSelectedIndex(0);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al crear Sentencia " + ex.getMessage());
        }
    }

    private JTable cargaTabla(JScrollPane scroll, String consulta, Connection conn) {
        ResultSet rs;
        ResultSetMetaData metaDatos;
        DefaultTableModel dtm;
        JTable tabla = null;

        try {
            rs = conn.createStatement().executeQuery(consulta);
            metaDatos = rs.getMetaData();
            int numColumnas = metaDatos.getColumnCount();
            Object[] etiquetas = new Object[numColumnas];

            jCombo_Col1.setModel(new DefaultComboBoxModel());
              jCombo_Col2.setModel(new DefaultComboBoxModel());
              
            for (int i = 0; i < numColumnas; i++) {
                etiquetas[i] = metaDatos.getColumnLabel(i + 1);
                jCombo_Col1.addItem(etiquetas[i].toString());
                jCombo_Col2.addItem(etiquetas[i].toString());

            }
            dtm = new DefaultTableModel(etiquetas, 0);

            Object[] fila = new Object[numColumnas];
            while (rs.next()) {
                for (int i = 0; i < numColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                dtm.addRow(fila);
            }
            tabla = new JTable(dtm);
            TableRowSorter ordenColumnas = new TableRowSorter(dtm);
            tabla.setRowSorter(ordenColumnas);
            scroll.setViewportView(tabla);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al crear Sentencia " + ex.getMessage());
        }
        return tabla;
    }

    private void listaAlumFichero(String curso) {

        Statement stmt = null;
        ResultSet rset = null;
        PrintWriter pw = null;
        int i = 1;

        try {
            pw = new PrintWriter("src/datos/" + curso + ".txt");
            stmt = conecta.connBd.getConn().createStatement();
            rset = stmt.executeQuery("select alumno from alumnos where grupo = '" + curso + "'");
            pw.println("Alumnos del curso: " + curso);
            while (rset.next()) {
                pw.println(i + ". " + rset.getString(1));
                i++;
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al crear el fichero " + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al crear Sentencia " + ex.getMessage());
        } finally {
            try {
                pw.close();
                stmt.close();
                rset.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error en el finally " + ex.getMessage());
            }
        }
    }

    private void cargaProv(JComboBox lista, String tabla, Connection conn) {
        try {
            lista.removeAllItems();
            Statement st = conn.createStatement();
            ResultSet rs;

            rs = st.executeQuery("select * from " + tabla);

            lista.addItem("Selecciona Provincia");
            while (rs.next()) {
                lista.addItem(rs.getString("provincia"));
            }
            lista.setSelectedIndex(0);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al crear Sentencia " + ex.getMessage());
        }
    }

    private void cargaPoblacion(JComboBox lista, String provincia, Connection conn) {
        try {
            lista.setModel(new DefaultComboBoxModel());
            Statement st = conn.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select * from poblaciones where idprovincia =(select idprovincia from provincias where provincia = '" + provincia + "')");
            while (rs.next()) {
                lista.addItem(rs.getString("poblacion"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al crear Sentencia " + ex.getMessage());
        }
    }

    private void cargaAlumno(Connection conn) {
        String alumno = textApellidos.getText() + ", " + textNombre.getText();
        try {
            try (PreparedStatement pst = conn.prepareStatement("INSERT INTO ALUMNOS VALUES(?,?,?,?,?,?)")) {
                pst.setString(1, alumno);
                pst.setInt(2, Integer.parseInt(textNie.getText()));
                pst.setString(3, textCP.getText());
                pst.setString(4, comboLocalidad.getSelectedItem().toString());
                pst.setString(5, comboGrupo.getSelectedItem().toString());
                pst.setString(6, textFoto.getText());
                pst.executeUpdate();
            }
            Object[] alm = {alumno, textNie.getText(), textCP.getText(),
                comboLocalidad.getSelectedItem().toString(), comboProvincia.getSelectedItem().toString(),
                comboGrupo.getSelectedItem().toString(),textFoto.getText()};
            ((DefaultTableModel)tableAlumnos.getModel()).addRow(alm);
           
         JOptionPane.showMessageDialog(rootPane, "Alumno " + textNombre.getText() + " dado de alta en el grupo " + comboGrupo.getSelectedItem().toString());            
         
         limpiarFicha();

        } catch (SQLException ex) {
           if(ex.getErrorCode()==1062)
            JOptionPane.showMessageDialog(rootPane, 
                    "Nie Duplicado, debe cambiarlo");
           else JOptionPane.showMessageDialog(rootPane, 
                    "Error Inesperado-> "+ex.getMessage());
        }

    }
    
    private String guardaLista() {
        int fila = tableGrupos.getSelectedRow();
        return tableGrupos.getValueAt(fila, 0).toString();  
    }
    
    private void ficha() {
        String nombre,nie,cp,localidad,grupo,foto2;
        int fila = tableAlumnos.getSelectedRow();
        nombre = jTable_BA.getValueAt(fila, 0).toString();
        nie = jTable_BA.getValueAt(fila, 1).toString();
        cp = jTable_BA.getValueAt(fila, 2).toString();
        localidad = (String) jTable_BA.getValueAt(fila, 3);
        grupo = jTable_BA.getValueAt(fila, 4).toString();
        foto2 = jTable_BA.getValueAt(fila, 5).toString();  
        textNomb.setText(nombre);
        textNif.setText(nie);
        textCodigoPostal.setText(cp);
        textLoc.setText(localidad);
        textGrup.setText(grupo);
        ImageIcon icon = new ImageIcon("src/fotos/" + foto2);
        Image imagen = icon.getImage();
        Image imagenNueva = imagen.getScaledInstance(labelFoto.getWidth(), labelFoto.getHeight(), Image.SCALE_SMOOTH);//se crea a partir de la imangen antigua una imagen nueva con las dimensiones que queramos
        labelFoto.setIcon(new ImageIcon(imagenNueva)); 
    }
    
    private void limpiarFicha(){
        textNombre.setText("");
        textNie.setText("");
        textApellidos.setText("");
        textCP.setText("");
        textFoto.setText("");
        textGrup.setText("");
        comboLocalidad.setModel(new DefaultComboBoxModel());
        comboProvincia.setSelectedIndex(0);
        comboGrupo.setSelectedIndex(0);
        labelFoto1.setIcon(new ImageIcon("../fotos/Ariza Ariza, Elena.jpg"));
    }   
    
    private void cargarAsig() {
        int fila = tableAlumnos.getSelectedRow();
        String nieAlumno = tableAlumnos.getValueAt(fila, 1).toString();  
        tableAlumnos1 = cargaTabla(scrollTabla2, "select CLAVE ASIGNATURA from modulos where grupo=(select GRUPO from alumnos where NIE='"+nieAlumno+"')", conecta.connBd.getConn());
    }
    
    private void ponerNum() {
        String x="";
        int num = tableAlumnos.getRowCount();
        x=x+num;
        jLabel4.setText(x);
    }
    
    private void cargaAlumnos() {
        if (grupoSeleccionado!=null) {
            DefaultTableModel tm = (DefaultTableModel)tableAlumnos.getModel();
            TableRowSorter model = new TableRowSorter(tm);
            RowFilter filtradoTabla = RowFilter.regexFilter(grupoSeleccionado, 1);
            model.setRowFilter(filtradoTabla);
            tableAlumnos.setRowSorter(model);
        }
    }

    private void cargarTablaFichero(String name) {
        try {
            String linea;
            DefaultListModel listaAlum= new DefaultListModel();
            FileReader file = new FileReader("src/datos/"+name);
            BufferedReader bf = new BufferedReader(file);
            while((linea = bf.readLine()) != null){
                listaAlum.addElement(linea);
            }
            listLista.setModel(listaAlum);
        } catch (FileNotFoundException ex) {
            System.err.println("No se encuentra el fichero: "+name); 
        } catch (IOException ex) {
            System.out.println("error-> "+ ex.getMessage());
        }  
    }
    
    /*private void ocultarColumnas() {
        int[] columnasOcultar = {1,2,3,5};
        for (int x=0;x!=columnasOcultar.length;x++) {
            int j=columnasOcultar[x];
            tableAlumnos.getColumnModel().getColumn(j).setMinWidth(0);
            tableAlumnos.getColumnModel().getColumn(j).setMaxWidth(0);
            tableAlumnos.getColumnModel().getColumn(j).setWidth(0);
        }
    }*/
}
