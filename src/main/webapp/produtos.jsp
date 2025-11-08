<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Produto"%>
<%@page import="java.util.List"%>
<%@page import="model.Categoria"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Controle de Produtos</title>
    <link rel="stylesheet" href="css/estilo.css">
    <script src="js/scripts.js"></script>
</head>
<body>
    
    <h1>Controle de Produtos</h1>

    <form action="produtos" method="post">
        <input type="text" name="nome" placeholder="Nome" required>
        <input type="number" name="preco" placeholder="Preço" required step="0.01">
        <input type="number" name="estoque" placeholder="Estoque" required>
        <input type="text" name="unidade" placeholder="Unidade" required>
        <input type="text" name="categoria" placeholder="Categoria" required>
        <input type="text" name="tamanho" placeholder="Tamanho" required>
        <input type="text" name="embalagem" placeholder="Embalagem" required>
        <button type="submit">Cadastrar</button>
    </form>

    <hr>

    <h2>Produtos Cadastrados</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th><th>Nome</th><th>Preço</th><th>Estoque</th>
            <th>Unidade</th><th>Categoria</th><th>Tamanho</th><th>Embalagem</th>
        </tr>
        <%
            List<Produto> lista = (List<Produto>) request.getAttribute("listaProdutos");
            if (lista != null) {
                for (Produto p : lista) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getNome() %></td>
            <td><%= p.getPreco_unitario() %></td>
            <td><%= p.getQuantidade_estoque() %></td>
            <td><%= p.getQuantidade_minima() %></td>
            <td><%= p.getQuantidade_maxima() %></td>
            <td><%= p.getUnidade() %></td>
            <td><%= p.getNome_categoria() %></td>
            <td><%= p.getTamanho() %></td>
            <td><%= p.getEmbalagem() %></td>

        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
