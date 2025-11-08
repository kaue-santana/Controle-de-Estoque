package servlet;

import dao.MovimentacaoDAO;
import model.Movimentacao;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

//@WebServlet("/movimentacoes")
public class MovimentacaoServlet extends HttpServlet {

    private MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Movimentacao m = new Movimentacao();
        m.setId_produto(Integer.parseInt(request.getParameter("idProduto")));
        m.setTipo(request.getParameter("tipo"));
        m.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));

        movimentacaoDAO.registrar(m);
        response.sendRedirect("movimentacoes.jsp?sucesso=true");
    }
}

