package com.mycompany.aula4;

public class Item {
    private Integer id;
    private String nome;
    private String descricao;
    private String marca;
    private String valor;
    private String estoque;


    public Item() {
    }

    public Item(Integer id, String nome, String descricao, String marca, String valor, String estoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
        this.valor = valor;
        this.estoque = estoque;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getEstoque() {
        return this.estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }
    
}


