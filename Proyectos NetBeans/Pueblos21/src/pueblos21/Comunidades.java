/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pueblos21;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Usuario
 */
public class Comunidades extends MiVentana1 {

    Connection conn;
    JTable pueblos;
    
    public Comunidades() {
        super("Hola", 600, 800);
        initComponents();
        conn= (new LoginBD(this,true)).conn;
        cargaComboComu(jC_comunidades);
        pueblos =cargaJTable("select * from poblaciones", jScrollPane1);
        
        jC_comunidades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //jC_provincias.removeAllItems();
             String comu=   ((Comunidad)jC_comunidades.getSelectedItem()).getCodigo();
             cargaComboProv(jC_provincias , comu);
            }
        });
  
        
        nombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e); 
                
                actualizaPueblos();
                /*
                 System.out.println("Se disparó ->keyReleased");
                ActionEvent ae = new ActionEvent(jC_provincias, ActionEvent.ACTION_PERFORMED, "Hola");
                
                jC_provincias.dispatchEvent(ae);*/
            }
        
        });
        
          jC_provincias.addActionListener((e) -> {
              actualizaPueblos();
            
        });
    }
   //----------------------------------------
    void cargaComboComu(JComboBox combo){
        try {
            combo.removeAllItems();
            
            Statement st = conn.createStatement();
            ResultSet  rst;
            
            rst= st.executeQuery("select * from comunidades");
            
            while(rst.next()){
                combo.addItem(
                        new Comunidad(rst.getString("nombre"),
                                      rst.getString("codigo")));
            }
            rst.close();
            
        } catch (SQLException ex) {
            System.out.println("Error-> "+ex.getMessage());
        }
    }
    //---------------------------------------------------
   
    void cargaComboProv(JComboBox combo, String comu){
        try {
           //combo.removeAllItems();
            combo.setModel(new DefaultComboBoxModel());
            
            Statement st = conn.createStatement();
            ResultSet  rst;
            
            rst= st.executeQuery("select * from provincias "
                               + "where comunidad='"+comu+"' "
                               + "order by provincia");
            
            while(rst.next()){
                combo.addItem(
                        new Provincia(rst.getString("comunidad"),
                                      rst.getString("provincia"),
                                      rst.getInt("idprovincia")
                         ));
            }
            rst.close();
            
        } catch (SQLException ex) {
            System.out.println("Error-> "+ex.getMessage());
        }
    }
    //------------------------------------------------------------
    JTable cargaJTable(String consulta, JScrollPane scroll){
         DefaultTableModel dtm =null;
         JTable tabla = null; 
        try {
            ResultSet rst=conn.createStatement().executeQuery(consulta);
            
            ResultSetMetaData rstMd = rst.getMetaData();
            
            int numeroColumnas = rstMd.getColumnCount();
            Object[] etiquetas = new Object[numeroColumnas];
            
            for (int i = 1; i <= numeroColumnas; i++) {
                etiquetas[i-1]=rstMd.getColumnLabel(i);
            }
            
            dtm = new DefaultTableModel(etiquetas, 0);
            
            while( rst.next()){
                for (int i = 0; i < numeroColumnas; i++) 
                    etiquetas[i]=rst.getObject(i+1);
                         
               dtm.addRow(etiquetas);
            }
            
            tabla = new JTable(dtm);
            
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(dtm);
            tabla.setRowSorter(elQueOrdena);
            
            scroll.setViewportView(tabla);
            
            
        } catch (SQLException ex) {
            System.out.println("Error Sql ->" +ex.getMessage());
        }
     return tabla;   
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jC_comunidades = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jC_provincias = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 754, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Pueblos.....", jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Comunidades-> ");
        jPanel3.add(jLabel1);

        jC_comunidades.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(jC_comunidades);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Provincias-> ");
        jPanel3.add(jLabel2);

        jC_provincias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(jC_provincias);

        jLabel3.setText("Cuyo nombre contenga->");
        jPanel3.add(jLabel3);

        nombre.setColumns(20);
        jPanel3.add(nombre);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);
        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Pueblos Por Provincia", jPanel2);

        jScrollPane2.setViewportView(jEditorPane1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Elecciones Autonómicas", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void  actualizaPueblos(){
           int prov=   ((Provincia)jC_provincias.getSelectedItem()).getIdprovincia();
            //cargaJTable("select * from poblaciones where idprovincia="+prov+"", jScrollPane1);
            TableRowSorter trs=(TableRowSorter) pueblos.getRowSorter();
            
            RowFilter filtroProv         =RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL,prov, 1);
            RowFilter filtroNombrePueblo =RowFilter.regexFilter(nombre.getText(), 2);
            
            LinkedList lista = new LinkedList();
            lista.add(filtroProv);
            lista.add(filtroNombrePueblo);

            RowFilter filtroAnd = RowFilter.andFilter(lista);
            
            trs.setRowFilter(filtroAnd);
           // RowFilter.numberFilter(ComparisionType.EQUAL, 10, 0);
            pueblos.setRowSorter(trs);
          
      }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Comunidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Comunidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Comunidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Comunidades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Comunidades().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jC_comunidades;
    private javax.swing.JComboBox jC_provincias;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField nombre;
    // End of variables declaration//GEN-END:variables
}
