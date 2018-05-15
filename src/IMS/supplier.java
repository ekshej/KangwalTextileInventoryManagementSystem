
package IMS;

public class supplier {//supplier
    
    private String supplierCode;
    private String supplierName;

     public supplier(String SupplierCode, String SupplierName){//defining the object 'Supplier' - in order to make the Array List
        this.supplierCode = SupplierCode;
        this.supplierName = SupplierName;
    }
   
    public String getSupplierCode() {//important for retrieving data
        return supplierCode;
    }

    public String getSupplierName() {//important for retrieving data
        return supplierName;
    }
   
}
