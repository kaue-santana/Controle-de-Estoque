<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Produto" %>
<%@ page import="java.util.List" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lista de Produtos</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
    </head>
    <body>

    <h2>Lista de Produtos</h2>
    
    <button><a href="${pageContext.request.contextPath}/produtos?acao=cadastrar" class="btn btn-primary">Novo Produto</a></button>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Preço</th>
            <th>Unidade</th>
            <th>Estoque</th>
            <th>Estoque Mínimo</th>
            <th>Estoque Máximo</th>
            <th>Categoria ID</th>
            <th>Nome Categoria</th>
            <th>Tamanho</th>
            <th>Embalagem</th>
            <th>Ações</th>
        </tr>

    <%
        List<Produto> lista = (List<Produto>) request.getAttribute("listaProdutos");

        if (lista == null || lista.isEmpty()) {
    %>
        <tr><td colspan="12">Nenhum produto cadastrado.</td></tr>
    <%
        } else {
            for (Produto p : lista) {
    %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getNome() %></td>
            <td><%= p.getPreco_unitario() %></td>
            <td><%= p.getUnidade() %></td>
            <td><%= p.getQuantidade_estoque() %></td>
            <td><%= p.getQuantidade_minima() %></td>
            <td><%= p.getQuantidade_maxima() %></td>
            <td><%= p.getCategoria_id() %></td>
            <td><%= p.getNome_categoria() %></td>
            <td><%= p.getTamanho() %></td>
            <td><%= p.getEmbalagem() %></td>
            <td>
                <a href="${pageContext.request.contextPath}/produtos?acao=editar&id=<%= p.getId() %>">Editar</a> |
                <a href="${pageContext.request.contextPath}/produtos?acao=excluir&id=<%= p.getId() %>"
                   onclick="return confirm('Tem certeza que deseja excluir este produto?');">Excluir</a>
            </td>
        </tr>
    <%
            }
        }
    %>
    </table>

    <br>

        <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">Voltar</button>

    </body>
</html>


