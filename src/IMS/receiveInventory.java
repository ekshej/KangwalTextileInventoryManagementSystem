package IMS;
//imports
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
import javax.swing.table.TableModel;

public class receiveInventory extends javax.swing.JFrame {

    public receiveInventory() {//receive inventory
        initComponents();//components
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());//gets the current date
        tfDate.setText(date);//sets the date into the textfield
        receiveInventoryTable();//shows the inventory table
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
        
         public ArrayList<inventoryItem> getInventoryList()//function to get a list of all the inventoryItems from the SQL Table
   {
       ArrayList<inventoryItem> receiveInventoryList = new ArrayList<inventoryItem>();//implements ArrayList of type 'inventoryItem'
       Connection connection = getConnection();//connection
       
       String query = "SELECT * FROM  `tblinventoryreceive`";//SQL Language
       Statement statement;//statement
       ResultSet resultSet;//result set
       
       try {//try-catch statements have to be implemented for SQL Searches (makes it easier to identify problems)
           statement = connection.createStatement();//statement for connection
           resultSet = statement.executeQuery(query);//statement for resultset

           
           inventoryItem InventoryItem;//creating an object of type inventoryItem

           while(resultSet.next())//this traverses through all the results from the tblsupplierlist
           {
InventoryItem = new inventoryItem(resultSet.getString("Date"),resultSet.getString("RSN"),resultSet.getString("Inventory Code"),resultSet.getInt("Quantity"),resultSet.getDouble("Unit Price"),resultSet.getDouble("Total Price"));//assigning values to the object
               receiveInventoryList.add(InventoryItem);//adds the object to the ArrayList
           }

       } 
      catch (Exception e) {//catch any errors
           e.printStackTrace();
       }
       return receiveInventoryList;//returns the Array List
   }
        
         public void receiveInventoryTable()//function to show the data from SQL into the JTable
   {
       ArrayList<inventoryItem> list = getInventoryList();//Using the previous function
       DefaultTableModel model = (DefaultTableModel)tblInventoryReceive.getModel();//DefaultTableModels allows for a varying length of rows
       Object[] row = new Object[6];//each row has six columns
       for(int i = 0; i < list.size(); i++)//to traverse through all the inventoryItem arraylist and add them to the table
       {
           row[0] = list.get(i).getDate();//sets the value in the first column as the Date
           row[1] = list.get(i).getRSN();//sets the value in the second column as the RSN
           row[2] = list.get(i).getInventoryCode();//sets the value in the third column as the Inventory Code
           row[3] = list.get(i).getQuantity();//sets the value in the fourth column as the Quantity
           row[4] = list.get(i).getUnitPrice();//sets the value in the fifth column as the Unit Price
           row[5] = list.get(i).getTotalPrice();//sets the value in the sixth column as the Total Price
           model.addRow(row);//adds it as a row
       }
       tblInventoryReceive.setAutoCreateRowSorter(true);//allows for data to be sorted
    }
         
