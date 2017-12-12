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
            <!-- TODO: remove plain text from h1, h3 after datbase is connected -->
            <h1>Maggard ${employee.lastName}, Alex ${employee.firstName}<h1>
            <h3>635345 ${employee.employeeID}</h3>
            
            <div id="timeTable">
                <table>
                    <tr>
                        <th>Date</th>
                        <th>Start</th>
                        <th colspan="2" id="lunchColumn">Lunch</th>
                        <th>End</th>
                    </tr>
                    
                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <c:forEach var="timeCard" items="${timeCard}">
                    <tr>
                      <td>${timeCard.day}</td>
                      <td>${timeCard.startTime}</td>
                      <td>${timeCard.lunchOut}</td>
                      <td>${timeCard.lunchIn}</td>
                      <td>${timeCard.endTime}</td>
                      
                    </tr>
                    </c:forEach>
                    
                </table><br>
            </div><!-- END TIME TABLE -->
                    
            <button action="clockIn">Clock-In</button>
            <button action="clockOut">Clock-Out</button>
            <button action="changePass">Change Password</button>
            <a href="employee">View Employees</a>

        </div>
    </body>
    
</html>
