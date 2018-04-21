/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc192;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ice
 */
@WebServlet(name = "ComandaServlet", urlPatterns = {"/index.html", "/criar-comanda.html", "/listar-comandas.html"})
public class ComandaServlet extends HttpServlet {

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
            out.println("<title>Servlet ComandaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComandaServlet at " + request.getContextPath() + "</h1>");
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
        if ("/index.html".equals(request.getServletPath())) {
            index(request, response);
            return;
        } else if ("/criar-comanda.html".equals(request.getServletPath())) {
            criarComanda(request, response);
            return;
        } else if ("/listar-comandas.html".equals(request.getServletPath())) {
            listarComanda(request, response);
            return;
        }
        response.sendError(404);
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
    
    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("titulo", "HOME");
        RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/index.jsp");
        despachante.forward(request, response);
    }
    
    private void criarComanda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("titulo", "Criar Comanda");
        RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/criar-comanda.jsp");
        despachante.forward(request, response);
    }
    
    private void listarComanda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("titulo", "Listar Comandas");
        RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/listar-comandas.jsp");
        despachante.forward(request, response);
    }

}
