package servlet;

import dao.ProdutoDAO;
import dao.CategoriaDAO;
import model.Produto;
import model.Categoria;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

public class ProdutoServlet extends HttpServlet {

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String idParam = request.getParameter("id");

        try {
            if ("excluir".equals(acao) && idParam != null) {
                produtoDAO.excluir(Integer.parseInt(idParam));
                response.sendRedirect(request.getContextPath() + "/produtos?acao=listar");;
                return;
            }

            if ("editar".equals(acao) && idParam != null) {
                Produto produto = produtoDAO.carregar(Integer.parseInt(idParam));
                if (produto == null) {
                    response.sendRedirect(request.getContextPath() + "/produtos?acao=listar");
                    return;
                }

                List<Categoria> categorias = categoriaDAO.listarTodas();
                request.setAttribute("produto", produto);
                request.setAttribute("categorias", categorias);

                RequestDispatcher rd = request.getRequestDispatcher("/produtos/editar.jsp");
                rd.forward(request, response);
                return;
            }

            if ("cadastrar".equals(acao)) {
                List<Categoria> categorias = categoriaDAO.listarTodas();
                request.setAttribute("categorias", categorias);

                RequestDispatcher rd = request.getRequestDispatcher("/produtos/cadastrarProd.jsp");
                rd.forward(request, response);
                return;
            }

            List<Produto> produtos = produtoDAO.listarTodos(); 
            request.setAttribute("listaProdutos", produtos);

            RequestDispatcher rd = request.getRequestDispatcher("/produtos/listar.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String acao = request.getParameter("acao");
        System.out.println("AÇÃO: " + acao);

        try {
            System.out.println("---- DEBUG FORM ----");
            System.out.println("id: " + request.getParameter("id"));
            System.out.println("nome: " + request.getParameter("nome"));
            System.out.println("preco_unitario: " + request.getParameter("preco_unitario"));
            System.out.println("unidade: " + request.getParameter("unidade"));
            System.out.println("quantidade_estoque: " + request.getParameter("quantidade_estoque"));
            System.out.println("quantidade_minima: " + request.getParameter("quantidade_minima"));
            System.out.println("quantidade_maxima: " + request.getParameter("quantidade_maxima"));
            System.out.println("categoria_id: " + request.getParameter("categoria_id"));
            
            System.out.println("--------------------");

            // Validação: se algum campo vier null, mostre imediatamente
            if (request.getParameter("preco_unitario") == null ||
                request.getParameter("quantidade_minima") == null ||
                request.getParameter("quantidade_maxima") == null ||
                request.getParameter("categoria_id") == null) {
                throw new ServletException(" Um ou mais campos estão vindo NULL. Veja o log acima.");
            }

            String nome = request.getParameter("nome");
            double preco = Double.parseDouble(request.getParameter("preco_unitario"));
            String unidade = request.getParameter("unidade");
            int estoque = Integer.parseInt(request.getParameter("quantidade_estoque"));
            int minimo = Integer.parseInt(request.getParameter("quantidade_minima"));
            int maximo = Integer.parseInt(request.getParameter("quantidade_maxima"));
            int categoriaId = Integer.parseInt(request.getParameter("categoria_id"));

            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setPreco_unitario(preco);
            produto.setUnidade(unidade);
            produto.setQuantidade_estoque(estoque);
            produto.setQuantidade_minima(minimo);
            produto.setQuantidade_maxima(maximo);
            produto.setCategoria_id(categoriaId);

            if ("atualizar".equals(acao)) {
            int id = Integer.parseInt(request.getParameter("id"));
            produto.setId(id);
            produtoDAO.atualizar(produto);
        } else if ("inserir".equals(acao)) {
            produtoDAO.inserir(produto);
        }

            
        response.sendRedirect(request.getContextPath() + "/produtos?acao=listar");


        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}


