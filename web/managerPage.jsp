<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <head>
        <meta charset=UTF-8">
        <link rel="stylesheet" href="styles/base.css" type="text/css"/>
        <title>Manager View</title>
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
                         <th>Start</th>
                         <th colspan="2" id="lunchColumn">Lunch</th>
                         <th>End</th>
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
            <a href="managerPageServlet" action="clockIn"><button>Clock-In</button></a>
            <a href="managerPageServlet" action="clockOut"><button>Clock-Out</button></a>
            <button action="changePass">Change Password</button>
            <a href="employee"><button>View Employees</button></a>
            <a href="addEmployeePage.jsp"><button> Add Employee</button></a>
            <a href="managerPageServlet?action=showMyHours&amp;employeeID=${employee.employeeID}"/><button>Show My Hours</button></a>

        </div>
    </body>
    
</html>
