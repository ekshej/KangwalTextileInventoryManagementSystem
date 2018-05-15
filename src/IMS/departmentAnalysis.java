package IMS;
//imports
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class departmentAnalysis extends javax.swing.JFrame {

    public departmentAnalysis() {
        initComponents();//components
        analysisTable();//Shows the analysis table
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
        
        public ArrayList<inventoryIssueItem> getInventoryList()//function to get a list of all the Inventory Issue Items from the SQL Table
   {
       ArrayList<inventoryIssueItem> issueInventoryList = new ArrayList<inventoryIssueItem>();//implements ArrayList of type 'inventoryIssueItem'
       Connection connection = getConnection();//getting the connection
       
       String query = "SELECT * FROM  `tblinventoryissue`";//SQL Language
       Statement statement;//statement
       ResultSet resultSet;//resultset
       
       try {//try-catch statements have to be implemented for SQL Searches (makes it easier to identify problems)
           statement = connection.createStatement();
           resultSet = statement.executeQuery(query);

           
           inventoryIssueItem InventoryIssueItem;//creating an object of type inventoryIssueItem

           while(resultSet.next())//this traverses through all the results from the tblinventoryissue
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
        public ArrayList<departmentCode> getDepartmentCodes()//gets array list of department codes
   {
       ArrayList<departmentCode> departmentCodes = new ArrayList<departmentCode>();//array list of department codes
       Connection connection = getConnection();//gets connections
       
       String query = "SELECT * FROM  `tbldepartmentcode`";//SQL query
       Statement statement;//statement
       ResultSet resultSet;//result set
       
       try {
           statement = connection.createStatement();
           resultSet = statement.executeQuery(query);

           
           departmentCode DepartmentCode;//creates object of type departmentCode

           while(resultSet.next())//traverses through result set
           {
DepartmentCode = new departmentCode(resultSet.getString("Department"),resultSet.getString("Section"));//creates new object by passing arguments
               departmentCodes.add(DepartmentCode);//adds to the array list
           }

       } 
      catch (Exception e) {//catches errors
           e.printStackTrace();
       }
       return departmentCodes;//returns array lsit
   }
         public void analysisTable()//function to show the data from SQL into the JTable
   {
       ArrayList<inventoryIssueItem> list = getInventoryList();//getting inventory list
       ArrayList<departmentCode> list2 = getDepartmentCodes();//getting department code list
       DefaultTableModel model = (DefaultTableModel)tblAnalysis.getModel();//DefaultTableModels allows for a varying length of rows
       Object[] row = new Object[4];//4 columns for each row of data
       for(int i = 0; i < list2.size(); i++)//to traverse through all the Inventory and add them to the table
       {
           row[0] = list2.get(i).getDepartment();//sets the value in the first column as the Department
           row[1] = list2.get(i).getSection();//sets the value in the second column to section
           row[2] = findTotalPriceDepartmentSection(list2.get(i).getDepartment(), list2.get(i).getSection());//finding total expenditure of each department and inputting into the third column
           model.addRow(row);//adds it as a row
       }
       tblAnalysis.setAutoCreateRowSorter(true);//to allow for sorting
    }
         private double findTotalPriceDepartmentSection(String Department, String Section){//finds the expenditure of each section with a department
             ArrayList<inventoryIssueItem> list = getInventoryList();//gets Inventory list
             double total = 0;//total
             for(int i = 0; i < list.size(); i++)//to traverse through all the Suppliers and add them to the table
       {
           if(Department.equals(list.get(i).getDepartment()) && Section.equals(list.get(i).getSection())){//checks if the department matches that of the argument
           total = total + list.get(i).getTotalPrice();//adding to the total
             }
             }
             return total;//returning total
         }
         
         private double findTotalPriceDepartment(String Department){//finds total expenditure of entire department
             ArrayList<inventoryIssueItem> list = getInventoryList();//gets inventory list
             double total = 0;
             for(int i = 0; i < list.size(); i++)//to traverse through all the Invenotry and adds them to the table
       {
           if(Department.equals(list.get(i).getDepartment())){//compares the Department in the list to that of the argument
           total = total + list.get(i).getTotalPrice();//adding to the total
             }
             }
             return total;//returning total
         }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAnalysis = new javax.swing.JTable();
        btnPieChart = new javax.swing.JButton();
        panelChart = new javax.swing.JPanel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tblAnalysis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Department", "Section", "Total Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblAnalysis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblAnalysisMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblAnalysis);

        btnPieChart.setText("Show Pie Chart");
        btnPieChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPieChartActionPerformed(evt);
            }
        });

        panelChart.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(btnPieChart)
                        .addGap(155, 155, 155))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPieChart)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblAnalysisMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAnalysisMousePressed

    }//GEN-LAST:event_tblAnalysisMousePressed

    private void btnPieChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPieChartActionPerformed
       DefaultPieDataset pieDataSet = new DefaultPieDataset();//creates a data set for the pie chart
       ArrayList<departmentCode> list = getDepartmentCodes();//gets an array list of department codes
       for(int i = 0; i < list.size(); i++)//to traverse through all the Department Codes
       {
           double totalPrice = findTotalPriceDepartment(list.get(i).getDepartment());//stores the total expenditure by each department
           pieDataSet.setValue(list.get(i).getDepartment(), new Double(totalPrice));//sets values for pie chart
       }
       JFreeChart pieChart = ChartFactory.createPieChart("Department Analysis", pieDataSet, true, true, true);//creates pie chart
       ChartPanel piePanel = new ChartPanel(pieChart);//displays pie chart
       panelChart.removeAll();//removes all other components
       panelChart.add(piePanel);//adds the pie chart
       panelChart.validate();//validates the pie chart
    }//GEN-LAST:event_btnPieChartActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(departmentAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(departmentAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(departmentAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(departmentAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {//run
                new departmentAnalysis().setVisible(true);//sets the frame as visible
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPieChart;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelChart;
    private javax.swing.JTable tblAnalysis;
    // End of variables declaration//GEN-END:variables

    
}
