<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Produto" %>
<%@ page import="model.Categoria" %>
<%@ page import="java.util.List" %>

<%
    Produto produto = (Produto) request.getAttribute("produto");
    if (produto == null) {
        produto = new Produto(); // evita NPE
    }
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    if (categorias == null) {
        categorias = new java.util.ArrayList<>();
    }
%>

<html>
<head>
    <title>Editar Produto</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilo.css">
</head>
<body>
<h2>Editar Produto</h2>

<form action="<%= request.getContextPath() %>/produtos" method="post">
    <input type="hidden" name="acao" value="atualizar">
    <input type="hidden" name="id" value="<%= produto.getId() %>">

    <label>Nome:</label>
    <input type="text" name="nome" value="<%= produto.getNome() %>" required><br>

    <label>Preço:</label>
    <input type="number" name="preco_unitario" step="0.01" value="<%= produto.getPreco_unitario() %>" required><br>

    <label>Unidade:</label>
    <input type="text" name="unidade" value="<%= produto.getUnidade() %>" required><br>

    <label>Estoque Atual:</label>
    <input type="number" name="quantidade_estoque" value="<%= produto.getQuantidade_estoque() %>" required><br>

    <label>Estoque Mínimo:</label>
    <input type="number" name="quantidade_minima" value="<%= produto.getQuantidade_minima() %>" required><br>

    <label>Estoque Máximo:</label>
    <input type="number" name="quantidade_maxima" value="<%= produto.getQuantidade_maxima() %>" required><br>

    <label>Categoria:</label>
    <select name="categoria_id">
        <%
            for (Categoria c : categorias) {
                String selected = (c.getId_categoria() == produto.getCategoria_id()) ? "selected" : "";
        %>
        <option value="<%= c.getId_categoria() %>" <%= selected %>><%= c.getNome_categoria() %></option>
        <% } %>
    </select><br>

    <input type="submit" value="Atualizar">
</form>

<a href="<%= request.getContextPath() %>/produtos?acao=listar">Voltar</a>

</body>
</html>


