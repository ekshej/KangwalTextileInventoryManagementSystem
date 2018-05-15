package IMS;

public class inventoryIssueItem {//inventoryIssueItem
        public String date;//date
	public String inventoryCode;//inventory code
	public String iSN;//ISN
	public int quantity;//quantity
	public String machineNumber;//machine number
	public String department;//department
        public String section;//section
        public String costCode;//cost code
        public double totalPrice;//total price
	
        public inventoryIssueItem(String Date, String ISN, String InventoryCode, int Quantity, String MachineNumber, String Department, String Section, String CostCode, double TotalPrice){//constructor for inventoryIssueItem'
        this.date = Date;
        this.iSN = ISN;
        this.inventoryCode = InventoryCode;
        this.quantity = Quantity;
        this.machineNumber = MachineNumber;
        this.department = Department;
        this.section = Section;
        this.costCode = CostCode;
        this.totalPrice = TotalPrice;
       
    }
	public String getDate() {//returns date
		return date;
	}

	public String getISN() {//returns ISN
		return iSN;
        }

	public String getInventoryCode() {//returns inventory code
		return inventoryCode;
	}

	public int getQuantity() {//returns quantity
		return quantity;
	}
        
        public String getMachineNumber() {//returns machine number
		return machineNumber;
	}
        
        public String getDepartment() {//returns department
		return department;
	}
        
        public String getSection() {//returns section
		return section;
	}
        
        public String getCostCode() {//returns cost code
		return costCode;
	}
        
        public double getTotalPrice(){//returns total price
            return totalPrice;
        }

		

}
