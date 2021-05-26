package institutointerfaz;

import BD.Conectate;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Ventana extends javax.swing.JFrame {

    Conectate conecta;

    public Ventana() {
        conecta = new Conectate(null, true);
        initComponents();
        botonActualizar.setVisible(false);
        comboTablaGrupos = new JComboBox<>();

        //cargaGrupos(comboGrupos, "grupos", conecta.connBd.getConn());
         cargaGrupos(comboGrupo, "select grupo from grupos", conecta.connBd.getConn());

        tableAlumnos = cargaTabla(scrollTabla, "select * from alumnos", conecta.connBd.getConn());
        ocultar();
        jTable_BA = cargaTabla(scrollTablaBA, "select * from alumnos", conecta.connBd.getConn());

        comboGrupos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //aqui hay que poner el filtro 
                DefaultTableModel dtm = (DefaultTableModel) tableAlumnos.getModel();
                TableRowSorter trs = new TableRowSorter(dtm);

                RowFilter rf = RowFilter.regexFilter((String) comboGrupos.getSelectedItem(), 5);

                trs.setRowFilter(rf);
                tableAlumnos.setRowSorter(trs);
            }
        });

        tableAlumnos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                ficha();
            }
        });
        tableAlumnos.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                ficha();
            }

        });
        botonGuardarLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboGrupos.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(rootPane, "Ningun curso seleccionado");
                } else {
                    listaAlumFichero(comboGrupos.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(rootPane, "Listado del curso guardado correctamente");
                }
            }
        });
