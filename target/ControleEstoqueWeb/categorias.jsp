<%@ page import="model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Categoria editar = (Categoria) request.getAttribute("categoria");
%>

<h2>Adicionar / Editar Categoria</h2>
<form method="post" action="categorias">
    <input type="hidden" name="id_categoria" value="<%= editar != null ? editar.getId_categoria() : "" %>">
    Nome: <input type="text" name="nome_categoria" value="<%= editar != null ? editar.getNome_categoria() : "" %>" required><br>
    Tamanho: <input type="text" name="tamanho" value="<%= editar != null ? editar.getTamanho() : "" %>"><br>
    Embalagem: <input type="text" name="embalagem" value="<%= editar != null ? editar.getEmbalagem() : "" %>"><br>
    <input type="submit" value="Salvar">
    <input type="button" value="Cancelar" onclick="limparCampos()">
</form>
    <button type="button" onclick="voltarIndex()">Voltar</button>
</form>
    
<h2>Lista de Categorias</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Tamanho</th>
        <th>Embalagem</th>
        <th>Ações</th>
    </tr>
    <%
        if (categorias != null && !categorias.isEmpty()) {
            for (Categoria c : categorias) {
    %>
    <tr>
        <td><%= c.getId_categoria() %></td>
        <td><%= c.getNome_categoria() %></td>
        <td><%= c.getTamanho() %></td>
        <td><%= c.getEmbalagem() %></td>
        <td>
            <a href="categorias?acao=editar&id=<%= c.getId_categoria() %>">Editar</a> |
            <a href="categorias?acao=excluir&id=<%= c.getId_categoria() %>" onclick="return confirm('Confirma exclusão?')">Excluir</a>
        </td>
    </tr>
    <%
            }
        } else { 
    %>
    <tr><td colspan="5">Nenhuma categoria cadastrada.</td></tr>
    <%
        } // ✅ fechamento do else
    %>
</table>

<script>
function limparCampos() {
    document.querySelector('form').reset(); 
    window.location.href = 'categorias'; 
}

function voltarIndex() {
    window.location.href = '<%= request.getContextPath() %>/index.jsp';
}
</script>
