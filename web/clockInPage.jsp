<%-- 
    Document   : clockInPage
    Created on : Dec 17, 2017, 11:03:41 AM
    Author     : amagg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/base.css" type="text/css"/>
        <link rel="stylesheet" href="styles/tables.css" type="text/css"/>
        <title>Clock In Page</title>
    </head>
    <body>
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
                
                <a href="clockInServlet" action="clockIn"><button>Clock-In</button></a>
                <a href="clockInServlet" action="clockOut"><button>Clock-Out</button></a>
            </div><!-- END TIME TABLE -->
    </body>
</html>
