
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;//imported Libraries

public class issueInventory extends javax.swing.JFrame {

    public issueInventory() {//issueInventory
        initComponents();//components
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());//date
        tfDate.setText(date);//sets the date textfield to the current date
        issueInventoryTable();//shows the inventory table
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
        
        public ArrayList<inventoryIssueItem> getInventoryList()//function to get a list of all inventoryIssueItems from the SQL Table
   {
       ArrayList<inventoryIssueItem> issueInventoryList = new ArrayList<inventoryIssueItem>();//implements ArrayList of type 'inventoryIssueItem'
       Connection connection = getConnection();//connection
       
       String query = "SELECT * FROM  `tblinventoryissue`";//SQL Language
       Statement statement;//statement
       ResultSet resultSet;//result set
       
       try {//try-catch statements have to be implemented for SQL Searches (makes it easier to identify problems)
           statement = connection.createStatement();//creates statement for connection
           resultSet = statement.executeQuery(query);//executes statement in the result set

           
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
        
         public void issueInventoryTable()//function to show the data from SQL into the JTable
   {
       ArrayList<inventoryIssueItem> list = getInventoryList();//Using the previous function
       DefaultTableModel model = (DefaultTableModel)tblInventoryIssue.getModel();//DefaultTableModels allows for a varying length of rows
       model.setRowCount(0);//initialize table
       Object[] row = new Object[9];//reach row has 9 columns
       for(int i = 0; i < list.size(); i++)//to traverse through all the inventoryIssueItems and add them to the table
       {
           row[0] = list.get(i).getDate();//sets the value in the first column as the date
           row[1] = list.get(i).getISN();//sets the value in the first column as the ISN
           row[2] = list.get(i).getInventoryCode();//sets the value in the third column as the Inventory Code
           row[3] = list.get(i).getQuantity();//sets the value in the fourth column as the Quantity
           row[4] = list.get(i).getMachineNumber();//sets the value in the fifth column as the Machine Code
           row[5] = list.get(i).getDepartment();//sets the value in the sixth column as the Department
           row[6] = list.get(i).getSection();//sets the value in the seventh column as the Section
           row[7] = list.get(i).getCostCode();//sets the value in the eigh column as the Code Code
           row[8] = list.get(i).getTotalPrice();//sets the value in the ninth column as the Total Price
           model.addRow(row);//adds it as a row
       }
       tblInventoryIssue.setAutoCreateRowSorter(true);//allows for sorting
    }
      public void issueDepartmentSpecificInventoryTable(String userDepartment)//function to show the data from SQL into the JTable specific to the User's choice of department
   {
       ArrayList<inventoryIssueItem> list = getInventoryList();//getting an arraylist for inventoryIssueItems
       DefaultTableModel model = (DefaultTableModel)tblInventoryIssue.getModel();//DefaultTableModels allows for a varying length of rows
       model.setRowCount(0);//initialise table
       Object[] row = new Object[9];//each row has 9 columns
       for(int i = 0; i < list.size(); i++)//to traverse through all the inventoryIssueItems and add them to the table
       {
           row[0] = list.get(i).getDate();//sets the value in the first column as the date
           row[1] = list.get(i).getISN();//sets the value in the first column as the ISN
           row[2] = list.get(i).getInventoryCode();//sets the value in the third column as the Inventory Code
           row[3] = list.get(i).getQuantity();//sets the value in the fourth column as the Quantity
           row[4] = list.get(i).getMachineNumber();//sets the value in the fifth column as the Machine Code
           row[5] = list.get(i).getDepartment();//sets the value in the sixth column as the Department
           row[6] = list.get(i).getSection();//sets the value in the seventh column as the Section
           row[7] = list.get(i).getCostCode();//sets the value in the eigh column as the Code Code
           row[8] = list.get(i).getTotalPrice();//sets the value in the ninth column as the Total Price
           if(list.get(i).getDepartment().equals(userDepartment)){//checks if the department of the data set is as the user requested
           model.addRow(row);//adds it as a row
           }
       }
       tblInventoryIssue.setAutoCreateRowSorter(true);//allows for sorting
    }   
         
         public void executeSQLQuery(String query, String message)//function used for a dual purpose, sending a query to the SQL Table and to display a popup depending if the former was done successfuly or not
   {
       Connection con = getConnection();//Decomposition
       Statement st;
       try{
           st = con.createStatement();
           if((st.executeUpdate(query)) == 1)//checks if the query has happened
           {
               DefaultTableModel model = (DefaultTableModel)tblInventoryIssue.getModel();//creates a new table
               model.setRowCount(0);//initialises this table
               issueInventoryTable();//reloads this data back into the JTable
               JOptionPane.showMessageDialog(null, "Data "+message+" Successfully");//creates a pop up to indicate the request was done successfully
           }else{
               JOptionPane.showMessageDialog(null, "Data Not "+message);//popup to show the request was not completed
           }
       }catch(Exception ex){//for errors
           ex.printStackTrace();
       }
   }
    private boolean intblInventoryCode() {//checks if the user-entered data in in tblInventoryCode
       boolean exists = false;//sets exists as false
             String userCode = tfInventoryCode.getText();//gets user data
             String query = "SELECT * FROM  `tblinventorycode`";//SQL query
             Connection connection = getConnection();//connection
       Statement statement;//statement
       ResultSet resultSet;//result set
       
       try {
           statement = connection.createStatement();//statement for connection
           resultSet = statement.executeQuery(query);//statement for result set
           while(resultSet.next())//traverses through the result set
           {
               String inventoryCode = resultSet.getString("Inventory Code");//gets value associated with inventory code
               if(inventoryCode.equals(userCode)){//checks if the code is the same as that of the user
                   exists = true;//sets true
                   break;//break
               }
           }

       } 
      catch (Exception e) {//for errors
           e.printStackTrace();
       }
             return exists;//return
    }
    private boolean intblDepartmentCode() {//checks if the department entered has been pre-defined
       boolean exists = false;//sets exists as false
             String department = tfDepartment.getText();//gets user data
             String query = "SELECT * FROM  `tbldepartmentcode`";//SQL query
             Connection connection = getConnection();//connection
       Statement statement;//statement
       ResultSet resultSet;//result set
       
       try {
           statement = connection.createStatement();//statement for connection
           resultSet = statement.executeQuery(query);//statement for result set
           while(resultSet.next())//traverses through the result set
           {
               String inventoryCode = resultSet.getString("Department");//gets value associated with department
               if(inventoryCode.equals(department)){//checks if the code is the same as that of the user
                   exists = true;//sets true
                   break;//break
               }
           }

       } 
      catch (Exception e) {//for errors
           e.printStackTrace();
       }
             return exists;//return
    }
    private boolean intblDepartmentSectionCode() {//checks if the section entered has been pre-defined
       boolean exists = false;//sets exists as false
             String Section = tfSection.getText();//gets text from the textfield
             String query = "SELECT * FROM  `tbldepartmentcode`";//SQL query
             Connection connection = getConnection();//connection
       Statement statement;//statement
       ResultSet resultSet;//result set
;       
       try {
           statement = connection.createStatement();//statement for connection
           resultSet = statement.executeQuery(query);//statement for result set
           while(resultSet.next())//traverses through result set
           {
               String section = resultSet.getString("Section");//gets the value associated with 'section'
               if(section.equals(Section)){//checks if this is the same as the user entered section
                   exists = true;//sets true
                   break;//break
               }
           }
       } 
      catch (Exception e) {//for errors
           e.printStackTrace();
       }
             return exists;
    }
    private boolean intblCostCode() {//checks if the user entered cost code has been pre-defined
       boolean exists = false;//sets exists as false
             String userCostCode = tfCostCode.getText();//gets user data
             String query = "SELECT * FROM  `tblcostheading`";//SQL query
             Connection connection = getConnection();//connection
       Statement statement;//statement
       ResultSet resultSet;//result set
       
       try {
           statement = connection.createStatement();//statement for connection
           resultSet = statement.executeQuery(query);//statement for result set
           while(resultSet.next())//traverses through the result set
           {
               String inventoryCode = resultSet.getString("Cost Code");//gets value associated with cost code
               if(inventoryCode.equals(userCostCode)){//checks if the code is the same as that of the user
                   exists = true;//sets true
                   break;//break
               }
           }

       } 
      catch (Exception e) {//for errors
           e.printStackTrace();
       }
             return exists;//return
    }
    public boolean quantityAvailable(String IC, int Quantity){//checks if there is quantity available for a given inventory code
      
        int QAvailable = FindTotalQuantity(IC);//decomposition
        
        if(QAvailable>=Quantity){//check statement
            return true;//true
        }else{
        return false;//false
        }
        }
    

    private int FindTotalQuantity(String IC) {//finds total quantity available for an inventory code
             String query = "SELECT * FROM  `tblinventorydetails`";//SQL query
             Connection connection = getConnection();//connection
       Statement statement;//statement
       ResultSet resultSet;//result set
       int totalQuantity = 0;//initialising quantity
       
       try {
           int temporaryQuantity;//initialising a different quantity variable to store temporary quantities
           statement = connection.createStatement();//statement for the connection
           resultSet = statement.executeQuery(query);//statement for the result set
           while(resultSet.next())//traversing the result set
           {
               String inventoryCode = resultSet.getString("Inventory Code");//gets value associated with the inventory code
               if(inventoryCode.equals(IC)){//checks if this inventory code is that same as that of the argument
                  temporaryQuantity = resultSet.getInt("Quantity");//gets the value associated with the quantity
                  totalQuantity = totalQuantity + temporaryQuantity;//adds to the total quantity
               }
           }
       } 
      catch (Exception e) {//for errors
           e.printStackTrace();
       }
             return totalQuantity;//return
    }  
 
    
    private boolean isAvailable(String InventoryCode){//checks if there is any quantity of a given inventory
        boolean isAvailable = false;//sets it as false initially
             String userCode = tfInventoryCode.getText();//gets text
             String query = "SELECT * FROM  `tblinventorydetails`";//SQL query
             Connection connection = getConnection();//connection
       Statement statement;//statement
       ResultSet resultSet;//resultSet
       
       try {
           statement = connection.createStatement();//statement for connectoin
           resultSet = statement.executeQuery(query);//statement for result set
           while(resultSet.next())//traverses the result set
           {
               String inventoryCode = resultSet.getString("Inventory Code");//gets value associated with inventory code
               if(inventoryCode.equals(userCode)){//checks if this value is equal to the value from the user
                   isAvailable = true;//sets true
               }
           }

       } 
      catch (Exception e) {//error
           e.printStackTrace();
       }
             return isAvailable;//return
    }
    
    private double executeIssue(String InventoryCode, int Quantity){//executes the Issue
        String query = "SELECT * FROM  `tblinventorydetails`";//SQL query
             Connection connection = getConnection();//connection
       Statement statement1;//statement1
       Statement statement2;//statement2
       Statement statement3;//statement3
       Statement statement4;//statement4
       ResultSet resultSet;//result set
       int totalQuantity = 0;//total quantity
       double totalPrice = 0;//total price
       try {
           statement1 = connection.createStatement();//statement1 for the connection
           statement2 = connection.createStatement();//statement2 for the connection
           statement3 = connection.createStatement();//statement3 for the connection
           statement4 = connection.createStatement();//statement3 for the connection
           resultSet = statement1.executeQuery(query);//statement1 for the result set
           int quantity;//quantity
           double temporaryTotalPrice;//temporary total price
           double unitPrice;//unit price
           double temporaryTotalPricePerIssue;//temporary total price per issue
           while(resultSet.next() == true)//traverses through result set
           {
               String inventoryCode = resultSet.getString("Inventory Code");//gets the value associated with inventory code
               if(inventoryCode.equals(InventoryCode)){//checks if the value matches that of the argument
                  quantity = resultSet.getInt("Quantity");//gets the value associated with the quantity
                  temporaryTotalPrice = resultSet.getDouble("Total Price");//gets the total price of the issue
                  unitPrice = resultSet.getDouble("Unit Price");//gets the unit price of the issue
                  int temp = Quantity - quantity;//temp is the remaining number of quantity of inventory left to give to the user
                  if(temp>0){//if there is still an excess in the order
                  totalQuantity = totalQuantity+quantity;//increases the total quantity according to the order
                  totalPrice = totalPrice + temporaryTotalPrice;//increases the total price according to the order
                  String query2 = "DELETE FROM `tblinventorydetails` WHERE `Inventory Code` = '"+InventoryCode + "' AND `RSN` = '" + resultSet.getString("RSN") + "'";//SQL Query
                  statement2.executeUpdate(query2);//executes the query
                  Quantity = Quantity - quantity;//decreases required quantity to supply
                  
                  }else if(temp<=0){//if the quantity happens to be exceeded by a quantity aassociated with the item
                      totalQuantity = totalQuantity+Quantity;//adds to the totalQuantity
                      temporaryTotalPricePerIssue = Quantity*unitPrice;//finds the total price according to the temporary inventory stock in question
                      totalPrice = totalPrice + temporaryTotalPricePerIssue;//adds this to the total price
                      quantity = quantity - Quantity;//decreases quantity
                   String query3 = "UPDATE `tblinventorydetails` SET `Date`='"+resultSet.getString("Date")+"',`RSN`='"+resultSet.getString("RSN")+"',`Inventory Code`='" + InventoryCode + "',`Lot`='" + resultSet.getInt("Lot") + "',`Part Description`='" + resultSet.getString("Part Description") + "',`Quantity`='" + quantity +  "',`Unit Price`='" + resultSet.getDouble("Unit Price") + "',`Total Price`='" + (quantity*resultSet.getDouble("Unit Price")) + "' WHERE `RSN`= '"+resultSet.getString("RSN")+"' AND `Inventory Code`= '"+ InventoryCode +"'";//SQL query
                   statement3.executeUpdate(query3);//executes the query
                   if(temp == 0){
                  String query4 = "DELETE FROM `tblinventorydetails` WHERE `Inventory Code` = '"+InventoryCode + "' AND `RSN` = '" + resultSet.getString("RSN") + "'";//SQL Query
                  statement4.executeUpdate(query4);//executes the query
                   }
                   break;
                  }
               }
           }

       }catch (Exception e) {//for errors
           e.printStackTrace();
       }
       return totalPrice;//returns the total price of the orer
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventoryIssue = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnDefault = new javax.swing.JButton();
        btnDepartmentAnalysis = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInventoryIssue1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblISN = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        tfDate = new javax.swing.JTextField();
        tfISN = new javax.swing.JTextField();
        tfInventoryCode = new javax.swing.JTextField();
        lblInventoryCode = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblMachineCode = new javax.swing.JLabel();
        lblQuantity = new javax.swing.JLabel();
        tfQuantity = new javax.swing.JTextField();
        tfMachineCode = new javax.swing.JTextField();
        tfDepartment = new javax.swing.JTextField();
        lblDepartment = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblCostCode = new javax.swing.JLabel();
        lblSection = new javax.swing.JLabel();
        tfSection = new javax.swing.JTextField();
        tfCostCode = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        PriceShow = new javax.swing.JButton();
        tfValue = new javax.swing.JTextField();
        lblbaht = new javax.swing.JLabel();
        DepartmentShow = new javax.swing.JButton();
        tfUserDepartment = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1000, 540));
        setPreferredSize(new java.awt.Dimension(1000, 590));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblInventoryIssue.setModel(new javax.swing.table.DefaultTableModel(
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
        tblInventoryIssue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblInventoryIssueMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblInventoryIssue);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 900, 329));

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 490, 76, 50));

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 490, 76, 50));

        btnDefault.setText("Default");
        btnDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDefaultActionPerformed(evt);
            }
        });
        getContentPane().add(btnDefault, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 490, -1, 50));

        btnDepartmentAnalysis.setText("Department Analysis");
        btnDepartmentAnalysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepartmentAnalysisActionPerformed(evt);
            }
        });
        getContentPane().add(btnDepartmentAnalysis, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 380, 180, 86));

        tblInventoryIssue1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblInventoryIssue1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblInventoryIssue1MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblInventoryIssue1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 900, 329));

        lblISN.setText("ISN:");

        lblDate.setText("Date:");

        tfDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDateActionPerformed(evt);
            }
        });

        tfISN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfISNActionPerformed(evt);
            }
        });

        tfInventoryCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfInventoryCodeActionPerformed(evt);
            }
        });

        lblInventoryCode.setText("Inventory Code:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblISN)
                    .addComponent(lblDate)
                    .addComponent(lblInventoryCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfInventoryCode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfISN, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(tfDate)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(tfDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblISN)
                    .addComponent(tfISN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfInventoryCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInventoryCode))
                .addGap(6, 6, 6))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, -1, -1));

        lblMachineCode.setText("Machine Code:");

        lblQuantity.setText("Quantity:");

        tfQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfQuantityActionPerformed(evt);
            }
        });

        tfMachineCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMachineCodeActionPerformed(evt);
            }
        });

        tfDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDepartmentActionPerformed(evt);
            }
        });

        lblDepartment.setText("Department:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMachineCode)
                    .addComponent(lblQuantity)
                    .addComponent(lblDepartment))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfMachineCode, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(tfQuantity)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantity)
                    .addComponent(tfQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMachineCode)
                    .addComponent(tfMachineCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDepartment))
                .addGap(6, 6, 6))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, -1, -1));

        lblCostCode.setText("Cost Code:");

        lblSection.setText("Section:");

        tfSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSectionActionPerformed(evt);
            }
        });

        tfCostCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCostCodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCostCode)
                    .addComponent(lblSection))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfCostCode, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(tfSection))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSection)
                    .addComponent(tfSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCostCode)
                    .addComponent(tfCostCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 370, -1, -1));

        PriceShow.setText("Show Issues over");
        PriceShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriceShowActionPerformed(evt);
            }
        });

        tfValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfValueActionPerformed(evt);
            }
        });

        lblbaht.setText("baht");

        DepartmentShow.setText("Only show Issues in Department");
        DepartmentShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DepartmentShowActionPerformed(evt);
            }
        });

        tfUserDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUserDepartmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addComponent(PriceShow)
                .addGap(7, 7, 7)
                .addComponent(tfValue, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblbaht)
                .addGap(19, 19, 19)
                .addComponent(DepartmentShow)
                .addGap(7, 7, 7)
                .addComponent(tfUserDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PriceShow)
                    .addComponent(tfValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblbaht, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DepartmentShow)
                        .addComponent(tfUserDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 60, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfUserDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUserDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUserDepartmentActionPerformed

    private void DepartmentShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DepartmentShowActionPerformed
        issueDepartmentSpecificInventoryTable(tfUserDepartment.getText());
    }//GEN-LAST:event_DepartmentShowActionPerformed

    private void tfValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfValueActionPerformed

    private void PriceShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriceShowActionPerformed
        new priceShow().setVisible(true);
    }//GEN-LAST:event_PriceShowActionPerformed

    private void tfCostCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCostCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCostCodeActionPerformed

    private void tfSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSectionActionPerformed

    private void tfDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDepartmentActionPerformed

    private void tfMachineCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMachineCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMachineCodeActionPerformed

    private void tfQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfQuantityActionPerformed

    private void tfInventoryCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfInventoryCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfInventoryCodeActionPerformed

    private void tfISNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfISNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfISNActionPerformed

    private void tfDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDateActionPerformed

    private void tblInventoryIssue1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventoryIssue1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblInventoryIssue1MousePressed

    private void btnDepartmentAnalysisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepartmentAnalysisActionPerformed
        new departmentAnalysis().setVisible(true);//if the user clicks department analysis
    }//GEN-LAST:event_btnDepartmentAnalysisActionPerformed

    private void btnDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDefaultActionPerformed
        issueInventoryTable();//defaults the table
    }//GEN-LAST:event_btnDefaultActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int i = tblInventoryIssue.getSelectedRow();//gets data of a selected row
        TableModel model = tblInventoryIssue.getModel();//gets the table
        String query = "DELETE FROM `tblinventoryissue` WHERE `ISN`= '"+model.getValueAt(i,1).toString()+"' AND `Inventory Code`= '"+ model.getValueAt(i,2).toString() + "'";//creates an SQL query to delete a specific entry

        executeSQLQuery(query, "Deleted");//executes the SQL function
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        boolean codeExists = intblInventoryCode();//checks if the inventory code exists
        boolean departmentExists = intblDepartmentCode();//checks if the department exists
        boolean sectionExists = intblDepartmentSectionCode();//checks if the section exists
        boolean costCodeExists = intblCostCode();//checks if the cost code exists
        boolean quantityExists = quantityAvailable(tfInventoryCode.getText(), Integer.valueOf(tfQuantity.getText()));//checks if there is enough quantity

        if(tfDate.getText().matches("\\d{2}-\\d{2}-\\d{4}") && tfISN.getText().matches("\\d{7}") && quantityExists == true && codeExists == true && departmentExists == true && sectionExists == true && costCodeExists == true){//important validation check, for all possible factors
            String query = "INSERT INTO `tblinventoryissue`(`Date`, `ISN`, `Inventory Code`, `Quantity`, `Machine Number`, `Department`, `Section`, `Cost Code`, `Total Price`) VALUES ('"+tfDate.getText()+"','"+tfISN.getText()+"','"+tfInventoryCode.getText()+"','"+tfQuantity.getText()+"','"+tfMachineCode.getText()+"','"+tfDepartment.getText()+"','"+tfSection.getText()+"','"+tfCostCode.getText()+"','"+executeIssue(tfInventoryCode.getText(), Integer.parseInt(tfQuantity.getText()))+"')";//SQL query
            executeSQLQuery(query, "Inserted");//executes the SQL query
        }else if(!tfDate.getText().matches("\\d{2}-\\d{2}-\\d{4}")){//validation for date
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a Date in the form DD-MM-YYYY");//popup to inform the user
        }else if(!tfISN.getText().matches("\\d{7}")){//validation for ISN
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid (7-digit) ISN number");//popup to inform the user
        }else if(quantityExists == false){//validation for if there isn't enough inventory
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"There is/are only " + FindTotalQuantity(tfInventoryCode.getText()) + " unit(s) of this inventory available, please revise your order");//popup to inform the user
        }else if(codeExists == false){//validation for whether the inventory code exists
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid, pre-defined Inventory Code");//popup to inform the user
        }else if(departmentExists == false){//validation for if the department exists
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid, pre-defined Department Code");//popup to inform the user
        }else if(sectionExists == false){//validation for if the section exists
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid, pre-defined Section Code");//popup to inform the user
        }else if(costCodeExists == false){//validation for if the cost code exists
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid, pre-defined Cost Code");//popup to inform the user
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblInventoryIssueMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventoryIssueMousePressed

        int i = tblInventoryIssue.getSelectedRow();//gets the selected row
        TableModel model = tblInventoryIssue.getModel();//gets the values associated with a specific row

        //sets the value in that row to the textfields - allowing for easier editing
        tfDate.setText(model.getValueAt(i,0).toString());
        tfISN.setText(model.getValueAt(i,1).toString());
        tfInventoryCode.setText(model.getValueAt(i,2).toString());
        tfQuantity.setText(model.getValueAt(i,3).toString());
        tfMachineCode.setText(model.getValueAt(i,4).toString());
        tfDepartment.setText(model.getValueAt(i,5).toString());
        tfSection.setText(model.getValueAt(i,6).toString());
        tfCostCode.setText(model.getValueAt(i,7).toString());
    }//GEN-LAST:event_tblInventoryIssueMousePressed

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
            java.util.logging.Logger.getLogger(issueInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(issueInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(issueInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(issueInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {//run
                new issueInventory().setVisible(true);//sets true
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DepartmentShow;
    private javax.swing.JButton PriceShow;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDefault;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDepartmentAnalysis;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCostCode;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDepartment;
    private javax.swing.JLabel lblISN;
    private javax.swing.JLabel lblInventoryCode;
    private javax.swing.JLabel lblMachineCode;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblSection;
    private javax.swing.JLabel lblbaht;
    private javax.swing.JTable tblInventoryIssue;
    private javax.swing.JTable tblInventoryIssue1;
    private javax.swing.JTextField tfCostCode;
    private javax.swing.JTextField tfDate;
    private javax.swing.JTextField tfDepartment;
    private javax.swing.JTextField tfISN;
    private javax.swing.JTextField tfInventoryCode;
    private javax.swing.JTextField tfMachineCode;
    private javax.swing.JTextField tfQuantity;
    private javax.swing.JTextField tfSection;
    private javax.swing.JTextField tfUserDepartment;
    public static javax.swing.JTextField tfValue;
    // End of variables declaration//GEN-END:variables

}
