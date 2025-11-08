package dao;

import model.Movimentacao;
import java.sql.*;

public class MovimentacaoDAO {
    private String user = "root";
    private String password = "admin";

    private Connection getConexao() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/estoque?useTimezone=true&serverTimezone=UTC";
        return DriverManager.getConnection(url, user, password);
    }

    public boolean registrar(Movimentacao m) {
        try (Connection con = getConexao()) {

            // Atualizar o estoque
            String sqlGet = "SELECT quantidade_estoque FROM tb_produto WHERE id = ?";
            int estoqueAtual = 0;
            try (PreparedStatement ps = con.prepareStatement(sqlGet)) {
                ps.setInt(1, m.getId_produto());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) estoqueAtual = rs.getInt("quantidade_estoque");
            }

            int novoEstoque = estoqueAtual;
            if (m.getTipo().equalsIgnoreCase("Entrada")) {
                novoEstoque += m.getQuantidade();
            } else if (m.getTipo().equalsIgnoreCase("Saída")) {
                if (estoqueAtual < m.getQuantidade()) return false;
                novoEstoque -= m.getQuantidade();
            }

            String sqlUpdate = "UPDATE tb_produto SET quantidade_estoque = ? WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
                ps.setInt(1, novoEstoque);
                ps.setInt(2, m.getId_produto());
                ps.executeUpdate();
            }

            // Inserir a movimentação
            String sqlInsert = "INSERT INTO tb_movimentacao (id_produto, tipo, quantidade) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sqlInsert)) {
                ps.setInt(1, m.getId_produto());
                ps.setString(2, m.getTipo());
                ps.setInt(3, m.getQuantidade());
                ps.executeUpdate();
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


