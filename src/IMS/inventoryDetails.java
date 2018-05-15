
package IMS;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;//imported Libraries

public class inventoryDetails extends javax.swing.JFrame {
    public inventoryDetails() {//inventoryDetails
        initComponents();//components
        inventoryTable();//inventory table
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
       public void inventoryTable()//function to show the data from SQL into the JTable
   {
       ArrayList<inventoryDetailItem> list = getInventoryDetailsList();//gets inventory item list
       DefaultTableModel model = (DefaultTableModel)tblInventoryDetails.getModel();//DefaultTableModels allows for a varying length of rows
       Object[] row = new Object[8];//8 columns for each row
       for(int i = 0; i < list.size(); i++)//to traverse through all the inventory itrems and add them to the table
       {
           row[0] = list.get(i).getDate();//sets the value in the first column as the Date
           row[1] = list.get(i).getRSN();//sets the value in the second column as the RSN
           row[2] = list.get(i).getInventoryCode();//sets the value in the third column as the Inventory Code
           row[3] = list.get(i).getLot();//sets the value in the fourth column as the 'lot' number
           row[4] = list.get(i).getPartDescription();//sets the value in the fifth column as the inventory code number
           row[5] = list.get(i).getQuantity();//sets the value in the sixth column as the Quantity
           row[6] = list.get(i).getUnitPrice();//sets the value in the seventh column as the Unit Price
           row[7] = list.get(i).getTotalPrice();//sets the value in the eight column as the Total Price
           model.addRow(row);//adds it as a row
       }
       tblInventoryDetails.setAutoCreateRowSorter(true);//allows for sorting
    }
       
       public ArrayList<inventoryDetailItem> getInventoryDetailsList()//gets array list for inventory details
   {
       ArrayList<inventoryDetailItem> inventoryDetailsList = new ArrayList<inventoryDetailItem>();//creates a new arraylist for inventory details
       Connection connection = getConnection();//conection
       
       String query = "SELECT * FROM  `tblinventorydetails`";//SQL query
       Statement statement;//statement
       ResultSet resultSet;//result set
       
       try {
           statement = connection.createStatement();//statement
           resultSet = statement.executeQuery(query);//executing the statement

           
           inventoryDetailItem InventoryDetailItem;//Inventory Detail of type inventoryDetailItem

           while(resultSet.next())//traverses through the result set
           {
InventoryDetailItem = new inventoryDetailItem(resultSet.getString("Date"),resultSet.getString("RSN"),resultSet.getString("Inventory Code"),resultSet.getInt("Lot"), resultSet.getString("Part Description"), resultSet.getInt("Quantity"),resultSet.getDouble("Unit Price"),resultSet.getDouble("Total Price"));//new object Inventory Detail Item with arguments
               inventoryDetailsList.add(InventoryDetailItem);//adds Inventory Detail to the array list
           }

       } 
      catch (Exception e) {//for errors
           e.printStackTrace();
       }
       return inventoryDetailsList;
   }
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventoryDetails = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tblInventoryDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Receive Slip Number (RSN)", "Inventory Code", "Lot", "Part Description", "Quantity", "Unit Price", "Total Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInventoryDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblInventoryDetailsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblInventoryDetails);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblInventoryDetailsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventoryDetailsMousePressed

    }//GEN-LAST:event_tblInventoryDetailsMousePressed

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
            java.util.logging.Logger.getLogger(inventoryDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inventoryDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inventoryDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inventoryDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {//run
                new inventoryDetails().setVisible(true);//sets visible
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblInventoryDetails;
    // End of variables declaration//GEN-END:variables
}
