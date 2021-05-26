package regexp;

import java.awt.Color;
import javax.swing.JFrame;

public class RegExp extends JFrame {
    public RegExp() {
        inicioPrograma();
    }                 
    private void inicioPrograma() {
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jCheckBox4 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();

        jLabel1.setText("jLabel1");

        jTextField2.setText("[A-Z][0-9]{3,}");
        jTextField2.setToolTipText("[A-Z][0-9]{3,}");
        jTextField2.addActionListener((java.awt.event.ActionEvent evt) -> {
            jTextField2ActionPerformed(evt);
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Flags");

        jTextField1.setForeground(new java.awt.Color(0, 51, 255));
        jTextField1.setText("[A-Z][0-9]{3,}");
        jTextField1.setToolTipText("[A-Z][0-9]{3,}");
        jTextField1.addActionListener((java.awt.event.ActionEvent evt) -> {
            jTextField1ActionPerformed(evt);
        });

        jTextField3.setForeground(new java.awt.Color(0, 0, 255));
        jTextField3.setText("\\m");
        jTextField3.setToolTipText("\\m");
        jTextField3.addActionListener((java.awt.event.ActionEvent evt) -> {
            jTextField3ActionPerformed(evt);
        });

        jCheckBox1.setForeground(new java.awt.Color(0, 153, 51));
        jCheckBox1.setText("global");
        jCheckBox1.addActionListener((java.awt.event.ActionEvent evt) -> {
            jCheckBox1ActionPerformed(evt,estadoCheck1);
        });

        jCheckBox2.setForeground(new java.awt.Color(0, 153, 51));
        jCheckBox2.setText("ignore case");
        jCheckBox2.addActionListener((java.awt.event.ActionEvent evt) -> {
            jCheckBox2ActionPerformed(evt,estadoCheck2);
        });

        jCheckBox3.setForeground(new java.awt.Color(0, 153, 51));
        jCheckBox3.setText("multiline");
        jCheckBox3.addActionListener((java.awt.event.ActionEvent evt) -> {
            jCheckBox3ActionPerformed(evt,estadoCheck3);
        });

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Regular expresion");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Test string"));

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Match ?");

        jTextField4.setForeground(new java.awt.Color(0, 0, 255));
        jTextField4.setText("Match");
        jTextField4.addActionListener((java.awt.event.ActionEvent evt) -> {
            jTextField4ActionPerformed(evt);
        });

        jCheckBox4.setForeground(new java.awt.Color(0, 153, 51));
        jCheckBox4.setText("AutoCheck");
        jCheckBox4.addActionListener((java.awt.event.ActionEvent evt) -> {
            jCheckBox4ActionPerformed(evt,estadoCheck4);
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox4))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jButton1.setText("ComProbar...");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jButton1FocusGained(evt);
            }
        });
        jButton1.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton1ActionPerformed(evt);
        });

        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Info");
        
        jScrollPane2.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jScrollPane2FocusGained(evt);
            }
        });
        jCheckBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCheckBox1FocusGained(evt);
            }
        });
        jCheckBox2.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCheckBox2FocusGained(evt);
            }
        });
        jCheckBox3.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCheckBox3FocusGained(evt);
            }
        });
        jCheckBox4.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jCheckBox4FocusGained(evt);
            }
        });
        
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });
                
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField3FocusGained(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(271, 271, 271))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox2)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox3)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }                      
    
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt,boolean x) {                                           
        estadoCheck1=!x;
    }                                          

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt,boolean x) {                                           
        estadoCheck2=!x;
    }                                          

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt,boolean x) {                                           
        estadoCheck3=!x;
    }                                          

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt,boolean x) {                                           
        estadoCheck4=!x;
        if (estadoCheck4) {
            expresion=jTextField1.getText();
            flags=jTextField3.getText();
            texarea=jTextArea1.getText();
            info=jScrollPane2.getToolTipText();
            boolean correcto;
            if (!estadoCheck3) {
                if (estadoCheck2) {
                    correcto = texarea.matches("\\"+flags+expresion.toLowerCase()+" | "+expresion.toUpperCase());
                } else {
                    correcto = texarea.matches("\\"+flags+expresion);
                }
                if (correcto) {
                    jTextField4.setForeground(Color.green);
                } else {
                    jTextField4.setText("NO Macth");
                    jTextField4.setForeground(Color.red);
                } 
            } else {
                boolean newline=false;
                int i;
                int cntLineas=0;
                int j=texarea.length();
                do {
                    for(i=0;i==texarea.length();i++){
                        char readC=texarea.charAt(i);
                        String x1=""+readC;
                        if (x1.equals("\\n")) {
                            newline=true;
                            j=i;
                            i=texarea.length();
                        }
                    }
                } while(newline!=true);
                String lineacomprobar="";
                if (newline) {
                    for(int i1=0;i1==j;i1++){
                        char readC1=texarea.charAt(i);
                        lineacomprobar=lineacomprobar+readC1;
                    }
                }
                if (estadoCheck2) {
                    correcto = lineacomprobar.matches("\\"+flags+expresion.toLowerCase()+" | "+expresion.toUpperCase());
                } else {
                    correcto = lineacomprobar.matches("\\"+flags+expresion);
                }
                if (correcto) {
                    cntLineas++;
                    jTextField4.setText(cntLineas+" Macth");
                    jTextField4.setForeground(Color.green);
                } else {
                    jTextField4.setText("NO Macth");
                    jTextField4.setForeground(Color.red);
                } 
            }
        }
    }                                          

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        expresion=jTextField1.getText();
        flags=jTextField3.getText();
        texarea=jTextArea1.getText();
        info=jScrollPane2.getToolTipText();
        boolean correcto;
        if (!estadoCheck3) {
            if (estadoCheck2) {
                correcto = texarea.matches("\\"+flags+expresion.toLowerCase()+" | "+expresion.toUpperCase());
            } else {
                correcto = texarea.matches("\\"+flags+expresion);
            }
            if (correcto) {
                jTextField4.setText("Macth");
                jTextField4.setForeground(Color.green);
            } else {
                jTextField4.setText("NO Macth");
                jTextField4.setForeground(Color.red);
            } 
        } else {
            boolean newline=false;
            int i;
            int cntLineas=0;
            int j=texarea.length();
            do {
                for(i=0;i==texarea.length();i++){
                    char readC=texarea.charAt(i);
                    String x=""+readC;
                    if (x.equals("\\n")) {
                        newline=true;
                        j=i;
                        i=texarea.length();
                    }
                }
            } while(newline!=true);
            String lineacomprobar="";
            if (newline) {
                for(int i1=0;i1==j;i1++){
                    char readC1=texarea.charAt(i);
                    lineacomprobar=lineacomprobar+readC1;
                }
            }
            if (estadoCheck2) {
                correcto = lineacomprobar.matches("\\"+flags+expresion.toLowerCase()+" | "+expresion.toUpperCase());
            } else {
                correcto = lineacomprobar.matches("\\"+flags+expresion);
            }
            if (correcto) {
                cntLineas++;
                jTextField4.setText(cntLineas+" Macth");
                jTextField4.setForeground(Color.green);
            } else {
                jTextField4.setText("NO Macth");
                jTextField4.setForeground(Color.red);
            } 
        }
    }
    
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {                                      
        System.out.println("TEST ACTIONEVENT");
    }
    
    //INFO
    private void jCheckBox1FocusGained(java.awt.event.FocusEvent evt) {                                       
        jScrollPane2.setToolTipText("INFO CHECKBOX1");
    }  
    private void jCheckBox2FocusGained(java.awt.event.FocusEvent evt) {                                       
        jScrollPane2.setToolTipText("INFO CHECKBOX2");
    }  
    private void jCheckBox3FocusGained(java.awt.event.FocusEvent evt) {                                       
        jScrollPane2.setToolTipText("INFO CHECKBOX3");
    }  
    private void jCheckBox4FocusGained(java.awt.event.FocusEvent evt) {                                       
        jScrollPane2.setToolTipText("INFO CHECKBOX4");
    }
    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {                                        
        jScrollPane2.setToolTipText("INFO TEXT FIELD 1");
    } 
    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {                                        
        jScrollPane2.setToolTipText("INFO TEXT FIELD 2");
    }
    private void jButton1FocusGained(java.awt.event.FocusEvent evt) {                                     
        jScrollPane2.setToolTipText("INFO BOTON 1");
    }
    private void jScrollPane2FocusGained(java.awt.event.FocusEvent evt) {                                         
        jScrollPane2.setToolTipText("INFO SCROLL PANEL");
    }
    String expresion,flags,texarea,info;
    boolean estadoCheck1 = false;
    boolean estadoCheck2 = false;
    boolean estadoCheck3 = false;
    boolean estadoCheck4 = false;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    public static void main(String args[]) {
        String diseño="Metal";
        RegExp regExp = new RegExp();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (diseño.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegExp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new RegExp().setVisible(true);
        });
    }
}
