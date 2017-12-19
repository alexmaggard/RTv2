package RTv2.utilities;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DateFormatter {
    public static void main(String args[]){
        
        //Formatting for DayID column
        Date today = new Date();
        SimpleDateFormat DATE_DAY = new SimpleDateFormat("MM-dd");
        String dayString = DATE_DAY.format(today);
        System.out.println(dayString);
        
        //Formatting for time
        Date time = new Date();
        SimpleDateFormat DATE_TIME = new SimpleDateFormat("hh:mm a");
        String timeString = DATE_TIME.format(time);
        System.out.println(timeString);
    }
}