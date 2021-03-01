/*
 * Copyright (C) 2021 Luis Guisso <luis.guisso at ifnmg.edu.br>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.guisso.hellofirstjakartaee8.onetoone;

import io.github.guisso.hellofirstjakartaee8.util.Util;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

/**
 *
 * @author Luis Guisso <luis.guisso at ifnmg.edu.br>
 */
// TODO Entender mecanismo @Transactional e o porquê dele ser aplicado somente ao método de processamento não gera o repasse da transação para o EJB (requer anotação na classe!)
@Transactional
@WebServlet(name = "PessoaServletOneToOne",
        urlPatterns = {"/PessoaServletOneToOne"})
public class PessoaServlet extends HttpServlet {

    @Inject
    private PessoaOneToOneBeanLocal pessoaManyToManyBean;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // TODO Deveria possibilitar a continuidade da transação para carga de elementos em cascata tardiamenta, mas falha
//    @Transactional
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("---- PRÉ PERSISTÊNCIA ----");

        //--
        Endereco endereco1 = new Endereco();
        endereco1.setLogradouro("Rua Um");
        endereco1.setNumero(1000);

        System.out.println(">> E1: " + endereco1);

        Endereco endereco2 = new Endereco();
        endereco2.setLogradouro("Rua Dois");
        endereco2.setNumero(2000);

        System.out.println(">> E2: " + endereco2);

        //--
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Ana Zaira");
        pessoa1.setAtiva(true);

        // Ligação
        pessoa1.setEndereco(endereco1);

        System.out.println(">> P1: " + pessoa1);

        pessoaManyToManyBean.save(pessoa1);

        // --
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Beatriz Yana");
        pessoa2.setAtiva(false);

        // Ligação
        pessoa2.setEndereco(endereco2);

        System.out.println(">> P2: " + pessoa2);

        pessoaManyToManyBean.save(pessoa2);

        System.out.println("---- PÓS PERSISTÊNCIA ----");

        System.out.println("-> P1: " + pessoa1);
        System.out.println("-> P2: " + pessoa2);

        System.out.println("---- PÓS RECUPERAÇÃO ----");

        Pessoa pessoa1Bd = pessoaManyToManyBean
                .find(pessoa1.getId());
        System.out.println("-> P1: " + pessoa1Bd);

        Pessoa pessoa2Bd = pessoaManyToManyBean
                .find(pessoa2.getId());
        System.out.println("-> P2: " + pessoa2Bd);

        //<editor-fold defaultstate="collapsed" desc="HTML">
        response
                .setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PessoaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PessoaServlet: Um-para-um unidirecional</h1>");
            out.println("<p>Processamento concluído com sucesso.</p>");
            out.println("<p><pre>"
                    + Util.toJson(pessoa1Bd)
                    + "</pre></p>");
            out.println("<p><pre>"
                    + Util.toJson(pessoa2Bd)
                    + "</pre></p>");
            out.println("<a href=\"index.html\">Voltar</a>");
            out.println("</body>");
            out.println("</html>");
        }
        //</editor-fold>
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