package IMS;
//imports
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class inventoryCodeList extends javax.swing.JFrame {

    public inventoryCodeList() {
        initComponents();//components
        inventoryCodesTable();
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
        
        public ArrayList<inventoryCode> getInventoryCodesList()//gets arraylist of inventory codes
   {
       ArrayList<inventoryCode> inventoryCodesList = new ArrayList<inventoryCode>();//creates new array list
       Connection connection = getConnection();//gets connection
       
       String query = "SELECT * FROM  `tblinventorycode`";//SQL query
       Statement statement;//statement
       ResultSet resultSet;//result set
       
       try {
           statement = connection.createStatement();//creates statement for the connection
           resultSet = statement.executeQuery(query);//executes query across the result set

           
           inventoryCode InventoryCode;//new object of type inventoryCode called InventoryCode

           while(resultSet.next())//traverses through result set
           {
InventoryCode = new inventoryCode(resultSet.getString("Inventory Code"),resultSet.getString("Part Description"),resultSet.getString("Supplier Code"));//passes arguments through InventoryCode
               inventoryCodesList.add(InventoryCode);//adds InventoryCode to the array list
           }

       } 
      catch (Exception e) {//for errors
           e.printStackTrace();
       }
       return inventoryCodesList;//returns array list
   }
        
         public void inventoryCodesTable()//displays all inventory codes in the table
   {
       ArrayList<inventoryCode> list = getInventoryCodesList();//gets array list of inventory codes
       DefaultTableModel model = (DefaultTableModel)tblInventoryCodes.getModel();//creates a table
       Object[] row = new Object[3];//3 columns for every row
       for(int i = 0; i < list.size(); i++)//loop to fill in each row
       {
           row[0] = list.get(i).getCode();//first column is the code
           row[1] = list.get(i).getPartDescription();//second column is the part description
           row[2] = list.get(i).getSupplierCode();//third column is the supplier code
           model.addRow(row);//adds the row
       }
       tblInventoryCodes.setAutoCreateRowSorter(true);//allows for sorting
    }
         public boolean intblSupplierList(){//checks if the supplier code is already declared in supplier list
             boolean exists = false;//boolean to check if it exists
             String userSupplier = tfSupplierCode.getText();//gets the value from the text field
             String query = "SELECT * FROM  `tblsupplierlist`";//SQL query
             Connection connection = getConnection();//gets connection
       Statement statement;//statement
       ResultSet resultSet;//result set
       
       try {
           statement = connection.createStatement();//statement for the connection
           resultSet = statement.executeQuery(query);//executing the statement for the result set
           while(resultSet.next())//traverses through the result set
           {
               String supplierCode = resultSet.getString("Supplier Code");//gets the string Supplier Code from the SQL result set
               if(supplierCode.equals(userSupplier)){//validation to check whether the user entered supplier code exists in the pre declared supplier codes
                   exists = true;//if the above condition is true, then exists = true
                   break;//breaks out of the loop
               }
           }

       } 
      catch (Exception e) {//for errors
           e.printStackTrace();
       }
             return exists;//will be false unless it has been pre-declared
         }
         
         public boolean unique(){//checks if inventory codes are unique
             boolean isUnique = true;//assumes initially that it is unique
              String userInventoryCode = tfInventoryCode.getText();//gets the value of the inventory code from the user
             String query = "SELECT * FROM  `tblinventorycode`";//SQL query
             Connection connection = getConnection();//gets connection
       Statement statement;//statement
       ResultSet resultSet;//result set
       
       try {
           statement = connection.createStatement();//statement for the connection
           resultSet = statement.executeQuery(query);//executes statement across the result set
           while(resultSet.next())//traverses the result set
           {
               String inventoryCode = resultSet.getString("Inventory Code");//gets the values associated with the colum "Inventory Code"
               if(inventoryCode.equals(userInventoryCode)){//checks is any inventory codes match the one entered by the user
                   isUnique = false;//if they do, then the inventory code is not unique
                   break;//break
               }
           }

       } 
      catch (Exception e) {//errors
           e.printStackTrace();
      }           
             return isUnique;//return
         }
         
         public void executeSQLQuery(String query, String message)//function to execute SQL query
   {
       Connection connection = getConnection();//connection
       Statement statement;//statement
       try{
           statement = connection.createStatement();//statement for the connection
           if((statement.executeUpdate(query)) == 1)//checks if the query has been executed
           {
               //refresh jtable data
               DefaultTableModel model = (DefaultTableModel)tblInventoryCodes.getModel();
               model.setRowCount(0);
               inventoryCodesTable();
               JOptionPane.showMessageDialog(null, "Data "+message+" Successfully");//to notify user the query has been executed
           }else{
               JOptionPane.showMessageDialog(null, "Data Not "+message);//to notify user the query has not been executed
           }
       }catch(Exception ex){//for errors
           ex.printStackTrace();
       }
   }
         
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblPartDescription = new javax.swing.JLabel();
        lblInventoryCode = new javax.swing.JLabel();
        tfInventoryCode = new javax.swing.JTextField();
        tfPartDescription = new javax.swing.JTextField();
        tfSupplierCode = new javax.swing.JTextField();
        lblSupplierCode = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventoryCodes = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

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

        lblPartDescription.setText("Part Description:");

        lblInventoryCode.setText("Inventory Code:");

        tfInventoryCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfInventoryCodeActionPerformed(evt);
            }
        });

        tfPartDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPartDescriptionActionPerformed(evt);
            }
        });

        tfSupplierCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSupplierCodeActionPerformed(evt);
            }
        });

        lblSupplierCode.setText("Supplier Code:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPartDescription)
                    .addComponent(lblInventoryCode)
                    .addComponent(lblSupplierCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tfSupplierCode)
                    .addComponent(tfPartDescription, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(tfInventoryCode, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInventoryCode)
                    .addComponent(tfInventoryCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPartDescription)
                    .addComponent(tfPartDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSupplierCode)
                    .addComponent(tfSupplierCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        tblInventoryCodes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Inventory Code", "Part Description", "Supplier Code"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblInventoryCodes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblInventoryCodesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblInventoryCodes);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
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
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        boolean exists = intblSupplierList();//checks if the supplier exists
        boolean isUnique = unique();//checks if the inventory code is unique
        if(tfInventoryCode.getText().matches("\\d{2}-\\d{2}-\\d{2}-\\d{2}") &&  exists == true && isUnique == true){//validaiton
            String query = "INSERT INTO `tblinventorycode`(`Inventory Code`, `Part Description`,`Supplier Code`) VALUES ('"+tfInventoryCode.getText()+"','"+tfPartDescription.getText()+"','"+tfSupplierCode.getText() + "')";//SQL Query
            executeSQLQuery(query, "Inserted");//executing the SQL query
        }else if(!tfInventoryCode.getText().matches("\\d{2}-\\d{2}-\\d{2}-\\d{2}")){//identifies if the inventory code is not properly entered
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid Inventory Code (8 digits long, in the form xx-xx-xx-xx)");//asks the user to enter a correct inventory code
        }else if(isUnique == false){//identifies if the inventory code is not unique
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a unique Inventory Code");//pop up
        }else{//identifies if the supplier code has not been enetered
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a defined Supplier Code (Update the Supplier Table if this is an undefined code)");//pop up
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
  
        int i = tblInventoryCodes.getSelectedRow();//gets selected row
        TableModel model = tblInventoryCodes.getModel();//gets the table
        boolean exists = intblSupplierList();//checks if the supplier code exists    
        boolean isUnique = unique();//checks if the inventory code is unique
    if(tfInventoryCode.getText().matches("\\d{2}-\\d{2}-\\d{2}-\\d{2}") &&  exists == true && isUnique == true){//validations
        String query = "UPDATE `tblinventorycode` SET `Inventory Code`='"+tfInventoryCode.getText()+"',`Part Description`='"+tfPartDescription.getText()+"',`Supplier Code`='" + tfSupplierCode.getText() + "' WHERE `Inventory Code` = '"+model.getValueAt(i,0).toString() +"'";//SQL query
        executeSQLQuery(query, "Updated");//executing SQL
    }else if(!tfInventoryCode.getText().matches("\\d{2}-\\d{2}-\\d{2}-\\d{2}")){//checks if the inventory code is the problem
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid Inventory Code (8 digits long, in the form xx-xx-xx-xx)");//pop up
        }else if(isUnique == false){//checks if the inventory code not being unique is the problem
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a unique Inventory Code");//pop up
        }else{//checks if the supplier code is the problem
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a defined Supplier Code (Update the Supplier Table if this is an undefined code)");//pop up
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tfInventoryCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfInventoryCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfInventoryCodeActionPerformed

    private void tfPartDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPartDescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPartDescriptionActionPerformed

    private void tblInventoryCodesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventoryCodesMousePressed

        int i = tblInventoryCodes.getSelectedRow();//gets selected row
        TableModel model = tblInventoryCodes.getModel();//gets the table

        tfInventoryCode.setText(model.getValueAt(i,0).toString());//sets the value in the textfield to be that of the inventory code

        tfPartDescription.setText(model.getValueAt(i,1).toString());//sets the vlaue in the textfield to be that of the part description
    }//GEN-LAST:event_tblInventoryCodesMousePressed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int i = tblInventoryCodes.getSelectedRow();//gets selected row
        TableModel model = tblInventoryCodes.getModel();//gets the table  
        String query = "DELETE FROM `tblinventorycode` WHERE `Inventory Code`= '"+model.getValueAt(i,0).toString() + "'";//deletes from the SQL table (query)
        
         executeSQLQuery(query, "Deleted");//executing the query
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tfSupplierCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSupplierCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSupplierCodeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {//main
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
            java.util.logging.Logger.getLogger(inventoryCodeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inventoryCodeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inventoryCodeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inventoryCodeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {//run
                new inventoryCodeList().setVisible(true);//sets the frame to be visible
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInventoryCode;
    private javax.swing.JLabel lblPartDescription;
    private javax.swing.JLabel lblSupplierCode;
    private javax.swing.JTable tblInventoryCodes;
    private javax.swing.JTextField tfInventoryCode;
    private javax.swing.JTextField tfPartDescription;
    private javax.swing.JTextField tfSupplierCode;
    // End of variables declaration//GEN-END:variables
}
