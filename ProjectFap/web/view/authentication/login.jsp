<%-- 
    Document   : login
    Created on : Mar 11, 2024, 8:31:43 AM
    Author     : Fatvv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Screen</title>
    </head>
    <body>
        <form action="" method="POST">
            Username: <input type="text" name="username"/> <br/>
            Password: <input type="password" name="password"/> <br/>
            <input type="checkbox" name="remember" value="remember"/> Remember me in 7 days
            <br/>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
