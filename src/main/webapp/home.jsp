<%-- 
    Document   : login
    Created on : Aug 5, 2016, 12:10:48 PM
    Author     : PRanjan
--%>

<%@page import="com.cp.entities.CPUserProfile"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="com.cp.dto.UserProfileService"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        
        <% if(everythingOK) { %>
        
            <h1>Welcome <%= cpup.getEmailAddress() %></h1>
        
            <h4>Your current Position</h4>     

            <%= cpup.getCurrentPosition() %>
            
            <h4>Your Desired Position</h4>     

            <%= cpup.getDesiredPosition() %>
 
         <% } %>
        
        <h4>Required Skills to reach Desired Position </h4>
        <table>
        <% for(Object[] obj : service.getUkGovDesiredSkillsRating() ){ %>
        
    <tr>
        <td><%= obj[0] %></td><td><%= obj[1] %></td>
    </tr>
   
        <%}%>
        </table>
        
        
         <% Object[] avgSal = service.getAvgUKGovSalary().get(0); %>    
    <h4>Average Salary Range</h4> (<%= avgSal[0]%> - <%= avgSal[1] %>)
    </body>
</html>