//---------------------------------------nuevo alumno-------------------------
        cargaProv(comboProvincia, "provincias", conecta.connBd.getConn());
        comboProvincia.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String provincia = comboProvincia.getSelectedItem().toString();
                cargaPoblacion(comboLocalidad, provincia, conecta.connBd.getConn());
            }
        });

        comboLocalidad.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

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
            }
        });

        cargaGrupos(comboGrupo, "select grupo from grupos", conecta.connBd.getConn());

        botonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Statement stm = null;
                ResultSet rst = null;

                int fila = tableAlumnos.getSelectedRow();
                String nie = tableAlumnos.getValueAt(fila, 1).toString();
                String nuevoGrupo = comboTablaGrupos.getSelectedItem().toString();
                try {
                    stm = conecta.connBd.getConn().createStatement();
                    stm.executeUpdate("update alumnos set grupo = '" + nuevoGrupo + "'where nie =" + nie);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Error al crear Sentencia " + ex.getMessage());
                } finally {
                    try {
                        stm.close();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(rootPane, "Error cerrando stm" + ex.getMessage());

                    }

                }
                botonActualizar.setVisible(false);
            }
        });
        // BottonGroup.............
        jRB_Or.setActionCommand("OR");jRB_And.setActionCommand("AND");
        bG_AND_OR.add(jRB_Or);bG_AND_OR.add(jRB_And);
       //---Busquedas-------------------------------------
       
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

                LinkedList<RowFilter> lista = new LinkedList<RowFilter>();
                lista.add(filtraCombo1);
                lista.add(filtraCombo2);

               // AND y OR.....
               String opera= bG_AND_OR.getSelection().getActionCommand();
               if (opera.equalsIgnoreCase("AND"))// and de ambos filtros
                    filtro = RowFilter.andFilter((Iterable)lista);
               else
                    filtro = RowFilter.orFilter((Iterable)lista);  // or de ambos filtros.
               
               
                TableRowSorter ordena=(TableRowSorter) jTable_BA.getRowSorter();
                ordena.setRowFilter(filtro);
            }
            
        });
       //------------------combio en los combos disparar el key Event....
       jCombo_Col1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               KeyEvent ke = 
                new KeyEvent( texto_col1, KeyEvent.KEY_RELEASED, 1,1,1,'a');
                texto_col1.dispatchEvent(ke);
            }
        });
       
       jCombo_Col2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               KeyEvent ke = 
                new KeyEvent( texto_col1, KeyEvent.KEY_RELEASED, 1,1,1,'a');
                texto_col1.dispatchEvent(ke);
            }
        });
       
        texto_col2.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                
                KeyEvent ke = 
                new KeyEvent( texto_col1, KeyEvent.KEY_RELEASED, 1,1,1,'a');
                texto_col1.dispatchEvent(ke);
                
               }
       
        });
        //------------Cambio den los RAdio..--------
        jRB_Or.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                KeyEvent ke = 
                new KeyEvent( texto_col1, KeyEvent.KEY_RELEASED, 1,1,1,'a');
                texto_col1.dispatchEvent(ke);
            }
        } );
        
         jRB_And.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                KeyEvent ke = 
                new KeyEvent( texto_col1, KeyEvent.KEY_RELEASED, 1,1,1,'a');
                texto_col1.dispatchEvent(ke);
            }
        } );
        
    } // CONSTURCTOR...........

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bG_AND_OR = new javax.swing.ButtonGroup();
        BusquedaAlumnos = new javax.swing.JTabbedPane();
        panelAlum = new javax.swing.JPanel();
        labelGrupos = new javax.swing.JLabel();
        comboGrupos = new javax.swing.JComboBox<>();
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
        panelProcedencia = new javax.swing.JPanel();
        textLoc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textCodigoPostal = new javax.swing.JTextField();
        labelCodPostal = new javax.swing.JLabel();
        panelJuego = new javax.swing.JPanel();
        labelGanadas = new javax.swing.JLabel();
        textGana = new javax.swing.JTextField();
        labelJugadas = new javax.swing.JLabel();
        textJug = new javax.swing.JTextField();
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
        jPanel1 = new javax.swing.JPanel();
        labelLocalidad = new javax.swing.JLabel();
        comboLocalidad = new javax.swing.JComboBox<>();
        labelProv = new javax.swing.JLabel();
        comboProvincia = new javax.swing.JComboBox<>();
        labelCP = new javax.swing.JLabel();
        textCP = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        labelJug = new javax.swing.JLabel();
        textJugadas = new javax.swing.JTextField();
        labelGan = new javax.swing.JLabel();
        textGanadas = new javax.swing.JTextField();
        panelLista = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listLista = new javax.swing.JList<>();
        botonExaminar = new javax.swing.JButton();
        botonGuardarLista = new javax.swing.JButton();
        botonNota = new javax.swing.JButton();
        botonCambiarGrupo = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tableAlumnos = new javax.swing.JTable();
        botonActualizar = new javax.swing.JButton();
        panelGrupos = new javax.swing.JPanel();
        panelProfes = new javax.swing.JPanel();
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

        comboGrupos.setSelectedItem("Elige Grupo");

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

        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelNom)
                            .addComponent(labelNif))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textNif, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textNomb, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE))
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textGrup, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)))
                .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(foto)
                .addGap(107, 107, 107))
        );
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textNomb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNom))
                        .addGap(18, 18, 18)
                        .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textNif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNif))
                        .addGap(35, 35, 35)
                        .addComponent(textGrup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(foto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        panelJuego.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Juego", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        labelGanadas.setText("Ganadas");

        labelJugadas.setText("Jugadas");

        javax.swing.GroupLayout panelJuegoLayout = new javax.swing.GroupLayout(panelJuego);
        panelJuego.setLayout(panelJuegoLayout);
        panelJuegoLayout.setHorizontalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJuegoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJuegoLayout.createSequentialGroup()
                        .addComponent(labelGanadas)
                        .addGap(18, 18, 18)
                        .addComponent(textGana, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelJuegoLayout.createSequentialGroup()
                        .addComponent(labelJugadas)
                        .addGap(18, 18, 18)
                        .addComponent(textJug, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        panelJuegoLayout.setVerticalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJuegoLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelJugadas)
                    .addComponent(textJug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelGanadas)
                    .addComponent(textGana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout panelFichaLayout = new javax.swing.GroupLayout(panelFicha);
        panelFicha.setLayout(panelFichaLayout);
        panelFichaLayout.setHorizontalGroup(
            panelFichaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFichaLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelFichaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFichaLayout.createSequentialGroup()
                        .addComponent(panelProcedencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFichaLayout.setVerticalGroup(
            panelFichaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFichaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelFichaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelProcedencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        tabbedPaneLista.addTab("Ficha", panelFicha);

        botonReiniciar.setText("Reiniciar");
        botonReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReiniciarActionPerformed(evt);
            }
        });

        botonCargar.setText("Alta ");
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(labelNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelGrupo)
                            .addComponent(labelNie)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonCargarFoto)
                    .addComponent(textNie, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textNie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelGrupo))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCargarFoto)
                    .addComponent(textFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelCP)
                            .addComponent(labelLocalidad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textCP, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(59, Short.MAX_VALUE))
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
                    .addComponent(comboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCP)
                    .addComponent(textCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Juego", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        labelJug.setText("Jugadas: ");

        textJugadas.setText("0");
        textJugadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textJugadasActionPerformed(evt);
            }
        });

        labelGan.setText("Ganadas: ");

        textGanadas.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelJug)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textJugadas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelGan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textGanadas)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelJug)
                    .addComponent(textJugadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelGan)
                    .addComponent(textGanadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelNuevoAlumLayout = new javax.swing.GroupLayout(panelNuevoAlum);
        panelNuevoAlum.setLayout(panelNuevoAlumLayout);
        panelNuevoAlumLayout.setHorizontalGroup(
            panelNuevoAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoAlumLayout.createSequentialGroup()
                .addGroup(panelNuevoAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNuevoAlumLayout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(botonReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(botonCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelNuevoAlumLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panelNuevoAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelNuevoAlumLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 33, Short.MAX_VALUE))
        );
        panelNuevoAlumLayout.setVerticalGroup(
            panelNuevoAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoAlumLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelNuevoAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(panelNuevoAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonReiniciar)
                    .addComponent(botonCargar))
                .addContainerGap(86, Short.MAX_VALUE))
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
                .addContainerGap(53, Short.MAX_VALUE))
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
                .addContainerGap(97, Short.MAX_VALUE))
        );

        tabbedPaneLista.addTab("Listas", panelLista);

        botonGuardarLista.setText("Guardar Lista");

        botonNota.setText("Poner Nota");

        botonCambiarGrupo.setText("Cambiar Grupo");
        botonCambiarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCambiarGrupoActionPerformed(evt);
            }
        });

        scrollTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrollTablaMouseClicked(evt);
            }
        });

        tableAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Ganadas"
            }
        ));
        tableAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAlumnosMouseClicked(evt);
            }
        });
        scrollTabla.setViewportView(tableAlumnos);

        botonActualizar.setText("Actualizar");

        javax.swing.GroupLayout panelAlumLayout = new javax.swing.GroupLayout(panelAlum);
        panelAlum.setLayout(panelAlumLayout);
        panelAlumLayout.setHorizontalGroup(
            panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlumLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelGrupos)
                    .addComponent(comboGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPaneLista, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelAlumLayout.createSequentialGroup()
                        .addComponent(botonGuardarLista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonNota)
                        .addGap(18, 18, 18)
                        .addComponent(botonCambiarGrupo)
                        .addGap(84, 84, 84)
                        .addComponent(botonActualizar)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelAlumLayout.setVerticalGroup(
            panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlumLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelGrupos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAlumLayout.createSequentialGroup()
                        .addComponent(comboGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tabbedPaneLista)
                            .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(panelAlumLayout.createSequentialGroup()
                        .addGroup(panelAlumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonGuardarLista)
                            .addComponent(botonNota)
                            .addComponent(botonCambiarGrupo)
                            .addComponent(botonActualizar))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        BusquedaAlumnos.addTab("Alumnos", panelAlum);

        javax.swing.GroupLayout panelGruposLayout = new javax.swing.GroupLayout(panelGrupos);
        panelGrupos.setLayout(panelGruposLayout);
        panelGruposLayout.setHorizontalGroup(
            panelGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1072, Short.MAX_VALUE)
        );
        panelGruposLayout.setVerticalGroup(
            panelGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
        );

        BusquedaAlumnos.addTab("Grupos", panelGrupos);

        javax.swing.GroupLayout panelProfesLayout = new javax.swing.GroupLayout(panelProfes);
        panelProfes.setLayout(panelProfesLayout);
        panelProfesLayout.setHorizontalGroup(
            panelProfesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1072, Short.MAX_VALUE)
        );
        panelProfesLayout.setVerticalGroup(
            panelProfesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
        );

        BusquedaAlumnos.addTab("Profesores", panelProfes);

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
                .addContainerGap(240, Short.MAX_VALUE))
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

    private void textNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNombreActionPerformed

    private void botonCambiarGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCambiarGrupoActionPerformed
       /* tableAlumnos.removeAll();
        botonActualizar.setVisible(true);
        tableAlumnos = cargaTabla(scrollTabla, "select * from alumnos", conecta.connBd.getConn());
        ocultar2();*/
        tableAlumnos.getColumnModel().getColumn(5).setPreferredWidth(100);
        tableAlumnos.getColumnModel().getColumn(5).setMaxWidth(100);
        tableAlumnos.getColumnModel().getColumn(5).setMinWidth(100);
        
        tableAlumnos.getColumnModel().getColumn(5).setHeaderValue("Grupos");
        
         cargaGrupos(comboGrupo, "select grupo from grupos", conecta.connBd.getConn());
        
        TableColumn columnaGrupos = tableAlumnos.getColumnModel().getColumn(5);
        columnaGrupos.setCellEditor(new DefaultCellEditor(comboTablaGrupos));


    }//GEN-LAST:event_botonCambiarGrupoActionPerformed

    private void scrollTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollTablaMouseClicked

    }//GEN-LAST:event_scrollTablaMouseClicked

    private void tableAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAlumnosMouseClicked


    }//GEN-LAST:event_tableAlumnosMouseClicked

    private void textNifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNifActionPerformed

    private void textJugadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textJugadasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textJugadasActionPerformed

    private void botonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarActionPerformed
        if (textNombre.getText().isEmpty() || textApellidos.getText().isEmpty() || textNie.getText().isEmpty() || comboProvincia.getSelectedItem().toString().isEmpty() || comboLocalidad.getSelectedItem().toString().isEmpty() || comboGrupo.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Debe relllenar todos los campos");

        } else {
            cargaAlumno(conecta.connBd.getConn());
           /* tableAlumnos = cargaTabla(scrollTabla, "select * from alumnos", conecta.connBd.getConn());
            ocultar();*/
            
        }
    }//GEN-LAST:event_botonCargarActionPerformed

    private void comboGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboGrupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboGrupoActionPerformed

    private void botonExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExaminarActionPerformed
        JTable tabla = new JTable();
        JFileChooser selector = new JFileChooser("src/datos/");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(".txt", "txt");
        selector.setFileFilter(filtro);
        int estado = selector.showOpenDialog(null);
        File archivoelegido = selector.getSelectedFile();
        String ruta = archivoelegido.getPath();
        if (estado == JFileChooser.APPROVE_OPTION);
        {

           /* try {
                tabla = cargarTablaFichero(archivoelegido.getName());
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }*/

        }


    }//GEN-LAST:event_botonExaminarActionPerformed

    private void botonCargarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarFotoActionPerformed
       JFileChooser selector = new JFileChooser("src/fotos/");
       FileNameExtensionFilter filtro = new FileNameExtensionFilter(".jpg & .gif", "jpg", "gif");
       selector.setFileFilter(filtro);
       int estado = selector.showOpenDialog(null);
       File archivoelegido = selector.getSelectedFile();
       String ruta = archivoelegido.getPath();
       if (estado == JFileChooser.APPROVE_OPTION);
                {
                    ImageIcon imagen = new ImageIcon(ruta);
                    textFoto.setText(archivoelegido.getName());
                }
    }//GEN-LAST:event_botonCargarFotoActionPerformed

    private void botonReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReiniciarActionPerformed
        panelNuevoAlum.removeAll();
    }//GEN-LAST:event_botonReiniciarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane BusquedaAlumnos;
    private javax.swing.ButtonGroup bG_AND_OR;
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonCambiarGrupo;
    private javax.swing.JButton botonCargar;
    private javax.swing.JButton botonCargarFoto;
    private javax.swing.JButton botonExaminar;
    private javax.swing.JButton botonGuardarLista;
    private javax.swing.JButton botonNota;
    private javax.swing.JButton botonReiniciar;
    private javax.swing.JComboBox<String> comboGrupo;
    private javax.swing.JComboBox<String> comboGrupos;
    private javax.swing.JComboBox<String> comboLocalidad;
    private javax.swing.JComboBox<String> comboProvincia;
    private javax.swing.JLabel foto;
    private javax.swing.JComboBox<String> jCombo_Col1;
    private javax.swing.JComboBox<String> jCombo_Col2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRB_And;
    private javax.swing.JRadioButton jRB_Or;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_BA;
    private javax.swing.JLabel labelCP;
    private javax.swing.JLabel labelCodPostal;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelGan;
    private javax.swing.JLabel labelGanadas;
    private javax.swing.JLabel labelGrupo;
    private javax.swing.JLabel labelGrupos;
    private javax.swing.JLabel labelJug;
    private javax.swing.JLabel labelJugadas;
    private javax.swing.JLabel labelLocalidad;
    private javax.swing.JLabel labelNie;
    private javax.swing.JLabel labelNif;
    private javax.swing.JLabel labelNom;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelProv;
    private javax.swing.JList<String> listLista;
    private javax.swing.JPanel panelAlum;
    private javax.swing.JPanel panelBA;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelFicha;
    private javax.swing.JPanel panelGrupos;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JPanel panelLista;
    private javax.swing.JPanel panelNuevoAlum;
    private javax.swing.JPanel panelProcedencia;
    private javax.swing.JPanel panelProfes;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JScrollPane scrollTablaBA;
    private javax.swing.JTabbedPane tabbedPaneLista;
    private javax.swing.JTable tableAlumnos;
    private javax.swing.JTextField textApellidos;
    private javax.swing.JTextField textCP;
    private javax.swing.JTextField textCodigoPostal;
    private javax.swing.JTextField textFoto;
    private javax.swing.JTextField textGana;
    private javax.swing.JTextField textGanadas;
    private javax.swing.JTextField textGrup;
    private javax.swing.JTextField textJug;
    private javax.swing.JTextField textJugadas;
    private javax.swing.JTextField textLoc;
    private javax.swing.JTextField textNie;
    private javax.swing.JTextField textNif;
    private javax.swing.JTextField textNomb;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField texto_col1;
    private javax.swing.JTextField texto_col2;
    // End of variables declaration//GEN-END:variables
