package IMS;
public class inventoryCode {//inventory code
    
    private String code;//code
    private String partDescription;//part description
    private String supplierCode;//supplier code

     public inventoryCode(String Code, String PartDescription, String SupplierCode){//constructor for inventoryCode
        this.code = Code;
        this.partDescription = PartDescription;
        this.supplierCode = SupplierCode;
    }
   
    public String getCode() {//gets code
        return code;
    }
    public String getPartDescription() {//gets part description
        return partDescription;
    }
    public String getSupplierCode() {//gets supplier code
        return supplierCode;
    }
}
