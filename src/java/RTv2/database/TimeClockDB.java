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
    
    
    public static int insertTimeClock(int employeeID) {
        SimpleDateFormat dayFormat = new SimpleDateFormat ("MM/dd/yy");
        SimpleDateFormat timeFormat = new SimpleDateFormat ("hh:mm a");
        Date aDate = new Date();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
            Employee employee = null;
            TimeClock timeClock = null;
        
        //employees are set to 0 status by default
        //at first clockIn they will have day and start time generated
        
        employee = EmployeeDB.selectEmployee(employeeID);
        
        
        if(employee.getStatus()==0){
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
                employee.setStatus(1);
                return ps.executeUpdate();

            } catch(SQLException e) {
                System.out.println(e);
                return 0;
            } finally {
                DBUtil.closePreparedStatement(ps);
                pool.freeConnection(connection);
            }
        }
        
        //when the employee leaves for lunch they will add a clockin to the lunchout section
        else if(employee.getStatus()==1){
            String query = "UPDATE cs_workhours SET "
                    +"LunchOut = ? "
                    +"WHERE EmployeeID = ? AND DayID = ?";
            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, timeFormat.format(aDate));
                ps.setInt(2, employeeID);
                ps.setString(3, timeClock.getDayID());
                employee.setStatus(2);
                return ps.executeUpdate();

            } catch(SQLException e) {
                System.out.println(e);
                return 0;
            } finally {
                DBUtil.closePreparedStatement(ps);
                pool.freeConnection(connection);
            }
        }
        //upon returning from lunch they will put a clockin in the lunchIn slot
        else if(employee.getStatus()==2){
            String query = "UPDATE cs_workhours SET "
                    +"LunchIn = ? "
                    +"WHERE EmployeeID = ? AND DayID = ?";
            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, timeFormat.format(aDate));
                ps.setInt(2, employeeID);
                ps.setString(3, timeClock.getDayID());
                employee.setStatus(3);
                return ps.executeUpdate();

            } catch(SQLException e) {
                System.out.println(e);
                return 0;
            } finally {
                DBUtil.closePreparedStatement(ps);
                pool.freeConnection(connection);
            }
        }
        //at the end of the day when you clock out the last time will go into EndTime and reset your status to 0
        else if(employee.getStatus()==3){
            String query = "UPDATE cs_workhours SET "
                    +"EndTime = ? "
                    +"WHERE EmployeeID = ? AND DayID = ?";
            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, timeFormat.format(aDate));
                ps.setInt(2, employeeID);
                ps.setString(3, timeClock.getDayID());
                employee.setStatus(0);
                return ps.executeUpdate();

            } catch(SQLException e) {
                System.out.println(e);
                return 0;
            } finally {
                DBUtil.closePreparedStatement(ps);
                pool.freeConnection(connection);
            }
        }
        return 0;
        
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