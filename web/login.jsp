<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
         <link rel="stylesheet" href="styles/portal.css" type="text/css"/>
        <title>Login Page</title>
    </head>
    <body>
        <div id="loginBanner">
            <h1>Login Page</h1>
        </div>
        
        <div id="loginContainer">
            <form action="employee" method="post">
            <input type="hidden" name="action" value="verifyLogIn">      
                <label>Employee ID:</label><br>
                    <input type="text" name="loginID"><br>
                <label>Password:</label><br>
                    <input type="password" name="password"><br><br>
                <button type="submit" value="update" name="submit" >Log In</button><br>
            </form>
        </div>
    </body>
</html>
