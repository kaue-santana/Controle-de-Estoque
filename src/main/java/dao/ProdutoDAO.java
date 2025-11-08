package dao;

import model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private String user = "root";
    private String password = "admin";

    private Connection getConexao() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/estoque?useTimezone=true&serverTimezone=UTC";
        return DriverManager.getConnection(url, user, password);
    }

    public List<Produto> listarTodos() {
    List<Produto> lista = new ArrayList<>();
    String sql = "SELECT p.*, c.nome_categoria, c.tamanho, c.embalagem " +
                 "FROM tb_produto p " +
                 "LEFT JOIN tb_categoria c ON p.categoria_id = c.categoria_id";

        try (Connection con = getConexao();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Produto p = new Produto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setPreco_unitario(rs.getDouble("preco_unitario"));
            p.setUnidade(rs.getString("unidade"));
            p.setQuantidade_estoque(rs.getInt("quantidade_estoque"));
            p.setQuantidade_minima(rs.getInt("quantidade_minima"));
            p.setQuantidade_maxima(rs.getInt("quantidade_maxima"));
            p.setCategoria_id(rs.getInt("categoria_id"));
            p.setNome_categoria(rs.getString("nome_categoria"));
            p.setTamanho(rs.getString("tamanho"));
            p.setEmbalagem(rs.getString("embalagem"));
            System.out.println("Produtos encontrados: " + lista.size());

               lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean inserir(Produto p) {
        String sql = "INSERT INTO tb_produto (nome, preco_unitario, unidade, quantidade_estoque, quantidade_minima, quantidade_maxima, categoria_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco_unitario());
            ps.setString(3, p.getUnidade());
            ps.setInt(4, p.getQuantidade_estoque());
            ps.setInt(5, p.getQuantidade_minima());
            ps.setInt(6, p.getQuantidade_maxima());
            ps.setInt(7, p.getCategoria_id());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Produto p) {
        String sql = "UPDATE tb_produto SET nome = ?, preco_unitario = ?, unidade = ?, quantidade_estoque = ?, quantidade_minima = ?, quantidade_maxima = ?, categoria_id = ? WHERE id = ?";

        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco_unitario());
            ps.setString(3, p.getUnidade());
            ps.setInt(4, p.getQuantidade_estoque());
            ps.setInt(5, p.getQuantidade_minima());
            ps.setInt(6, p.getQuantidade_maxima());
            ps.setInt(7, p.getCategoria_id());
            ps.setInt(8, p.getId());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM tb_produto WHERE id = ?";

        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Produto carregar(int id) {
        Produto p = null;
        String sql = "SELECT * FROM tb_produto WHERE id = ?";

        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco_unitario(rs.getDouble("preco_unitario"));
                p.setUnidade(rs.getString("unidade"));
                p.setQuantidade_estoque(rs.getInt("quantidade_estoque"));
                p.setQuantidade_minima(rs.getInt("quantidade_minima"));
                p.setQuantidade_maxima(rs.getInt("quantidade_maxima"));
                p.setCategoria_id(rs.getInt("categoria_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
}