         public void executeSQLQuery(String query, String message)//function used for a dual purpose, sending a query to the SQL Table and to display a popup depending if the former was done successfuly or not
   {
       Connection connection = getConnection();//Decomposition
       Statement statement;
       try{
           statement = connection.createStatement();
           if((statement.executeUpdate(query)) == 1)//checks if the query has happened
           {
               DefaultTableModel model = (DefaultTableModel)tblInventoryReceive.getModel();//creates a new table
               model.setRowCount(0);//initialises this table
               receiveInventoryTable();//reloads this data back into the JTable
               JOptionPane.showMessageDialog(null, "Data "+message+" Successfully");//creates a pop up to indicate the request was done successfully
               String IC = tfInventoryCode.getText();
               String rsn = tfRSN.getText();
               int lot = getLot(IC, rsn);
               String PD = getPD(String.valueOf(tfInventoryCode.getText()));
               String query2 = "INSERT INTO `tblinventorydetails`(`Date`, `RSN`, `Inventory Code`, `Lot`, `Part Description`, `Quantity`, `Unit Price`, `Total Price`) VALUES ('"+tfDate.getText()+"','"+tfRSN.getText()+"','"+tfInventoryCode.getText()+"','"+lot+"','"+PD+"','"+tfQuantity.getText()+"','"+tfUnitPrice.getText()+"','"+tfTotalPrice.getText()+"')";
               executeAlternateSQLQuery(query2);
           }else{
               JOptionPane.showMessageDialog(null, "Data Not "+message);//popup to show the request was not completed
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
         public void executeUpdateSQLQuery(String query, String initialRSN, String initialIC, String message)//function used for a dual purpose, sending a query to the SQL Table and to display a popup depending if the former was done successfuly or not
   {
       Connection connection = getConnection();//Decomposition
       Statement statement;
       
       try{
           statement = connection.createStatement();//statement for connection
           if((statement.executeUpdate(query)) == 1)//checks if the query has happened
           {
               DefaultTableModel model = (DefaultTableModel)tblInventoryReceive.getModel();//creates a new table
               model.setRowCount(0);//initialises this table
               receiveInventoryTable();//reloads this data back into the JTable
               JOptionPane.showMessageDialog(null, "Data "+message+" Successfully");//creates a pop up to indicate the request was done successfully
               String IC = tfInventoryCode.getText();
               String rsn = tfRSN.getText();
               int lot = getLot(IC, rsn);
               String PD = getPD(IC);
               String query2 = "UPDATE `tblinventorydetails` SET `Date`='"+tfDate.getText()+"',`RSN`='"+tfRSN.getText()+"',`Inventory Code`='" + tfInventoryCode.getText() + "',`Lot`='" + lot + "',`Part Description`='" + PD + "',`Quantity`='" + tfQuantity.getText() + "',`Unit Price`='" + tfUnitPrice.getText() + "',`Total Price`='" + tfTotalPrice.getText() + "' WHERE `RSN`= '"+initialRSN+"' AND `Inventory Code`= '"+ initialIC +"'";
               executeAlternateSQLQuery(query2);
           }else{
               JOptionPane.showMessageDialog(null, "Data Not "+message);//popup to show the request was not completed
           }
       }catch(Exception ex){//errors
           ex.printStackTrace();
       }
         }     
         public void executeAlternateSQLQuery(String query){//function to execute an alternate query
       Connection connection = getConnection();//Decomposition
       Statement statement;//statement
       try{
           statement = connection.createStatement();//statement for connection
           statement.executeUpdate(query);//executing the query
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
    public int getLot(String inventoryCode, String rSN){//gets the 'lot' number of the inventory code and rsn in question
           ArrayList<inventoryItem> list = getInventoryList();//gets the inventory item list
           int inventoryCount = 0;//initialises the inventoryCount
           int lot = 0;//initialises the lot
           for(int i=0; i<list.size(); i++){//traverses through the array list
               String InventoryCode = list.get(i).getInventoryCode();//gets the inventory code associated with the data set
               if(inventoryCode.equals(InventoryCode) == true){//checks if this matches the one specificed by the user
                   inventoryCount++;//increments if true
                   if(rSN.equals(list.get(i).getRSN()) == true){//checks if the rsn matches the one specified by the user
                       lot = inventoryCount;//increments if true
                       break;//break
                   }
               }
               
           }
           return lot;//return
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
    
    public String getPD(String InventoryCode){//gets the part description of the inventory code in question
        String PD = null;//initializing the part description
        ArrayList<inventoryCode> list = getInventoryCodesList();//gets the arraylist for inventory codes
        for(int j=0; j<list.size(); j++){//traverses through the arraylist
               String inventoryCode = String.valueOf(list.get(j).getCode());//gets the inventory code in question
               if(InventoryCode.equals(inventoryCode)){//if that inventory code is equal to the user specified one, then:
           PD = list.get(j).getPartDescription();//get the respective part description
           break;//break
               }
           }
        return PD;//return the part description
    }
    
    public boolean uniqueInventoryCode(String rsn, String inventoryCode){//checks if the rsn has a unique inventory code
        ArrayList<inventoryItem> list = getInventoryList();//gets the list of inventory
        for(int j=0; j<list.size(); j++){//traverses through the list
            if(rsn.equals(list.get(j).getRSN())){//checks if the RSN from the list is equal to that specified by the user
               String InventoryCode = list.get(j).getInventoryCode();//gets the inventory code associated with the rsn
               if(inventoryCode.equals(InventoryCode)){//if the user entered inventory code already exists for that RSN
                   return false;//return false
               }
            }
           }
        return true;//return true
    }
    
    private void updateLots(String date, String rsn, String ic, String pd, String quantity, String unitPrice, String totalPrice) {//function to update lots
       Connection connection = getConnection();//connection
       Statement statement;//statement
       int lot = getLot(ic, rsn);//gets the current lot of the inventory code and rsn in question
       String query = "UPDATE `tblinventorydetails` SET `Date`='"+date+"',`RSN`='"+rsn+"',`Inventory Code`='" + ic + "',`Lot`='" + lot + "',`Part Description`='" + pd + "',`Quantity`='" + quantity + "',`Unit Price`='" + unitPrice + "',`Total Price`='" + totalPrice + "' WHERE `RSN`= '"+rsn+"' AND `Inventory Code`= '"+ ic +"'";//SQL query
       try{
           statement = connection.createStatement();//statement for connection
       }catch(Exception ex){//errors
           ex.printStackTrace();
       }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventoryReceive = new javax.swing.JTable();
        lblQuantity = new javax.swing.JLabel();
        tfQuantity = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        tfUnitPrice = new javax.swing.JTextField();
        tfTotalPrice = new javax.swing.JTextField();
        lblTotalPrice = new javax.swing.JLabel();
        lblUnitPrice = new javax.swing.JLabel();
        tfInventoryCode = new javax.swing.JTextField();
        lblInventoryCode = new javax.swing.JLabel();
        lblRSN = new javax.swing.JLabel();
        tfRSN = new javax.swing.JTextField();
        tfDate = new javax.swing.JTextField();
        lblDate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 650));
        setResizable(false);

        tblInventoryReceive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Receive Slip Number (RSN)", "Inventory Code", "Quantity", "Unit Price", "Total Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblInventoryReceive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblInventoryReceiveMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblInventoryReceive);

        lblQuantity.setText("Quantity:");

        tfQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfQuantityActionPerformed(evt);
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

        tfUnitPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUnitPriceActionPerformed(evt);
            }
        });

        tfTotalPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTotalPriceActionPerformed(evt);
            }
        });

        lblTotalPrice.setText("Total Price:");

        lblUnitPrice.setText("Unit Price:");

        tfInventoryCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfInventoryCodeActionPerformed(evt);
            }
        });

        lblInventoryCode.setText("Inventory Code:");

        lblRSN.setText("RSN:");

        tfRSN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfRSNActionPerformed(evt);
            }
        });

        tfDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDateActionPerformed(evt);
            }
        });

        lblDate.setText("Date:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblInventoryCode)
                            .addComponent(lblDate)
                            .addComponent(lblRSN))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfInventoryCode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTotalPrice))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(tfRSN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblUnitPrice))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(tfDate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(lblQuantity)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnUpdate)
                .addGap(15, 15, 15)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuantity)
                    .addComponent(tfQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfRSN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUnitPrice)
                    .addComponent(tfUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRSN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfInventoryCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInventoryCode)
                    .addComponent(lblTotalPrice)
                    .addComponent(tfTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblInventoryReceiveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventoryReceiveMousePressed

        int i = tblInventoryReceive.getSelectedRow();
        TableModel model = tblInventoryReceive.getModel();//gets the values associated with a specific row

        //sets the value in that row to the textfields - allowing for easier editing
        tfDate.setText(model.getValueAt(i,0).toString());
        tfRSN.setText(model.getValueAt(i,1).toString());
        tfInventoryCode.setText(model.getValueAt(i,2).toString());
        tfQuantity.setText(model.getValueAt(i,3).toString());
        tfUnitPrice.setText(model.getValueAt(i,4).toString());
        tfTotalPrice.setText(model.getValueAt(i,5).toString());
    }//GEN-LAST:event_tblInventoryReceiveMousePressed

    private void tfQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfQuantityActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        boolean exists = intblInventoryCode();
        boolean unique = uniqueInventoryCode(String.valueOf(tfRSN.getText()), String.valueOf(tfInventoryCode.getText()));
        int quantity = Integer.valueOf(tfQuantity.getText());
        double unitPrice = Double.parseDouble(tfUnitPrice.getText());
        double totalPrice = Double.parseDouble(tfTotalPrice.getText());
        double realTotalPrice = quantity * unitPrice;
        String query = "INSERT INTO `tblinventoryreceive`(`Date`, `RSN`, `Inventory Code`, `Quantity`, `Unit Price`, `Total Price`) VALUES ('"+tfDate.getText()+"','"+tfRSN.getText()+"','"+tfInventoryCode.getText()+"','"+tfQuantity.getText()+"','"+tfUnitPrice.getText()+"','"+tfTotalPrice.getText()+"')";
        if(tfDate.getText().matches("\\d{2}-\\d{2}-\\d{4}") && tfRSN.getText().matches("\\d{5}") && exists == true && unique == true && totalPrice == realTotalPrice){//important validation check, checks if enetered value is 5 digits
            executeSQLQuery(query, "Inserted");
        }else if(!tfDate.getText().matches("\\d{2}-\\d{2}-\\d{4}")){
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a Date in the form DD-MM-YYYY");//popup to inform the user
        }else if(!tfRSN.getText().matches("\\d{5}")){
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid (5-digit) RSN number");//popup to inform the user
        }else if(exists == false){
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid, pre-defined Inventory Code");//popup to inform the user
        }else if(totalPrice != realTotalPrice){
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter correct values for Unit and Total Price");//popup to inform the user
        }else if(unique == false){
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a unique inventory code for this RSN");//popup to inform the user
        }
        int i = tblInventoryReceive.getSelectedRow();
        TableModel model = tblInventoryReceive.getModel();
        for(i=0; i<model.getRowCount(); i++){
            String PD = getPD(String.valueOf(model.getValueAt(i, 2)));
            updateLots(model.getValueAt(i,0).toString(), model.getValueAt(i,1).toString(), model.getValueAt(i,2).toString(), PD, model.getValueAt(i,3).toString(), model.getValueAt(i,4).toString(), model.getValueAt(i,5).toString());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        //same principles as the Add button, with the only difference being the query generated
        int i = tblInventoryReceive.getSelectedRow();//gets selected row
        TableModel model = tblInventoryReceive.getModel();//gets the model
        String query = "UPDATE `tblinventoryreceive` SET `Date`='"+tfDate.getText()+"',`RSN`='"+tfRSN.getText()+"',`Inventory Code`='" + tfInventoryCode.getText() + "',`Quantity`='" + tfQuantity.getText() + "',`Unit Price`='" + tfUnitPrice.getText() + "',`Total Price`='" + tfTotalPrice.getText() + "' WHERE `RSN`= '"+model.getValueAt(i,1).toString()+"' AND `Inventory Code`= '"+ model.getValueAt(i,2).toString() +"'";
        boolean exists = intblInventoryCode();//checks if the inventory code already exists
        boolean unique = uniqueInventoryCode(String.valueOf(tfRSN.getText()), String.valueOf(tfInventoryCode.getText()));//checks if the inventory code is unique for the RSN
        int quantity = Integer.valueOf(tfQuantity.getText());//gets the quantity from the user
        double unitPrice = Double.parseDouble(tfUnitPrice.getText());//gets the unit price from the user
        double totalPrice = Double.parseDouble(tfTotalPrice.getText());//gets the total price from the user
        double realTotalPrice = quantity * unitPrice;//performs calculation to verify the total price from the user is correct
        if(tfDate.getText().matches("\\d{2}-\\d{2}-\\d{4}") && tfRSN.getText().matches("\\d{5}") && exists == true && unique == true && totalPrice == realTotalPrice){//important validation check, checks every factor
            executeSQLQuery(query, "Inserted");//performs the SQL query
        }else if(!tfDate.getText().matches("\\d{2}-\\d{2}-\\d{4}")){//checks if the date is incorrect
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a Date in the form DD-MM-YYYY");//popup to inform the user
        }else if(!tfRSN.getText().matches("\\d{5}")){//checks if the RSN is incorrect
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid (5-digit) RSN number");//popup to inform the user
        }else if(exists == false){//checks if the inventory code is not defined yet
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a valid, pre-defined Inventory Code");//popup to inform the user
        }else if(totalPrice != realTotalPrice){//checks if the total price values match up
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter correct values for Unit and Total Price");//popup to inform the user
        }else if(unique == false){//checks if the rsn already has the same inventory code under it
            Component frame = null;
            JOptionPane.showMessageDialog(frame,"Please enter a unique inventory code for this RSN");//popup to inform the user
        }
        for(i=0; i<model.getRowCount(); i++){//traverses through rows
            String PD = getPD(String.valueOf(model.getValueAt(i, 2)));//gets the appropriate part description
            updateLots(model.getValueAt(i,0).toString(), model.getValueAt(i,1).toString(), model.getValueAt(i,2).toString(), PD, model.getValueAt(i,3).toString(), model.getValueAt(i,4).toString(), model.getValueAt(i,5).toString());//updates lots accordingly
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int i = tblInventoryReceive.getSelectedRow();//gets data of a selected row
        TableModel model = tblInventoryReceive.getModel();
        String query = "DELETE FROM `tblinventoryreceive` WHERE `RSN`= '"+model.getValueAt(i,1).toString()+"' AND `Inventory Code`= '"+ model.getValueAt(i,2).toString() + "'";//creates an SQL query to delete a specific entry
        String query2 = "DELETE FROM `tblinventorydetails` WHERE `RSN`= '"+model.getValueAt(i,1).toString()+"' AND `Inventory Code`= '"+ model.getValueAt(i,2).toString() + "'";//creates an SQL query to delete a specific entry
        executeSQLQuery(query,"Deleted");//Sends this to the prior function
        executeAlternateSQLQuery(query2);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tfUnitPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUnitPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUnitPriceActionPerformed

    private void tfTotalPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTotalPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTotalPriceActionPerformed

    private void tfInventoryCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfInventoryCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfInventoryCodeActionPerformed

    private void tfRSNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfRSNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRSNActionPerformed

    private void tfDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDateActionPerformed

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
            java.util.logging.Logger.getLogger(receiveInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(receiveInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(receiveInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(receiveInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new receiveInventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblInventoryCode;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblRSN;
    private javax.swing.JLabel lblTotalPrice;
    private javax.swing.JLabel lblUnitPrice;
    private javax.swing.JTable tblInventoryReceive;
    private javax.swing.JTextField tfDate;
    private javax.swing.JTextField tfInventoryCode;
    private javax.swing.JTextField tfQuantity;
    private javax.swing.JTextField tfRSN;
    private javax.swing.JTextField tfTotalPrice;
    private javax.swing.JTextField tfUnitPrice;
    // End of variables declaration//GEN-END:variables
}
