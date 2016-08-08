/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cp.servlets;

import com.cp.entities.CPUserProfile;
import com.cp.entities.UserProfileJpaController;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.PUFactory;

/**
 *
 * @author PRanjan3
 */
public class SaveProfile extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String currentSkills = request.getParameter("current_skills");
        String currentPosition = request.getParameter("current_position");
        String desiredSkills = request.getParameter("desired_skills");
        String desiredPosition = request.getParameter("desired_position");
        
        System.err.println(currentPosition + " " + currentSkills);
        try {
            String emailAddress = request.getSession().getAttribute("emailAddress").toString();
            EntityManager em = PUFactory.getEM();
            CPUserProfile up = em.createNamedQuery("up.findByEmailAddress", CPUserProfile.class).setParameter("emailAddress", emailAddress).getSingleResult();
            up.setCurrentPosition(currentPosition);
            up.setCurrentSkills(currentSkills);
            up.setDesiredPosition(desiredPosition);
            up.setDesiredSkills(desiredSkills);
            UserProfileJpaController profileJpaController = new UserProfileJpaController(PUFactory.getEMF());
            profileJpaController.edit(up);
            response.sendRedirect("home.jsp");
        } catch (Exception ex) {
            response.sendRedirect("profile_error.jsp");
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
