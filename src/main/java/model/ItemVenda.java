package model;

import java.io.Serializable;

public class ItemVenda implements Serializable {
    private Produto produto;
    private int quantidade;
    private double subTotal;

    public ItemVenda(Produto p, int qtd){
        produto = p;
        quantidade = qtd;
        subTotal = quantidade*produto.getPreco();
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
