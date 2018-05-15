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

public class costHeadingList extends javax.swing.JFrame {//extending the JFrame

    public costHeadingList() {
        initComponents();//Components
        costHeadingTable();//Function to display all the Cost Headings and their respective Nature of Expense in a JTable
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
        
        public ArrayList<costHeading> getCostHeadings()//Function to create and return an Array List of Cost Headings
   {
       ArrayList<costHeading> costHeadings = new ArrayList<costHeading>();//Array List of type costHeading
       Connection connection = getConnection();//Retrieving connection
       
       String query = "SELECT * FROM  `tblcostheading`";//SQL query
       Statement statement;//statement
       ResultSet resultSet;//result set
       
       try {
           statement = connection.createStatement();
           resultSet = statement.executeQuery(query);

           
           costHeading CostHeading;//creating object CostHeading of type costHeading

           while(resultSet.next())//traversing through result set
           {
CostHeading = new costHeading(resultSet.getString("Cost Code"),resultSet.getString("Nature of Expense"));//passing arguments through costHeading for object CostHeading
               costHeadings.add(CostHeading);//adding CostHeading to the array list of costHeadings
           }

       } 
      catch (Exception e) {//for errors
           e.printStackTrace();//print errors
       }
       return costHeadings;//returning the arrat list costHeadings
   }
        
         public void costHeadingTable()//Function to display data in the table
   {
       ArrayList<costHeading> list = getCostHeadings();//Getting an ArrayList from the SQL data
       DefaultTableModel model = (DefaultTableModel)tblCostHeadings.getModel();//Using DefaultTableModel to allow for dynamic data
       Object[] row = new Object[2];//two rows
       for(int i = 0; i < list.size(); i++)//traversing through all elements of the ArrayList
       {
           row[0] = list.get(i).getCostCode();//gets the Cost Code
           row[1] = list.get(i).getNoE();//gets the Nature of Expense
           model.addRow(row);//adds the row to the table
       }
       tblCostHeadings.setAutoCreateRowSorter(true);//to allow for sorting
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
               DefaultTableModel model = (DefaultTableModel)tblCostHeadings.getModel();
               model.setRowCount(0);
               costHeadingTable();
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
        lblNoE = new javax.swing.JLabel();
        lblCostCode = new javax.swing.JLabel();
        tfCostHeading = new javax.swing.JTextField();
        tfNoE = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCostHeadings = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 400));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNoE.setText("Nature of Expense:");

        lblCostCode.setLabelFor(tfCostHeading);
        lblCostCode.setText("Cost Heading:");

        tfCostHeading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCostHeadingActionPerformed(evt);
            }
        });

        tfNoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNoEActionPerformed(evt);
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

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNoE)
                    .addComponent(lblCostCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfNoE, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(tfCostHeading))
                .addGap(46, 46, 46)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btnUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCostCode)
                            .addComponent(tfCostHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNoE)
                            .addComponent(tfNoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 266, -1, 110));

        tblCostHeadings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cost Code", "Nature of Expense"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCostHeadings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCostHeadingsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblCostHeadings);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 16, 420, 250));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNoEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNoEActionPerformed

    private void tfCostHeadingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCostHeadingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCostHeadingActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int i = tblCostHeadings.getSelectedRow();//gets selected row
        TableModel model = tblCostHeadings.getModel();//retrieves 'tblCostHeadings'
        String query = "DELETE FROM `tblcostheading` WHERE `Cost Code`= "+model.getValueAt(i,0).toString();//creates SQL statement to delete selected row and respective data
        
         executeSQLQuery(query, "Deleted");//passes the query and the message through 'executeSQLQuery' for execution
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
   
        if(tfCostHeading.getText().matches("\\d{3}")){//checks that the entered Cost Heading is 3 digits
            String query = "INSERT INTO `tblcostheading`(`Cost Code`, `Nature of Expense`) VALUES ('"+tfCostHeading.getText()+"','"+tfNoE.getText()+"')";//SQL statement for insertion
            
            executeSQLQuery(query, "Inserted");//execute SQL
        }else{//if Cost Heading is not 3 digits
            Component frame = null;//frame
            JOptionPane.showMessageDialog(frame,"Please enter a valid Cost Code (3 digits long)");//pop up to inform user to enter valid cost code
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        
        int i = tblCostHeadings.getSelectedRow();//gets selected row
        TableModel model = tblCostHeadings.getModel();//gets the cost headings table              
       
    if(tfCostHeading.getText().matches("\\d{3}")){//checks that the entered Cost Heading is 3 digits
        String query = "UPDATE `tblcostheading` SET `Cost Code`='"+tfCostHeading.getText()+"',`Nature of Expense`='"+tfNoE.getText()+"' " + "WHERE `Cost Code` = "+model.getValueAt(i,0).toString();//SQL statement for updating
        
        executeSQLQuery(query, "Updated");//execute SQL
    }else{//if Cost Heading is not 3 digits
        Component frame = null;//frame
            JOptionPane.showMessageDialog(frame,"Please enter a valid Cost Code (3 digits long)");//pop up
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblCostHeadingsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCostHeadingsMousePressed

        int i = tblCostHeadings.getSelectedRow();//gets selected row
        TableModel model = tblCostHeadings.getModel();//gets table
        
        tfCostHeading.setText(model.getValueAt(i,0).toString());//sets cost heading to value of the row

        tfNoE.setText(model.getValueAt(i,1).toString());//sets nature of expense to value of the row
    }//GEN-LAST:event_tblCostHeadingsMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {//main
        try {//GUI
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }//for errors
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(costHeadingList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(costHeadingList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(costHeadingList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(costHeadingList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
  
        //Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {//run
                new costHeadingList().setVisible(true);//sets frame visisble
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCostCode;
    private javax.swing.JLabel lblNoE;
    private javax.swing.JTable tblCostHeadings;
    private javax.swing.JTextField tfCostHeading;
    private javax.swing.JTextField tfNoE;
    // End of variables declaration//GEN-END:variables
}
