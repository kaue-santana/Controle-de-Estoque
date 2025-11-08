package servlet;

import dao.CategoriaDAO;
import model.Categoria;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

//@WebServlet("/categorias")
public class CategoriaServlet extends HttpServlet {

    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        String idParam = request.getParameter("id");

        if ("excluir".equals(acao) && idParam != null) {
            categoriaDAO.excluir(Integer.parseInt(idParam));
            response.sendRedirect(request.getContextPath() + "/categorias");
            return;
        }

        if ("editar".equals(acao) && idParam != null) {
            Categoria c = categoriaDAO.carregar(Integer.parseInt(idParam));
            request.setAttribute("categoria", c);
        }

        List<Categoria> categorias = categoriaDAO.listarTodas();
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("/produtos/cadastrarCat.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id_categoria");
        String nome = request.getParameter("nome_categoria");
        String tamanho = request.getParameter("tamanho");
        String embalagem = request.getParameter("embalagem");
        int id = 0;
        String acao = request.getParameter("acao");
        
        if ("voltar".equals(acao)) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        if ("cancelar".equals(acao)) {
            response.sendRedirect(request.getContextPath() + "/categorias");
            return;
        }

           if (idParam != null && !idParam.isEmpty()) {
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                id = 0;
            }
        }

        Categoria c = new Categoria();
        c.setNome_categoria(nome);
        c.setTamanho(tamanho);
        c.setEmbalagem(embalagem);

                if (id > 0) {
            c.setId_categoria(id);
            categoriaDAO.atualizar(c);
        } else {
            categoriaDAO.inserir(c);
        }
       /*
        if (idParam != null && !idParam.isEmpty()) {
            c.setId_categoria(Integer.parseInt(idParam));
            categoriaDAO.atualizar(c);
        } else {
            categoriaDAO.inserir(c);
        }
         */           
        
            response.sendRedirect(request.getContextPath() + "/categorias");
            System.out.println("✅ Categoria salva: " + c.getNome_categoria());
      
    }
}


