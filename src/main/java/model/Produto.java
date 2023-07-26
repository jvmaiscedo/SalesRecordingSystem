package model;

import java.io.Serializable;
import java.util.Locale;
import java.util.UUID;

public class Produto implements Serializable {

    private  String descricao;
    private Double custo;
    private Double preco;
    private Double percentual;

    public Produto (String descricao, Double custo, Double percentual){
        this.descricao = descricao.toUpperCase(Locale.ROOT);
        this.percentual = percentual;
        this.custo = custo;
        this.preco = calculaPreco();


    }

    public String getDescricao() {
        return descricao;
    }
    private Double calculaPreco(){
        this.preco = custo+(custo*(this.percentual/100));
        return preco;
    }
    public Double getPreco() {
        return preco;
    }

    public Double getCusto() {
        return custo;
    }

    public Double getPercentual() {
        return percentual;
    }

    @Override
    public String toString() {
        return "Descrição: "+descricao + "\nPreço: "+preco+"\n";
    }

    public void setPreco(Double preco) {
        this.custo = preco;
        calculaPreco();
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
        calculaPreco();
    }

}
