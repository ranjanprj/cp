<%-- 
    Document   : login
    Created on : Aug 5, 2016, 12:10:48 PM
    Author     : PRanjan
--%>

<%@page import="com.cp.entities.CPUserProfile"%>
<%@page import="com.cp.dto.UserProfileService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
        <style>
            
            form input{
                /*margin-left: 80px;*/
                display: block;
            }
        </style>
    </head>
    <body>
              <jsp:include page="loggedin_links.jsp"></jsp:include>

           <%
            UserProfileService service = null;
            CPUserProfile cpup = null;
            boolean everythingOK = false;
            if (request.getSession() != null) {
                Object emailAddressObj = request.getSession().getAttribute("emailAddress");
                if (emailAddressObj == null) {
                    response.sendRedirect("login.jsp");
                } else {
                    service = new UserProfileService();
                    cpup = service.getUserProfile(emailAddressObj.toString());
                    everythingOK = true;
                }
            } else {
                response.sendRedirect("login.jsp");
            }

        %>
        <h1>Welcome</h1>
        <form method="POST" action="save_profile">            
            Key Programming Skills (, separated) > <input type="text" name="current_skills" value="<%= cpup.getCurrentSkills() %>"/>
            Current Position > <input type="text" name="current_position" value="<%= cpup.getCurrentPosition() %>"/>
             Desired Programming Skills (, separated) > <input type="text" name="desired_skills"  value="<%= cpup.getDesiredSkills() %>"/>
            Desired Position > <input type="text" name="desired_position"  value="<%= cpup.getDesiredPosition() %>"/>
            <input type="submit"/>
        </form>
    </body>
</html>
