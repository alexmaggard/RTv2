<%-- 
    Document   : manager
    Created on : Nov 2, 2017, 3:21:46 PM
    Author     : Alexander
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <title>Manager View</title>
    </head>
    
    <body>
        <div>
            <h1>${employee.lastName}, ${employee.firstName}<h1>

            <h3>${employee.employeeID}</h3>
            
            <div id="timeTable">
                <table>
                    <tr>
                        <th>Date</th>
                        <th>Start</th>
                        <th colspan="2" id="lunchColumn">Lunch</th>
                        <th>End</th>
                    </tr>
                    
                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <c:forEach var="timeClock" items="${timeClocks}">
                    <tr>
                      <td>${timeClock.day}</td>
                      <td>${timeClock.startTime}</td>
                      <td>${timeClock.lunchOut}</td>
                      <td>${timeClock.lunchIn}</td>
                      <td>${timeClock.endTime}</td>
                      
                    </tr>
                    </c:forEach>
                    
                </table><br>
            </div><!-- END TIME TABLE -->
                    
            <button action="clockIn">Clock-In</button>
            <button action="clockOut">Clock-Out</button>
            <button action="changePass">Change Password</button>
            <a href="employee"><button>View Employees</button></a>
            <a href="addEmployeePage.jsp"><button> Add Employee</button></a>
            <a href="timeclock"><button>Show Hours</button></a>

        </div>
    </body>
    
</html>
