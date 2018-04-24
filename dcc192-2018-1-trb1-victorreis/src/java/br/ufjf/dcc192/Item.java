package br.ufjf.dcc192;

public class Item {
    private Integer id;
    private String nome;
    private Double valor;

    public Item(Integer id, String nome, Double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
