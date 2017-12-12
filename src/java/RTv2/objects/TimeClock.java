package RTv2.objects;

import java.io.Serializable;

public class TimeClock implements Serializable{
    
    private int employeeID;
    private String day;
    private String startTime;
    private String lunchOut;
    private String lunchIn;
    private String endTime;
    
    public TimeClock(){}
    
    public TimeClock(int employeeID, String day, String startTime, String lunchOut, 
                    String lunchIn, String endTime){
        
        this.employeeID = employeeID;
        this.day = day;
        this.startTime = startTime;
        this.lunchOut = lunchOut;
        this.lunchIn = lunchIn;
        this.endTime = endTime;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getLunchOut() {
        return lunchOut;
    }

    public void setLunchOut(String lunchOut) {
        this.lunchOut = lunchOut;
    }

    public String getLunchIn() {
        return lunchIn;
    }

    public void setLunchIn(String lunchIn) {
        this.lunchIn = lunchIn;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
}
