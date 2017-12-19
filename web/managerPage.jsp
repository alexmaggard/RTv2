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
            <a href="managerPageServlet?action=clockIn&amp;employeeID=${employee.employeeID}"><button>Clock-In/Out</button></a>
            <button action="changePass">Change Password</button>
            <a href="employee"><button>View Employees</button></a>
            <a href="addEmployeePage.jsp"><button> Add Employee</button></a>
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD:web/manager.jsp
            <a href="timeclock"><button>Show Hours</button></a>
        </div>
=======
            <button action="showMyHours">Show My Hours</button></a>
>>>>>>> alexBranch:web/managerPage.jsp
=======
            <a href="managerPageServlet" action="showMyHours"><button>Show My Hours</button></a>
>>>>>>> master
=======
            <a href="managerPageServlet?action=showMyHours&amp;employeeID=${employee.employeeID}"/><button>Show My Hours</button></a>
>>>>>>> alexBranch

        </divzzz
    </body>
    
</html>
