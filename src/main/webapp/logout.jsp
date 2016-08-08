<%-- 
    Document   : logout
    Created on : Aug 5, 2016, 3:25:23 PM
    Author     : PRanjan3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="links.jsp"></jsp:include>
        <h1>You have been loggedout</h1>
        
        <%
            request.getSession().invalidate();
        %>
    </body>
</html>
