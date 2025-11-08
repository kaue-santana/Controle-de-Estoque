package model;

public class Movimentacao {
    private int id_produto;
    private String tipo; // "Entrada" ou "Saída"
    private int quantidade;

    public Movimentacao() {}

    public Movimentacao(int id_produto, String tipo, int quantidade) {
        this.id_produto = id_produto;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public int getId_produto() { return id_produto; }
    public void setId_produto(int id_produto) { this.id_produto = id_produto; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
