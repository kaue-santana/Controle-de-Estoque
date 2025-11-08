package dao;

import model.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private String user = "root";
    private String password = "admin";

    private Connection getConexao() throws SQLException {
            try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        throw new SQLException("Driver JDBC MySQL não encontrado!", e);
    }
        String url = "jdbc:mysql://localhost:3306/estoque?useSSL=false&serverTimezone=UTC";
        return DriverManager.getConnection(url, user, password);
    }

    public List<Categoria> listarTodas() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM tb_categoria";

        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId_categoria(rs.getInt("categoria_id"));
                c.setNome_categoria(rs.getString("nome_categoria"));
                c.setTamanho(rs.getString("tamanho"));
                c.setEmbalagem(rs.getString("embalagem"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar categorias:");
            e.printStackTrace();
        }
        System.out.println("✅ Quantidade de categorias listadas: " + lista.size());
        return lista;
    }

    public boolean inserir(Categoria c) {
        String sql = "INSERT INTO tb_categoria (nome_categoria, tamanho, embalagem) VALUES (?, ?, ?)";

        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNome_categoria());
            ps.setString(2, c.getTamanho());
            ps.setString(3, c.getEmbalagem());
            
            int linhas = ps.executeUpdate();
            System.out.println("Linhas inseridas: " + linhas);
             if (linhas > 0) {
                // pegar ID gerado
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    c.setId_categoria(id);
                    System.out.println("ID gerado: " + id);
                }
            return true;
              } else {
                System.err.println("⚠ Nenhuma linha inserida!");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir categoria:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Categoria c) {
        String sql = "UPDATE tb_categoria SET nome_categoria = ?, tamanho = ?, embalagem = ? WHERE categoria_id = ?";

        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNome_categoria());
            ps.setString(2, c.getTamanho());
            ps.setString(3, c.getEmbalagem());
            ps.setInt(4, c.getId_categoria());
            int linhas = ps.executeUpdate();
            System.out.println("Linhas atualizadas: " + linhas);
            return linhas > 0;


        } catch (SQLException e) {
            System.err.println("Erro ao atualizar categoria:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM tb_categoria WHERE categoria_id = ?";

        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            System.out.println("Linhas excluídas: " + linhas);
            return linhas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir categoria:");
            e.printStackTrace();
            return false;
        }
    }

    public Categoria carregar(int id) {
        Categoria c = null;
        String sql = "SELECT * FROM tb_categoria WHERE categoria_id = ?";

        try (Connection con = getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Categoria();
                c.setId_categoria(rs.getInt("categoria_id"));
                c.setNome_categoria(rs.getString("nome_categoria"));
                c.setTamanho(rs.getString("tamanho"));
                c.setEmbalagem(rs.getString("embalagem"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao carregar categoria:");
            e.printStackTrace();
        }
        if (c != null) {
            System.out.println("✅ Categoria carregada: " + c.getNome_categoria());
        } else {
            System.out.println("⚠ Categoria não encontrada para id: " + id);
        }
        return c;
    }
}


