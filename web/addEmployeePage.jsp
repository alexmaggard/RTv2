<%-- 
    Document   : addEmployeePage
    Created on : Dec 13, 2017, 3:21:55 PM
    Author     : Alexander
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Employee</title>
    </head>
    <body>
        
         <div id="employeeFormPanel">
                <form action="employee" method="get">
                    <input type="hidden" name="action" value="add_employee">
                    <label class="pad_top">Employee ID:</label>
                    <input type="text" name="employeeID" value="${employee.employeeID}"><br>
                    <label class="pad_top">First Name:</label>
                    <input type="text" name="firstName" value="${employee.firstName}"><br>
                    <label class="pad_top">Last Name:</label>
                    <input type="text" name="lastName" value="${employee.lastName}"><br>
                    <label class="pad_top">Auth Level:</label>
                    <input type="text" name="authLevel" value="${employee.authLevel}"><br>
                    <label class="pad_top">Pay Rate:</label>
                    <input type="text" name="payRate" value="${employee.payRate}"><br>
                    <input href ="employee" type="submit" value="Add Employee" action="add_employee">
                </form>
        
    </body>
</html>
