<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Movimentações de Estoque</title>
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body>
    <h1>Registrar Movimentação</h1>

    <form action="movimentacoes" method="post">
        <input type="number" name="idProduto" placeholder="ID do Produto" required>
        <select name="tipo">
            <option value="Entrada">Entrada</option>
            <option value="Saída">Saída</option>
        </select>
        <input type="number" name="quantidade" placeholder="Quantidade" required>
        <button type="submit">Registrar</button>
    </form>

    <%
        if (request.getParameter("sucesso") != null) {
    %>
        <p style="color:green;">Movimentação registrada com sucesso!</p>
    <%
        }
    %>
</body>
</html>
