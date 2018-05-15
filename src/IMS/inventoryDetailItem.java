package IMS;

public class inventoryDetailItem {
	public String date;//date
        public String rSN;//rsn
	public String inventoryCode;//inventory code
        public int lot;//lot
        public String partDescription;//part description
	public int quantity;//quantity
	public double unitPrice;//unit price
	public double totalPrice;//total price
	
        public inventoryDetailItem(String Date, String RSN, String InventoryCode, int Lot, String PartDescription, int Quantity, double UnitPrice, double TotalPrice){//constructor for inventory item
        this.date = Date;
        this.rSN = RSN;
        this.inventoryCode = InventoryCode;
        this.lot = Lot;
        this.partDescription = PartDescription;
        this.quantity = Quantity;
        this.unitPrice = UnitPrice;
        this.totalPrice = TotalPrice;
    }
	public String getDate() {//returns date
		return date;
	}

	public String getRSN() {//returns RSN
		return rSN;
        }

	public String getInventoryCode() {//returns inventory code
		return inventoryCode;
	}
        
        public int getLot() {//returns lot
		return lot;
	}
        
        public String getPartDescription() {//returns part description
		return partDescription;
	}

	public int getQuantity() {//returns quantity
		return quantity;
	}

	public double getUnitPrice() {//returns unit price
		return unitPrice;
	}

	public double getTotalPrice() {//returns total price
		return totalPrice;
	}	

}
