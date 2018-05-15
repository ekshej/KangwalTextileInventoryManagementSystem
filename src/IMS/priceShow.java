package IMS;
//imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class priceShow extends javax.swing.JFrame {

    public priceShow() {//price show
        String num = issueInventory.tfValue.getText();//gets the value associated with that entered by the user for the price
        double value = Double.parseDouble(num);//converts the number to a double for manipulation
        initComponents();//components
        inventoryPriceShowTable(value);//shows the table with only entries above the value
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
        
        public ArrayList<inventoryIssueItem> getInventoryList()//function to get a list of all the inventoryIssueItem from the SQL Table
   {
       ArrayList<inventoryIssueItem> issueInventoryList = new ArrayList<inventoryIssueItem>();//implements ArrayList of type 'inventoryIssueItem'
       Connection connection = getConnection();//connection
       
       String query = "SELECT * FROM  `tblinventoryissue`";//SQL Query
       Statement statement;//statement
       ResultSet resultSet;//result set
       
       try {//try-catch statements have to be implemented for SQL Searches (makes it easier to identify problems)
           statement = connection.createStatement();//statement for connection
           resultSet = statement.executeQuery(query);//statement for result set

           
           inventoryIssueItem InventoryIssueItem;//creating an object of type inventoryIssueItem

           while(resultSet.next())//this traverses through the result set
           {
InventoryIssueItem = new inventoryIssueItem(resultSet.getString("Date"),resultSet.getString("ISN"),resultSet.getString("Inventory Code"),resultSet.getInt("Quantity"),resultSet.getString("Machine Number"),resultSet.getString("Department"),resultSet.getString("Section"),resultSet.getString("Cost Code"),resultSet.getDouble("Total Price"));//assigning values to the object
               issueInventoryList.add(InventoryIssueItem);//adds the object to the ArrayList
           }

       } 
      catch (Exception e) {//catch any errors
           e.printStackTrace();
       }
       return issueInventoryList;//returns the Array List
   }
        
         public void inventoryPriceShowTable(double value)//function to show the data from SQL into the JTable
   {
       ArrayList<inventoryIssueItem> list = getInventoryList();//getting the arraylist
       DefaultTableModel model = (DefaultTableModel)tblInventoryIssuePriceShow.getModel();//DefaultTableModels allows for a varying length of rows
       Object[] row = new Object[9];//every column has 9 rows
       for(int i = 0; i < list.size(); i++)//to traverse through the array list and show the relevant ones in the table
       {
           row[0] = list.get(i).getDate();//sets the value in the first column as the Date
           row[1] = list.get(i).getISN();//sets the value in the second column as the ISN
           row[2] = list.get(i).getInventoryCode();//sets the value in the third column as the Inventory Code
           row[3] = list.get(i).getQuantity();//sets the value in the fourth column as the Quantity
           row[4] = list.get(i).getMachineNumber();//sets the value in the fifth column as the Machine Number
           row[5] = list.get(i).getDepartment();//sets the value in the sixth column as the Department
           row[6] = list.get(i).getSection();//sets the value in the seventh column as the Section
           row[7] = list.get(i).getCostCode();//sets the value in the eighth column as the Cost Code
           row[8] = list.get(i).getTotalPrice();//sets the value in the ninth column as the Total Price
           if(list.get(i).getTotalPrice()>= value){//checks if the total price is grater than the user given value
           model.addRow(row);//adds it as a row
           }
       }
       tblInventoryIssuePriceShow.setAutoCreateRowSorter(true);//allows for data to be sorted
    }
         
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventoryIssuePriceShow = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 650));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblInventoryIssuePriceShow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Inventory Slip Number (ISN)", "Inventory Code", "Quantity", "Machine Number", "Department", "Section", "Cost Code", "Total Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblInventoryIssuePriceShow);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 720, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(priceShow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(priceShow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(priceShow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(priceShow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {//run
                new priceShow().setVisible(true);//sets the frame as visible
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblInventoryIssuePriceShow;
    // End of variables declaration//GEN-END:variables
}
