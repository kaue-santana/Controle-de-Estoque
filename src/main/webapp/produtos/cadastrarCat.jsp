<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Categoria"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Gerenciar Categorias</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
    <h1>Gerenciar Categorias</h1>

    <h2><%= (request.getAttribute("categoria") != null ? "Editar Categoria" : "Nova Categoria") %></h2>

    <%
        Categoria c = (Categoria) request.getAttribute("categoria");
        if (c == null) c = new Categoria();
    %>

    <form action="${pageContext.request.contextPath}/categorias" method="post">
        <input type="hidden" name="id_categoria" value="<%= c.getId_categoria() %>">

        <label>Nome da Categoria:</label><br>
        <input type="text" name="nome_categoria" value="<%= c.getNome_categoria() != null ? c.getNome_categoria() : "" %>" required><br><br>

        <label>Tamanho:</label><br>
        <input type="text" name="tamanho" value="<%= c.getTamanho() != null ? c.getTamanho() : "" %>" required><br><br>

        <label>Embalagem:</label><br>
        <input type="text" name="embalagem" value="<%= c.getEmbalagem() != null ? c.getEmbalagem() : "" %>" required><br><br>

        <button type="submit"><%= (c.getId_categoria() > 0 ? "Atualizar" : "Cadastrar") %></button>
        <button type="button" onclick="limparCampos()">Cancelar</button>
        <button type="button" onclick="voltarIndex()">Voltar</button>
    </form>

    <hr>

    <h2>Categorias Cadastradas</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Tamanho</th>
            <th>Embalagem</th>
            <th>Ações</th>
        </tr>

        <%
            List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
            if (categorias != null && !categorias.isEmpty()) {
                for (Categoria cat : categorias) {
        %>
            <tr>
                <td><%= cat.getId_categoria() %></td>
                <td><%= cat.getNome_categoria() %></td>
                <td><%= cat.getTamanho() %></td>
                <td><%= cat.getEmbalagem() %></td>
                <td>
                    <a href="${pageContext.request.contextPath}/categorias?acao=editar&id=<%= cat.getId_categoria() %>">Editar</a> |
                    <a href="${pageContext.request.contextPath}/categorias?acao=excluir&id=<%= cat.getId_categoria() %>" onclick="return confirm('Deseja excluir esta categoria?');">Excluir</a>
                </td>
            </tr>
        <%
                }
            } else {
        %>
            <tr><td colspan="5">Nenhuma categoria cadastrada.</td></tr>
        <%
            }
        %>
    </table>

    <script>
       
        function limparCampos() {
            document.querySelector('form').reset();
            window.location.href = '<%= request.getContextPath() %>/categorias';
        }

        function voltarIndex() {
            window.location.replace('<%= request.getContextPath() %>/index.jsp');
        }
    </script>

</body>
</html>

