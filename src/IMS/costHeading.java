package IMS;

public class costHeading {//Cost Heading
    
    private String costCode;//Cost Code
    private String noE;//Nature of Expense

     public costHeading(String CostCode, String NoE){//The constructor, taking in the parameters of the Cost Code and Nature of Expense
        this.costCode = CostCode;
        this.noE = NoE;
    }
   
    public String getCostCode(){//function to return Cost Code
        return costCode;
    }

    public String getNoE(){//function to return Nature of Expense
        return noE;
    }
   
}
