/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RTv2.database;

import RTv2.objects.Employee;
import RTv2.objects.TimeClock;
import RTv2.utilities.ConnectionPool;
import RTv2.utilities.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class TimeClockDB {
    
    public static int checkTimes (TimeClock timeClock) {
        if(timeClock.getStartTime().equals("")) {
            
        }
        else if (timeClock.getLunchOut().equals("")) {
            
        }
        else if (timeClock.getLunchIn().equals("")) {
            
        }
        else if (timeClock.getEndTime().equals("")) {
            
        }
        else
            
            return 0;
    }
    
    public static int insertTimeClock(int employeeID) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        SimpleDateFormat dayFormat = new SimpleDateFormat ("MM/dd/yy");
        SimpleDateFormat timeFormat = new SimpleDateFormat ("hh:mm a");
        Date aDate = new Date();
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        ps = null;
        
            String query = "INSERT INTO cs_workhours (DayID, StartTime, "
                    + "LunchOut, LunchIn, EndTime, EmployeeID) VALUES "
                    + "(?, ?, ?, ?, ?, ?)";
            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, dayFormat.format(aDate));
                ps.setString(2, timeFormat.format(aDate));
                ps.setString(3, "");
                ps.setString(4, "");
                ps.setString(5, "");
                ps.setInt(6, employeeID);
                return ps.executeUpdate();

            } catch(SQLException e) {
                System.out.println(e);
                return 0;
            } finally {
                DBUtil.closePreparedStatement(ps);
                pool.freeConnection(connection);
            }
        }
    
    public static int updateTimeClock(TimeClock timeClock){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE cs_workhours SET "
                + "StartTime = ?, LunchOut = ?, "
                + "LunchIn = ?, EndTime = ? "
                + "WHERE EmployeeID = ? AND DayID = ?";
        //TODO: find out if this should have employeeID field
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, timeClock.getStartTime());
            ps.setString(2, timeClock.getLunchOut());
            ps.setString(3, timeClock.getLunchIn());
            ps.setString(4, timeClock.getEndTime());
            ps.setInt(5, timeClock.getEmployeeID());
            ps.setString(6, timeClock.getDayID());

            return ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<TimeClock> selectOneTimeClock(String DayID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
           
        ResultSet rs = null;
        
        String query = "SELECT * FROM cs_workhours WHERE DayID = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, DayID);
            rs = ps.executeQuery();
            ArrayList<TimeClock> timeClocks = new ArrayList<>();
                    TimeClock timeClock = new TimeClock();
                    timeClock.setDayID(rs.getString("DayID"));
                    timeClock.setStartTime(rs.getString("StartTime"));
                    timeClock.setLunchOut(rs.getString("LunchOut"));
                    timeClock.setLunchIn(rs.getString("LunchIn"));
                    timeClock.setEndTime(rs.getString("EndTime"));
                    timeClocks.add(timeClock);
            return timeClocks;
            
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    //select timeclock based on employeeID
    public static ArrayList<TimeClock> selectTimeClock(int employeeID){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
           
        ResultSet rs = null;

        String query = "SELECT * FROM cs_workhours "
                + "WHERE EmployeeID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, employeeID);
            rs = ps.executeQuery();
            ArrayList<TimeClock> timeClocks = new ArrayList<>();
            while (rs.next()) {
                    TimeClock timeClock = new TimeClock();
                    timeClock.setDayID(rs.getString("DayID"));
                    timeClock.setStartTime(rs.getString("StartTime"));
                    timeClock.setLunchOut(rs.getString("LunchOut"));
                    timeClock.setLunchIn(rs.getString("LunchIn"));
                    timeClock.setEndTime(rs.getString("EndTime"));
                    timeClocks.add(timeClock);
            }
            return timeClocks;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<TimeClock> selectTimeClocks(){
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    String query = "SELECT * FROM cs_workhours";
    try {
        ps = connection.prepareStatement(query);
        rs = ps.executeQuery();
        ArrayList<TimeClock> timeClocks = new ArrayList<>();
            while (rs.next())
            {
                TimeClock timeClock = new TimeClock();
                timeClock.setEmployeeID(rs.getInt("EmployeeID"));
                timeClock.setDayID(rs.getString("DayID"));
                timeClock.setStartTime(rs.getString("StartTime"));
                timeClock.setLunchOut(rs.getString("LunchOut"));
                timeClock.setLunchIn(rs.getString("LunchIn"));
                timeClock.setEndTime(rs.getString("EndTime"));
                timeClocks.add(timeClock);
            }
        return timeClocks;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}