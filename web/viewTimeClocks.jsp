<%-- 
    Document   : viewTimeClocks
    Created on : Nov 4, 2017, 10:03:17 AM
    Author     : Alexander
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset=UTF-8">
        <!--<link rel="stylesheet" href="styles/viewEmployees.css" type="text/css"/>-->
        <title>View TimeClocks</title>
    </head>
    
    <body>
            
            <div id="timeClockFormPanel">
                <form id="timeClockForm">
                    <label class="pad_top">Day:</label>
                    <input type="date" name="day" value="${timeClock.day}">
                    <label class="pad_top">Start Time:</label>
                    <input type="date" name="startTime" value="${timeClock.startTime}">
                    <label class="pad_top">Lunch Start:</label>
                    <input type="date" name="lunchOut" value="${timeClock.lunchOut}">
                    <label class="pad_top">Lunch End:</label>
                    <input type="date" name="lunchIn" value="${timeClock.lunchIn}">
                    <label class="pad_top">End Time:</label>
                    <input type="date" name="endTime" value="${timeClock.endTime}">
                </form>
            </div>
                
            <div id="timeClockTablePanel">
                <table id="timeClockTable">
                    <tr>
                        <th>Day</th>
                        <th>Start Time</th>
                        <th>Lunch Start</th>
                        <th>Lunch End</th>
                        <th>End Time</th>
                        <th>Options</th>
                    </tr>
                    
                    
                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                        <c:forEach var="timeClock" items="${timeClocks}">
                        <tr>
                          <td>${timeClock.day}</td>
                          <td>${timeClock.startTime}</td>
                          <td>${timeClock.lunchOut}</td>
                          <td>${timeClock.lunchIn}</td>
                          <td>${timeClock.endTime}</td>
                          <!-- edit time button will populate the timeClockForm with the selected dates information -->
                          <td><button action="editTime">Edit</button></td>
                        </tr>
                        </c:forEach>
                    
                </table>
                        <p><a href="timeclock" action="display_timeClock">Refresh</a></p>
            </div>
               
    </body>
    
</html>
