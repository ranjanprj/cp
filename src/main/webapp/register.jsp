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
        <title>JSP Page</title>
           <style>
            
            form input{
                /*margin-left: 80px;*/
                display: block;
            }
        </style>
    </head>
    <body>
        <jsp:include page="links.jsp"></jsp:include>
        <form method="POST" action="registration_form">
            First Name : > <input type="text" name="first_name"/>
            Last Name : > <input type="text" name="last_name"/>
           
            Email : > <input type="text" name="email"/>
             User Name : > <input type="text" name="user_name"/>
             Password : > <input type="text" name="password"/>
             <input type="submit"/>
        </form>
    </body>
</html>
