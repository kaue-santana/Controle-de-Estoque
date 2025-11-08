package model;

public class Produto extends Categoria{
    private int id;
    private String nome;
    private double preco_unitario;
    private String unidade;
    private int quantidade_estoque;
    private int quantidade_minima;
    private int quantidade_maxima;
    private int categoria_id;
    private String nome_categoria;
    private String tamanho;
    private String embalagem;




    public Produto() {}

    public Produto(int id, String nome, double preco, String unidade, int qtd, int min, int max, int categoria_id) {
        this.id = id;
        this.nome = nome;
        this.preco_unitario = preco;
        this.unidade = unidade;
        this.quantidade_estoque = qtd;
        this.quantidade_minima = min;
        this.quantidade_maxima = max;
        this.categoria_id = categoria_id;
        
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco_unitario() { return preco_unitario; }
    public void setPreco_unitario(double preco_unitario) { this.preco_unitario = preco_unitario; }

    public String getUnidade() { return unidade; }
    public void setUnidade(String unidade) { this.unidade = unidade; }

    public int getQuantidade_estoque() { return quantidade_estoque; }
    public void setQuantidade_estoque(int quantidade_estoque) { this.quantidade_estoque = quantidade_estoque; }

    public int getQuantidade_minima() { return quantidade_minima; }
    public void setQuantidade_minima(int quantidade_minima) { this.quantidade_minima = quantidade_minima; }

    public int getQuantidade_maxima() { return quantidade_maxima; }
    public void setQuantidade_maxima(int quantidade_maxima) { this.quantidade_maxima = quantidade_maxima; }

    public int getCategoria_id() { return categoria_id; }
    public void setCategoria_id(int categoria_id) { this.categoria_id = categoria_id; }
    
    public String getNome_categoria() { return nome_categoria; }
    public void setNome_categoria(String nome_categoria) { this.nome_categoria = nome_categoria; }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }

    public String getEmbalagem() { return embalagem; }
    public void setEmbalagem(String embalagem) { this.embalagem = embalagem; }
    }
