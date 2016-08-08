<%-- 
    Document   : login
    Created on : Aug 5, 2016, 12:10:48 PM
    Author     : PRanjan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
            
            form input{
                /*margin-left: 80px;*/
                display: block;
            }
        </style>
    </head>
    <body>
        <jsp:include page="links.jsp"></jsp:include>
        
        <h1>Welcome</h1>
        <form method="POST" action="login">
            User name > <input type="text" name="user_name"/>
            Password > <input type="password" name="password"/>
            <input type="submit"/>
        </form>
    </body>
</html>
