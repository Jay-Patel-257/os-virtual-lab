/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;

/**
 *
 * @author dell
 */
public class About_Us extends javax.swing.JFrame {

    /**
     * Creates new form About_Us
     */
    public About_Us() {
    	getContentPane().setBackground(new Color(230, 230, 250));
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1022, 572));
        setPreferredSize(new java.awt.Dimension(1022, 572));
        
        JButton btnBack = new JButton("Back");
        btnBack.setBackground(new Color(0, 128, 128));
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Home a = new Home();
                a.setLocationRelativeTo(null);
                a.setVisible(true);
                dispose();
        	}
        	
        });
        
        JTextArea Team17 = new JTextArea();
        Team17.setEditable(false);
        Team17.setFont(new Font("Tahoma", Font.PLAIN, 26));
        Team17.setText("                      Team-17\r\n              (Page Replacement)\r\n\r\n-->Vainavi Gangvekar\r\n     vainavi.gce19@sot.pdpu.ac.in\r\n-->Tanvi Modi\r\n     tanvi.mce19@sot.pdpu.ac.in\r\n-->Ritu Banker\r\n     ritu.bce19@sot.pdpu.ac.in\r\n-->Nikita Kubavat\r\n     nikita.kce19@sot.pdpu.ac.in\r\n-->Zeel Sonara\r\n     zeel.sce19@sot.pdpu.ac.in");
        Team17.setBackground(new Color(0, 128, 128));
        
        JTextArea txtrTeamconucurrencyAnd = new JTextArea();
        txtrTeamconucurrencyAnd.setEditable(false);
        txtrTeamconucurrencyAnd.setText("                      Team-18\r\n        (Concurrency and Deadlock)\r\n\r\n-->Jay Patel\r\n      jay.pce19@sot.pdpu.ac.in\r\n-->Nikhil Jain\r\n     nikhil.jce19@sot.pdpu.ac.in\r\n-->Devnani Chirag\r\n     devnani.rce19@sot.pdpu.ac.in\r\n-->Kunjan Vaghasiya\r\n     kunjan.vce19@sot.pdpu.ac.in\r\n-->Kalpita Gadhe\r\n     kalpitaben.gce19@sot.pdpu.ac.in");
        txtrTeamconucurrencyAnd.setFont(new Font("Tahoma", Font.PLAIN, 26));
        txtrTeamconucurrencyAnd.setBackground(new Color(0, 128, 128));
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(20)
        					.addComponent(Team17, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
        					.addComponent(txtrTeamconucurrencyAnd, GroupLayout.PREFERRED_SIZE, 461, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap(931, Short.MAX_VALUE)
        					.addComponent(btnBack)))
        			.addGap(33))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(21)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(Team17, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        				.addComponent(txtrTeamconucurrencyAnd, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addComponent(btnBack)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(About_Us.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(About_Us.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(About_Us.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(About_Us.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new About_Us().setVisible(true);
            }
        });
    }
}