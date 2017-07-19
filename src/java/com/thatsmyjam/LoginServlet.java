package com.thatsmyjam;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bean51591
 */
@WebServlet(urlPatterns = {"/TestServlet"})
public class LoginServlet extends HttpServlet {
        
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
        
        String action = (String)request.getAttribute("action");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
        if(user == null){
            user = new User();
        }
        
        // Login page is the index page.
        String url = URL.URL_INDEX;
        
        if(action == null){
            action = Action.ACTION_INDEX;
        }
        
        if(action.equals(Action.ACTION_INDEX)){
            // 
            url = URL.URL_INDEX;
        }else if(action.equals(Action.ACTION_LOGIN)){
            
            if(true){
                user.setLoggedIn(true);
            }
            // Login to user account
            if(user.isLoggedIn())
                url = URL.URL_HOME;
            else
                url= URL.URL_INDEX;
            // On fail got to index / login
        }
        else if(action.equals(Action.ACTION_SIGNUP))
        {
            // Create user account
            url = URL.URL_SIGNUP;
        }
        else if (action.equals(Action.ACTION_ACCOUNT))
        {
            // View user account and edit account settings from here
            url = URL.URL_ACCOUNT;
        }
        else if(action.equals(Action.ACTION_UPDATE_USER)){
            // Pull in data from update user form and edit account
            
            String error = updateUser(request, user);
            request.setAttribute("error", error);
            
            url = URL.URL_ACCOUNT;
        }
        
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
        
//        response.setContentType("text/html;charset=UTF-8");
//        
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet TestServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }
    
    /**
     * 
     * @param request
     * @param user
     * @return String that indicates the error. If no error then the user is update and null is returned
     */
    public String updateUser(HttpServletRequest request, User user)
    {            
        String email = request.getParameter("email");
        if(email.isEmpty())
        {
            return "Error email is empty.";
        }
        user.setEmail(email);
        // user.isValid()
        return null;
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
