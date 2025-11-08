<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Categoria" %>

<%
    // O ProdutoServlet já deve ter colocado "categorias" como atributo:
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    if (categorias == null) {
        categorias = new java.util.ArrayList<>(); // evita NPE se algo falhar
    }
%>

<html>
<head>
    <title>Cadastrar Produto</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilo.css">
</head>
<body>
<h2>Cadastrar Novo Produto</h2>

<form action="${pageContext.request.contextPath}/produtos" method="post">
    <input type="hidden" name="acao" value="inserir">

    <label>Nome:</label>
    <input type="text" name="nome" required><br>

    <label>Preço:</label>
    <input type="number" name="preco_unitario" step="0.01" required><br>

    <label>Unidade:</label>
    <input type="text" name="unidade" required><br>

    <label>Estoque Atual:</label>
    <input type="number" name="quantidade_estoque" required><br>

    <label>Estoque Mínimo:</label>
    <input type="number" name="quantidade_minima" required><br>

    <label>Estoque Máximo:</label>
    <input type="number" name="quantidade_maxima" required><br>

    <label>Categoria:</label>
    <select name="categoria_id">
        <%
            for (Categoria c : categorias) {
        %>
        <option value="<%= c.getId_categoria() %>"><%= c.getNome_categoria() %></option>
        <% } %>
    </select><br>

    <input type="submit" value="Salvar">
</form>

<a href="<%= request.getContextPath() %>/produtos?acao=listar">Voltar</a>

</body>
</html>


