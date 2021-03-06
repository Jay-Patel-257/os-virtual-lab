package pagereplacement;
import home.Home;
import java.awt.Dimension;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Point;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
/**
 *
 * @author Tanvi Modi
 */
public class index extends javax.swing.JFrame {
	
    /**
     * Creates new form index
     */
    public index() {
    	setPreferredSize(new Dimension(1022, 572));
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


        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlLockMini = new javax.swing.JPanel();
        pnlLockMini.setBackground(new Color(192, 192, 192));
        pnlLockMini.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(0, 0, 0)));
        jLabel2 = new javax.swing.JLabel();
        btnInfoFifo = new javax.swing.JButton();
        btnInfoFifo.setBackground(new Color(0, 128, 128));
        pnlCountMini = new javax.swing.JPanel();
        pnlCountMini.setBackground(new Color(192, 192, 192));
        pnlCountMini.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(0, 0, 0)));
        jLabel4 = new javax.swing.JLabel();
        btnInfoRandom = new javax.swing.JButton();
        btnInfoRandom.setBackground(new Color(0, 128, 128));
        btnInfoRandom.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		  RandomInfo a = new   RandomInfo();
        	        a.setLocationRelativeTo(null);
        	        a.setVisible(true);
        	        dispose();
        	}
        });
        
        
        pnlBinaryMini = new javax.swing.JPanel();
        pnlBinaryMini.setBackground(new Color(192, 192, 192));
        pnlBinaryMini.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(0, 0, 0)));
        jLabel5 = new javax.swing.JLabel();
        btnInfoLifo = new javax.swing.JButton();
        btnInfoLifo.setBackground(new Color(0, 128, 128));
        btnInfoLifo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		  LifoInfo a = new LifoInfo();
        	        a.setLocationRelativeTo(null);
        	        a.setVisible(true);
        	        dispose();
        	}
        });
        pnlInterestMini = new javax.swing.JPanel();
        pnlInterestMini.setBackground(new Color(192, 192, 192));
        pnlInterestMini.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(0, 0, 0)));
        jLabel6 = new javax.swing.JLabel();
        btnInfoOpr = new javax.swing.JButton();
        btnInfoOpr.setBackground(new Color(0, 128, 128));
        pnlTestSetMini = new javax.swing.JPanel();
        pnlTestSetMini.setBackground(new Color(192, 192, 192));
        pnlTestSetMini.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(0, 0, 0)));
        jLabel11 = new javax.swing.JLabel();
        btnInfoLru = new javax.swing.JButton();
        btnInfoLru.setBackground(new Color(0, 128, 128));
        btnBack = new javax.swing.JButton();
        btnBack.setBackground(new Color(0, 128, 128));
        jPanel2 = new javax.swing.JPanel();
        jPanel2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 128, 128), null, new Color(0, 128, 128), null));
        this.pack();
        this.setVisible(true);
        Whybtn = new javax.swing.JButton();
        Whybtn.setText("WHY");
        Whybtn.setBorder(new MatteBorder(5, 5, 5, 4, (Color) new Color(0, 0, 0)));
        Whybtn.setLocation(new Point(5, 5));
        Whybtn.setIconTextGap(0);
        Whybtn.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 30));
        Whatbtn = new javax.swing.JButton();
        Whatbtn.setText("WHAT");
        Whatbtn.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(0, 0, 0)));
        Whatbtn.setLocation(new Point(5, 5));
        Whatbtn.setIconTextGap(0);
        Whatbtn.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 30));
        Wherebtn = new javax.swing.JButton();
        Wherebtn.setText("WHERE");
        Wherebtn.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(0, 0, 0)));
        Wherebtn.setIconTextGap(0);
        Wherebtn.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 30));
        AlgoBtn = new javax.swing.JButton();
        AlgoBtn.setBackground(new Color(0, 128, 128));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1022, 572));
        setResizable(false);

        jPanel1.setBackground(UIManager.getColor("ComboBox.buttonShadow"));

        jLabel1.setBackground(new Color(0, 128, 128));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        jLabel1.setForeground(new Color(230, 230, 250));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PAGE REPLACEMENT ");
        jLabel1.setOpaque(true);

        pnlLockMini.setMinimumSize(new java.awt.Dimension(238, 100));

        jLabel2.setBackground(new Color(0, 128, 128));
        jLabel2.setFont(new java.awt.Font("Verdana", 2, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("First-In-First-Out");
        jLabel2.setOpaque(true);

        btnInfoFifo.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        btnInfoFifo.setText("Information");
        btnInfoFifo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoFifoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLockMiniLayout = new javax.swing.GroupLayout(pnlLockMini);
        pnlLockMiniLayout.setHorizontalGroup(
        	pnlLockMiniLayout.createParallelGroup(Alignment.TRAILING)
        		.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        		.addGroup(Alignment.LEADING, pnlLockMiniLayout.createSequentialGroup()
        			.addGap(60)
        			.addComponent(btnInfoFifo)
        			.addContainerGap(59, Short.MAX_VALUE))
        );
        pnlLockMiniLayout.setVerticalGroup(
        	pnlLockMiniLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnlLockMiniLayout.createSequentialGroup()
        			.addComponent(jLabel2)
        			.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
        			.addComponent(btnInfoFifo))
        );
        pnlLockMini.setLayout(pnlLockMiniLayout);

        pnlCountMini.setMinimumSize(new java.awt.Dimension(238, 100));
        jLabel4.setBackground(new Color(0, 128, 128));
        jLabel4.setFont(new java.awt.Font("Verdana", 2, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Random Page Replacement");
        jLabel4.setOpaque(true);
        btnInfoRandom.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        btnInfoRandom.setText("Information");
        javax.swing.GroupLayout pnlCountMiniLayout = new javax.swing.GroupLayout(pnlCountMini);
        pnlCountMiniLayout.setHorizontalGroup(
        	pnlCountMiniLayout.createParallelGroup(Alignment.TRAILING)
        		.addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        		.addGroup(pnlCountMiniLayout.createSequentialGroup()
        			.addContainerGap(69, Short.MAX_VALUE)
        			.addComponent(btnInfoRandom)
        			.addGap(66))
        );
        pnlCountMiniLayout.setVerticalGroup(
        	pnlCountMiniLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnlCountMiniLayout.createSequentialGroup()
        			.addComponent(jLabel4)
        			.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
        			.addComponent(btnInfoRandom))
        );
        pnlCountMini.setLayout(pnlCountMiniLayout);

        pnlBinaryMini.setMinimumSize(new java.awt.Dimension(238, 100));
        jLabel5.setBackground(new Color(0, 128, 128));
        jLabel5.setFont(new java.awt.Font("Verdana", 2, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Last-In-First-Out");
        jLabel5.setOpaque(true);

        btnInfoLifo.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        btnInfoLifo.setText("Information");

        javax.swing.GroupLayout pnlBinaryMiniLayout = new javax.swing.GroupLayout(pnlBinaryMini);
        pnlBinaryMiniLayout.setHorizontalGroup(
        	pnlBinaryMiniLayout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        		.addGroup(pnlBinaryMiniLayout.createSequentialGroup()
        			.addGap(65)
        			.addComponent(btnInfoLifo)
        			.addContainerGap())
        );
        pnlBinaryMiniLayout.setVerticalGroup(
        	pnlBinaryMiniLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnlBinaryMiniLayout.createSequentialGroup()
        			.addComponent(jLabel5)
        			.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
        			.addComponent(btnInfoLifo))
        );
        pnlBinaryMini.setLayout(pnlBinaryMiniLayout);

        pnlInterestMini.setMinimumSize(new java.awt.Dimension(238, 100));

        jLabel6.setBackground(new Color(0, 128, 128));
        jLabel6.setFont(new java.awt.Font("Verdana", 2, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Optimal Page Replacement");
        jLabel6.setOpaque(true);

        btnInfoOpr.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        btnInfoOpr.setText("Information");
        btnInfoOpr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoOprActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInterestMiniLayout = new javax.swing.GroupLayout(pnlInterestMini);
        pnlInterestMiniLayout.setHorizontalGroup(
        	pnlInterestMiniLayout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jLabel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        		.addGroup(Alignment.TRAILING, pnlInterestMiniLayout.createSequentialGroup()
        			.addContainerGap(73, Short.MAX_VALUE)
        			.addComponent(btnInfoOpr)
        			.addGap(57))
        );
        pnlInterestMiniLayout.setVerticalGroup(
        	pnlInterestMiniLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnlInterestMiniLayout.createSequentialGroup()
        			.addComponent(jLabel6)
        			.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
        			.addComponent(btnInfoOpr))
        );
        pnlInterestMini.setLayout(pnlInterestMiniLayout);

        pnlTestSetMini.setMinimumSize(new java.awt.Dimension(238, 100));

        jLabel11.setBackground(new Color(0, 128, 128));
        jLabel11.setFont(new java.awt.Font("Verdana", 2, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Least Recently Used");
        jLabel11.setOpaque(true);

        btnInfoLru.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        btnInfoLru.setText("Information");
        btnInfoLru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoLruActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTestSetMiniLayout = new javax.swing.GroupLayout(pnlTestSetMini);
        pnlTestSetMiniLayout.setHorizontalGroup(
        	pnlTestSetMiniLayout.createParallelGroup(Alignment.TRAILING)
        		.addComponent(jLabel11, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        		.addGroup(pnlTestSetMiniLayout.createSequentialGroup()
        			.addContainerGap(66, Short.MAX_VALUE)
        			.addComponent(btnInfoLru)
        			.addGap(53))
        );
        pnlTestSetMiniLayout.setVerticalGroup(
        	pnlTestSetMiniLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnlTestSetMiniLayout.createSequentialGroup()
        			.addComponent(jLabel11)
        			.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
        			.addComponent(btnInfoLru))
        );
        pnlTestSetMini.setLayout(pnlTestSetMiniLayout);

        btnBack.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jPanel2.setBackground(Color.LIGHT_GRAY);

        Whybtn.setBackground(new Color(0, 128, 128));
        Whybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WhybtnActionPerformed(evt);
            }
        });

        Whatbtn.setBackground(new java.awt.Color(0, 153, 153));
        Whatbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WhatbtnActionPerformed(evt);
            }
        });

        Wherebtn.setBackground(new java.awt.Color(0, 153, 153));
        Wherebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WherebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2Layout.setHorizontalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addGap(77)
        			.addComponent(Whybtn, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
        			.addComponent(Whatbtn, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
        			.addGap(176)
        			.addComponent(Wherebtn, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
        			.addGap(83))
        );
        jPanel2Layout.setVerticalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addGap(43)
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(Whybtn, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
        				.addComponent(Wherebtn, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
        				.addComponent(Whatbtn, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2.setLayout(jPanel2Layout);

        AlgoBtn.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        AlgoBtn.setText("Algorithm");
        AlgoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlgoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(AlgoBtn, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 772, Short.MAX_VALUE)
        			.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(68)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(145)
        					.addComponent(pnlBinaryMini, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(54)
        					.addComponent(pnlCountMini, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(pnlLockMini, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(50)
        					.addComponent(pnlTestSetMini, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
        					.addComponent(pnlInterestMini, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(87, GroupLayout.PREFERRED_SIZE))
        		.addComponent(jPanel2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(18)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(pnlTestSetMini, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(pnlInterestMini, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(pnlLockMini, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(AlgoBtn)
        						.addComponent(btnBack))
        					.addContainerGap())
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(pnlCountMini, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(pnlBinaryMini, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(31))))
        );
        jPanel1.setLayout(jPanel1Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        Home a = new Home();
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void AlgoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlgoBtnActionPerformed
        // TODO add your handling code here:
        Algorithm a = new Algorithm();
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_AlgoBtnActionPerformed

    private void WhatbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WhatbtnActionPerformed
        // TODO add your handling code here:
        What a = new What();
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        dispose();
       
    }//GEN-LAST:event_WhatbtnActionPerformed

    private void btnInfoFifoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoFifoActionPerformed
        // TODO add your handling code here:
        FifoInfo a = new FifoInfo();
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        dispose();

    }//GEN-LAST:event_btnInfoFifoActionPerformed

    private void btnInfoOprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoOprActionPerformed
           // TODO add your handling code here:
        OprInfo a = new OprInfo();
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        dispose();

    }//GEN-LAST:event_btnInfoOprActionPerformed

    private void btnInfoLruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoLruActionPerformed
        // TODO add your handling code here:
        LruInfo a = new LruInfo();
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        dispose();

    }//GEN-LAST:event_btnInfoLruActionPerformed


    private void WhybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WhybtnActionPerformed
        // TODO add your handling code here:
        Why w = new Why();
        w.setLocationRelativeTo(null);
        w.setVisible(true);
        dispose();
    }//GEN-LAST:event_WhybtnActionPerformed

    private void WherebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WherebtnActionPerformed
        // TODO add your handling code here:
        Where w = new Where();
        w.setLocationRelativeTo(null);
        w.setVisible(true);
        dispose();
    }//GEN-LAST:event_WherebtnActionPerformed

    
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
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                index a = new index();
                a.setLocationRelativeTo(null);
                a.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AlgoBtn;
    private javax.swing.JButton Whatbtn;
    private javax.swing.JButton Wherebtn;
    private javax.swing.JButton Whybtn;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnInfoFifo;
    private javax.swing.JButton btnInfoLifo;
    private javax.swing.JButton btnInfoLru;
    private javax.swing.JButton btnInfoOpr;
    private javax.swing.JButton btnInfoRandom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel pnlBinaryMini;
    private javax.swing.JPanel pnlCountMini;
    private javax.swing.JPanel pnlInterestMini;
    private javax.swing.JPanel pnlLockMini;
    private javax.swing.JPanel pnlTestSetMini;
}
