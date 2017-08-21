/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.controllers;

import com.thatsmyjam.data.User;
import com.thatsmyjam.data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mshaffer
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/profileController/*"})
public class ProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String requestURI = request.getRequestURI();
        Principal p = request.getUserPrincipal(); // if null user is not logged in
        String url = "/profile";

        User user = getUser(request);
        if (user == null){
            // Error loading user form db
        }
        if (requestURI.endsWith("view")) {
            url = "/profile/index.jsp";
        }else if (requestURI.endsWith("ChangeProfile")){
            url = "/profile/index.jsp";
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }
    
    public User getUser(HttpServletRequest request){
        Principal p = request.getUserPrincipal(); // if null user is not logged in
        if(p == null)// user is not logged in
            return null;
        
        String userName = request.getUserPrincipal().getName();
        HttpSession session =  request.getSession();
        User user = (User)session.getAttribute("user");
        
        if ( user == null){
            user = UserDB.selectUser(userName);
            session.setAttribute("user", user);
        }
        
        return user;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
