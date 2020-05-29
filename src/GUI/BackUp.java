/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class BackUp extends javax.swing.JFrame {

    /**
     * Creates new form BackUp
     */
    Statement st;
    ResultSet rs;
    
    public BackUp() {
        initComponents();
        
    }
    
    
    private void createBackupDirectory()
    {
        try 
        {
            File myDir = new File("C:\\SoftwareBackup");
            
            if (myDir.exists() == false) 
            {
                myDir.mkdir();
                JOptionPane.showMessageDialog(this, "Backup directory created successfully.");
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Backup directory already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                
            }

        } 
        catch (Exception e) 
        {
        }
    }
    
    //method to save Backup File
    //Return type-void
    //Modifier-private        
    private void saveBackupFile()
    {
        try 
        {
            String sql1 = "C:/Program Files/MySQL/MySQL Server 5.5/bin/mysqldump -uroot -p12345 oop -r C:\\SoftwareBackup\\Backup.sql";
            
            
            Process runtimeProcess = Runtime.getRuntime().exec(sql1);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 1) 
            {
                
                JOptionPane.showMessageDialog(null, "Backup creation unsuccessful.", "Error", JOptionPane.ERROR_MESSAGE);
                
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Backup saved successfully.");

            }
        } 
        catch (Exception ex) 
        {
          
        }
    }
    
    //method to restore Backup
    //Return type-void
    //Modifier-private  
    public void restoreBackup()
    {
        try 
        {
            String db = "oop";
            String us = "root";
            String pw = "12345";
            //            String pathrestore=jTextField2.getText();
            int processComplete;
            //            JOptionPane.showMessageDialog(null,Path.proPath );
            String[] executeCmd = new String[]{"C:/Program Files/MySQL/MySQL Server 5.5/bin/mysql", db, "-u" + us, "-p" + pw, "-e", " source C:\\SoftwareBackup\\Backup.sql"};
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            processComplete = runtimeProcess.waitFor();
            if (processComplete == 1) 
            {
                JOptionPane.showMessageDialog(null, "Backup restoration unsuccessful.", "Error", JOptionPane.ERROR_MESSAGE);
                
            } 
            else if (processComplete == 0) 
            {
                JOptionPane.showMessageDialog(this, "Backup restored successfully.");
            }
            
        } 
        catch (Exception ex) 
        {
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        LblTime1 = new javax.swing.JLabel();
        lblDate1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnCreateBackupDirectory = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnSaveBackupFile = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnRestoreBackup = new javax.swing.JButton();
        lblCreateShortcut = new javax.swing.JLabel();
        lblSaveShortcut = new javax.swing.JLabel();
        lblRestoreShortcut = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblTime1.setBackground(new java.awt.Color(102, 102, 0));
        LblTime1.setFont(new java.awt.Font("SketchFlow Print", 1, 14)); // NOI18N
        LblTime1.setForeground(new java.awt.Color(102, 102, 0));

        lblDate1.setBackground(new java.awt.Color(102, 102, 0));
        lblDate1.setFont(new java.awt.Font("SketchFlow Print", 1, 14)); // NOI18N
        lblDate1.setForeground(new java.awt.Color(102, 102, 0));

        jLabel7.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 0));
        jLabel7.setText("Syntax Educational Institute");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(LblTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(lblDate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblTime1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("BackUp"));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        btnCreateBackupDirectory.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btnCreateBackupDirectory.setText("OK");
        btnCreateBackupDirectory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCreateBackupDirectoryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCreateBackupDirectoryMouseExited(evt);
            }
        });
        btnCreateBackupDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateBackupDirectoryActionPerformed(evt);
            }
        });
        btnCreateBackupDirectory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCreateBackupDirectoryKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Step 1:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel5.setText("Create backup directory(Path=\"C:\\\\tSoftwareBackup\")");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Step 2:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel9.setText("Save backup file inside backup directory(File name=\"Backup.sql\")");

        btnSaveBackupFile.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btnSaveBackupFile.setText("OK");
        btnSaveBackupFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveBackupFileMouseEntered(evt);
            }
        });
        btnSaveBackupFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveBackupFileActionPerformed(evt);
            }
        });
        btnSaveBackupFile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSaveBackupFileKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Step 3:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel11.setText("Restore backup");

        btnRestoreBackup.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btnRestoreBackup.setText("OK");
        btnRestoreBackup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRestoreBackupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRestoreBackupMouseExited(evt);
            }
        });
        btnRestoreBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreBackupActionPerformed(evt);
            }
        });
        btnRestoreBackup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnRestoreBackupKeyPressed(evt);
            }
        });

        lblCreateShortcut.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N

        lblSaveShortcut.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N

        lblRestoreShortcut.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRestoreBackup)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRestoreShortcut, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSaveBackupFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSaveShortcut, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addComponent(btnCreateBackupDirectory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCreateShortcut, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(86, 86, 86))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel4)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(btnCreateBackupDirectory))
                    .addComponent(lblCreateShortcut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jLabel8)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(btnSaveBackupFile))
                    .addComponent(lblSaveShortcut, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jLabel10)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(btnRestoreBackup))
                    .addComponent(lblRestoreShortcut, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateBackupDirectoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateBackupDirectoryMouseEntered
        // TODO add your handling code here
        lblCreateShortcut.setText("Ctrl+C");
    }//GEN-LAST:event_btnCreateBackupDirectoryMouseEntered

    private void btnCreateBackupDirectoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateBackupDirectoryMouseExited
        // TODO add your handling code here:
        lblCreateShortcut.setText("");
    }//GEN-LAST:event_btnCreateBackupDirectoryMouseExited

    private void btnCreateBackupDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateBackupDirectoryActionPerformed
        // TODO add your handling code here:
        lblCreateShortcut.setText("");
        createBackupDirectory();
    }//GEN-LAST:event_btnCreateBackupDirectoryActionPerformed

    private void btnCreateBackupDirectoryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCreateBackupDirectoryKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnCreateBackupDirectoryKeyPressed

    private void btnSaveBackupFileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveBackupFileMouseEntered
        // TODO add your handling code here:
        lblSaveShortcut.setText("Ctrl+S");
    }//GEN-LAST:event_btnSaveBackupFileMouseEntered

    private void btnSaveBackupFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveBackupFileActionPerformed
        // TODO add your handling code here:
        lblSaveShortcut.setText("");
        saveBackupFile();
    }//GEN-LAST:event_btnSaveBackupFileActionPerformed

    private void btnSaveBackupFileKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSaveBackupFileKeyPressed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_btnSaveBackupFileKeyPressed

    private void btnRestoreBackupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRestoreBackupMouseEntered
        // TODO add your handling code here:
        lblRestoreShortcut.setText("Ctrl+R");
    }//GEN-LAST:event_btnRestoreBackupMouseEntered

    private void btnRestoreBackupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRestoreBackupMouseExited
        // TODO add your handling code here:
        lblRestoreShortcut.setText("");
    }//GEN-LAST:event_btnRestoreBackupMouseExited

    private void btnRestoreBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreBackupActionPerformed
        // TODO add your handling code here:
        lblRestoreShortcut.setText("");
        restoreBackup();
    }//GEN-LAST:event_btnRestoreBackupActionPerformed

    private void btnRestoreBackupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnRestoreBackupKeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_btnRestoreBackupKeyPressed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
        // TODO add your handling code here:
       
       
        
    }//GEN-LAST:event_jPanel1KeyPressed

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
            java.util.logging.Logger.getLogger(BackUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BackUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BackUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BackUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BackUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblTime;
    private javax.swing.JLabel LblTime1;
    private javax.swing.JButton btnCreateBackupDirectory;
    private javax.swing.JButton btnRestoreBackup;
    private javax.swing.JButton btnSaveBackupFile;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCreateShortcut;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDate1;
    private javax.swing.JLabel lblRestoreShortcut;
    private javax.swing.JLabel lblSaveShortcut;
    // End of variables declaration//GEN-END:variables
}
