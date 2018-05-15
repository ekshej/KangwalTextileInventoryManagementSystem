package IMS;
 
public class departmentCode {//department code
    
    private String department;//department
    private String section;//section

     public departmentCode(String Department, String Section){//constructor 'departmentCode' and its respective parameters
        this.department = Department;//Department
        this.section = Section;//Section
    }
   
    public String getDepartment() {//Get Department
        return department;//return department
    }

    public String getSection() {//Get Section
        return section;//return section
    }
   
}
