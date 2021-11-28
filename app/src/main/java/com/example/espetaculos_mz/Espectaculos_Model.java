package com.example.espetaculos_mz;

public class Espectaculos_Model {
    String descricao;
    String local;
    String nome;
    String promotor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id; double preco,qtdVendida,quantidade;

    public Espectaculos_Model(String id,String descricao, String local, String nome, String promotor, double qtdVendida, double quantidade, double preco) {
        this.id=id;
        this.descricao = descricao;
        this.local = local;
        this.nome = nome;
        this.preco = preco;
        this.promotor = promotor;
        this.qtdVendida = qtdVendida;
        this.quantidade = quantidade;
    }

    public Espectaculos_Model(String descricao, String local, String nome, String promotor) {
        this.descricao = descricao;
        this.local = local;
        this.nome = nome;
        this.promotor = promotor;
    }
    public Espectaculos_Model() {
    }

    @Override
    public String toString() {
        return "Espectaculos_Model{" +
                "descricao='" + descricao + '\'' +
                ", local='" + local + '\'' +
                ", nome='" + nome + '\'' +
                ", preco='" + preco + '\'' +
                ", promotor='" + promotor + '\'' +
                ", qtdVendida='" + qtdVendida + '\'' +
                ", quantidade='" + quantidade + '\'' +
                '}';
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getPromotor() {
        return promotor;
    }

    public void setPromotor(String promotor) {
        this.promotor = promotor;
    }

    public double getQtdVendida() {
        return qtdVendida;
    }

    public void setQtdVendida(double qtdVendida) {
        this.qtdVendida = qtdVendida;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
