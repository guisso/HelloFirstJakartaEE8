<%-- 
    Document   : calculadora
    Created on : Feb 19, 2021, 8:18:45 AM
    Author     : Luis Guisso <luis.guisso at ifnmg.edu.br>
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="io.github.guisso.hellofirstjakartaee8.Calc"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page Calculadora</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>JSP Calculadora</h1>
        <p><%
            Float a = Float.parseFloat(request.getParameter("vlra"));
            Float b = Float.parseFloat(request.getParameter("vlrb"));
            String operacao = request.getParameter("operacao");
            String simbolo = "";
            Float resultado = 0f;
            
            Calc calc = new Calc();
            calc.setA(a);
            calc.setB(b);
            
            switch (operacao) {
                case "somar":
                    resultado = calc.somar();
                    simbolo = " + ";
                    break;

                case "subtrair":
                    resultado = calc.subtrair();
                    simbolo = " - ";
                    break;

                case "multiplicar":
                    resultado = calc.multiplicar();
                    simbolo = " * ";
                    break;

                case "dividir":
                    resultado = calc.dividir();
                    simbolo = " / ";
                    break;

            }
            
            out.print(a + simbolo + b + " = " + resultado);
            %></p>

        <p><%= LocalDateTime.now()%></p>

        <a href="entrada.html">Voltar</a>
    </body>
</html>
