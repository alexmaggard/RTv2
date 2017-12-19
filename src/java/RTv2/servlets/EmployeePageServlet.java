/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RTv2.servlets;

import RTv2.database.EmployeeDB;
import RTv2.database.TimeClockDB;
import RTv2.objects.Employee;
import RTv2.objects.TimeClock;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

@WebServlet(name = "EmployeePageServlet", urlPatterns = {"/employeePageServlet"})

public class EmployeePageServlet extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
    
        String url = "/employeePage.jsp";
        
        // get current action
        String action = request.getParameter("action");
        
        JOptionPane.showMessageDialog(null, action);
        
        if(action == null){
            action = "showMyHours";
        }
        
        if (action.equals("clockIn")) {
            int employeeID = Integer.parseInt(request.getParameter("employeeID"));
            Employee employee = EmployeeDB.selectEmployee(employeeID);
            JOptionPane.showMessageDialog(null, employeeID);
            TimeClockDB.insertTimeClock(employeeID);
            request.setAttribute("employee",employee);
            url = "/employeePage.jsp";
        }
        
        else if (action.equals("update_timeClock")) {
            // get parameters from the request
            int employeeID = Integer.parseInt(request.getParameter("employeeID"));
            String dayID = request.getParameter("dayID");
            String clockIn = request.getParameter("clockIn");
            String lunchOut = request.getParameter("lunchOut");
            String lunchIn = request.getParameter("lunchIn");
            String clockOut = request.getParameter("clockOut");

            // get and update user
            TimeClock timeClock = (TimeClock) session.getAttribute("employeeID"); 
            timeClock.setEmployeeID(employeeID);
            timeClock.setDayID(dayID);
            timeClock.setStartTime(clockIn);
            timeClock.setLunchOut(lunchOut);
            timeClock.setLunchIn(lunchIn);
            timeClock.setEndTime(clockOut);
            TimeClockDB.updateTimeClock(timeClock);

            // get and set updated users
            ArrayList<TimeClock> timeClocks = TimeClockDB.selectTimeClocks();            
            request.setAttribute("timeClocks", timeClocks);       
        }
        
        else if (action.equals("showMyHours")){
            int employeeID = Integer.parseInt(request.getParameter("employeeID")); //get the employeeID in the employeePage.jsp
            
            JOptionPane.showMessageDialog(null, employeeID);
            
            ArrayList<TimeClock> timeClock = TimeClockDB.selectTimeClock(employeeID); //this gets all the employee's time clocked in
            
            Employee employee = EmployeeDB.selectEmployee(employeeID); //get employee object
            
            request.setAttribute("timeClocks",timeClock);
            
            request.setAttribute("employee",employee);
            
            //TODO: add if statment to check authLevel based on
            //authLevel choose either manager or employee.jsp...
            url = "/employeePage.jsp";
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
     
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
