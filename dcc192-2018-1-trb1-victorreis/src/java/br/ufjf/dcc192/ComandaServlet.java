/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.dcc192;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
@WebServlet(name = "ComandaServlet", urlPatterns = {"/index.html", "/criar-comanda.html", "/listar-comandas.html", "/fechar-comanda.html", "/adicionar-itens-comanda.html", "/listar-itens-comanda.html", "/listar-itens-cadastrados.html", "/remover-itens.html"})
public class ComandaServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("/index.html".equals(request.getServletPath())) {
            index(request, response);
            return;
        } else if ("/criar-comanda.html".equals(request.getServletPath())) {
            criarComanda(request, response);
            return;
        } else if ("/listar-comandas.html".equals(request.getServletPath())) {
            listarComandas(request, response);
            return;
        } else if ("/fechar-comanda.html".equals(request.getServletPath())) {
            abrirOuFecharComanda(request, response);
            return;
        } else if ("/listar-itens-comanda.html".equals(request.getServletPath())) {
            listarItensComanda(request, response);
            return;
        } else if ("/remover-itens.html".equals(request.getServletPath())) {
            removerItensComanda(request, response);
            return;
        } else if ("/listar-itens-cadastrados.html".equals(request.getServletPath())) {
            listarItensCadastrados(request, response);
            return;
        }
        response.sendError(404);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("titulo", "HOME");
        RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/index.jsp");
        despachante.forward(request, response);
    }
    
    private void criarComanda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idMesa = request.getParameter("mesa");
        String nomeCliente = request.getParameter("nome-cliente");
        
        // Recebendo a requisição POST, ou seja, o formulário de CADASTRO foi enviado
        if (idMesa != null && nomeCliente != null) {
            Persistencia.criarNovaComanda(idMesa, "".equals(nomeCliente) ? "-" : nomeCliente, new Date().toString());
            
            // Garante que o código fora do IF não será executado
            response.sendRedirect("listar-comandas.html");
            return;
        }
        
        // Recebendo a requisição GET, ou seja, o usuário digitou a URL no navegador ou usou algum link
        request.setAttribute("titulo", "Criar Comanda");
        request.setAttribute("mesas", Persistencia.getInstanceMesas());
        RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/criar-comanda.jsp");
        despachante.forward(request, response);
    }
    
    private void listarComandas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("titulo", "Listar Comandas");
        request.setAttribute("comandas", Persistencia.getInstanceComandas());
        RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/listar-comandas.jsp");
        despachante.forward(request, response);
    }
    
    private void abrirOuFecharComanda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Persistencia.abrirOuFecharComanda(request.getParameter("id"));
        response.sendRedirect("listar-comandas.html");
    }
    
    private void listarItensCadastrados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("titulo", "Listar Itens cadastrados no sistema");
        request.setAttribute("itens", Persistencia.getInstanceItens());
        RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/listar-itens-cadastrados.jsp");
        despachante.forward(request, response);
    }
    
    private void listarItensComanda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idComanda = request.getParameter("id-comanda");
        String idItem = request.getParameter("item");
        String quantidade = request.getParameter("quantidade");
        
        // Recebendo a requisição POST, ou seja, o formulário de CADASTRO foi enviado
        if (idItem != null && quantidade != null && idComanda != null) {
            if ("".equals(idItem) || "".equals(quantidade) || "".equals(idComanda) || "0".equals(quantidade)) {
                request.setAttribute("erro", "Escrever um inteiro diferente de zero para a quantidade.");
            } else {
                Persistencia.adicionarItensNaComanda(idComanda, idItem, quantidade);
            }
        }
        
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            response.sendRedirect("listar-comandas.html");
            return;
        }
        
        // Recebendo a requisição GET, ou seja, o usuário digitou a URL no navegador ou usou algum link
        request.setAttribute("titulo", "Listar Itens na Comanda");
        request.setAttribute("comanda", Persistencia.getComandaById(request.getParameter("id")));
        request.setAttribute("itens", Persistencia.getInstanceItens());
        RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/listar-itens-comanda.jsp");
        despachante.forward(request, response);
    }
    
    private void removerItensComanda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idComanda = request.getParameter("id");
        String idItem = request.getParameter("id-item");
        
        // Recebendo a requisição GET quando o botão EXCLUIR é clicado
        if (idItem != null && idComanda != null) {
            if ("".equals(idItem) || "".equals(idComanda)) {
                request.setAttribute("erro", "Erro na exclusão de itens.");
            } else {
                Persistencia.removerItensNaComanda(idComanda, idItem);
            }
        }
        
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            response.sendRedirect("listar-comandas.html");
            return;
        }
        
        // Recebendo a requisição GET, ou seja, o usuário digitou a URL no navegador ou usou algum link
        request.setAttribute("titulo", "Listar Itens na Comanda");
        request.setAttribute("comanda", Persistencia.getComandaById(request.getParameter("id")));
        request.setAttribute("itens", Persistencia.getInstanceItens());
        RequestDispatcher despachante = request.getRequestDispatcher("/WEB-INF/listar-itens-comanda.jsp");
        despachante.forward(request, response);
    }
}