JComboBox<Object> comboTablaGrupos;

    void cargaGrupos(JComboBox lista, String select, Connection conn) {
        try {
            lista.removeAllItems();
            Statement st = conn.createStatement();
            ResultSet rs;

            //rs = st.executeQuery("select grupo from " + tabla);
            rs = st.executeQuery(select);
            ResultSetMetaData rsMd= rs.getMetaData();
            
            lista.addItem("Selecciona un "+ rsMd.getColumnLabel(1));
            while (rs.next()) {
                //lista.addItem(new Grupos(rs.getString("grupo"))); PARA CARGAR OBEJTOS
                //lista.addItem(rs.getString("grupo")); //CARGAMOS UN STRING
                lista.addItem(rs.getObject(1));
            }
            lista.setSelectedIndex(0);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al crear Sentencia " + ex.getMessage());
        }
    }

    JTable cargaTabla(JScrollPane scroll, String consulta, Connection conn) {
        ResultSet rs;
        ResultSetMetaData metaDatos;
        DefaultTableModel dtm;
        JTable tabla = null;

        try {
            rs = conn.createStatement().executeQuery(consulta);
            metaDatos = rs.getMetaData();

            //OBTENER LAS COLUMNAS DE LA TABLA
            int numColumnas = metaDatos.getColumnCount();
            Object[] etiquetas = new Object[numColumnas];

            jCombo_Col1.setModel(new DefaultComboBoxModel());
              jCombo_Col2.setModel(new DefaultComboBoxModel());
              
            for (int i = 0; i < numColumnas; i++) {
                etiquetas[i] = metaDatos.getColumnLabel(i + 1);
                //etiquetas[i] = new JCheckBox(metaDatos.getColumnLabel(i + 1));
                jCombo_Col1.addItem(etiquetas[i].toString());
                jCombo_Col2.addItem(etiquetas[i].toString());

            }

            //Creamos el modelo de la tabla
            dtm = new DefaultTableModel(etiquetas, 0);
            // cargamos los combos........................
           /* jCombo_Col1= new JComboBox(etiquetas);
            jCombo_Col2= new JComboBox(etiquetas);*/

            Object[] fila = new Object[numColumnas];
            while (rs.next()) {
                for (int i = 0; i < numColumnas; i++) {
                    fila[i] = rs.getObject(i + 1); //ponemos i +1 porque las columnas empiezan en 1 
                }

                dtm.addRow(fila);
            }

            tabla = new JTable(dtm);
            
            TableRowSorter ordenColumnas = new TableRowSorter(dtm);
            tabla.setRowSorter(ordenColumnas);
            
            

            scroll.setViewportView(tabla); //un scroll solo puede mostrar un componente 

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al crear Sentencia " + ex.getMessage());
        }
        return tabla;
    }

   
    void listaAlumFichero(String curso) {

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

    //--------------------------NUEVO ALUMNO----------------------------------
    void cargaProv(JComboBox lista, String tabla, Connection conn) {
        try {
            lista.removeAllItems();
            Statement st = conn.createStatement();
            ResultSet rs;

            rs = st.executeQuery("select * from " + tabla);

            lista.addItem("Selecciona Provincia");
            while (rs.next()) {
                //lista.addItem(new Grupos(rs.getString("grupo"))); PARA CARGAR OBEJTOS
                lista.addItem(rs.getString("provincia")); //CARGAMOS UN STRING
            }
            lista.setSelectedIndex(0);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al crear Sentencia " + ex.getMessage());
        }
    }

    void cargaPoblacion(JComboBox lista, String provincia, Connection conn) {

        try {
            //lista.removeAllItems();
            lista.setModel(new DefaultComboBoxModel());
            Statement st = conn.createStatement();
            ResultSet rs;

            rs = st.executeQuery("select * from poblaciones where idprovincia =(select idprovincia from provincias where provincia = '" + provincia + "')");

            while (rs.next()) {
                //lista.addItem(new Grupos(rs.getString("grupo"))); PARA CARGAR OBEJTOS
                lista.addItem(rs.getString("poblacion")); //CARGAMOS UN STRING
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al crear Sentencia " + ex.getMessage());
        }

    }

    void cargaAlumno(Connection conn) {
        String alumno = textApellidos.getText() + ", " + textNombre.getText();

        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO ALUMNOS VALUES(?,?,?,?,?,?,?,?,?)");

            pst.setString(1, alumno);
            pst.setInt(2, Integer.parseInt(textNie.getText()));
            pst.setString(3, textCP.getText());
            pst.setString(4, comboLocalidad.getSelectedItem().toString());
            pst.setString(5, comboProvincia.getSelectedItem().toString());
            pst.setString(6, comboGrupo.getSelectedItem().toString());
            pst.setInt(7, Integer.parseInt(textJugadas.getText()));
            pst.setInt(8, Integer.parseInt(textGanadas.getText()));
            pst.setString(9, textFoto.getText());

            pst.executeUpdate();
            pst.close();
            
            // Añadir al modelo de la tabla alumnos..
            Object[] alm = {alumno, textNie.getText(), textCP.getText(),
                comboLocalidad.getSelectedItem().toString(), comboProvincia.getSelectedItem().toString(),
                comboGrupo.getSelectedItem().toString(),0,0,textFoto.getText()};
            
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
    //-------------------------------------

    void ficha() {
        int fila = tableAlumnos.getSelectedRow();

        String nombre = tableAlumnos.getValueAt(fila, 0).toString();
        String nie = tableAlumnos.getValueAt(fila, 1).toString();
        String cp = tableAlumnos.getValueAt(fila, 2).toString();
        String localidad = (String) tableAlumnos.getValueAt(fila, 3);
        String grupo = tableAlumnos.getValueAt(fila, 5).toString();
        String jugadas = tableAlumnos.getValueAt(fila, 6).toString();
        String ganadas = tableAlumnos.getValueAt(fila, 7).toString();
        String foto = tableAlumnos.getValueAt(fila, 8).toString();

        textNomb.setText(nombre);
        textNif.setText(nie);
        textCodigoPostal.setText(cp);
        textLoc.setText(localidad);
        textGrup.setText(grupo);
        textJug.setText(jugadas);
        textGana.setText(ganadas);
        
        ImageIcon icon = new ImageIcon("src/fotos/" + foto);//seleccionamos el icono
        Image imagen = icon.getImage();//convertimos el icono en una imagen
        Image imagenNueva = imagen.getScaledInstance(labelFoto.getWidth(), labelFoto.getHeight(), Image.SCALE_SMOOTH);//se crea a partir de la imangen antigua una imagen nueva con las dimensiones que queramos
        
        labelFoto.setIcon(new ImageIcon(imagenNueva));
        
    }
 void limpiarFicha(){
        textNomb.setText("");
        textNif.setText("");
        textCodigoPostal.setText("");
        textLoc.setText("");
        textGrup.setText("");
        textJug.setText("0");
        textGana.setText("0");
        comboLocalidad.setModel(new DefaultComboBoxModel());
        comboProvincia.setSelectedIndex(0);
        comboGrupo.setSelectedIndex(0);
       
        
        labelFoto.setIcon(new ImageIcon("../fotos/Ariza Ariza, Elena.jpg"));
     
 }   
 void ocultar() {

        tableAlumnos.getColumnModel().getColumn(1).setMaxWidth(0);
        tableAlumnos.getColumnModel().getColumn(2).setMaxWidth(0);
        tableAlumnos.getColumnModel().getColumn(3).setMaxWidth(0);
        tableAlumnos.getColumnModel().getColumn(4).setMaxWidth(0);
        tableAlumnos.getColumnModel().getColumn(5).setMaxWidth(0);
        tableAlumnos.getColumnModel().getColumn(6).setMaxWidth(0);
        tableAlumnos.getColumnModel().getColumn(7).setMaxWidth(0);
         tableAlumnos.getColumnModel().getColumn(8).setMaxWidth(0);

        tableAlumnos.getColumnModel().getColumn(1).setMinWidth(0);
        tableAlumnos.getColumnModel().getColumn(2).setMinWidth(0);
        tableAlumnos.getColumnModel().getColumn(3).setMinWidth(0);
        tableAlumnos.getColumnModel().getColumn(4).setMinWidth(0);
        tableAlumnos.getColumnModel().getColumn(5).setMinWidth(0);
        tableAlumnos.getColumnModel().getColumn(6).setMinWidth(0);
        tableAlumnos.getColumnModel().getColumn(7).setMinWidth(0);
        tableAlumnos.getColumnModel().getColumn(8).setMinWidth(0);

        tableAlumnos.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);

        tableAlumnos.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
         tableAlumnos.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);

        tableAlumnos.getColumnModel().getColumn(1).setResizable(false);
        tableAlumnos.getColumnModel().getColumn(2).setResizable(false);
        tableAlumnos.getColumnModel().getColumn(3).setResizable(false);
        tableAlumnos.getColumnModel().getColumn(4).setResizable(false);
        tableAlumnos.getColumnModel().getColumn(5).setResizable(false);
        tableAlumnos.getColumnModel().getColumn(6).setResizable(false);
        tableAlumnos.getColumnModel().getColumn(7).setResizable(false);
        tableAlumnos.getColumnModel().getColumn(8).setResizable(false);
    }
    

    void ocultar2() {

        tableAlumnos.getColumnModel().getColumn(1).setMaxWidth(0);
        tableAlumnos.getColumnModel().getColumn(2).setMaxWidth(0);
        tableAlumnos.getColumnModel().getColumn(3).setMaxWidth(0);
        tableAlumnos.getColumnModel().getColumn(4).setMaxWidth(0);
        tableAlumnos.getColumnModel().getColumn(6).setMaxWidth(0);
        tableAlumnos.getColumnModel().getColumn(7).setMaxWidth(0);
        tableAlumnos.getColumnModel().getColumn(8).setMaxWidth(0);

        tableAlumnos.getColumnModel().getColumn(1).setMinWidth(0);
        tableAlumnos.getColumnModel().getColumn(2).setMinWidth(0);
        tableAlumnos.getColumnModel().getColumn(3).setMinWidth(0);
        tableAlumnos.getColumnModel().getColumn(4).setMinWidth(0);
        tableAlumnos.getColumnModel().getColumn(6).setMinWidth(0);
        tableAlumnos.getColumnModel().getColumn(7).setMinWidth(0);
        tableAlumnos.getColumnModel().getColumn(8).setMinWidth(0);

        tableAlumnos.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);

        tableAlumnos.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
        tableAlumnos.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);

        tableAlumnos.getColumnModel().getColumn(1).setResizable(false);
        tableAlumnos.getColumnModel().getColumn(2).setResizable(false);
        tableAlumnos.getColumnModel().getColumn(3).setResizable(false);
        tableAlumnos.getColumnModel().getColumn(4).setResizable(false);
        tableAlumnos.getColumnModel().getColumn(6).setResizable(false);
        tableAlumnos.getColumnModel().getColumn(7).setResizable(false);
        tableAlumnos.getColumnModel().getColumn(8).setResizable(false);
    }

}
