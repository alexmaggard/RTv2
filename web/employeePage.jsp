<%-- 
    Document   : employee
    Created on : Nov 2, 2017, 3:21:33 PM
    Author     : Alexander
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <link rel="stylesheet" href="styles/base.css" type="text/css"/>
        <title>Employee View</title>
    </head>
    
    <body>
        <div id="employeeBanner">
            <!-- TODO: remove plain text from h1, h3 after datbase is connected -->
            <h1>${employee.lastName}, ${employee.firstName}<h1>
            <h3>Employee ID: ${employee.employeeID}</h3>
         </div>
         
         <div id="container">
            <div id="timeTable">
                <table>
                    <tr>
                        <th>Date</th>
                        <th>Start Time</th>
                        <th>Lunch Start</th>
                        <th>Lunch End</th>
                        <th>End Time</th>
                    </tr>
                   
                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <c:forEach var="timeClock" items="${timeClocks}">
                    <tr>
                      <td>${timeClock.dayID}</td>
                      <td>${timeClock.startTime}</td>
                      <td>${timeClock.lunchOut}</td>
                      <td>${timeClock.lunchIn}</td>
                      <td>${timeClock.endTime}</td>
                    </tr>
                    </c:forEach>
                    
                </table><br>
            </div><!-- END TIME TABLE -->
            
            <div id="buttons">
                <button value="Clock-In" action="clockIn">Clock-In</button>
                <button action="timeclock" value ="Clock-Out" action="clockOut">Clock-Out</button>
                <button value="Change Password" action="changePass">Change Password</button>
                <a href="employeePageServlet?action=showMyHours&amp;employeeID=${employee.employeeID}"/><button>Show My Hours</button></a>
            </div>
         </div>
    </body>
</html>
