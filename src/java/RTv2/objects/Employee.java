package RTv2.objects;

import java.io.Serializable;

public class Employee implements Serializable{
    
    private int employeeID;
    private String firstName;
    private String lastName;
    private String password;
    private int authLevel;
    private boolean status;
    private double payRate;
    
    public Employee(){}

    public Employee(int employeeID, String firstName, String lastName, 
                    String password, int authLevel, boolean status, 
                    double payRate){
        
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.authLevel = authLevel;
        this.status = status;
        this.payRate = payRate;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAuthLevel() {
        return authLevel;
    }

    public void setAuthLevel(int authLevel) {
        this.authLevel = authLevel;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

}
