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
package io.github.guisso.hellofirstjakartaee8.jpql;

import io.github.guisso.hellofirstjakartaee8.util.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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
@Transactional
@WebServlet(name = "PessoaGrupoServlet",
        urlPatterns = {"/PessoaGrupoServlet"})
public class PessoaServlet extends HttpServlet {

    @Inject
    private PessoaBeanLocal pessoaBean;

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

        //
        // -- PESSOA 1 --
        //
        Telefone telefone1 = new Telefone();
        telefone1.setNumero(111111);

        Telefone telefone2 = new Telefone();
        telefone2.setNumero(222222);

        Endereco endereco1 = new Endereco();
        endereco1.setLogradouro("Rua Um");
        endereco1.setNumero(111);
        endereco1.setBairro("Alcântara");

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Dayse Washington");
        pessoa1.setNascimento(LocalDate.of(1986, Month.MARCH, 3));

        pessoa1.getTelefones().add(telefone1);
        pessoa1.getTelefones().add(telefone2);
        pessoa1.setEndereco(endereco1);

        Grupo grupo1 = new Grupo();
        grupo1.setNome("Grupo 1");
        grupo1.setAtivo(true);

        Grupo grupo2 = new Grupo();
        grupo2.setNome("Grupo 2");
        grupo2.setAtivo(false);

        Cadastro cadastro1 = new Cadastro();
        cadastro1.setAtivo(true);
        cadastro1.setGrupo(grupo1);
        cadastro1.setDataHoraCadastro(
                LocalDateTime.of( // 02/03/2020 15:30
                        2020, Month.MARCH, 2, 15, 30));

        cadastro1.setPessoa(pessoa1);
        pessoa1.getCadastros().add(cadastro1);

        Cadastro cadastro2 = new Cadastro();
        cadastro2.setAtivo(true);
        cadastro2.setGrupo(grupo2);
        cadastro2.setDataHoraCadastro(
                LocalDateTime.of( // 02/03/2021 08:00
                        2021, Month.MARCH, 2, 8, 0));

        cadastro2.setPessoa(pessoa1);
        pessoa1.getCadastros().add(cadastro2);

        pessoaBean.save(pessoa1);
        System.out.println(">> P1: " + pessoa1);

        //
        // -- PESSOA 2 --
        //
        Telefone telefone3 = new Telefone();
        telefone3.setNumero(333333);

        Telefone telefone4 = new Telefone();
        telefone4.setNumero(444444);

        Telefone telefone5 = new Telefone();
        telefone5.setNumero(555555);

        Endereco endereco2 = new Endereco();
        endereco2.setLogradouro("Rua Dois");
        endereco2.setNumero(222);
        endereco2.setBairro("Boituva");

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Eugênia Vieira");
        pessoa2.setNascimento(LocalDate.of(1988, Month.DECEMBER, 25));

        pessoa2.getTelefones().add(telefone3);
        pessoa2.getTelefones().add(telefone4);
        pessoa2.getTelefones().add(telefone5);
        pessoa2.setEndereco(endereco2);

        Grupo grupo4 = new Grupo();
        grupo4.setNome("Grupo 4");
        grupo4.setAtivo(true);

        Grupo grupo5 = new Grupo();
        grupo5.setNome("Grupo 5");
        grupo5.setAtivo(false);

        Grupo grupo6 = new Grupo();
        grupo6.setNome("Grupo 6");
        grupo6.setAtivo(true);

        Cadastro cadastro3 = new Cadastro();
        cadastro3.setAtivo(true);
        cadastro3.setGrupo(grupo1);
        cadastro3.setDataHoraCadastro(
                LocalDateTime.of( // 02/03/2020 15:30
                        2020, Month.MARCH, 2, 15, 30));

        cadastro3.setPessoa(pessoa2);
        pessoa2.getCadastros().add(cadastro3);

        Cadastro cadastro4 = new Cadastro();
        cadastro4.setAtivo(true);
        cadastro4.setGrupo(grupo4);
        cadastro4.setDataHoraCadastro(
                LocalDateTime.of( // 02/03/2021 08:00
                        2021, Month.MARCH, 2, 8, 0));

