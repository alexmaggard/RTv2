package RTv2.servlets;

import RTv2.database.EmployeeDB;
import RTv2.objects.Employee;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EmployeesServlet", urlPatterns = {"/employee"})
public class EmployeesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        String url = "/viewEmployees.jsp";
        
        // get current action
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "display_employees";  // default action
        }
        //display all employees
        if (action.equals("display_employees")) {            
            // get list of users
            ArrayList<Employee> employees = EmployeeDB.selectEmployees();            
            request.setAttribute("employees", employees);
            url = "/viewEmployees.jsp";
        }
        
        else if (action.equals("display_employee")) {
            int employeeID = Integer.parseInt(request.getParameter("employeeID"));
            Employee employee = EmployeeDB.selectEmployee(employeeID);
            session.setAttribute("employee", employee);
            url = "/viewEmployees.jsp";
        }
        
        else if (action.equals("update_employee")) {
            // get parameters from the request
            int employeeID = Integer.parseInt(request.getParameter("employeeID"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            int authLevel = Integer.parseInt(request.getParameter("authLevel"));
            int status = Integer.parseInt(request.getParameter("status"));
            double payRate = Double.parseDouble(request.getParameter("payRate"));

            // get and update employee
            Employee employee = (Employee) session.getAttribute("employee"); 
            employee.setEmployeeID(employeeID);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setPassword(password);
            employee.setAuthLevel(authLevel);
            employee.setStatus(status);
            employee.setPayRate(payRate);
            EmployeeDB.update(employee);

            // get and set updated users
            ArrayList<Employee> employees = EmployeeDB.selectEmployees();            
            request.setAttribute("employees", employees);            
        }
        
       //verify user and launch proper landing page
        else if (action.equals("verifyLogIn")) {           
            
            int employeeID = Integer.parseInt(request.getParameter("loginID"));
            String password = request.getParameter("password");
            int authLevel = 0;
            
            Employee verifyEmployee;            
            verifyEmployee = EmployeeDB.verifyLogin(employeeID, password);
            
            if(verifyEmployee != null)
            {
            authLevel = verifyEmployee.getAuthLevel();
            }
            else{
                ;
            }
            request.setAttribute("employee", verifyEmployee);

            switch (authLevel) {
                case 1:
                    url="/managerPage.jsp";
                    break;
                case 2:
                    url="/employeePage.jsp";
                    break;
                default:
                    url="/login.jsp";
                    break;
            }

        }
        
        else if (action.equals("add_employee")) {
            // get parameters from the request
            int employeeID = Integer.parseInt(request.getParameter("employeeID"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            int authLevel = Integer.parseInt(request.getParameter("authLevel"));
            int status = Integer.parseInt(request.getParameter("status"));
            String password = request.getParameter("password");
            double payRate = Double.parseDouble(request.getParameter("payRate"));
            
            // store data in User object
            Employee employee = new Employee(employeeID, firstName, lastName,
            authLevel, status,password, payRate);
            
            EmployeeDB.insert(employee);
            
            url = "/viewEmployees.jsp";
  
        }

          else if (action.equals("changePassword")) {
            // get parameters from the request
            String password = request.getParameter("password");

            // get and update user
            Employee employee = (Employee) session.getAttribute("employee"); 
            employee.setPassword(password);
            EmployeeDB.update(employee);
            
        }
        
        else if (action.equals("delete_employee")) {
            // get the user
            int employeeID = Integer.parseInt(request.getParameter("employeeID"));
            Employee employee = EmployeeDB.selectEmployee(employeeID);
            
            // delte the user
            EmployeeDB.delete(employee);
            
            // get and set updated users
            ArrayList<Employee> employees = EmployeeDB.selectEmployees();            
            request.setAttribute("employees", employees);            
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
