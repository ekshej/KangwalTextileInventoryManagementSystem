package IMS;

public class mainMenu extends javax.swing.JFrame {

    /**
     * Creates new form ManuMain
     */
    public mainMenu() {
        initComponents();//components
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCurrentStoreInventory = new javax.swing.JButton();
        btnReceiveInventory = new javax.swing.JButton();
        btnIssueInventory = new javax.swing.JButton();
        btnSupplierList = new javax.swing.JButton();
        btnInventoryMasterList = new javax.swing.JButton();
        btnCostHeading = new javax.swing.JButton();
        btnDepartmentCode = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        btnCurrentStoreInventory.setText("Current Store Inventory");
        btnCurrentStoreInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCurrentStoreInventorybtnCurrentStoreInventoryActionPerformed(evt);
            }
        });

        btnReceiveInventory.setText("Receive Inventory");
        btnReceiveInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceiveInventorybtnReceiveInventoryActionPerformed(evt);
            }
        });

        btnIssueInventory.setText("Issue Inventory");
        btnIssueInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIssueInventorybtnIssueInventoryActionPerformed(evt);
            }
        });

        btnSupplierList.setText("Supplier List");
        btnSupplierList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierListbtnSupplierListActionPerformed(evt);
            }
        });

        btnInventoryMasterList.setText("Inventory Master List");
        btnInventoryMasterList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventoryMasterListbtnInventoryMasterListActionPerformed(evt);
            }
        });

        btnCostHeading.setText("Cost Heading");
        btnCostHeading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCostHeadingbtnCostHeadingActionPerformed(evt);
            }
        });

        btnDepartmentCode.setText("Department Code");
        btnDepartmentCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepartmentCodebtnDepartmentCodeActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 34)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Kangwal Textile Co. Ltd. Inventory Management System");
        lblTitle.setToolTipText("");
        lblTitle.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnDepartmentCode, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addGap(39, 39, 39)
                                .addComponent(btnCostHeading, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addGap(37, 37, 37)
                                .addComponent(btnInventoryMasterList, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addGap(40, 40, 40)
                                .addComponent(btnSupplierList, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnReceiveInventory, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addGap(83, 83, 83)
                                .addComponent(btnCurrentStoreInventory, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addGap(83, 83, 83)
                                .addComponent(btnIssueInventory, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                        .addGap(26, 26, 26)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(btnReceiveInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCurrentStoreInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIssueInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDepartmentCode, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCostHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnInventoryMasterList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                        .addComponent(btnSupplierList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCurrentStoreInventorybtnCurrentStoreInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCurrentStoreInventorybtnCurrentStoreInventoryActionPerformed
        new inventoryDetails().setVisible(true);//shows inventoryDetails
    }//GEN-LAST:event_btnCurrentStoreInventorybtnCurrentStoreInventoryActionPerformed

    private void btnReceiveInventorybtnReceiveInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceiveInventorybtnReceiveInventoryActionPerformed
        new receiveInventory().setVisible(true);//shows receiveInventory
    }//GEN-LAST:event_btnReceiveInventorybtnReceiveInventoryActionPerformed

    private void btnIssueInventorybtnIssueInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIssueInventorybtnIssueInventoryActionPerformed
        new issueInventory().setVisible(true);//shows issueInventory
    }//GEN-LAST:event_btnIssueInventorybtnIssueInventoryActionPerformed

    private void btnSupplierListbtnSupplierListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierListbtnSupplierListActionPerformed
        new supplierList().setVisible(true);//shows supplierList
    }//GEN-LAST:event_btnSupplierListbtnSupplierListActionPerformed

    private void btnInventoryMasterListbtnInventoryMasterListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventoryMasterListbtnInventoryMasterListActionPerformed
        new inventoryCodeList().setVisible(true);//shows inventoryCodeList
    }//GEN-LAST:event_btnInventoryMasterListbtnInventoryMasterListActionPerformed

    private void btnCostHeadingbtnCostHeadingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCostHeadingbtnCostHeadingActionPerformed
        new costHeadingList().setVisible(true);//shows costHeadingList
    }//GEN-LAST:event_btnCostHeadingbtnCostHeadingActionPerformed

    private void btnDepartmentCodebtnDepartmentCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepartmentCodebtnDepartmentCodeActionPerformed
        new departmentCodeList().setVisible(true);//shows departmentCodeList
    }//GEN-LAST:event_btnDepartmentCodebtnDepartmentCodeActionPerformed

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
            java.util.logging.Logger.getLogger(mainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {//run
                new mainMenu().setVisible(true);//sets mainMenu as visible
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCostHeading;
    private javax.swing.JButton btnCurrentStoreInventory;
    private javax.swing.JButton btnDepartmentCode;
    private javax.swing.JButton btnInventoryMasterList;
    private javax.swing.JButton btnIssueInventory;
    private javax.swing.JButton btnReceiveInventory;
    private javax.swing.JButton btnSupplierList;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}
