/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.servlets;

import com.thatsmyjam.beans.CartBean;
import com.thatsmyjam.beans.InfoBean;
import com.thatsmyjam.beans.Item;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "ShoppingCart", urlPatterns = {"/ShoppingCart"})
public class ShoppingCart extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShoppingCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShoppingCart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

        InfoBean info = (InfoBean) request.getSession(false).getAttribute("infoBean");
        CartBean cart = (CartBean) request.getSession(false).getAttribute("cartBean");
        if (cart == null) {
            cart = new CartBean();
            HttpSession session = request.getSession();
            session.setAttribute("cartBean", cart);
        }

        if (request.getParameter("addAlbumCart") != null) {
            Item itm = new Item(info.getAlbumName(), info.getImageName(), info.getArtistName(), info.getArtistID(), info.getAlbumName(), info.getAlbumID(), null, 0, info.getAlbumPrice());
            cart.addItem(itm);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
        } else if ((request.getParameter("remove") != null)) {
            String []removeItm = request.getParameterValues("remove");
            out.println(removeItm[0]);
            cart.removeItem(removeItm[0]);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);

        } else if ((request.getParameter("continue_shop") != null)) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/homepage.jsp");
            dispatcher.forward(request, response);

        } else if ((request.getParameter("checkout") != null)) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/checkout.jsp");
            dispatcher.forward(request, response);
        }
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
