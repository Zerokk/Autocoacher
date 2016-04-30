/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import utilities.DB;
import utilities.StatusCellRenderer;
import datasets.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javax.swing.BoundedRangeModel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author hp
 */
public class CoachingFrame extends javax.swing.JFrame {

    ArrayList<Registry> regs = new ArrayList<>();
    AdvicesManager am;
    DB db = new DB();
    JPanel panel = new JPanel();
    DefaultTableModel modelo;
    int clickedTab;

    public CoachingFrame() {
        initComponents();

    }

    public CoachingFrame(AdvicesManager am) {
        initComponents();
        this.am = am;
        mytopic.setText("   Topic: " + am.topic.getName());
        setTitle("Autocoacher");
        renderQuestions();

        actualizar();

        satisfaccion.removeAllItems();
        for (int i = 0; i < am.topic.getSatisfactionLevels().length; i++) {
            satisfaccion.addItem(am.topic.getSatisfactionLevels()[i]);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mytopic = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        questions = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        title = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        coachMeBtn = new javax.swing.JButton();
        save = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        satisfaccion = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        mytopic.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        mytopic.setText("     Coaching zone for topic:");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        questions.setBackground(java.awt.SystemColor.controlHighlight);

        descripcion.setColumns(20);
        descripcion.setLineWrap(true);
        descripcion.setRows(5);
        jScrollPane3.setViewportView(descripcion);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Description (Optional):");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Questions:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Satisfaction level:");

        output.setEditable(false);
        output.setColumns(20);
        output.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        output.setLineWrap(true);
        output.setRows(5);
        jScrollPane4.setViewportView(output);

        coachMeBtn.setBackground(new java.awt.Color(153, 153, 255));
        coachMeBtn.setText("Coach me!");
        coachMeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coachMeBtnActionPerformed(evt);
            }
        });

        save.setBackground(new java.awt.Color(102, 255, 0));
        save.setText("Save registry");
        save.setEnabled(false);
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tips generated:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Title:");

        satisfaccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(373, 373, 373))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mytopic, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(questions, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(satisfaccion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(coachMeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(save))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mytopic, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(satisfaccion))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(questions, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(coachMeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void renderQuestions() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (int i = 0; i < am.advices.size(); i++) {
            panel.add(new JLabel(" "));
            JLabel label = new JLabel(am.advices.get(i).getQuestion());
            label.setSize(200, 40);
            panel.add(label);

            if (am.advices.get(i) instanceof AdviceFromValue) {
                AdviceFromValue adv = (AdviceFromValue) am.advices.get(i);
                JTextField input = new JTextField();
                input.setText("0");
                JPanel centeringPanel = new JPanel();
                input.setPreferredSize(new Dimension(100, 30));
                input.setMaximumSize(new Dimension(100, 30));
                centeringPanel.add(new JLabel());
                centeringPanel.add(input);
                panel.add(centeringPanel);

            } else {

                ButtonGroup group = new ButtonGroup();
                JPanel buttonsPanel = new JPanel();
                buttonsPanel.setLayout(new FlowLayout());
                JLabel lab1 = new JLabel("Yes");
                JLabel lab2 = new JLabel("No");
                JRadioButton rad1 = new JRadioButton();
                JRadioButton rad2 = new JRadioButton();
                group.add(rad1);
                group.add(rad2);
                buttonsPanel.add(lab1);
                buttonsPanel.add(rad1);
                buttonsPanel.add(lab2);
                buttonsPanel.add(rad2);
                panel.add(buttonsPanel);
            }

            panel.revalidate();
        }

        questions.setViewportView(panel);
        questions.revalidate();
    }

    public void actualizar() {

        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"Satisfacción", "Registro"});
        tabla.setModel(modelo);
        ResultSet rs = db.getRegistrosForTopic(am.topic.getId());
        try {
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString("success"), rs.getString("title")});
                regs.add(new Registry(rs.getInt("topic_id"), rs.getInt("registry_num"), rs.getString("title"), rs.getString("success"), rs.getString("description"), rs.getString("tips")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoachingFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Act");
        tabla.getColumnModel().getColumn(0).setCellRenderer(new StatusCellRenderer(am.topic));
    }


    private void coachMeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coachMeBtnActionPerformed
        Component[] comps = panel.getComponents();
        Component[] innerComps;
        int listIndex = 0;

        for (int i = 1; i < comps.length; i++) {
            if (comps[i] instanceof JPanel) {
                innerComps = ((JPanel) comps[i]).getComponents();
                if (innerComps[1] instanceof JRadioButton) {
                    if (((JRadioButton) innerComps[1]).isSelected()) {
                        am.advices.get(listIndex).setActive(true);
                        listIndex++;
                    } else {
                        am.advices.get(listIndex).setActive(false);
                        listIndex++;
                    }
                } else if (innerComps[1] instanceof JTextField) {

                    AdviceFromValue adv = (AdviceFromValue) am.advices.get(listIndex);
                    try {
                        float value = Float.valueOf(((JTextField) innerComps[1]).getText());
                        adv.updateTip(value);

                        listIndex++;

                    } catch (NumberFormatException excp) {
                        JOptionPane.showMessageDialog(this, "Unable to compare all data. Only use numbers in the data section!");
                    }
                }
            }
        } // For

        am.checkAdvices();
        am.checkMultiAdvices();
        output.setText(am.outputAdvices());
        save.setEnabled(true);
    }//GEN-LAST:event_coachMeBtnActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed

        if (!title.getText().equals("")) {

            Registry reg = new Registry(am.topic.getId(), (String) satisfaccion.getSelectedItem(), descripcion.getText(), output.getText());
            reg.setTitle(title.getText());
            db.insertRegistry(reg);
            regs.add(reg);
            actualizar();

            output.setText("");
            descripcion.setText("");
            title.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "You have to write a title!");
        }
    }//GEN-LAST:event_saveActionPerformed

    private void tablaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMousePressed
        if (tabla.getSelectedRow() != clickedTab) {
            clickedTab = tabla.getSelectedRow();
        } else {
            Registry reg = regs.get(clickedTab);
            System.out.println("Reg title: " + reg.getTitle());

            RegistryDialog diag = new RegistryDialog(this, false, reg);
            diag.setVisible(true);
        }
    }//GEN-LAST:event_tablaMousePressed

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
            java.util.logging.Logger.getLogger(CoachingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CoachingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CoachingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CoachingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CoachingFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton coachMeBtn;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel mytopic;
    private javax.swing.JTextArea output;
    private javax.swing.JScrollPane questions;
    private javax.swing.JComboBox<String> satisfaccion;
    private javax.swing.JButton save;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField title;
    // End of variables declaration//GEN-END:variables
}
