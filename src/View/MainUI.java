package View;

import Model.PeerNode;
import Model.RMIClientServer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author mcnabba
 */
public class MainUI extends javax.swing.JFrame {
    
    private RMIClientServer localCS;
//    private PeerServer server;
    private PeerNode node;

    /**
     * Creates new form MainUI
     * @param p
     */
    public MainUI() {
        initComponents();
        this.setSize(800, 800);
//        PeerNode node = new PeerNode();
      
    }
    
    public void setNode(PeerNode node)  {
        this.node = node;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPnl = new javax.swing.JPanel();
        toolbarPnl = new javax.swing.JPanel();
        startBtn = new javax.swing.JButton();
        pauseBtn = new javax.swing.JButton();
        stopBtn = new javax.swing.JButton();
        filelistPnl = new javax.swing.JPanel();
        lineSep = new javax.swing.JSeparator();
        menuBar = new javax.swing.JMenuBar();
        fileItm = new javax.swing.JMenu();
        connectItm = new javax.swing.JMenuItem();
        exitItm = new javax.swing.JMenuItem();
        editItem = new javax.swing.JMenu();
        settingsItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        startBtn.setText("Start");

        pauseBtn.setText("Pause");

        stopBtn.setText("Stop");

        javax.swing.GroupLayout toolbarPnlLayout = new javax.swing.GroupLayout(toolbarPnl);
        toolbarPnl.setLayout(toolbarPnlLayout);
        toolbarPnlLayout.setHorizontalGroup(
            toolbarPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolbarPnlLayout.createSequentialGroup()
                .addComponent(startBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pauseBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stopBtn)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        toolbarPnlLayout.setVerticalGroup(
            toolbarPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, toolbarPnlLayout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(toolbarPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startBtn)
                    .addComponent(pauseBtn)
                    .addComponent(stopBtn)))
        );

        javax.swing.GroupLayout filelistPnlLayout = new javax.swing.GroupLayout(filelistPnl);
        filelistPnl.setLayout(filelistPnlLayout);
        filelistPnlLayout.setHorizontalGroup(
            filelistPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        filelistPnlLayout.setVerticalGroup(
            filelistPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainPnlLayout = new javax.swing.GroupLayout(mainPnl);
        mainPnl.setLayout(mainPnlLayout);
        mainPnlLayout.setHorizontalGroup(
            mainPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(filelistPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lineSep, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(toolbarPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainPnlLayout.setVerticalGroup(
            mainPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPnlLayout.createSequentialGroup()
                .addComponent(toolbarPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineSep, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filelistPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(mainPnl, java.awt.BorderLayout.CENTER);

        fileItm.setText("File");

        connectItm.setText("Connect...");
        connectItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectItmActionPerformed(evt);
            }
        });
        fileItm.add(connectItm);

        exitItm.setText("Exit");
        exitItm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitItmActionPerformed(evt);
            }
        });
        fileItm.add(exitItm);

        menuBar.add(fileItm);

        editItem.setText("Edit");

        settingsItem.setText("Settings");
        settingsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsItemActionPerformed(evt);
            }
        });
        editItem.add(settingsItem);

        menuBar.add(editItem);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitItmActionPerformed
        dispose();
    }//GEN-LAST:event_exitItmActionPerformed

    private void connectItmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectItmActionPerformed
        
    }//GEN-LAST:event_connectItmActionPerformed

    private void startBtnActionPerformed(java.awt.event.ActionEvent evt) {
        node.start();
       
    }
    
    private void stopBtnActionPerformed(java.awt.event.ActionEvent evt) {
//        server.stop();
        node.stop();
    }
    
    private void settingsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsItemActionPerformed
        SettingsUI settings = new SettingsUI(this, false);
        settings.setVisible(true);
    }//GEN-LAST:event_settingsItemActionPerformed

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
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem connectItm;
    private javax.swing.JMenu editItem;
    private javax.swing.JMenuItem exitItm;
    private javax.swing.JMenu fileItm;
    private javax.swing.JPanel filelistPnl;
    private javax.swing.JButton startBtn;
    private javax.swing.JButton pauseBtn;
    private javax.swing.JButton stopBtn;
    private javax.swing.JSeparator lineSep;
    private javax.swing.JPanel mainPnl;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem settingsItem;
    private javax.swing.JPanel toolbarPnl;
    // End of variables declaration//GEN-END:variables
}
