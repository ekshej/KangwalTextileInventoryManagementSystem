package IMS;

public class inventoryItem {
	public String date;//date
	public String inventoryCode;//inventory code
	public String rSN;//rsn
	public int quantity;//quantity
	public double unitPrice;//unit price
	public double totalPrice;//total price
	
        public inventoryItem(String Date, String RSN, String InventoryCode, int Quantity, double UnitPrice, double TotalPrice){//constructor for inventory item
        this.date = Date;
        this.rSN = RSN;
        this.inventoryCode = InventoryCode;
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
