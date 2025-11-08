package model;

public class Categoria {
    private int id_categoria;
    private String nome_categoria;
    private String tamanho;
    private String embalagem;

    public Categoria() {}

    public Categoria(int id, String nome, String tamanho, String embalagem) {
        this.id_categoria = id;
        this.nome_categoria = nome;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
    }

    public int getId_categoria() { return id_categoria; }
    public void setId_categoria(int id_categoria) { this.id_categoria = id_categoria; }

    public String getNome_categoria() { return nome_categoria; }
    public void setNome_categoria(String nome_categoria) { this.nome_categoria = nome_categoria; }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }

    public String getEmbalagem() { return embalagem; }
    public void setEmbalagem(String embalagem) { this.embalagem = embalagem; }
}
