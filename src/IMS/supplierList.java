package IMS;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;//imported Libraries

public class supplierList extends javax.swing.JFrame {

    public supplierList() {
        initComponents();//shows all the elements of program
        suppliersTable();//shows Suppliers from SQL into the JTable
    }
    
        public Connection getConnection()//Getting the connection to the SQL table
   {
       Connection connection;//creates object connection of type Connection
       try {
           connection = DriverManager.getConnection("jdbc:mysql://localhost/kangwal_textiles", "root","root");//the table and relative information
           return connection;//return the connection
       } 
      catch (Exception e) {//in case of an error
           e.printStackTrace();//prints errors
           return null;
       }
   }
        
        public ArrayList<supplier> getSuppliersList()//function to get a list of all the Suppliers from the SQL Table
   {
       ArrayList<supplier> suppliersList = new ArrayList<supplier>();//implements ArrayList of type 'Supplier'
       Connection connection = getConnection();//Using our first function
       
       String query = "SELECT * FROM  `tblsupplierlist`";//SQL Language
       Statement st;
       ResultSet rs;
       
       try {//try-catch statements have to be implemented for SQL Searches (makes it easier to identify problems)
           st = connection.createStatement();
           rs = st.executeQuery(query);

           
           supplier supplier;//creating an object of type Supplier

           while(rs.next())//this traverses through all the results from the tblsupplierlist
           {
supplier = new supplier(rs.getString("Supplier Code"),rs.getString("Supplier Name"));//assigning values to the object
               suppliersList.add(supplier);//adds the object to the ArrayList
           }

       } 
      catch (Exception e) {//catch any errors
           e.printStackTrace();
       }
       return suppliersList;//returns the Array List
   }
        
         public void suppliersTable()//function to show the data from SQL into the JTable
   {
       ArrayList<supplier> list = getSuppliersList();//Using the previous function
       DefaultTableModel model = (DefaultTableModel)tblSuppliers.getModel();//DefaultTableModels allows for a varying length of rows
       Object[] row = new Object[2];
       for(int i = 0; i < list.size(); i++)//to traverse through all the Suppliers and add them to the table
       {
           row[0] = list.get(i).getSupplierCode();//sets the value in the first column as the Code
           row[1] = list.get(i).getSupplierName();//sets the value in the second column as the Name
           model.addRow(row);//adds it as a row
       }
       tblSuppliers.setAutoCreateRowSorter(true);
    }
         
         public void executeSQLQuery(String query, String message)//function used for a dual purpose, sending a query to the SQL Table and to display a popup depending if the former was done successfuly or not
   {
       Connection con = getConnection();//Decomposition
       Statement st;
       try{
           st = con.createStatement();
           if((st.executeUpdate(query)) == 1)//checks if the query has happened
           {
               DefaultTableModel model = (DefaultTableModel)tblSuppliers.getModel();//creates a new table
               model.setRowCount(0);//initialises this table
               suppliersTable();//reloads this data back into the JTable
               JOptionPane.showMessageDialog(null, "Data "+message+" Successfully");//creates a pop up to indicate the request was done successfully
           }else{
               JOptionPane.showMessageDialog(null, "Data Not "+message);//popup to show the request was not completed
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
    
    @SuppressWarnings("unchecked")//avoids any hassles
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblSupplierName = new javax.swing.JLabel();
        lblSupplierCode = new javax.swing.JLabel();
        tfSupplierCode = new javax.swing.JTextField();
        tfSupplierName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSuppliers = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblSupplierName.setText("Supplier Name:");

        lblSupplierCode.setLabelFor(tfSupplierCode);
        lblSupplierCode.setText("Supplier Code:");

        tfSupplierCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSupplierCodeActionPerformed(evt);
            }
        });

        tfSupplierName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSupplierNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSupplierName)
                    .addComponent(lblSupplierCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfSupplierName, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(tfSupplierCode))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSupplierCode)
                    .addComponent(tfSupplierCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSupplierName)
                    .addComponent(tfSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        tblSuppliers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier Code", "Supplier Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblSuppliers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSuppliersMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSuppliers);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.setToolTipText("");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfSupplierNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSupplierNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSupplierNameActionPerformed

    private void tfSupplierCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSupplierCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSupplierCodeActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int i = tblSuppliers.getSelectedRow();//gets data of a selected row
        TableModel model = tblSuppliers.getModel();  
        String query = "DELETE FROM `tblsupplierlist` WHERE `Supplier Code`= "+model.getValueAt(i,0).toString();//creates an SQL query to delete a specific entry
        
         executeSQLQuery(query, "Deleted");//Sends this to the prior function
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
   
        if(tfSupplierCode.getText().matches("\\d{5}")){//important validation check, checks if enetered value is 5 digits
            String query = "INSERT INTO `tblsupplierlist`(`Supplier Code`, `Supplier Name`) VALUES ('"+tfSupplierCode.getText()+"','"+tfSupplierName.getText()+"')";
            //creates an SQL Query
            executeSQLQuery(query, "Inserted");
        }else{
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid Supplier Code (5 digits long)");//popup to inform the user
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        //same principles as the Add button, with the only difference being the query generated
        int i = tblSuppliers.getSelectedRow();
        TableModel model = tblSuppliers.getModel();              
       
    if(tfSupplierCode.getText().matches("\\d{5}")){
        String query = "UPDATE `tblsupplierlist` SET `Supplier Code`='"+tfSupplierCode.getText()+"',`Supplier Name`='"+tfSupplierName.getText()+"' " + "WHERE `Supplier Code` = "+model.getValueAt(i,0).toString();
        
        executeSQLQuery(query, "Updated");
    }else{
        Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid Supplier Code (5 digits long)");
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblSuppliersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuppliersMousePressed

        int i = tblSuppliers.getSelectedRow();
        TableModel model = tblSuppliers.getModel();//gets the values associated with a specific row
        
        //sets the value in that row to the textfields - allowing for easier editing
        tfSupplierCode.setText(model.getValueAt(i,0).toString());

        tfSupplierName.setText(model.getValueAt(i,1).toString());
    }//GEN-LAST:event_tblSuppliersMousePressed

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
            java.util.logging.Logger.getLogger(supplierList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(supplierList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(supplierList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(supplierList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {//main
            public void run() {//run
                new supplierList().setVisible(true);//shows the table
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSupplierCode;
    private javax.swing.JLabel lblSupplierName;
    private javax.swing.JTable tblSuppliers;
    private javax.swing.JTextField tfSupplierCode;
    private javax.swing.JTextField tfSupplierName;
    // End of variables declaration//GEN-END:variables
}
