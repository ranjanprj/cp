/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cp.servlets;

import com.cp.entities.CPUserProfile;
import com.cp.entities.UserProfileJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.PUFactory;

/**
 *
 * @author PRanjan3
 */
public class RegistrationForm extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("user_name");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {

            CPUserProfile up = new CPUserProfile();
            up.setFirstName(firstName);
            up.setLastName(lastName);
            up.setUserName(userName);
            up.setEmailAddress(email);
            up.setPassword(password);

            UserProfileJpaController profileJpaController = new UserProfileJpaController(PUFactory.getEMF());
            profileJpaController.create(up);
        } catch (Exception ex) {
            response.sendRedirect("registration_error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