        cadastro4.setPessoa(pessoa2);
        pessoa2.getCadastros().add(cadastro4);

        Cadastro cadastro5 = new Cadastro();
        cadastro5.setAtivo(false);
        cadastro5.setGrupo(grupo5);
        cadastro5.setDataHoraCadastro(
                LocalDateTime.of( // 15/09/2020 13:10
                        2020, Month.SEPTEMBER, 15, 13, 10));

        cadastro5.setPessoa(pessoa2);
        pessoa2.getCadastros().add(cadastro5);

        Cadastro cadastro6 = new Cadastro();
        cadastro6.setAtivo(true);
        cadastro6.setGrupo(grupo6);
        cadastro6.setDataHoraCadastro(
                LocalDateTime.of( // 28/02/2021 22:00
                        2021, Month.FEBRUARY, 28, 22, 00));

        cadastro6.setPessoa(pessoa2);
        pessoa2.getCadastros().add(cadastro6);

        pessoaBean.save(pessoa2);
        System.out.println(">> P2: " + pessoa2);

        //
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PessoaServlet</title>");
            out.println("<style>.high{border:solid thin #DF07F0;}</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PessoaServlet: JPQL</h1>");
            out.println("<p>Processamento concluído com sucesso.</p>");
            //--
            out.println("<h2>Objeto toJson</h2>");
            out.println("<pre class=\"high\">"
                    + Util.toJson(pessoa1)
                    + "</pre>");
            //--
//            out.println("<pre class=\"high\">"
//                    + Util.toJson(pessoa2)
//                    + "</pre>");
            //--
//            out.println("<h2>FindPessoaByIdQuery</h2>");
//            out.println("<pre class=\"high\">"
//                    + Util.toJson(pessoaBean.findPessoaByIdQuery(1L))
//                    + "</pre>");
            //--
//            out.println("<h2>findPessoaByIdTypedQuery</h2>");
//            out.println("<pre class=\"high\">"
//                    + Util.toJson(pessoaBean.findPessoaByIdTypedQuery(2L))
//                    + "</pre>");
            //--
//            out.println("<h2>FindAllN+1</h2>");
//            out.println("<pre class=\"high\">"
//                    + Util.toJson(pessoaBean.findAllPessoaNPlusOne())
//                    + "</pre>");
            //--
//            out.println("<h2>findAllByNamedQuery</h2>");
//            out.println("<pre class=\"high\">"
//                    + Util.toJson(pessoaBean.findAllByNamedQuery())
//                    + "</pre>");
            //--
//            String inicial = request.getParameter("inicial");
//            out.println("<h2>findByNameBeginningWith: \"" + inicial + "\"</h2>");
//            out.println("<p><small>* Cada nova execução do Servlet insere novos dados no Banco de dados</small></p>");
//            out.println("<pre class=\"high\">"
//                    + Util.toJson(pessoaBean.findByNameBeginningWith(inicial))
//                    + "</pre>");
            //--
//            out.println("<h2>findPessoaGrupoAtivo</h2>");
//            out.println("<pre class=\"high\">"
//                    + Util.toJson(pessoaBean.findPessoaGrupoAtivo())
//                    + "</pre>");
            //--
//            out.println("<h2>findPessoaGrupoInGrupoAtivo</h2>");
//            out.println("<pre class=\"high\">"
//                    + Util.toJson(pessoaBean.findPessoaGrupoInGrupoAtivo())
//                    + "</pre>");
            //--
//            out.println("<h2>findPessoaGrupoCadastroApos</h2>");
//            out.println("<pre class=\"high\">"
//                    + Util.toJson(pessoaBean.findPessoaGrupoCadastroApos(
//                            // LocalDateTime do dia específico a partir da 00:00:00.000000000
//                            LocalDate.of(2021, Month.JANUARY, 1).atStartOfDay()))
//                    + "</pre>");
            //--
            out.println("<a href=\"index.html\">Voltar</a>");
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
