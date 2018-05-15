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

public class departmentCodeList extends javax.swing.JFrame {

    public departmentCodeList() {
        initComponents();//components
        departmentCodesTable();//shows the table of department codes
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
        
        public ArrayList<departmentCode> getDepartmentCodes()//gets an array list of department codes
   {
       ArrayList<departmentCode> departmentCodes = new ArrayList<departmentCode>();//creates a new object of type ArrayList of departmentCode
       Connection connection = getConnection();//gets connection
       
       String query = "SELECT * FROM  `tbldepartmentcode`";//SQL query
       Statement statement;//statement
       ResultSet resultSet;//result set
       
       try {
           statement = connection.createStatement();//statement
           resultSet = statement.executeQuery(query);//executes query to fetch result set

           
           departmentCode DepartmentCode;//creates an object of type departmentCode

           while(resultSet.next())//traverses through result set
           {
DepartmentCode = new departmentCode(resultSet.getString("Department"),resultSet.getString("Section"));//new object DepartmentCode with arguments
               departmentCodes.add(DepartmentCode);//adds DepartmentCode to the arraylist
           }

       } 
      catch (Exception e) {//for errors
           e.printStackTrace();//print errors
       }
       return departmentCodes;//returns the array list
   }
        
         public void departmentCodesTable()//function to display the table
   {
       ArrayList<departmentCode> list = getDepartmentCodes();//gets the array list of department codes
       DefaultTableModel model = (DefaultTableModel)tblDepartmentCodes.getModel();//creates a dynamic table
       Object[] row = new Object[2];//2 columns for every row
       for(int i = 0; i < list.size(); i++)//loop to add rows
       {
           row[0] = list.get(i).getDepartment();//department as the first column
           row[1] = list.get(i).getSection();//section as the second column
           model.addRow(row);//adding the row
       }
       tblDepartmentCodes.setAutoCreateRowSorter(true);//allows to data to be sorted
    }
         
         public void executeSQLQuery(String query, String message)//function to execute the SQL query
   {
       Connection connection = getConnection();
       Statement statement;
       try{
           statement = connection.createStatement();
           if((statement.executeUpdate(query)) == 1)//checks if statement has been executed
           {
               //refresh jtable data
               DefaultTableModel model = (DefaultTableModel)tblDepartmentCodes.getModel();
               model.setRowCount(0);
               departmentCodesTable();
               JOptionPane.showMessageDialog(null, "Data "+message+" Successfully");
           }else{
               JOptionPane.showMessageDialog(null, "Data Not "+message);//in case there was an error in updating
           }
       }catch(Exception e){//for errors
           e.printStackTrace();//print errors
       }
   }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblSection = new javax.swing.JLabel();
        lblDepartment = new javax.swing.JLabel();
        tfDepartment = new javax.swing.JTextField();
        tfSection = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDepartmentCodes = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(650, 450));
        setPreferredSize(new java.awt.Dimension(650, 450));
        setResizable(false);

        lblSection.setText("Section:");

        lblDepartment.setLabelFor(tfDepartment);
        lblDepartment.setText("Department:");

        tfDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDepartmentActionPerformed(evt);
            }
        });

        tfSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSection)
                    .addComponent(lblDepartment))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfSection, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(tfDepartment))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDepartment)
                    .addComponent(tfDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSection)
                    .addComponent(tfSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        tblDepartmentCodes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Department", "Section"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDepartmentCodes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDepartmentCodesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblDepartmentCodes);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdd, btnDelete, btnUpdate});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(38, 38, 38))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdd, btnDelete, btnUpdate});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int i = tblDepartmentCodes.getSelectedRow();//gets selected row
        TableModel model = tblDepartmentCodes.getModel();//gets model
        String query = "DELETE FROM `tbldepartmentcode`" + " WHERE `Department` = "+ model.getValueAt(i,0).toString() + " AND `Section` = "+model.getValueAt(i,1).toString();//SQL query
        
         executeSQLQuery(query, "Deleted");//execute deletion
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
   
        if(tfDepartment.getText().matches("\\d{3}") && tfSection.getText().matches("\\d{2}")){//checks that department is 3 digits and section is 2 digits
            String query = "INSERT INTO `tbldepartmentcode`(`Department`, `Section`) VALUES ('"+tfDepartment.getText()+"','"+tfSection.getText()+"')";//SQL insertion statement
            
            executeSQLQuery(query, "Inserted");//inserts SQL
        }else{
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid Department Code (3 digits long) and/or Section Code (2 digits long)");//error message
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        
        int i = tblDepartmentCodes.getSelectedRow();//gets selected row
        TableModel model = tblDepartmentCodes.getModel();//table              
       
    if(tfDepartment.getText().matches("\\d{3}") && tfSection.getText().matches("\\d{2}")){//validation
        String query = "UPDATE `tbldepartmentcode` SET `Department`='"+tfDepartment.getText()+"',`Section`='"+tfSection.getText()+"' " + "WHERE `Department` = "+ model.getValueAt(i,0).toString() + " AND `Section` = "+model.getValueAt(i,1).toString();//SQL update query
        
        executeSQLQuery(query, "Updated");//execution of SQL
    }else{
        Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid Department Code (3 digits long) and/or Section Code (2 digits long)");//error message
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblDepartmentCodesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDepartmentCodesMousePressed

        int i = tblDepartmentCodes.getSelectedRow();//gets selected row
        TableModel model = tblDepartmentCodes.getModel();//gets table
        
        tfDepartment.setText(model.getValueAt(i,0).toString());//sets text field to value in the row

        tfSection.setText(model.getValueAt(i,1).toString());//sets text field to value in the row
    }//GEN-LAST:event_tblDepartmentCodesMousePressed

    private void tfSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSectionActionPerformed

    private void tfDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDepartmentActionPerformed

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
            java.util.logging.Logger.getLogger(departmentCodeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(departmentCodeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(departmentCodeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(departmentCodeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {//run
                new departmentCodeList().setVisible(true);//sets frame as visible
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDepartment;
    private javax.swing.JLabel lblSection;
    private javax.swing.JTable tblDepartmentCodes;
    private javax.swing.JTextField tfDepartment;
    private javax.swing.JTextField tfSection;
    // End of variables declaration//GEN-END:variables
}
