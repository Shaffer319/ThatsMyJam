/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.controllers;

import com.thatsmyjam.data.User;
import com.thatsmyjam.data.UserDB;
import java.io.IOException;
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
@WebServlet(name = "createaccount", urlPatterns = {"/createAccount/*"})
public class CreateAccountServlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        String url;
        
        url = handleCreateUser(request, response);

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }

    /**
     * Handle the @see Action.ACTION_CREATE_ACCOUNT
     *
     * @param request
     * @param response
     * @return
     */
    public String handleCreateUser(HttpServletRequest request, HttpServletResponse response) {

        // Pull in user info from form
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String cpass = request.getParameter("cpass");

        request.setAttribute("fname", fname);
        request.setAttribute("lname", lname);
        request.setAttribute("email", email);

        String url = "/signup.jsp";

        // Make sure the user info is valid
        if (!request.getMethod().equalsIgnoreCase("POST")) {
            request.setAttribute("message", "Invalid request mode.");
        } else if (fname == null || fname.isEmpty()) {
            request.setAttribute("message", "Invalid First Name.");
        } else if (lname == null || lname.isEmpty()) {
            request.setAttribute("message", "Invalid Last Name.");
        } else if (email == null || email.isEmpty()) {
            request.setAttribute("message", "Invalid Email.");
        } else if (pass == null || pass.isEmpty()) {
            request.setAttribute("message", "Password is invalid.");
        } else if (!pass.equals(cpass)) {
            request.setAttribute("message", "Passwords did not match.");
        } else {
            // Try to create account
            // Valid
            User user = new User();
            user.setFirstName(fname);
            user.setLastName(lname);
            user.setEmail(email);
            user.setPassword(pass);

            if (UserDB.emailExists(email)) {
                request.setAttribute("message", "Email already exists!");
                return "/signup.jsp";
            }

            int count = UserDB.insert(user);
            if (count == 0) {
                request.setAttribute("message", "Error try again later.");
            } else {
                url = "/homepage.jsp";
            }

            // On success
        }
        return url;
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
